package tester;

import java.util.ArrayList;
import java.util.List;

import utils.MinUtils;
import utils.MinUtils.*;

public class Test6 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(List.of(10,54,25,62,10,-25));
		System.out.println(MinUtils.minElementInAnyList(list));
	}
}
