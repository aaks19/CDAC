package enums;

import java.util.Scanner;

public class testEnum {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Fruits:");
			
			for(Fruits f : Fruits.values()) {
				System.out.println(f);
			}
			
			System.out.println("Enter choice of fruit:");
			Fruits chooseFruit = Fruits.valueOf(sc.next().toUpperCase());
			System.out.println("Your choice : "+chooseFruit+" position : "+chooseFruit.ordinal());
		}
	}
}
