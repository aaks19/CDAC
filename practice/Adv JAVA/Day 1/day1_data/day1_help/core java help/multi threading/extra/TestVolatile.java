package test_volatile;

public class TestVolatile extends Thread {
	private boolean flag;

	public void run() {
		long count = 0;
		while (!flag) {
			count++;
		}

		System.out.println("Thread terminated  " + count);
	}

	public static void main(String[] args) throws InterruptedException {
		TestVolatile t = null;
		for (int i = 0; i < 10; i++) {
			t = new TestVolatile();
			t.start();
			Thread.sleep(1000);
			System.out.println("1");
			t.flag = true;
			System.out.println("flag set to true.");

		}
		System.out.println("main over...");
	}
}