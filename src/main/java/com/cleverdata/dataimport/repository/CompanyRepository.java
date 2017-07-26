package com.cleverdata.dataimport.repository;

import com.cleverdata.dataimport.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,String> {
}
