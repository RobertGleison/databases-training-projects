package application;

import database.DatabaseConnection;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);

        DatabaseConnection.closeConnection();
    }
}
