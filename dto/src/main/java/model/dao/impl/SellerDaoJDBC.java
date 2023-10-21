package model.dao.impl;

import database.DBException;
import database.DatabaseConnection;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement st;
        try {
            st = connection.prepareStatement(
                    "iNSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    seller.setId(id);
                }
                DatabaseConnection.closeResultSet();
            } else throw new DBException("No rows affected!");
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
        }
    }

    @Override
    public void update(Seller seller) {
        PreparedStatement st;
        try {
            st = connection.prepareStatement(
                    "UPDATE seller SET Name=?, Email=?,BirthDate=?,BaseSalary=?,DepartmentId=? "
                            + "WHERE Id = ?");
            st.setString(1, seller.getName());
            st.setString(1, seller.getEmail());
            st.setDate(1, new java.sql.Date(seller.getBirthDate().getTime()));
            st.setDouble(1, seller.getBaseSalary());
            st.setInt(1, seller.getDepartment().getId());
            st.setInt(6, seller.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st;
        try {
            st = connection.prepareStatement("DELETE FROM seller WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "Where seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = instatiateDepartment(rs);
                return instantiateSeller(rs, dep);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
            DatabaseConnection.closeResultSet();
        }
        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller sel = new Seller();
        sel.setId(rs.getInt("id"));
        sel.setName(rs.getString("Name"));
        sel.setEmail(rs.getString("Email"));
        sel.setBaseSalary(rs.getDouble("BaseSalary"));
        sel.setBirthDate(rs.getDate("BirthDate"));
        sel.setDepartment(dep);
        return sel;
    }

    private Department instatiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentID"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.ID "
                            + "ORDER BY Name");

            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Seller sel = instantiateSeller(rs, dep);
                list.add(sel);
            }
            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
            DatabaseConnection.closeResultSet();
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st;
        ResultSet rs;
        try {
            st = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.ID "
                            + "WHERE DepartmentId = ? ORDER BY Name");
            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }
                Seller sel = instantiateSeller(rs, dep);
                list.add(sel);
            }
            return list;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DatabaseConnection.closeStatement();
            DatabaseConnection.closeResultSet();
        }
    }
}
