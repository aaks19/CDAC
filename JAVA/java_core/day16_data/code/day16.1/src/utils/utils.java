package utils;

import java.util.List;

import fruits.Fruit;

public interface utils {
	static void displayTaste(List<? extends Fruit> basket) {
		basket.forEach(f -> f.taste());
	}
}
