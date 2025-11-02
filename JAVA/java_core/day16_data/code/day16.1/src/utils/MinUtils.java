package utils;

import java.util.List;

public interface MinUtils {
	static Comparable minElementInAnyList(List<? extends Comparable> list) {
		Comparable min = list.get(0);
		for(Comparable c : list) {
			int ret = c.compareTo(min);
			if(ret<0) {
				min = c;
			}
		}
		return min;
	}
//	public static <T extends Comparable> T minElement(List<T> list) {
//		
//	}
}
