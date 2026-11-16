package employee_HW;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmpInsert {
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		System.out.println("1.cfg ready..");
		cfg.addAnnotatedClass(Employeee.class);
		
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println("2.factory ready..");
		
		Session ssn = sf.openSession();
		System.out.println("3.ssn ready...");
		
		Employeee emp = new Employeee(2, "Sapna", "Dev", 70000,"Devloper");
		
		ssn.save(emp);            //insert into emp table
		ssn.beginTransaction().commit();
		System.out.println("Emp data inserted successfullyy...");
	}
}



