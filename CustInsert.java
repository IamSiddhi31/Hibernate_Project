package customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CustInsert {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		System.out.println("1.cfg ready..");
		cfg.addAnnotatedClass(Customer.class);
		
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println("2.factory ready..");
		
		Session ssn = sf.openSession();
		System.out.println("3.ssn ready...");
		
		Customer cust = new Customer(101, "Raj", "raj@gmail.com", "9532645120","Pune", 26, 850 );
		
		ssn.save(cust);            
		ssn.beginTransaction().commit();
		System.out.println("Customer data inserted successfullyy...");
	}

}
