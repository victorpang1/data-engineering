package company;

import java.util.*;

public final class App {
    /**
     * 
     */
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        Employee e1 = new Employee("Han Solo", 2, 10000);
        Employee e2 = new Employee("Darth Vader", 1, 250000);
        Employee e3 = new Employee("Luke Skywalker", 1, 1000);
        Employee e4 = new Employee("Chewbacca", 3, 100);
        Employee e5 = new Employee("BB-8", 3, 0);
        
        employees.add(e1);employees.add(e2);employees.add(e3);employees.add(e4);employees.add(e5);        
    	
        System.out.println(employees);
        
        
        // times are good, give each employee a 10% raise
                       
        // a pandemic hits, employees take 10% cut 
        
        // fairness disputes, each employee with 0 dollars 50 dollars base
        
    }
}
