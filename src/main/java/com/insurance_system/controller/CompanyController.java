package com.insurance_system.controller;

import com.insurance_system.model.Company;
import com.insurance_system.model.Role;
import com.insurance_system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public Iterable<Company> getListOfCompanies() {
        return companyService.getListOfCompanies();
    }

    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(@PathVariable("id") Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public void createCompany(@RequestBody Company company) {
         companyService.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable("id") Long id) {
        return companyService.updateCompany(company, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }

}
