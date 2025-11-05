package Application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args){

        Department obj = new Department(1, "books");
        Seller seller = new Seller(3, "Maria", "maria@gmail.com", LocalDate.of(1990, 5, 12), 1000.0, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(seller);
    }
}
