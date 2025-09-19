package com.shop.tester;

import static com.shop.utils.ShopUtils.populateProductList;
import static com.shop.utils.ShopUtils.populateProductMap;

import java.util.Map;

import com.shop.core.Category;
import com.shop.core.Product;

public class Test4 {

	public static void main(String[] args) {
		// get populated Map of products
		Map<Integer, Product> productMap = populateProductMap(populateProductList());
		/*
		 * New default method added in Map i/f public default void forEach(BiConsumer<?
		 * super K,? super V> action) BiConsumer<T,U> - functional i/f SAM - public void
		 * accept(T t,U u)
		 */
		// solve - display entries from the map.
		System.out.println("..................All Products......................");
		productMap.forEach((pid, product) -> System.out.println("Key " + pid + " Value " + product));
		System.out.println("........................................");
		Category category = Category.BREAD;

		//convert map to collections by the .values() method
		productMap.values().removeIf(p -> p.getProductCategory()==category);
		productMap.forEach((pid, product) -> System.out.println("Keys = " + pid + " values = " + product));

		
		System.out.println("..................All Products......................");
		productMap.forEach((pid, product) -> System.out.println("Key " + pid + " Value " + product));
		
		
		
	}

}
