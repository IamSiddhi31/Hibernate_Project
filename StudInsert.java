package students;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudInsert {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		System.out.println("1.cfg ready..");
		cfg.addAnnotatedClass(Student.class);
		
		SessionFactory sf = cfg.buildSessionFactory();
		System.out.println("2.factory ready..");
		
		Session ssn = sf.openSession();
		System.out.println("3.ssn ready...");
		
		Student stud = new Student(101, "Raj", 22, "raj@gmail.com", "Pune", "MCA", 85 );
		
		ssn.save(stud);            
		ssn.beginTransaction().commit();
		System.out.println("Student data inserted successfullyy...");
	}
	}


