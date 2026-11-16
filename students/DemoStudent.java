package students;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DemoStudent {

	static Scanner sc = new Scanner(System.in);
	static SessionFactory sf;

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Student.class);

		System.out.println("1. cfg ready..");

		sf = cfg.buildSessionFactory();

		System.out.println("2. factory ready..");

		int choice;

		do {
			System.out.println("\n===== STUDENT HIBERNATE TASKS =====");
			System.out.println("1. Insert student using persist()");
			System.out.println("2. Display all students");
			System.out.println("3. Fetch student by ID");
			System.out.println("4. Update student marks");
			System.out.println("5. Delete student by ID");
			System.out.println("6. Insert 10 students");
			System.out.println("7. Update using session.update()");
			System.out.println("8. Delete students by course");
			System.out.println("9. Display students with marks greater than 60");
			System.out.println("10. Display students by marks descending");
			System.out.println("11. Find maximum marks");
			System.out.println("12. Find total marks");
			System.out.println("13. Course-wise student count");
			System.out.println("14. Students with marks between 40 and 90");
			System.out.println("15. Student name starts with A");
			System.out.println("16. Students with marks greater than average");
			System.out.println("17. Find second highest marks");
			System.out.println("18. Students from selected courses");
			System.out.println("19. Course-wise average marks");
			System.out.println("20. Increase marks of Java students by 10%");
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

	// TASK 1: Insert student using persist()

	static void task1() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter student ID: ");
		int id = sc.nextInt();

		System.out.print("Enter student name: ");
		String name = sc.next();

		System.out.print("Enter age: ");
		int age = sc.nextInt();

		System.out.print("Enter email: ");
		String email = sc.next();

		System.out.print("Enter city: ");
		String city = sc.next();

		System.out.print("Enter course: ");
		String course = sc.next();

		System.out.print("Enter marks: ");
		double marks = sc.nextDouble();

		Student s = new Student(id, name, age, email, city, course, marks);

		ssn.persist(s);
		tx.commit();

		System.out.println("Student inserted successfully!");

		ssn.close();
	}

	// TASK 2: Display all students

	static void task2() {

		Session ssn = sf.openSession();

		List<Student> list = ssn.createQuery("from Student", Student.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 3: Fetch student by ID

	static void task3() {

		Session ssn = sf.openSession();

		System.out.print("Enter student ID: ");
		int id = sc.nextInt();

		Student s = ssn.get(Student.class, id);

		if (s != null) {
			System.out.println(s);
		} else {
			System.out.println("Student not found!");
		}

		ssn.close();
	}

	// TASK 4: Update student marks

	static void task4() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter student ID: ");
		int id = sc.nextInt();

		Student s = ssn.get(Student.class, id);

		if (s != null) {
			System.out.print("Enter new marks: ");
			s.marks = sc.nextDouble();

			tx.commit();
			System.out.println("Student marks updated!");
		} else {
			tx.rollback();
			System.out.println("Student not found!");
		}

		ssn.close();
	}

	// TASK 5: Delete student by ID

	static void task5() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter student ID: ");
		int id = sc.nextInt();

		Student s = ssn.get(Student.class, id);

		if (s != null) {
			ssn.remove(s);
			tx.commit();
			System.out.println("Student deleted!");
		} else {
			tx.rollback();
			System.out.println("Student not found!");
		}

		ssn.close();
	}

	// TASK 6: Insert 10 students

	static void task6() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		Student[] students = new Student[10];

		students[0] = new Student(301, "Amit", 20, "amit@gmail.com", "Pune", "Java", 85);

		students[1] = new Student(302, "Raj", 21, "raj@gmail.com", "Mumbai", "Python", 78);

		students[2] = new Student(303, "Priya", 19, "priya@gmail.com", "Pune", "Java", 92);

		students[3] = new Student(304, "Sita", 22, "sita@gmail.com", "Nashik", "Python", 75);

		students[4] = new Student(305, "Om", 20, "om@gmail.com", "Pune", "SQL", 80);

		students[5] = new Student(306, "Neha", 21, "neha@gmail.com", "Mumbai", "Java", 88);

		students[6] = new Student(307, "Ravi", 23, "ravi@gmail.com", "Nashik", "SQL", 65);

		students[7] = new Student(308, "Pooja", 19, "pooja@gmail.com", "Pune", "Python", 90);

		students[8] = new Student(309, "Kiran", 22, "kiran@gmail.com", "Mumbai", "Java", 72);

		students[9] = new Student(310, "Sneha", 20, "sneha@gmail.com", "Pune", "SQL", 95);

		for (int i = 0; i < students.length; i++) {
			ssn.persist(students[i]);
		}

		tx.commit();

		System.out.println("10 students inserted!");

		ssn.close();
	}

	// TASK 7: Update using session.update()

	static void task7() {

		System.out.print("Enter student ID: ");
		int id = sc.nextInt();

		Session ssn1 = sf.openSession();

		Student s = ssn1.get(Student.class, id);

		ssn1.close();

		if (s == null) {
			System.out.println("Student not found!");
			return;
		}

		System.out.print("Enter new city: ");
		s.city = sc.next();

		System.out.print("Enter new marks: ");
		s.marks = sc.nextDouble();

		Session ssn2 = sf.openSession();
		Transaction tx = ssn2.beginTransaction();

		ssn2.update(s);

		tx.commit();

		System.out.println("Detached student updated!");

		ssn2.close();
	}

	// TASK 8: Delete students by course

	static void task8() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		System.out.print("Enter course to delete: ");
		String course = sc.next();

		Query query = ssn.createQuery("delete from Student where course = :course");

		query.setParameter("course", course);

		int count = query.executeUpdate();

		tx.commit();

		System.out.println(count + " students deleted!");

		ssn.close();
	}

	// TASK 9: Native SQL - marks greater than 60

	static void task9() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT * FROM Student WHERE marks > 60").list();

		for (Object[] row : list) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + "  ");
			}
			System.out.println();
		}

		ssn.close();
	}

	// TASK 10: Native SQL - marks descending

	static void task10() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT * FROM Student ORDER BY marks DESC").list();

		for (Object[] row : list) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + "  ");
			}
			System.out.println();
		}

		ssn.close();
	}

	// TASK 11: Maximum marks

	static void task11() {

		Session ssn = sf.openSession();

		Object maxMarks = ssn.createNativeQuery("SELECT MAX(marks) FROM Student").getSingleResult();

		System.out.println("Maximum marks: " + maxMarks);

		ssn.close();
	}

	// TASK 12: Total marks

	static void task12() {

		Session ssn = sf.openSession();

		Object totalMarks = ssn.createNativeQuery("SELECT SUM(marks) FROM Student").getSingleResult();

		System.out.println("Total marks: " + totalMarks);

		ssn.close();
	}

	// TASK 13: Course-wise student count

	static void task13() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT course, COUNT(*) FROM Student GROUP BY course").list();

		for (Object[] row : list) {
			System.out.println("Course: " + row[0] + " Count: " + row[1]);
		}

		ssn.close();
	}

	// TASK 14: Marks between 40 and 90

	static void task14() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createNativeQuery("SELECT * FROM Student WHERE marks BETWEEN 40 AND 90").list();

		for (Object[] row : list) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i] + "  ");
			}
			System.out.println();
		}

		ssn.close();
	}

	// TASK 15: Student name starts with A

	static void task15() {

		Session ssn = sf.openSession();

		List<Student> list = ssn.createQuery("from Student where name LIKE 'A%'", Student.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 16: Students with marks greater than average

	static void task16() {

		Session ssn = sf.openSession();

		List<Student> list = ssn
				.createQuery("from Student where marks > (select avg(marks) from Student)", Student.class).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 17: Second highest marks

	static void task17() {

		Session ssn = sf.openSession();

		Object secondMarks = ssn
				.createQuery("select max(marks) from Student " + "where marks < (select max(marks) from Student)")
				.uniqueResult();

		System.out.println("Second highest marks: " + secondMarks);

		ssn.close();
	}

	// TASK 18: Students from selected courses

	static void task18() {

		Session ssn = sf.openSession();

		List<Student> list = ssn.createQuery("from Student where course in (:courses)", Student.class)
				.setParameterList("courses", Arrays.asList("Java", "Python", "SQL")).list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		ssn.close();
	}

	// TASK 19: Course-wise average marks

	static void task19() {

		Session ssn = sf.openSession();

		List<Object[]> list = ssn.createQuery("select course, avg(marks) from Student " + "group by course").list();

		for (Object[] row : list) {
			System.out.println("Course: " + row[0] + " Average marks: " + row[1]);
		}

		ssn.close();
	}

	// TASK 20: Increase Java students marks by 10%

	static void task20() {

		Session ssn = sf.openSession();
		Transaction tx = ssn.beginTransaction();

		Query query = ssn.createQuery("update Student set marks = marks * 1.10 " + "where course = :course");

		query.setParameter("course", "Java");

		int count = query.executeUpdate();

		tx.commit();

		System.out.println(count + " Java students marks increased by 10%!");

		ssn.close();
	}
}
