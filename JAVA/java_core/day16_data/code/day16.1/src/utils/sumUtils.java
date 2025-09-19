package utils;

import java.util.Set;

public interface sumUtils {
	static double sumAll(Set<? extends Number> numbers) {
		double sum = 0;
//		myset.forEach(f -> (double = double + f) )
		for(Number n : numbers) {
			sum += n.doubleValue();
		}
		return sum;
	}
}
