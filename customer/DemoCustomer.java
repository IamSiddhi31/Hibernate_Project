package customer;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DemoCustomer {

    static Scanner sc = new Scanner(System.in);

    static SessionFactory sf;

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Customer.class);

        sf = cfg.buildSessionFactory();

        int choice;

        do {
            System.out.println("\n===== CUSTOMER MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert Customer");
            System.out.println("2. Display All Customers");
            System.out.println("3. Display Customer By ID");
            System.out.println("4. Update Total Amount");
            System.out.println("5. Delete Customer By ID");
            System.out.println("6. Insert 10 Customers");
            System.out.println("7. Update Detached Customer");
            System.out.println("8. Delete Customers By City");
            System.out.println("9. Display Customers Having Total Amount > 5000");
            System.out.println("10. Display Customers Order By Total Amount");
            System.out.println("11. Find Maximum Total Amount");
            System.out.println("12. Find Sum Of Total Amount");
            System.out.println("13. Count Customers City Wise");
            System.out.println("14. Total Amount Between 1000 And 10000");
            System.out.println("15. Customer Name Starts With A");
            System.out.println("16. Total Amount Greater Than Average");
            System.out.println("17. Find Second Highest Total Amount");
            System.out.println("18. Customers From Pune, Mumbai, Nashik");
            System.out.println("19. Average Total Amount City Wise");
            System.out.println("20. Increase Pune Customers Total Amount By 10%");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

            case 1:
                insertCustomer();
                break;

            case 2:
                displayAll();
                break;

            case 3:
                displayById();
                break;

            case 4:
                updateAmount();
                break;

            case 5:
                deleteById();
                break;

            case 6:
                insert10Customers();
                break;

            case 7:
                detachedUpdate();
                break;

            case 8:
                deleteByCity();
                break;

            case 9:
                amountGreaterThan5000();
                break;

            case 10:
                orderByAmount();
                break;

            case 11:
                maximumAmount();
                break;

            case 12:
                sumAmount();
                break;

            case 13:
                countCityWise();
                break;

            case 14:
                amountBetween();
                break;

            case 15:
                nameStartsWithA();
                break;

            case 16:
                amountGreaterThanAverage();
                break;

            case 17:
                secondHighestAmount();
                break;

            case 18:
                customersByCities();
                break;

            case 19:
                averageAmountCityWise();
                break;

            case 20:
                increasePuneAmount();
                break;

            case 0:
                System.out.println("Thank You!");
                break;

            default:
                System.out.println("Invalid Choice!");
            }

        } while (choice != 0);

        sf.close();
        sc.close();
    }

    // 1. Insert Customer

    public static void insertCustomer() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Email: ");
        String email = sc.next();

        System.out.print("Enter Phone: ");
        String phone = sc.next();

        System.out.print("Enter City: ");
        String city = sc.next();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Total Amount: ");
        double totalAmount = sc.nextDouble();

        Customer c = new Customer(
                id, name, email, phone, city, age, totalAmount);

        ssn.persist(c);

        tx.commit();
        ssn.close();

        System.out.println("Customer Inserted Successfully!");
    }

    // 2. Display All Customers

    public static void displayAll() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery("from Customer", Customer.class);

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 3. Display Customer By ID

    public static void displayById() {

        Session ssn = sf.openSession();

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer c = ssn.get(Customer.class, id);

        System.out.println(c);

        ssn.close();
    }

    // 4. Update Total Amount

    public static void updateAmount() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer c = ssn.get(Customer.class, id);

        if (c != null) {

            System.out.print("Enter New Total Amount: ");
            double amount = sc.nextDouble();

            c.setTotalAmount(amount);

            System.out.println("Amount Updated Successfully!");
        } else {
            System.out.println("Customer Not Found!");
        }

        tx.commit();
        ssn.close();
    }

    // 5. Delete Customer By ID

    public static void deleteById() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer c = ssn.get(Customer.class, id);

        if (c != null) {
            ssn.remove(c);
            System.out.println("Customer Deleted Successfully!");
        } else {
            System.out.println("Customer Not Found!");
        }

        tx.commit();
        ssn.close();
    }

    // 6. Insert 10 Customers

    public static void insert10Customers() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        Customer[] arr = {

            new Customer(401, "Amit", "amit@gmail.com",
                    "9876543210", "Pune", 25, 8000),

            new Customer(402, "Raj", "raj@gmail.com",
                    "9876543211", "Mumbai", 28, 12000),

            new Customer(403, "Priya", "priya@gmail.com",
                    "9876543212", "Pune", 23, 6000),

            new Customer(404, "Sita", "sita@gmail.com",
                    "9876543213", "Nashik", 30, 4500),

            new Customer(405, "Om", "om@gmail.com",
                    "9876543214", "Pune", 26, 9000),

            new Customer(406, "Neha", "neha@gmail.com",
                    "9876543215", "Mumbai", 24, 15000),

            new Customer(407, "Ravi", "ravi@gmail.com",
                    "9876543216", "Nashik", 29, 7000),

            new Customer(408, "Pooja", "pooja@gmail.com",
                    "9876543217", "Pune", 22, 11000),

            new Customer(409, "Kiran", "kiran@gmail.com",
                    "9876543218", "Mumbai", 27, 5000),

            new Customer(410, "Sneha", "sneha@gmail.com",
                    "9876543219", "Pune", 25, 13000)
        };

        for (int i = 0; i < arr.length; i++) {
            ssn.persist(arr[i]);
        }

        tx.commit();
        ssn.close();

        System.out.println("10 Customers Inserted Successfully!");
    }

    // 7. Update Detached Customer

    public static void detachedUpdate() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Session ssn1 = sf.openSession();

        Customer c = ssn1.get(Customer.class, id);

        ssn1.close();

        if (c != null) {

            System.out.print("Enter New City: ");
            String city = sc.next();

            System.out.print("Enter New Phone: ");
            String phone = sc.next();

            c.setCity(city);
            c.setPhone(phone);

            Session ssn2 = sf.openSession();
            Transaction tx = ssn2.beginTransaction();

            ssn2.update(c);

            tx.commit();
            ssn2.close();

            System.out.println("Detached Customer Updated!");
        } else {
            System.out.println("Customer Not Found!");
        }
    }

    // 8. Delete Customers By City

    public static void deleteByCity() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        System.out.print("Enter City: ");
        String city = sc.next();

        Query q = ssn.createQuery(
                "delete from Customer where city = :city");

        q.setParameter("city", city);

        int count = q.executeUpdate();

        tx.commit();
        ssn.close();

        System.out.println(count + " Customers Deleted!");
    }

    // 9. Customers Having Total Amount > 5000

    public static void amountGreaterThan5000() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery(
                "from Customer where totalAmount > 5000",
                Customer.class);

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 10. Order By Total Amount Descending

    public static void orderByAmount() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery(
                "from Customer order by totalAmount desc",
                Customer.class);

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 11. Maximum Total Amount

    public static void maximumAmount() {

        Session ssn = sf.openSession();

        Query<Double> q = ssn.createQuery(
                "select max(totalAmount) from Customer",
                Double.class);

        Double max = q.uniqueResult();

        System.out.println("Maximum Total Amount: " + max);

        ssn.close();
    }

    // 12. Sum Of Total Amount

    public static void sumAmount() {

        Session ssn = sf.openSession();

        Query<Double> q = ssn.createQuery(
                "select sum(totalAmount) from Customer",
                Double.class);

        Double sum = q.uniqueResult();

        System.out.println("Total Amount Sum: " + sum);

        ssn.close();
    }

    // 13. Count Customers City Wise

    public static void countCityWise() {

        Session ssn = sf.openSession();

        Query<Object[]> q = ssn.createQuery(
                "select city, count(*) from Customer group by city",
                Object[].class);

        List<Object[]> list = q.list();

        for (Object[] row : list) {
            System.out.println("City: " + row[0]
                    + " Count: " + row[1]);
        }

        ssn.close();
    }

    // 14. Total Amount Between 1000 And 10000

    public static void amountBetween() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery(
                "from Customer where totalAmount between 1000 and 10000",
                Customer.class);

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 15. Customer Name Starts With A

    public static void nameStartsWithA() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery(
                "from Customer where name like 'A%'",
                Customer.class);

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 16. Total Amount Greater Than Average

    public static void amountGreaterThanAverage() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery(
                "from Customer where totalAmount > "
                + "(select avg(totalAmount) from Customer)",
                Customer.class);

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 17. Second Highest Total Amount

    public static void secondHighestAmount() {

        Session ssn = sf.openSession();

        Query<Double> q = ssn.createQuery(
                "select distinct totalAmount from Customer "
                + "order by totalAmount desc",
                Double.class);

        List<Double> list = q.list();

        if (list.size() >= 2) {
            System.out.println("Second Highest Total Amount: "
                    + list.get(1));
        } else {
            System.out.println("Second Highest Amount Not Available!");
        }

        ssn.close();
    }

    // 18. Customers From Pune, Mumbai, Nashik

    public static void customersByCities() {

        Session ssn = sf.openSession();

        Query<Customer> q = ssn.createQuery(
                "from Customer where city in (:cities)",
                Customer.class);

        q.setParameterList("cities",
                new String[]{"Pune", "Mumbai", "Nashik"});

        List<Customer> list = q.list();

        for (Customer c : list) {
            System.out.println(c);
        }

        ssn.close();
    }

    // 19. Average Total Amount City Wise

    public static void averageAmountCityWise() {

        Session ssn = sf.openSession();

        Query<Object[]> q = ssn.createQuery(
                "select city, avg(totalAmount) "
                + "from Customer group by city",
                Object[].class);

        List<Object[]> list = q.list();

        for (Object[] row : list) {
            System.out.println("City: " + row[0]
                    + " Average Amount: " + row[1]);
        }

        ssn.close();
    }

    // 20. Increase Pune Customers Total Amount By 10%

    public static void increasePuneAmount() {

        Session ssn = sf.openSession();
        Transaction tx = ssn.beginTransaction();

        Query q = ssn.createQuery(
                "update Customer set totalAmount = totalAmount * 1.10 "
                + "where city = 'Pune'");

        int count = q.executeUpdate();

        tx.commit();
        ssn.close();

        System.out.println(count
                + " Pune Customers Updated Successfully!");
    }
}
