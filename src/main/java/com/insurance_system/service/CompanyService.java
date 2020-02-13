package com.insurance_system.service;

import com.insurance_system.bean.NullAwareBeanUtilsBean;
import com.insurance_system.model.Company;
import com.insurance_system.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Iterable<Company> getListOfCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(Company company, Long id){
        Optional<Company> old_company = companyRepository.findById(id);
        old_company.ifPresent(u-> {
            NullAwareBeanUtilsBean bean = new NullAwareBeanUtilsBean();
            try {
                bean.copyProperties(u,company);
                if(company.getName() != null) u.setName(company.getName());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            companyRepository.save(u);
        });
        return getCompanyById(id).get();
    }

    public void deleteCompany(Long id){
        companyRepository.deleteById(id);
    }

}
