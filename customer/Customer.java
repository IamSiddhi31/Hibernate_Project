package customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	int id;
	String name;
	String email;
	String phone;
	String city;
	int age;
	public double totalAmount;

	public Customer() {
	}

	public Customer(int id, String name, String email, String phone, String city, int age, double totalAmount) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.age = age;
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", city=" + city
				+ ", age=" + age + ", totalAmount=" + totalAmount + "]";
	}

	public void setTotalAmount(double totalAmount) {
	    this.totalAmount = totalAmount;
		
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(String phone) {
		this.phone = phone;

	}
}
