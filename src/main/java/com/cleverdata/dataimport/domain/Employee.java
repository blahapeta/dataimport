package com.cleverdata.dataimport.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by petr.blaha on 24.7.2017.
 */

@Data
@Entity
@Table
@IdClass(EmployeeId.class)
public class Employee {

	private static Logger logger = LoggerFactory.getLogger(Company.class);

	@Id
	@Column
	String email;

	@Id
	@Column
	String name;

	@Id
	@Column
	String surname;

	@Column(name = "lastupdate")
	@Temporal(TemporalType.TIMESTAMP)
	Date datumPoslednihoUpdate;

	@Column
	String companyname;

	@PrePersist
	public void onPrePersist() {
		logger.info("@PrePersist");

	}

	@PreUpdate
	public void onPreUpdate() {
		logger.info("@PreUpdate");

	}

	@PreRemove
	public void onPreRemove() {
		logger.info("@PreRemove");

	}


}
