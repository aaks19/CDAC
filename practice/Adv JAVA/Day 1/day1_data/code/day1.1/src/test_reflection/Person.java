package test_reflection;

class Person {
	private String name;
	

	public Person(String name) {
		this.name = name;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	private void sayHello(String greeting) {
		System.out.println(greeting + name);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
