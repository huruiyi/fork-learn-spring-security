package com.baeldung.lss.web.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.access.method.P;
import org.springframework.data.repository.query.Param;

import com.baeldung.lss.persistence.UserRepository;
import com.baeldung.lss.web.model.User;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping
    public ModelAndView list() {
        Iterable<User> users = this.userRepository.findAll();
        return new ModelAndView("users/list", "users", users);
    }

    @RequestMapping("{id}")
    public ModelAndView view(@PathVariable("id") User user) {
        return new ModelAndView("users/view", "user", user);
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute User user) {
        return "users/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("users/form", "formErrors", result.getAllErrors());
        }
        user = this.userRepository.save(user);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new user");
        return new ModelAndView("redirect:/{user.id}", "user.id", user.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @RequestMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.userRepository.deleteUser(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyForm(@PathVariable("id") User user) {
        return new ModelAndView("users/form", "user", user);
    }

    /**************************************************************/
    @Secured("ROLE_USER")
    @RequestMapping(value = "/u1")
    @ResponseBody
    public String u1() {
        return "u1..........";
    }


    @RolesAllowed("ROLE_USER")
    @RequestMapping(value = "/u2")
    @ResponseBody
    public String u2() {
        return "u2..........";
    }

    /**
     * PreAuthorize:方法执行前权限校验
     *
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/test1")
    @ResponseBody
    public String test1() {
        //若没有USER权限,方法体不会被执行
        return "TEST @PreAuthorize注解";
    }

    /**
     * PostAuthorize:方法执行后被执行
     *
     * @return
     */
    @PostAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/test2")
    @ResponseBody
    public String test2() {
        //若没有USER权限,方法体依旧会被执行
        return "TEST @PostAuthorize注解";
    }

    //@PreFilter()
    //@PostFilter()

    /**************************************************************/
}
