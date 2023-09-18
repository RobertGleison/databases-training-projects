package model.dao.impl;

import database.DBException;
import database.DatabaseConnection;
import model.dao.DepartmentDao;
import model.entities.Department;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department client) {

    }

    @Override
    public void update(Department client) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                    "SELECT * FROM department WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt("Id"), rs.getString("Name"));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeResultSet();
            DatabaseConnection.closeStatement();
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st;
        ResultSet rs;
        List<Department> list = new ArrayList<>();
        try {
            st = connection.prepareStatement(
                    "SELECT * FROM department");
            rs = st.executeQuery();
            while (rs.next()) {
                Department department = new Department(rs.getInt("Id"), rs.getString("Name"));
                list.add(department);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return list;
    }
}
