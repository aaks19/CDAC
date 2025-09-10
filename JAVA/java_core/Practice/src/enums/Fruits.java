package enums;

public enum Fruits {
	MANGO, APPLE, ORANGE, GRAPES, CHICKOO;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
