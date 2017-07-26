package com.cleverdata.dataimport.domain;

import com.cleverdata.dataimport.scheduler.ReadFilesFromDirectoryScheduler;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by petr.blaha on 24.7.2017.
 */
@Data
@Entity
@Table
public class Company implements Serializable{

	private static Logger logger = LoggerFactory.getLogger(Company.class);

	@Column
	Integer Ico;

	@Id
	@Column
	String name;

	@Column
	String adresa;

	@Column(name = "lastupdate")
	@Temporal(TemporalType.TIMESTAMP)
	Date datumPoslednihoUpdate;

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;

		Company company = (Company) o;

		return name.equals(company.name);
	}

	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}



	//	@OneToMany(
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//	)
//	private List<Employee> comments = new ArrayList<>();

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
