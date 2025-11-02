package com.ems.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ems.entities.Department;
/*
 * Repository - mandatory class level annotation to declare DAO layer spring
 * bean - singleton & eager
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	// dependency - SessionFactory
	@Autowired // byType
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Department> getAllDepartments() {
		String jpql = "select d from Department d";
		
		return sessionFactory.getCurrentSession().createQuery(jpql,Department.class).getResultList();
	}

}
