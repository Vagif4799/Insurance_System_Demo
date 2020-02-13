package com.insurance_system.repo;

import com.insurance_system.model.Insurance;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
}
