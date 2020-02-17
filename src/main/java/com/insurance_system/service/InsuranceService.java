package com.insurance_system.service;

import com.insurance_system.bean.NullAwareBeanUtilsBean;
import com.insurance_system.exceptions.UserNotFoundException;
import com.insurance_system.model.Insurance;
import com.insurance_system.repo.InsuranceRepository;
import com.insurance_system.repo.UserRepository;
import com.insurance_system.utilities.EmailUtil;
import com.insurance_system.utilities.EmailUtilForPdf;
import com.insurance_system.utilities.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class InsuranceService {

    private InsuranceRepository insuranceRepository;

    private PDFGenerator pdfGenerator;

    private EmailUtilForPdf emailUtil;

    private UserRepository userRepository;

    @Autowired
    public InsuranceService(InsuranceRepository insuranceRepository, PDFGenerator pdfGenerator, EmailUtilForPdf emailUtil) {
        this.insuranceRepository = insuranceRepository;
        this.pdfGenerator = pdfGenerator;
        this.emailUtil = emailUtil;
    }

    public Insurance createInsurance(Insurance insurance) {
        Insurance save = insuranceRepository.save(insurance);
        String filePath = "/Users/quliyevvagif/Desktop/Programming/created_files/generated_file"+save.getId()+".pdf";
        pdfGenerator.generateInfo(save, filePath);
        // get user mail
        emailUtil.sendPDF(insurance.getUsers().get(0).getEmail(), filePath);
        return insurance;
    }

    public Iterable<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Insurance getInsuranceById(Long id) {
        return insuranceRepository.findById(id).orElseThrow(UserNotFoundException::new);
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
        return getInsuranceById(id);
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }

}
