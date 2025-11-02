package StringBuilderPractice;

public class testStringBuilder {
	public static void main(String[] args) {
		StringBuilder s1 = new StringBuilder("hello");
		StringBuilder s2 = new StringBuilder("hello");
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
		StringBuffer sb1 = new StringBuffer("hello");
		StringBuffer sb2 = new StringBuffer("hello");
		System.out.println(sb1 == sb2);
		System.out.println(sb1.equals(sb2));
		
		
		String st1 = new String("hello");
		String st2 = new String("hello");
		
		System.out.println(st1 == st2);
		System.out.println(st1.equals(st2));
		
	}
}
