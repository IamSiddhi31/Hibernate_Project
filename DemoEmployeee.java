package employee_HW;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class DemoEmployeee {

    static Scanner sc = new Scanner(System.in);
    static SessionFactory sf;

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Employeee.class);

        System.out.println("1. cfg ready..");

        sf = cfg.buildSessionFactory();

        System.out.println("2. factory ready..");

        int choice;

        do {
            System.out.println("\n===== HIBERNATE 20 TASKS =====");
            System.out.println("1. Insert using persist()");
            System.out.println("2. Display all employees");
            System.out.println("3. Fetch employee by ID");
            System.out.println("4. Update employee salary");
            System.out.println("5. Delete employee by ID");
            System.out.println("6. Insert 10 employees");
            System.out.println("7. Update using session.update()");
            System.out.println("8. Delete HR department employees");
            System.out.println("9. Display salary greater than 50000");
            System.out.println("10. Display employees by salary descending");
            System.out.println("11. Find maximum salary");
            System.out.println("12. Find total salary");
            System.out.println("13. Department-wise employee count");
            System.out.println("14. Salary between 40000 and 80000");
            System.out.println("15. Employee name starts with A");
            System.out.println("16. Salary greater than average salary");
            System.out.println("17. Find second highest salary");
            System.out.println("18. Employees from IT, HR, Finance");
            System.out.println("19. Department-wise average salary");
            System.out.println("20. Increase IT salary by 10%");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: task1(); break;
                case 2: task2(); break;
                case 3: task3(); break;
                case 4: task4(); break;
                case 5: task5(); break;
                case 6: task6(); break;
                case 7: task7(); break;
                case 8: task8(); break;
                case 9: task9(); break;
                case 10: task10(); break;
                case 11: task11(); break;
                case 12: task12(); break;
                case 13: task13(); break;
                case 14: task14(); break;
                case 15: task15(); break;
                case 16: task16(); break;
                case 17: task17(); break;
                case 18: task18(); break;
                case 19: task19(); break;
                case 20: task20(); break;
                case 0:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sf.close();
        sc.close();
    }

    // TASK 1: Insert using persist()

    static void task1() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter name: ");
        String name = sc.next();

        System.out.print("Enter role: ");
        String role = sc.next();

        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter department: ");
        String dept = sc.next();

        Employeee emp = new Employeee(id, name, role, salary, dept);

        ssn.persist(emp);
        tx.commit();

        System.out.println("Employee inserted successfully!");

        ssn.close();
    }

    // TASK 2: Display all employees

    static void task2() {

        Session ssn = sf.openSession();

        List<Employeee> list =
                ssn.createQuery("from Employeee", Employeee.class).list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        ssn.close();
    }

    // TASK 3: Fetch employee by ID

    static void task3() {

        Session ssn = sf.openSession();

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        Employeee emp = ssn.get(Employeee.class, id);

        if (emp != null) {
            System.out.println(emp);
        } else {
            System.out.println("Employee not found!");
        }

        ssn.close();
    }

    // TASK 4: Update employee salary

    static void task4() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        Employeee emp = ssn.get(Employeee.class, id);

        if (emp != null) {
            System.out.print("Enter new salary: ");
            emp.salary = sc.nextDouble();

            tx.commit();
            System.out.println("Salary updated!");
        } else {
            tx.rollback();
            System.out.println("Employee not found!");
        }

        ssn.close();
    }

    // TASK 5: Delete employee by ID

    static void task5() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        Employeee emp = ssn.get(Employeee.class, id);

        if (emp != null) {
            ssn.remove(emp);
            tx.commit();
            System.out.println("Employee deleted!");
        } else {
            tx.rollback();
            System.out.println("Employee not found!");
        }

        ssn.close();
    }

    // TASK 6: Insert 10 employees

    static void task6() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        Employeee[] emp = new Employeee[10];

        emp[0] = new Employeee(101, "Amit", "Developer", 45000, "IT");
        emp[1] = new Employeee(102, "Raj", "Tester", 35000, "IT");
        emp[2] = new Employeee(103, "Priya", "Manager", 65000, "HR");
        emp[3] = new Employeee(104, "Sita", "Developer", 55000, "IT");
        emp[4] = new Employeee(105, "Om", "Accountant", 40000, "Finance");
        emp[5] = new Employeee(106, "Neha", "HR", 42000, "HR");
        emp[6] = new Employeee(107, "Ravi", "Tester", 38000, "IT");
        emp[7] = new Employeee(108, "Pooja", "Manager", 75000, "Finance");
        emp[8] = new Employeee(109, "Kiran", "Developer", 60000, "IT");
        emp[9] = new Employeee(110, "Sneha", "Accountant", 50000, "Finance");

        for (int i = 0; i < emp.length; i++) {
            ssn.persist(emp[i]);
        }

        tx.commit();

        System.out.println("10 employees inserted!");

        ssn.close();
    }

    // TASK 7: Update using session.update()

    static void task7() {

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        Session ssn1 = sf.openSession();

        Employeee emp = ssn1.get(Employeee.class, id);

        ssn1.close();

        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("Enter new role: ");
        emp.role = sc.next();

        System.out.print("Enter new salary: ");
        emp.salary = sc.nextDouble();

        Session ssn2 = sf.openSession();
        Transaction tx = ssn2.beginTransaction();

        ssn2.update(emp);

        tx.commit();

        System.out.println("Detached employee updated!");

        ssn2.close();
    }

    // TASK 8: Delete all HR department employees

    static void task8() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        Query query = ssn.createQuery(
                "delete from Employeee where department = :dept");

        query.setParameter("dept", "HR");

        int count = query.executeUpdate();

        tx.commit();

        System.out.println(count + " HR employees deleted!");

        ssn.close();
    }

    // TASK 9: Native SQL - salary greater than 50000

    static void task9() {

        Session ssn = sf.openSession();

        List<Object[]> list = ssn.createNativeQuery(
                "SELECT * FROM Employeee WHERE salary > 50000").list();

        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] + "  ");
            }
            System.out.println();
        }

        ssn.close();
    }

    // TASK 10: Native SQL - salary descending

    static void task10() {

        Session ssn = sf.openSession();

        List<Object[]> list = ssn.createNativeQuery(
                "SELECT * FROM Employeee ORDER BY salary DESC").list();

        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] + "  ");
            }
            System.out.println();
        }

        ssn.close();
    }

    // TASK 11: Maximum salary

    static void task11() {

        Session ssn = sf.openSession();

        Object maxSalary = ssn.createNativeQuery(
                "SELECT MAX(salary) FROM Employeee").getSingleResult();

        System.out.println("Maximum salary: " + maxSalary);

        ssn.close();
    }

    // TASK 12: Total salary

    static void task12() {

        Session ssn = sf.openSession();

        Object totalSalary = ssn.createNativeQuery(
                "SELECT SUM(salary) FROM Employeee").getSingleResult();

        System.out.println("Total salary: " + totalSalary);

        ssn.close();
    }

    // TASK 13: Department-wise employee count

    static void task13() {

        Session ssn = sf.openSession();

        List<Object[]> list = ssn.createNativeQuery(
                "SELECT department, COUNT(*) FROM Employeee GROUP BY department").list();

        for (Object[] row : list) {
            System.out.println("Department: " + row[0]
                    + " Count: " + row[1]);
        }

        ssn.close();
    }

    // TASK 14: Salary between 40000 and 80000

    static void task14() {

        Session ssn = sf.openSession();

        List<Object[]> list = ssn.createNativeQuery(
                "SELECT * FROM Employeee WHERE salary BETWEEN 40000 AND 80000").list();

        for (Object[] row : list) {
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i] + "  ");
            }
            System.out.println();
        }

        ssn.close();
    }

    // TASK 15: Employee name starts with A

    static void task15() {

        Session ssn = sf.openSession();

        List<Employeee> list = ssn.createQuery(
                "from Employeee where name LIKE 'A%'", Employeee.class).list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        ssn.close();
    }

    // TASK 16: Salary greater than average salary

    static void task16() {

        Session ssn = sf.openSession();

        List<Employeee> list = ssn.createQuery(
                "from Employeee where salary > (select avg(salary) from Employeee)",
                Employeee.class).list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        ssn.close();
    }

    // TASK 17: Second highest salary

    static void task17() {

        Session ssn = sf.openSession();

        Object secondSalary = ssn.createQuery(
                "select max(salary) from Employeee "
                + "where salary < (select max(salary) from Employeee)")
                .uniqueResult();

        System.out.println("Second highest salary: " + secondSalary);

        ssn.close();
    }

    // TASK 18: Employees from IT, HR and Finance

    static void task18() {

        Session ssn = sf.openSession();

        List<Employeee> list = ssn.createQuery(
                "from Employeee where department in (:depts)",
                Employeee.class)
                .setParameterList("depts",
                        Arrays.asList("IT", "HR", "Finance"))
                .list();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        ssn.close();
    }

    // TASK 19: Department-wise average salary

    static void task19() {

        Session ssn = sf.openSession();

        List<Object[]> list = ssn.createQuery(
                "select department, avg(salary) from Employeee "
                + "group by department").list();

        for (Object[] row : list) {
            System.out.println("Department: " + row[0]
                    + " Average salary: " + row[1]);
        }

        ssn.close();
    }

    // TASK 20: Increase IT salary by 10%

    static void task20() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        Query query = ssn.createQuery(
                "update Employeee set salary = salary * 1.10 "
                + "where department = :dept");

        query.setParameter("dept", "IT");

        int count = query.executeUpdate();

        tx.commit();

        System.out.println(count + " IT employees salary increased by 10%!");

        ssn.close();
    }
}
