package Application;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String choiceManage;
        while(true) {
            System.out.print("Choose an option to manage (Department, Seller, or type Exit to quit): ");
            choiceManage = sc.nextLine();
            if(choiceManage.equalsIgnoreCase("department") || choiceManage.equalsIgnoreCase("seller")) {
                switch (choiceManage.toLowerCase()) {
                    case "department":
                        while(choiceManage.equalsIgnoreCase("department")){
                            DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
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

                    case "seller":
                        while(choiceManage.equalsIgnoreCase("seller")){

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
