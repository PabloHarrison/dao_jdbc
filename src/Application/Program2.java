package Application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("=== Test 1: Department findById ===");
        Department department = departmentDao.findById(2);
        System.out.println(department);
        System.out.println();

        System.out.println("=== Test 2: Department findByAll ===");
        Department department2 = new Department();
        List<Department> listDep = departmentDao.findAll();
        for(Department dep : listDep){
            System.out.println(dep);
        }


        sc.close();
    }
}
