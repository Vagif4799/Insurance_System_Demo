package com.insurance_system.controller;

import com.insurance_system.model.Company;
import com.insurance_system.model.Role;
import com.insurance_system.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public Iterable<Company> getListOfCompanies() {
        LOGGER.info("Inside of getListOfCompanies() method");
        return companyService.getListOfCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Long id) {
        LOGGER.info("getCompanyById() method is invoked by id: " + id);
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public void createCompany(@RequestBody Company company) {
        LOGGER.info("createCompany() method is invoked by insurance: " + company);
         companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable("id") Long id) {
        LOGGER.info("updateCompany() is invoked by company: {} and id: {}", company, id);
        return companyService.updateCompany(company, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        LOGGER.info("deleteCompany() is invoked by id: " + id);
    }

}
