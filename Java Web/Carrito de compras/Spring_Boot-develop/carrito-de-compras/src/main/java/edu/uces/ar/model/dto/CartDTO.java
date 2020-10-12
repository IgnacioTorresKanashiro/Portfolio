package edu.uces.ar.model.dto;

import java.time.LocalDate;
import java.util.List;

public class CartDTO {

	private Long id;
	private String fullName;
	private String email;
	private LocalDate creationDate;
	private List<ProductDTO> products;
	private double total;
	private String status;
	
	public CartDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> listProducts) {
		this.products = listProducts;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double d) {
		this.total = d;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
