package com.CricketerManagement.services;

import static com.CricketerManagement.services.CricketerValidation.validateAll;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.CricketerManagement.core.Cricketer;
import com.CricketerManagement.exception.CricketManagementSystemException;

public class CricketerServiceImplementation implements CricketerManagementService {

//	ArrayList<Cricketer> cricketerList = new ArrayList<>();
	
	Map<Integer, Cricketer> cricketerMap = new HashMap<>();
	
	@Override
	public String addCricketer(String name, int age, String email_id, String phone, double rating)
			throws CricketManagementSystemException {
		Cricketer cricketers = validateAll(name, age, email_id, phone, rating, cricketerMap);
		
//		cricketerList.add(cricketers);
		cricketerMap.put(cricketers.getId(), cricketers);
		return "Cricketer added successfully...";
	}

	@Override
	public void modifyRating(String email_id, double rating) throws CricketManagementSystemException {
		// TODO Auto-generated method stub
		CricketerValidation.checkEmail(email_id);
		
//		boolean found=cricketerList.stream()
//								   .anyMatch(p->p.getEmail_id().equals(email_id));
//		
//		if(!found) {
//			throw new CricketManagementSystemException("Email not exist...");
//		}
//		
//		cricketerList.stream()
//					 .filter(p->p.getEmail_id().equals(email_id))
//					 .peek(p->p.setRating(rating))
//					 .forEach(p->System.out.println(p));
//		
//		
//		System.out.println("Rating updated...");
		
		
		//using hashmap
		Cricketer cricketer = cricketerMap.values()
										  .stream()
										  .filter(c-> c.getEmail_id().equals(email_id))
										  .findFirst()
										  .orElseThrow(()-> new CricketManagementSystemException("Email.not found"));
		cricketer.setRating(rating);
		System.out.println("Rating updated");
	}

	@Override
	public void searchCricketer(String name) throws CricketManagementSystemException {
		// TODO Auto-generated method stub
//		boolean found = cricketerList.stream()
//					 .filter(p->p.getName().equals(name))
//					 .peek(p->System.out.println(p))
//					 .findFirst()
//					 .isPresent();
//		
//		if(!found) {
//			throw new CricketManagementSystemException("Name not found...");
//		}
//		else {
//			System.out.println("found...");
//		}
		
		Cricketer c = cricketerMap.values()
								  .stream()
								  .filter(p->p.getName().equals(name))
								  .findFirst()
								  .orElseThrow(()-> new CricketManagementSystemException("Not Found"));
		
		System.out.println("Found: "+c);
		
		
		
//		System.out.println("Found...");
	}

	@Override
	public void displayAllCricketer() {
//		for(Cricketer c : cricketerList) {
//			System.out.println(c);
//		}
		
		for(Cricketer c : cricketerMap.values()) {
			System.out.println(c);
		}

	}

	@Override
	public void DisplaySortedByRating() {
		// TODO Auto-generated method stub
		Comparator<Cricketer> comp = (c1,c2) -> ((Double)c1.getRating()).compareTo(c2.getRating());
		
		cricketerMap.values()
					.stream()
					.sorted(comp)
					.forEach(p->System.out.println(p));

	}

}
