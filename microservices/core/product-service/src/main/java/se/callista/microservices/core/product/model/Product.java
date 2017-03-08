package se.callista.microservices.core.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	private String id;

	private String productName;
	private String description;
	private int quantity;
	private int price;

	public Product() {

	}

	public Product(String id, String productName, String description, int quantity, int price) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	

	public Product(String id, String productName, String description) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
