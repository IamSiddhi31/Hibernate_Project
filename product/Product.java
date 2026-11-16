package product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	int id;

	String productName;
	String category;
	double price;
	int quantity;

	public Product() {
	}

	public Product(int id, String productName, String category, double price, int quantity) {
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", category=" + category + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
}
