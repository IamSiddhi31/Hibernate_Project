package students;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

	@Id
	int id;

	String name;
	int age;
	String email;
	String city;
	String course;
	double marks;

	public Student() {
	}

	public Student(int id, String name, int age, String email, String city, String course, double marks) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.city = city;
		this.course = course;
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", city=" + city
				+ ", course=" + course + ", marks=" + marks + "]";
	}
}
