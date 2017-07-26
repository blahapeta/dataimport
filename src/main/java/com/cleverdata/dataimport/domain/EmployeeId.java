package com.cleverdata.dataimport.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class EmployeeId implements Serializable{

	String email;

	String name;

	String surname;
}
