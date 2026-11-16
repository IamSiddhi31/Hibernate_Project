package account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class AccInsert {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		System.out.println("1.cfg ready..");
		cfg.addAnnotatedClass(Account.class);
		
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println("2.factory ready..");
		
		Session ssn = sf.openSession();
		System.out.println("3.ssn ready...");
		
		Account acc = new Account(101, "Raj", "22653", "Saving", "SBI", 55000,"Pune" );
		
		ssn.save(acc);            
		ssn.beginTransaction().commit();
		System.out.println("Account data inserted successfullyy...");
	}

}
