package com.ems.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entities.Department;

public interface DepartmentDao extends JpaRepository<Department,Long>{

}
