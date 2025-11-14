package com.exam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.dto.PolicyDetailDto;
import com.exam.service.PolicyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/policy")
@AllArgsConstructor
public class PolicyController {
	private final PolicyService policyService;
	
	@PostMapping("/addPolicy")
	public ResponseEntity<?> addNewPolicy(@Valid @RequestBody PolicyDetailDto policyDto){
		
			return ResponseEntity.status(HttpStatus.CREATED).body(policyService.addPolicy(policyDto));
		

	}
	
	@GetMapping("/fetchAllPolicy")
	public ResponseEntity<?> fetchAllPolicy(){
		
			return ResponseEntity.ok(policyService.fetchAllPolicies());
		
	}
	
	@PostMapping("/policyOfParticularType/{policyType}")
	public ResponseEntity<?> fetchPolicyOfParticularType(@PathVariable String policyType){
		
			return ResponseEntity.ok(policyService.findPolicyOfParticularType(policyType));
		
	}
	
	@PostMapping("/updateSalary")
	public ResponseEntity<?> updateAccomotation(){
		
			return ResponseEntity.ok(policyService.updateAccomodationPrice());
		
	}
	
	@DeleteMapping("/deletePolicy/{pId}")
	public ResponseEntity<?> deletePolicy(@PathVariable Long pId){
		
			return ResponseEntity.ok(policyService.deletePolicyById(pId));
		
	}
	
	@GetMapping("/getPolicyName/{pName}")
	public ResponseEntity<?> getPolicyName(@PathVariable String pName){
		
			return ResponseEntity.ok(policyService.getPolicyByName(pName));
		
	}
}
