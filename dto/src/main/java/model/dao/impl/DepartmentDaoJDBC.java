package model.dao.impl;

import database.DBException;
import database.DatabaseConnection;
import model.dao.DepartmentDao;
import model.entities.Department;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st;
        try {
            st = connection.prepareCall("INSERT INTO department (Id, Name) VALUES (?,?)");
            st.setInt(1, department.getId());
            st.setString(2, department.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DatabaseConnection.closeStatement();
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st;
        try{
            st=connection.prepareStatement("SET FROM departments (Id, Name) Values (?,?");
            st.setInt(1,department.getId());
            st.setString(2,department.getName());
            st.executeUpdate();
        }
        catch(SQLException e ){
            throw new DBException(e.getMessage());
        }finally {
            DatabaseConnection.closeStatement();
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st;
        try {
            st = connection.prepareCall("DELETE FROM department WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = connection.prepareStatement("SELECT * FROM department WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return new Department(rs.getInt("Id"), rs.getString("Name"));
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeResultSet();
            DatabaseConnection.closeStatement();
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st;
        ResultSet rs;
        List<Department> list = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();
        try {
            st = connection.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            while (rs.next()) {
                Department department = map.get(rs.getInt("Id"));
                if (department == null) {
                    map.put(rs.getInt("Id"), new Department(rs.getInt("Id"), rs.getString("Name")));
                    department = map.get(rs.getInt("Id"));
                    list.add(department);
                }
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DatabaseConnection.closeResultSet();
            DatabaseConnection.closeStatement();
        }
        return list;
    }
}
