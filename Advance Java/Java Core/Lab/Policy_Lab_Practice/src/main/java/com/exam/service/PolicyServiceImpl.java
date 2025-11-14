package com.exam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.customException.ResourseException;
import com.exam.customException.ResourseNotFoundException;
import com.exam.dto.PolicyDetailDto;
import com.exam.entity.Policy;
import com.exam.entity.PolicyType;
import com.exam.repository.PolicyRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {

	private final PolicyRepository policyRepo;
	private final ModelMapper mapper;

	@Override
	public String addPolicy(PolicyDetailDto policyDto) {
		System.out.println("in add policy");
		if (!policyDto.getStartDate().isBefore(policyDto.getEndDate())) {
			throw new ResourseException("start date should be before end date");
		}
		Policy policy = mapper.map(policyDto, Policy.class);
		Policy save = policyRepo.save(policy);
		return "policy saved " + save.getPolicyId();
	}

	@Override
	public List<Policy> fetchAllPolicies() {
		List<Policy> allPolicy = policyRepo.findAll();
		if (allPolicy.isEmpty()) {
			throw new ResourseNotFoundException("No Policy found");
		}
		return allPolicy;
	}

	@Override
	public List<PolicyDetailDto> findPolicyOfParticularType(String policyType) {
		PolicyType pType = PolicyType.valueOf(policyType.toUpperCase());
		List<Policy> byPolicyType = policyRepo.findByPolicyType(pType);
		if (byPolicyType.isEmpty()) {
			throw new ResourseNotFoundException("no policies of type " + policyType);
		}
		List<PolicyDetailDto> list = byPolicyType.stream().map(p -> mapper.map(p, PolicyDetailDto.class)).toList();
		return list;
	}

	@Override
	public String updateAccomodationPrice() {
		List<Policy> price = policyRepo.findByAccomodationAmmountGreater();
		price.stream().forEach(
				p -> p.setAccomodationAmmount(p.getAccomodationAmmount() + (p.getAccomodationAmmount() * 0.15)));

		return "Accomodation price updated";
	}

	@Override
	public String deletePolicyById(Long pId) {
		if(!policyRepo.existsById(pId)) {
			throw new ResourseNotFoundException("policy not found for id: "+pId);
		}
		policyRepo.deleteById(pId);
		return "Deleted policy with id : "+pId;
	}

	@Override
	public List<PolicyDetailDto> getPolicyByName(String pName) {
		List<Policy> byPolicyName = policyRepo.findByPolicyName(pName);
		List<PolicyDetailDto> list = byPolicyName.stream().map(p->mapper.map(p, PolicyDetailDto.class)).toList();
		return list;
	}
	

}
