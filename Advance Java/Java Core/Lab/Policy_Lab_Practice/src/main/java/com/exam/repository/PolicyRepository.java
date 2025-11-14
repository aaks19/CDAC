package com.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.entity.Policy;
import com.exam.entity.PolicyType;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

	List<Policy> findByPolicyType(PolicyType pType);

	@Query("select p from Policy p where p.accomodationAmmount > 700000")
	List<Policy> findByAccomodationAmmountGreater();

	List<Policy> findByPolicyName(String pName);

}
