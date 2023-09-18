package application;

import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department department = new Department(1, "Marketing");
        Seller seller = new Seller(1, "Robert", "robert@email", LocalDate.now(), 3000.0, department);
        System.out.println(seller);
    }
}