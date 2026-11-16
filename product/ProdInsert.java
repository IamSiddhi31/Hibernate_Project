package product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProdInsert {

	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		System.out.println("1.cfg ready..");
		cfg.addAnnotatedClass(Product.class);
		
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println("2.factory ready..");
		
		Session ssn = sf.openSession();
		System.out.println("3.ssn ready...");
		
		Product pr = new Product(101, "Laptop", "Electronics", 55000, 1);
		
		ssn.save(pr);            //insert into emp table
		ssn.beginTransaction().commit();
		System.out.println("Product data inserted successfullyy...");
	}

}
