package tester;

import static utils.EmpUtils.sumSalary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.app.core.Mgr;
import com.app.core.Worker;

public class test4 {
	public static void main(String[] args) {
		List<Mgr> salList = new ArrayList<>();
		salList.add(new Mgr(50000.201));
		salList.add(new Mgr(52410.521));
		salList.add(new Mgr(52960.951));
		salList.add(new Mgr(74580.481));
		System.out.println(sumSalary(salList));
		
//		List<Worker> wkr = new LinkedList<>(List.of(new Mgr(50000.201),new Mgr(50000.201),new Mgr(50000.201),new Mgr(50000.201)));
//		the above example shows that we cannot Manager object.Both manager and worker are subclass of emp.
		
		List<Worker> wkr = new LinkedList<>(List.of(new Worker(50000.201),new Worker(50000.201),new Worker(50000.201),new Worker(50000.201)));
		System.out.println(sumSalary(wkr));
	}
}
