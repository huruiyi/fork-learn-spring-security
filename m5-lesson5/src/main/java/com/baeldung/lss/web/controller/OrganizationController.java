package com.baeldung.lss.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baeldung.lss.persistence.model.Organization;

import java.util.List;

@Controller
public class OrganizationController {

    @Autowired
    private com.baeldung.lss.persistence.dao.OrganizationRepository organizationRepository;

    @PreAuthorize("isMember(#id)")
    @RequestMapping(method = RequestMethod.GET, value = "/organizations/{id}")
    @ResponseBody
    public Organization findOrgById(@PathVariable final long id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasAuthority('USER_READ_PRIVILEGE')")
    @RequestMapping(method = RequestMethod.GET, value = "/organizations")
    @ResponseBody
    public Organization findOrgByName(@RequestParam final String name) {
        return organizationRepository.findByName(name);
    }

    //http://localhost:7003/test0
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test0")
    public List<Organization> test0() {
        List<Organization> list = organizationRepository.findAll();
        return list;
    }

    //http://localhost:7003/test1/3
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test1/{id}")
    public Organization test1(@PathVariable final long id) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        return organization;
    }

    //http://localhost:7003/test2?name=FirstOrg
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/test2")
    public Organization test2(@RequestParam final String name) {
        Organization organization = organizationRepository.findByName(name);
        return organization;
    }

}
