package employee_HW;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employeee {

	@Id
	int id;
	String name;
	String role;
	double salary;
	String department;

	public Employeee() {
		System.out.println("New Employee() is ready to build");

	}

	public Employeee(int id, String name, String role, double salary, String department) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.salary = salary;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employeee [id=" + id + ", name=" + name + ", role=" + role + ", salary=" + salary + ", department="
				+ department + "]";
	}

}
