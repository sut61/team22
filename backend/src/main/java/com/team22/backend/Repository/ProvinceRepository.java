package com.team22.backend.Repository;

import com.team22.backend.Entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findByProvinceId(Long provinceId);
    Province findByProvinceName(String provinceName);
}
