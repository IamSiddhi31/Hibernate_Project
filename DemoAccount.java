package account;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DemoAccount {

	static Scanner sc = new Scanner(System.in);
	static SessionFactory sf;

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Account.class);

		sf = cfg.buildSessionFactory();

		int choice;

		do {
			System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");

			System.out.println("1. Insert Account");
			System.out.println("2. Display All Accounts");
			System.out.println("3. Display Account By ID");
			System.out.println("4. Update Balance");
			System.out.println("5. Delete Account By ID");
			System.out.println("6. Insert 10 Accounts");
			System.out.println("7. Update Detached Account");
			System.out.println("8. Delete Accounts By City");
			System.out.println("9. Display Accounts Having Balance > 5000");
			System.out.println("10. Display Accounts Order By Balance");
			System.out.println("11. Find Maximum Balance");
			System.out.println("12. Find Sum Of Balance");
			System.out.println("13. Count Accounts Bank Wise");
			System.out.println("14. Balance Between 1000 And 100000");
			System.out.println("15. Account Holder Name Starts With A");
			System.out.println("16. Balance Greater Than Average");
			System.out.println("17. Find Second Highest Balance");
			System.out.println("18. Accounts From Pune, Mumbai, Nashik");
			System.out.println("19. Average Balance Bank Wise");
			System.out.println("20. Increase Pune Accounts Balance By 10%");
			System.out.println("0. Exit");

			System.out.print("\nEnter Your Choice: ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				insertAccount();
				break;

			case 2:
				displayAll();
				break;

			case 3:
				displayById();
				break;

			case 4:
				updateBalance();
				break;

			case 5:
				deleteById();
				break;

			case 6:
				insert10Accounts();
				break;

			case 7:
				detachedUpdate();
				break;

			case 8:
				deleteByCity();
				break;

			case 9:
				balanceGreaterThan5000();
				break;

			case 10:
				orderByBalance();
				break;

			case 11:
				maximumBalance();
				break;

			case 12:
				sumBalance();
				break;

			case 13:
				countBankWise();
				break;

			case 14:
				balanceBetween();
				break;

			case 15:
				nameStartsWithA();
				break;

			case 16:
				balanceGreaterThanAverage();
				break;

			case 17:
				secondHighestBalance();
				break;

			case 18:
				accountsByCities();
				break;

			case 19:
				averageBalanceBankWise();
				break;

			case 20:
				increasePuneBalance();
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

	// 1. Insert Account

	public static void insertAccount() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter ID: ");
		int id = sc.nextInt();

		System.out.print("Enter Holder Name: ");
		String name = sc.next();

		System.out.print("Enter Account Number: ");
		String accNo = sc.next();

		System.out.print("Enter Account Type: ");
		String type = sc.next();

		System.out.print("Enter Bank Name: ");
		String bank = sc.next();

		System.out.print("Enter Balance: ");
		double balance = sc.nextDouble();

		System.out.print("Enter City: ");
		String city = sc.next();

		Account a = new Account(id, name, accNo, type, bank, balance, city);

		ssn.persist(a);

		tx.commit();
		ssn.close();

		System.out.println("Account Inserted Successfully!");
	}

	// 2. Display All Accounts

	public static void displayAll() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account", Account.class);

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 3. Display Account By ID

	public static void displayById() {

		Session ssn = sf.openSession();

		System.out.print("Enter Account ID: ");
		int id = sc.nextInt();

		Account a = ssn.get(Account.class, id);

		System.out.println(a);

		ssn.close();
	}

	// 4. Update Balance

	public static void updateBalance() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter Account ID: ");
		int id = sc.nextInt();

		Account a = ssn.get(Account.class, id);

		if (a != null) {

			System.out.print("Enter New Balance: ");
			double balance = sc.nextDouble();

			a.setBalance(balance);

			System.out.println("Balance Updated Successfully!");

		} else {
			System.out.println("Account Not Found!");
		}

		tx.commit();
		ssn.close();
	}

	// 5. Delete Account By ID

	public static void deleteById() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter Account ID: ");
		int id = sc.nextInt();

		Account a = ssn.get(Account.class, id);

		if (a != null) {
			ssn.remove(a);
			System.out.println("Account Deleted Successfully!");
		} else {
			System.out.println("Account Not Found!");
		}

		tx.commit();
		ssn.close();
	}

	// 6. Insert 10 Accounts

	public static void insert10Accounts() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		Account[] arr = {

				new Account(501, "Amit", "10001", "Savings", "SBI", 25000, "Pune"),

				new Account(502, "Raj", "10002", "Current", "HDFC", 50000, "Mumbai"),

				new Account(503, "Priya", "10003", "Savings", "ICICI", 35000, "Pune"),

				new Account(504, "Sita", "10004", "Savings", "SBI", 15000, "Nashik"),

				new Account(505, "Om", "10005", "Current", "HDFC", 60000, "Pune"),

				new Account(506, "Neha", "10006", "Savings", "ICICI", 45000, "Mumbai"),

				new Account(507, "Ravi", "10007", "Current", "SBI", 70000, "Nashik"),

				new Account(508, "Pooja", "10008", "Savings", "HDFC", 30000, "Pune"),

				new Account(509, "Kiran", "10009", "Current", "ICICI", 80000, "Mumbai"),

				new Account(510, "Sneha", "10010", "Savings", "SBI", 55000, "Pune") };

		for (int i = 0; i < arr.length; i++) {
			ssn.persist(arr[i]);
		}

		tx.commit();
		ssn.close();

		System.out.println("10 Accounts Inserted Successfully!");
	}

	// 7. Update Detached Account

	public static void detachedUpdate() {

		System.out.print("Enter Account ID: ");
		int id = sc.nextInt();

		Session ssn1 = sf.openSession();

		Account a = ssn1.get(Account.class, id);

		ssn1.close();

		if (a != null) {

			System.out.print("Enter New City: ");
			String city = sc.next();

			System.out.print("Enter New Balance: ");
			double balance = sc.nextDouble();

			a.setCity(city);
			a.setBalance(balance);

			Session ssn2 = sf.openSession();
			Transaction tx = ssn2.beginTransaction();

			ssn2.update(a);

			tx.commit();
			ssn2.close();

			System.out.println("Detached Account Updated!");

		} else {
			System.out.println("Account Not Found!");
		}
	}

	// 8. Delete Accounts By City

	public static void deleteByCity() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter City: ");
		String city = sc.next();

		Query q = ssn.createQuery("delete from Account where city = :city");

		q.setParameter("city", city);

		int count = q.executeUpdate();

		tx.commit();
		ssn.close();

		System.out.println(count + " Accounts Deleted!");
	}

	// 9. Balance Greater Than 5000

	public static void balanceGreaterThan5000() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account where balance > 5000", Account.class);

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 10. Order By Balance Descending

	public static void orderByBalance() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account order by balance desc", Account.class);

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 11. Maximum Balance

	public static void maximumBalance() {

		Session ssn = sf.openSession();

		Query<Double> q = ssn.createQuery("select max(balance) from Account", Double.class);

		Double max = q.uniqueResult();

		System.out.println("Maximum Balance: " + max);

		ssn.close();
	}

	// 12. Sum Of Balance

	public static void sumBalance() {

		Session ssn = sf.openSession();

		Query<Double> q = ssn.createQuery("select sum(balance) from Account", Double.class);

		Double sum = q.uniqueResult();

		System.out.println("Total Balance: " + sum);

		ssn.close();
	}

	// 13. Count Accounts Bank Wise

	public static void countBankWise() {

		Session ssn = sf.openSession();

		Query<Object[]> q = ssn.createQuery("select bankName, count(*) " + "from Account group by bankName",
				Object[].class);

		List<Object[]> list = q.list();

		for (Object[] row : list) {

			System.out.println("Bank: " + row[0] + " Count: " + row[1]);
		}

		ssn.close();
	}

	// 14. Balance Between 1000 And 100000

	public static void balanceBetween() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account where balance between 1000 and 100000", Account.class);

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 15. Account Holder Name Starts With A

	public static void nameStartsWithA() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account where holderName like 'A%'", Account.class);

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 16. Balance Greater Than Average

	public static void balanceGreaterThanAverage() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account where balance > " + "(select avg(balance) from Account)",
				Account.class);

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 17. Second Highest Balance

	public static void secondHighestBalance() {

		Session ssn = sf.openSession();

		Query<Double> q = ssn.createQuery("select distinct balance from Account " + "order by balance desc",
				Double.class);

		List<Double> list = q.list();

		if (list.size() >= 2) {

			System.out.println("Second Highest Balance: " + list.get(1));

		} else {
			System.out.println("Second Highest Balance Not Available!");
		}

		ssn.close();
	}

	// 18. Accounts From Pune, Mumbai, Nashik

	public static void accountsByCities() {

		Session ssn = sf.openSession();

		Query<Account> q = ssn.createQuery("from Account where city in (:cities)", Account.class);

		q.setParameterList("cities", new String[] { "Pune", "Mumbai", "Nashik" });

		List<Account> list = q.list();

		for (Account a : list) {
			System.out.println(a);
		}

		ssn.close();
	}

	// 19. Average Balance Bank Wise

	public static void averageBalanceBankWise() {

		Session ssn = sf.openSession();

		Query<Object[]> q = ssn.createQuery("select bankName, avg(balance) " + "from Account group by bankName",
				Object[].class);

		List<Object[]> list = q.list();

		for (Object[] row : list) {

			System.out.println("Bank: " + row[0] + " Average Balance: " + row[1]);
		}

		ssn.close();
	}

	// 20. Increase Pune Accounts Balance By 10%

	public static void increasePuneBalance() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		Query q = ssn.createQuery("update Account set balance = balance * 1.10 " + "where city = 'Pune'");

		int count = q.executeUpdate();

		tx.commit();
		ssn.close();

		System.out.println(count + " Pune Accounts Updated Successfully!");
	}
}
