package com.insurance_system.controller;

import com.insurance_system.model.Insurance;
import com.insurance_system.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/insurances")
public class InsuranceController {

    private InsuranceService insuranceService;

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public Iterable<Insurance> getAllInsurances() {
        return insuranceService.getAllInsurances();
    }

    @GetMapping("/{id}")
    public Optional<Insurance> getInsuranceById(@PathVariable Long id) {
        return insuranceService.getInsuranceById(id);
    }

    @PostMapping
    public void createInsurance(@RequestBody Insurance insurance) {
        insuranceService.createInsurance(insurance);
    }

    @PutMapping("/{id}")
    public Insurance updateInsurance(@RequestBody Insurance insurance,@PathVariable("id") Long id) {
       return insuranceService.updateInsurance(insurance, id);
    }

    @DeleteMapping
    public void deleteInsurance(Long id) {
        insuranceService.deleteInsurance(id);
    }

}
