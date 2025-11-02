package test_reflection;

import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter class / interface name to load");
			Class<?> cls3 = Class.forName(sc.next());
			System.out.println(cls3.getName());
			System.out.println(cls3.getSimpleName());
			System.out.println(cls3.getPackageName());
			System.out.println(cls3.getSuperclass());
			Arrays.stream(cls3.getInterfaces())
			.forEach(System.out::println);//c -> System.out.println(c)
			if(cls3.isInterface()) 
			{
				System.out.println("loaded type - interface");
			} else {
				System.out.println("loaded type - class");
				int clsModifiers=cls3.getModifiers();
				System.out.println(Modifier.isAbstract(clsModifiers)?"Abstract":"Concrete");
				System.out.println(Modifier.isPublic(clsModifiers)?"Public":"Package-Private");
				System.out.println(Modifier.isFinal(clsModifiers)?"Final":"non Final");
				 Arrays.stream(cls3.getConstructors())
				 .forEach(System.out::println);
				 Arrays.stream(cls3.getDeclaredFields())
				 .forEach(System.out::println);
				 Arrays.stream(cls3.getDeclaredMethods())
				 .forEach(System.out::println);
				 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
