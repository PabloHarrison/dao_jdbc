package Application;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String choiceManage;
        while(true) {
            System.out.print("Choose an option to manage (Department, Seller, or type Exit to quit): ");
            choiceManage = sc.nextLine();
            if(choiceManage.equalsIgnoreCase("department") || choiceManage.equalsIgnoreCase("seller")) {
                switch (choiceManage.toLowerCase()) {
                    case "department":
                        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
                        while(choiceManage.equalsIgnoreCase("department")){
                            System.out.println();
                            System.out.println("---------- Operation List ----------");
                            System.out.println("1 - Add new department");
                            System.out.println("2 - Update a department");
                            System.out.println("3 - Delete a department by ID");
                            System.out.println("4 - List all departments");
                            System.out.println("5 - Find a department by ID");
                            System.out.println("6 - To go back");
                            System.out.print("Write the operation number to perform: ");
                            int number = sc.nextInt();
                            sc.nextLine();
                            switch (number){
                                case 1:
                                    System.out.print("Enter the department name: ");
                                    String name = sc.nextLine();
                                    if(name.isBlank()){
                                        System.out.println("Department name cannot be empty.");
                                        break;
                                    }
                                    Department department1 = new Department(null, name);
                                    departmentDao.insert(department1);
                                    System.out.println("Department entered! Id: " + department1.getId() + " Department name: " + department1.getName());
                                    System.out.println();
                                    break;

                                case 2:
                                    System.out.print("Enter the department ID: ");
                                    int idUpdate = sc.nextInt();
                                    sc.nextLine();
                                    Department department2 = departmentDao.findById(idUpdate);
                                    if (department2 == null) {
                                        System.out.println("Department not found.");
                                        break;
                                    }
                                    System.out.print("Enter the updated department name: ");
                                    String updateName = sc.nextLine();
                                    department2.setName(updateName);
                                    departmentDao.update(department2);
                                    System.out.println("Department updated successfully!");
                                    System.out.println();
                                    break;

                                case 3:
                                    try {
                                        System.out.print("Enter the ID of the department you want to delete: ");
                                        int idDelete = sc.nextInt();
                                        sc.nextLine();
                                        departmentDao.deleteById(idDelete);
                                        System.out.println("Deleted!");
                                    }
                                    catch (DbException e){
                                        System.out.println("Error: " + e.getMessage());
                                        System.out.println();
                                    }
                                    break;

                                case 4:
                                    List<Department> depList = departmentDao.findAll();
                                    System.out.println();
                                    System.out.println("-------------- List --------------");
                                    for(Department dep : depList){
                                        System.out.println(dep);
                                    }
                                    System.out.println("----------------------------------");
                                    break;

                                case 5:
                                    System.out.print("Enter the ID of the department you wish to search for: ");
                                    int findId = sc.nextInt();
                                    sc.nextLine();
                                    Department department5 =  departmentDao.findById(findId);
                                    System.out.println(department5);
                                    break;

                                case 6:
                                    choiceManage = "";
                                    break;

                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                            }
                        }
                        break;

                    case "seller":
                        SellerDao sellerDao = DaoFactory.createSellerDao();
                        while(choiceManage.equalsIgnoreCase("seller")){
                            List<Seller> sellerList;
                            System.out.println();
                            System.out.println("---------- Operation List ----------");
                            System.out.println("1 - Add new seller");
                            System.out.println("2 - Update a seller");
                            System.out.println("3 - Delete a seller by ID");
                            System.out.println("4 - List all seller");
                            System.out.println("5 - Find a seller by ID");
                            System.out.println("6 - Find a seller by Department");
                            System.out.println("7 - To go back");
                            System.out.print("Write the operation number to perform: ");
                            int number = sc.nextInt();
                            sc.nextLine();
                            switch (number){
                                case 1:
                                    try {
                                        System.out.println("Enter the seller's information");
                                        System.out.print("Name: ");
                                        String name = sc.nextLine();
                                        System.out.print("Email: ");
                                        String email = sc.nextLine();
                                        System.out.print("Birth Date (dd/MM/yyyy): ");
                                        LocalDate birthDate = LocalDate.parse(sc.nextLine(), fmt);
                                        System.out.print("Base Salary: ");
                                        double baseSalary = sc.nextDouble();
                                        System.out.print("Department Id: ");
                                        int departmentId = sc.nextInt();
                                        Seller seller1 = new Seller(null, name, email, birthDate, baseSalary, new Department(departmentId, null));
                                        sellerDao.insert(seller1);
                                        System.out.println("Seller inserted!");
                                    }
                                    catch (DateTimeParseException e){
                                        System.out.println("Invalid date format. Use dd/MM/yyyy.");
                                        break;
                                    }
                                    break;

                                case 2:
                                    System.out.print("Enter the seller ID: ");
                                    int idSeller = sc.nextInt();
                                    sc.nextLine();
                                    Seller seller2 = sellerDao.findById(idSeller);
                                    if (seller2 == null) {
                                        System.out.println("Seller not found.");
                                        break;
                                    }
                                    System.out.print("What do you want to change (Name, Email or Base Salary)? ");
                                    String change = sc.nextLine().replace(" ", "");
                                    if(change.equalsIgnoreCase("name")){
                                        System.out.print("New name: ");
                                        String newName = sc.nextLine();
                                        seller2.setName(newName);
                                    }else if(change.equalsIgnoreCase("email")){
                                        System.out.print("New email: ");
                                        String newEmail = sc.nextLine();
                                        seller2.setEmail(newEmail);
                                    } else if(change.equalsIgnoreCase("basesalary")) {
                                        System.out.print("New salary: ");
                                        double newSalary = sc.nextDouble();
                                        seller2.setBaseSalary(newSalary);
                                    }else{
                                        System.out.println("Invalid option. Please try again.");
                                        break;
                                    }
                                    sellerDao.update(seller2);
                                    System.out.println("Seller updated!");
                                    break;

                                case 3:
                                    System.out.print("Enter the ID of the item you wish to delete: ");
                                    int idDelete = sc.nextInt();
                                    System.out.print("Do you want to delete (y/n)? ");
                                    char yOrN = sc.next().toLowerCase().charAt(0);
                                    while (yOrN != 'n') {
                                        if (yOrN == 'y') {
                                            sellerDao.deleteById(idDelete);
                                            System.out.println("Seller deleted!");
                                            break;
                                        } else {
                                            System.out.print("Do you want to delete (y/n)? ");
                                            yOrN = sc.next().toLowerCase().charAt(0);
                                        }
                                    }
                                    break;

                                case 4:
                                    System.out.print("Order by (Id, Name or Base Salary): ");
                                    String order = sc.nextLine().replace(" ", "");
                                    if(order.equalsIgnoreCase("id")){
                                        order = "Id";
                                    }else if(order.equalsIgnoreCase("name")){
                                        order = "Name";
                                    } else if(order.equalsIgnoreCase("basesalary")) {
                                        order = "BaseSalary";
                                    }else{
                                        System.out.println("Invalid option. Defaulting to Name.");
                                        order = "Name";
                                    }
                                    sellerList = sellerDao.findAll(order);
                                    System.out.println();
                                    for(Seller seller : sellerList){
                                        System.out.println(seller);
                                        System.out.println();
                                    }
                                    break;

                                case 5:
                                    System.out.print("Enter the seller ID: ");
                                    int idFindSeller = sc.nextInt();
                                    sc.nextLine();
                                    Seller seller5 = sellerDao.findById(idFindSeller);
                                    if(seller5 == null){
                                        System.out.println("Non-existing id");
                                    }else {
                                        System.out.println();
                                        System.out.println(seller5);
                                        System.out.println();
                                    }
                                    break;

                                case 6:
                                    System.out.print("Enter the department ID: ");
                                    int depId = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println();
                                    sellerList = sellerDao.findByDepartment(new Department(depId, null));
                                    if (sellerList.isEmpty()) {
                                        System.out.println("No sellers found for this department.");
                                    }else {
                                        for (Seller seller : sellerList) {
                                            System.out.println(seller);
                                            System.out.println();
                                        }
                                    }
                                    break;

                                case 7:
                                    choiceManage = "";
                                    break;

                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                            }
                        }
                        break;
                }
            }else if(choiceManage.equalsIgnoreCase("exit")){
                System.out.println("Exiting program...");
                break;
            }else{
                System.out.println("Error: Incorrectly typed term");
            }
        }
        sc.close();
    }
}
