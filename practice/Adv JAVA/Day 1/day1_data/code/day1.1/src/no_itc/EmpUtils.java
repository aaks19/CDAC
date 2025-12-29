package no_itc;

public class EmpUtils {
	private Emp e;

	public synchronized void writeData(Emp ref) throws Exception {
		System.out.println("write entered --- " + Thread.currentThread().getName());
		// produce data
		e = ref;
		System.out.println("Write Data " + e);
		System.out.println("write exited --- " + Thread.currentThread().getName());
	}
	public synchronized Emp readData() throws Exception {
		System.out.println("r entered --- " + Thread.currentThread().getName());
		// consume data
		System.out.println("Read  Data " + e);
		System.out.println("r exited --- " + Thread.currentThread().getName());
		return e;
	}

}
