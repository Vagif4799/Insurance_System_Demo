package com.insurance_system.controller;

import com.insurance_system.model.Insurance;
import com.insurance_system.service.InsuranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/insurances")
public class InsuranceController {

    private InsuranceService insuranceService;
    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceController.class);

    @Autowired
    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public Iterable<Insurance> getAllInsurances() {
        LOGGER.info("Inside of getAllInsurances() method");
        return insuranceService.getAllInsurances();
    }

    @GetMapping("/{id}")
    public Insurance getInsuranceById(@PathVariable Long id) {
        LOGGER.info("getInsuranceById() method is invoked by id: " + id);
        return insuranceService.getInsuranceById(id);
    }

    @PostMapping
    public void createInsurance(@RequestBody Insurance insurance) {
        LOGGER.info("createInsurance() method is invoked by insurance: " + insurance);
        insuranceService.createInsurance(insurance);
    }

    @PutMapping("/{id}")
    public Insurance updateInsurance(@RequestBody Insurance insurance,@PathVariable("id") Long id) {
        LOGGER.info("edit_user() is invoked by insurance: {} and id: {}", insurance, id);
       return insuranceService.updateInsurance(insurance, id);
    }

    @DeleteMapping
    public void deleteInsurance(Long id) {
        insuranceService.deleteInsurance(id);
        LOGGER.info("deleteInsurance() is invoked by id: " + id);
    }

}
