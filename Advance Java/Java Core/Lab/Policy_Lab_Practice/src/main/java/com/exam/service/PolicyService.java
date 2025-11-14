package com.exam.service;

import java.util.List;

import com.exam.dto.PolicyDetailDto;
import com.exam.entity.Policy;

public interface PolicyService {

	String addPolicy(PolicyDetailDto policyDto);

	List<Policy> fetchAllPolicies();

	List<PolicyDetailDto> findPolicyOfParticularType(String policyType);

	String updateAccomodationPrice();

	String deletePolicyById(Long pId);

	List<PolicyDetailDto> getPolicyByName(String pName);

}
