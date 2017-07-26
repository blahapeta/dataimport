package com.cleverdata.dataimport.repository;

import com.cleverdata.dataimport.domain.Employee;
import com.cleverdata.dataimport.domain.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,EmployeeId> {
}
