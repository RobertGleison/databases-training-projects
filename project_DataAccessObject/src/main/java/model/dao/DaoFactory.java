package model.dao;

import database.DatabaseConnection;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DatabaseConnection.getConnection());
    }
}
