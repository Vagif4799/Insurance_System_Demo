package com.insurance_system.service;

import com.insurance_system.bean.NullAwareBeanUtilsBean;
import com.insurance_system.model.Insurance;
import com.insurance_system.repo.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class InsuranceService {

    private InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public Insurance createInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
        return insurance;
    }

    public Iterable<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> getInsuranceById(Long id) {
        return insuranceRepository.findById(id);
    }

    public Insurance updateInsurance(Insurance insurance, Long id) {
        Optional<Insurance> old_insurance = insuranceRepository.findById(id);
        old_insurance.ifPresent(i -> {
            NullAwareBeanUtilsBean bean = new NullAwareBeanUtilsBean();
            try {
                bean.copyProperties(i, insurance);
                if (insurance.getName() != null) i.setName(insurance.getName());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return getInsuranceById(id).get();
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }

}
