package product;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DemoProduct {

	static Scanner sc = new Scanner(System.in);
	static SessionFactory sf;

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Product.class);

		System.out.println("1. cfg ready..");

		sf = cfg.buildSessionFactory();

		System.out.println("2. factory ready..");

		int choice;

		do {
			System.out.println("\n===== PRODUCT HIBERNATE TASKS =====");
			System.out.println("1. Insert product using persist()");
			System.out.println("2. Display all products");
			System.out.println("3. Fetch product by ID");
			System.out.println("4. Update product price");
			System.out.println("5. Delete product by ID");
			System.out.println("6. Insert 10 products");
			System.out.println("7. Update using session.update()");
			System.out.println("8. Delete all products of a category");
			System.out.println("9. Display products with price greater than 500");
			System.out.println("10. Display products by price descending");
			System.out.println("11. Find maximum product price");
			System.out.println("12. Find total product price");
			System.out.println("13. Category-wise product count");
			System.out.println("14. Products with price between 100 and 1000");
			System.out.println("15. Product name starts with A");
			System.out.println("16. Products with price greater than average");
			System.out.println("17. Find second highest product price");
			System.out.println("18. Products from selected categories");
			System.out.println("19. Category-wise average price");
			System.out.println("20. Increase Electronics price by 10%");
			System.out.println("0. Exit");

			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				task1();
				break;
			case 2:
				task2();
				break;
			case 3:
				task3();
				break;
			case 4:
				task4();
				break;
			case 5:
				task5();
				break;
			case 6:
				task6();
				break;
			case 7:
				task7();
				break;
			case 8:
				task8();
				break;
			case 9:
				task9();
				break;
			case 10:
				task10();
				break;
			case 11:
				task11();
				break;
			case 12:
				task12();
				break;
			case 13:
				task13();
				break;
			case 14:
				task14();
				break;
			case 15:
				task15();
				break;
			case 16:
				task16();
				break;
			case 17:
				task17();
				break;
			case 18:
				task18();
				break;
			case 19:
				task19();
				break;
			case 20:
				task20();
				break;
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

	// TASK 1: Insert product using persist()

	static void task1() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter product ID: ");
		int id = sc.nextInt();

		System.out.print("Enter product name: ");
		String name = sc.next();

		System.out.print("Enter category: ");
		String category = sc.next();

		System.out.print("Enter price: ");
		double price = sc.nextDouble();

		System.out.print("Enter quantity: ");
		int quantity = sc.nextInt();

		Product p = new Product(id, name, category, price, quantity);

		ssn.persist(p);
		tx.commit();

		System.out.println("Product inserted successfully!");

		ssn.close();
	}

	// TASK 2: Display all products

	static void task2() {

		Session ssn = sf.openSession();

		List<Product> list = ssn.createQuery("from Product", Product.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 3: Fetch product by ID

	static void task3() {

		Session ssn = sf.openSession();

		System.out.print("Enter product ID: ");
		int id = sc.nextInt();

		Product p = ssn.get(Product.class, id);

		if (p != null) {
			System.out.println(p);
		} else {
			System.out.println("Product not found!");
		}

		ssn.close();
	}

	// TASK 4: Update product price

	static void task4() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter product ID: ");
		int id = sc.nextInt();

		Product p = ssn.get(Product.class, id);

		if (p != null) {
			System.out.print("Enter new price: ");
			p.price = sc.nextDouble();

			tx.commit();
			System.out.println("Product price updated!");
		} else {
			tx.rollback();
			System.out.println("Product not found!");
		}

		ssn.close();
	}

	// TASK 5: Delete product by ID

	static void task5() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter product ID: ");
		int id = sc.nextInt();

		Product p = ssn.get(Product.class, id);

		if (p != null) {
			ssn.remove(p);
			tx.commit();
			System.out.println("Product deleted!");
		} else {
			tx.rollback();
			System.out.println("Product not found!");
		}

		ssn.close();
	}

	// TASK 6: Insert 10 products

	static void task6() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		Product[] products = new Product[10];

		products[0] = new Product(201, "Laptop", "Electronics", 55000, 10);
		products[1] = new Product(202, "Mobile", "Electronics", 20000, 20);
		products[2] = new Product(203, "Keyboard", "Electronics", 800, 15);
		products[3] = new Product(204, "Mouse", "Electronics", 500, 25);
		products[4] = new Product(205, "Rice", "Grocery", 1200, 30);
		products[5] = new Product(206, "Sugar", "Grocery", 50, 40);
		products[6] = new Product(207, "Shirt", "Clothing", 700, 12);
		products[7] = new Product(208, "Jeans", "Clothing", 1500, 8);
		products[8] = new Product(209, "Headphones", "Electronics", 2500, 18);
		products[9] = new Product(210, "Shoes", "Clothing", 1800, 14);

		for (int i = 0; i < products.length; i++) {
			ssn.persist(products[i]);
		}

		tx.commit();

		System.out.println("10 products inserted!");

		ssn.close();
	}

	// TASK 7: Update using session.update()

	static void task7() {

		System.out.print("Enter product ID: ");
		int id = sc.nextInt();

		Session ssn1 = sf.openSession();

		Product p = ssn1.get(Product.class, id);

		ssn1.close();

		if (p == null) {
			System.out.println("Product not found!");
			return;
		}

		System.out.print("Enter new price: ");
		p.price = sc.nextDouble();

		System.out.print("Enter new quantity: ");
		p.quantity = sc.nextInt();

		Session ssn2 = sf.openSession();
		Transaction tx = ssn2.beginTransaction();

		ssn2.update(p);

		tx.commit();

		System.out.println("Detached product updated!");

		ssn2.close();
	}

	// TASK 8: Delete all products of a category

	static void task8() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter category to delete: ");
		String category = sc.next();

		Query query = ssn.createQuery("delete from Product where category = :cat");

		query.setParameter("cat", category);

		int count = query.executeUpdate();

		tx.commit();

		System.out.println(count + " products deleted!");

		ssn.close();
	}

	// TASK 9: Native SQL - price greater than 500

	static void task9() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT * FROM Product WHERE price > 500").list();

		for (Object[] row : list) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + "  ");
			}
			System.out.println();
		}

		ssn.close();
	}

	// TASK 10: Native SQL - price descending

	static void task10() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT * FROM Product ORDER BY price DESC").list();

		for (Object[] row : list) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + "  ");
			}
			System.out.println();
		}

		ssn.close();
	}

	// TASK 11: Maximum product price

	static void task11() {

		Session ssn = sf.openSession();

		Object maxPrice = ssn.createNativeQuery("SELECT MAX(price) FROM Product").getSingleResult();

		System.out.println("Maximum product price: " + maxPrice);

		ssn.close();
	}

	// TASK 12: Total product price

	static void task12() {

		Session ssn = sf.openSession();

		Object totalPrice = ssn.createNativeQuery("SELECT SUM(price) FROM Product").getSingleResult();

		System.out.println("Total product price: " + totalPrice);

		ssn.close();
	}

	// TASK 13: Category-wise product count

	static void task13() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT category, COUNT(*) FROM Product GROUP BY category").list();

		for (Object[] row : list) {
			System.out.println("Category: " + row[0] + " Count: " + row[1]);
		}

		ssn.close();
	}

	// TASK 14: Products with price between 100 and 1000

	static void task14() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT * FROM Product WHERE price BETWEEN 100 AND 1000").list();

		for (Object[] row : list) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + "  ");
			}
			System.out.println();
		}

		ssn.close();
	}

	// TASK 15: Product name starts with A

	static void task15() {

		Session ssn = sf.openSession();

		List<Product> list = ssn.createQuery("from Product where productName LIKE 'A%'", Product.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 16: Products with price greater than average price

	static void task16() {

		Session ssn = sf.openSession();

		List<Product> list = ssn
				.createQuery("from Product where price > (select avg(price) from Product)", Product.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 17: Second highest product price

	static void task17() {

		Session ssn = sf.openSession();

		Object secondPrice = ssn
				.createQuery("select max(price) from Product " + "where price < (select max(price) from Product)")
				.uniqueResult();

		System.out.println("Second highest product price: " + secondPrice);

		ssn.close();
	}

	// TASK 18: Products from selected categories

	static void task18() {

		Session ssn = sf.openSession();

		List<Product> list = ssn.createQuery("from Product where category in (:categories)", Product.class)
				.setParameterList("categories", Arrays.asList("Electronics", "Grocery", "Clothing")).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 19: Category-wise average price

	static void task19() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createQuery("select category, avg(price) from Product " + "group by category").list();

		for (Object[] row : list) {
			System.out.println("Category: " + row[0] + " Average price: " + row[1]);
		}

		ssn.close();
	}

	// TASK 20: Increase Electronics price by 10%

	static void task20() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		Query query = ssn.createQuery("update Product set price = price * 1.10 " + "where category = :cat");

		query.setParameter("cat", "Electronics");

		int count = query.executeUpdate();

		tx.commit();

		System.out.println(count + " Electronics products price increased by 10%!");

		ssn.close();
	}
}
