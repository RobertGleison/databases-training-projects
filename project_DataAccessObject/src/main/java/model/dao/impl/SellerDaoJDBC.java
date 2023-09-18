package model.dao.impl;

import database.DBException;
import database.DatabaseConnection;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "Where seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentID"));
                dep.setName(rs.getString("DepName"));
                Seller sel = new Seller();
                sel.setId(rs.getInt("id"));
                sel.setName(rs.getString("Name"));
                sel.setEmail(rs.getString("Email"));
                sel.setBaseSalary(rs.getDouble("BaseSalary"));
                sel.setBirthDate(rs.getDate("BirthDate").toLocalDate());
                sel.setDepartment(dep);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        finally {
            DatabaseConnection.closeStatement();
            DatabaseConnection.closeResultSet();
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
