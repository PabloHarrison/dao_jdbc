package Application;

import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Program {
    public static void main(String[] args){

        Department obj = new Department(1, "books");
        Seller seller = new Seller(3, "Maria", "maria@gmail.com", LocalDate.of(1990, 5, 12), 1000.0, obj);

        System.out.println(seller);
    }
}
