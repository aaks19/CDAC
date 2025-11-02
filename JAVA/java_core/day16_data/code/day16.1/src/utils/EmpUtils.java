package utils;

import java.util.List;

import com.app.core.Emp;

public interface EmpUtils {
	static double sumSalary(List<? extends Emp> empList) {
		double sum = 0;
		for(Emp n : empList) {
			sum += n.getBasic();
		}
		return sum;
	}
}
