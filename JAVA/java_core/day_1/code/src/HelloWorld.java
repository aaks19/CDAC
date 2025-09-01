class Hello{
	public static void main(String[] args){
		System.out.println("Apna hello world "+args[0]);
	}
}

class HelloWorld{
	public static void main(String[] args){
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
		System.out.println("Sum = "+ (num1 + num2));
	}
}