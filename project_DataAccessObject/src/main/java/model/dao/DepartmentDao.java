package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department client);
    void update(Department client);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
