package account;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	private int id;
	private String holderName;
	private String accountNumber;
	private String accountType;
	private String bankName;
	private double balance;
	private String city;

	public Account() {
		System.out.println("New Account() is ready to build");
	}

	public Account(int id, String holderName, String accountNumber, String accountType, String bankName, double balance,
			String city) {

		this.id = id;
		this.holderName = holderName;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.bankName = bankName;
		this.balance = balance;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", holderName=" + holderName + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", bankName=" + bankName + ", balance=" + balance + ", city=" + city
				+ "]";
	}
	
	public void setBalance(double balance) {
	    this.balance = balance;
	}

	public void setCity(String city) {
	    this.city = city;
	}
}
