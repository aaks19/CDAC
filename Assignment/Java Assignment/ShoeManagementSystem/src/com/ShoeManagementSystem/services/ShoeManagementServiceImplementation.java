package com.ShoeManagementSystem.services;

import static com.ShoeManagementSystem.services.ShoeManagementValidation.validateAll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ShoeManagementSystem.Exception.ShoeManagementException;
import com.ShoeManagementSystem.core.ShoeGallery;

public class ShoeManagementServiceImplementation implements ShoeManagementService {

	private List<ShoeGallery> shoeList;

	public ShoeManagementServiceImplementation() {
		this.shoeList = new ArrayList<>();
	}

	@Override
	public String addNewShoe(String name, String brand, int rating, double price, boolean availableInGallery,
			String shoe_type) throws ShoeManagementException {
		ShoeGallery shoe = validateAll(name, brand, rating, price, availableInGallery, shoe_type, shoeList);
		shoeList.add(shoe);
		return null;
	}

	@Override
	public void displayAllShoe() {
		shoeList.stream().forEach(p -> System.out.println(p));
	}

	@Override
	public void displayShoeSortedById() {
		Comparator<ShoeGallery> comp = (s1, s2) -> ((Integer) s2.getShoe_id()).compareTo(s1.getShoe_id());

		shoeList.stream().sorted(comp).forEach(s -> System.out.println(s));
	}

	@Override
	public void searchMostExpensiveShoe() throws ShoeManagementException {
		Comparator<ShoeGallery> comp = (s1, s2) -> ((Double) s2.getPrice()).compareTo(s1.getPrice());
		ShoeGallery s = shoeList.stream().sorted(comp).findFirst()
				.orElseThrow(() -> new ShoeManagementException("Not Found"));
		
		System.out.println(s);

	}

	@Override
	public void removeShoe() throws ShoeManagementException {
		shoeList.removeIf(p->((Boolean)p.isAvailableInGallery()).equals(false));
		System.out.println("Removed unavailable shoes");
	}

	@Override
	public void updatePrice(String brand,double price) throws ShoeManagementException {
		shoeList.stream()
				.filter(s->s.getBrand().equals(brand))
				.forEach(s->s.setPrice(price));

	}

	@Override
	public void sortShoeByPriceDescending() {
		Comparator<ShoeGallery> comp = (s1,s2)->((Double)s2.getPrice()).compareTo(s1.getPrice());
		
		shoeList.stream()
				.sorted(comp)
				.forEach(s->System.out.println(s));

	}

}
