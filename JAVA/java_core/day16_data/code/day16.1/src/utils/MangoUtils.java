package utils;

import java.util.List;

import fruits.Mango;

public interface MangoUtils {
	static void addMango(List<? super Mango> basket,Mango ...mangos) {
		for(Mango m: mangos) {
			basket.add(m);
		}
	}
}
