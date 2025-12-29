package test_reflection;

import java.time.LocalDate;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// Get Loaded class reference
			Class<String> cls = String.class;
			LocalDate date = LocalDate.now();
			Class<?> cls2 = date.getClass();
			System.out.println("Enter class / interface name to load");
			Class<?> cls3 = Class.forName(sc.next());
			System.out.println(cls3.getName());
			System.out.println(cls3.getSimpleName());
			System.out.println(cls3.getPackageName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
