package com.IMS.Services;

import static com.IMS.Services.IMSValidation.validateALl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.IMS.Exception.IMSException;
import com.IMS.core.Inventory;
import com.IMS.core.Perishable;

public class IMSServiceImplementation implements IMSService {

//	private List<Inventory> inventoryList;
	private Map<Integer, Inventory> inventoryMap;

	public IMSServiceImplementation() {
		super();
//		this.inventoryList = new ArrayList<>();
		this.inventoryMap = new HashMap<>();
	}

	@Override
	public void addPerishableItem(String name, String category, double price, int quantity, String expiryDate)
			throws IMSException {
//		Inventory inv = validateALl(name, category, price, quantity, expiryDate, quantity, inventoryList);
//		inventoryList.add(inv);

		Inventory inv = validateALl(name, category, price, quantity, expiryDate, quantity, inventoryMap);
		inventoryMap.put(inv.getId(), inv);

	}

//	@Override
//	public void addNonPerishableItem(String name, String category, double price, int quantity, int warrentrPeriod)
//			throws IMSException {
////		Inventory inv = validateALl(name, category, price, quantity, category, warrentrPeriod, inventoryList);
////		inventoryList.add(inv);
//		
//		Inventory inv = validateALl(name, category, price, quantity, warrentrPeriod, inventoryMap)
//		inventoryMap.put(inv.getId(), inv);
//		
//	}

	@Override
	public void addNonPerishableItem(String name, String category, double price, int quantity, int warrentrPeriod)
			throws IMSException {
		// TODO Auto-generated method stub
//		Inventory inv = validateALl(name, category, price, quantity, category, warrentrPeriod, inventoryList);
//		inventoryList.add(inv);

		// using hashmap
		Inventory inv = validateALl(name, category, price, quantity, null, warrentrPeriod, inventoryMap);
		inventoryMap.put(inv.getId(), inv);
	}

	@Override
	public void deleteItemByCode(int id) throws IMSException {
		// TODO Auto-generated method stub
//		inventoryList.removeIf(i -> i.getId() == id);

//		using hashmap
		inventoryMap.remove(id);
	}

	@Override
	public void searchItem(String name) throws IMSException {
//		inventoryList.stream().filter(i -> i.getName().equals(name)).forEach(i -> System.out.println(i));

//		using hashmap
		boolean found = inventoryMap.values().stream().filter(i -> i.getName().equals(name)).peek(i->System.out.println(i)).findAny().isPresent();
		
		if(found) {
			System.out.println(name);
		}
	}

	@Override
	public void displayAllItems() {
//		inventoryList.stream().forEach(i -> System.out.println(i));

//		using hashmap
		inventoryMap.values().stream().forEach(i -> System.out.println(i));
	}

	@Override
	public void displayItemSortedByPrice() {
		Comparator<Inventory> comp = (i1, i2) -> ((Double) i1.getPrice()).compareTo(i2.getPrice());
//		inventoryList.stream().sorted(comp).forEach(i -> System.out.println(i));

		// using hashmap
		inventoryMap.values().stream().sorted(comp).forEach(i -> System.out.println(i));
	}

	@Override
	public void displayExpiredItem() {

//		inventoryList.stream().filter(i -> i instanceof Perishable) 
//        .map(i -> (Perishable) i)
//        .filter(p -> p.getExpiryDate().isBefore(LocalDate.now()))
//        .forEach(i->System.out.println(i));

		
		//using hashmap
		inventoryMap.values().stream()
        .filter(i -> i instanceof Perishable) 
        .map(i -> (Perishable) i)
        .filter(p -> p.getExpiryDate().isBefore(LocalDate.now()))
        .forEach(System.out::println);

	}

}
