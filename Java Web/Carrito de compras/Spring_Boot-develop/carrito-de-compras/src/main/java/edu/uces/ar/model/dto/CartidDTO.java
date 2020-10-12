package edu.uces.ar.model.dto;

import java.time.LocalDate;
import java.util.List;

public class CartidDTO {
	
	private Long id;
	private String fullName;
	private String email;
	private LocalDate creationDate;
	private List<ProductidDTO> products;
	private double total;
	private String status;
	
	
	public CartidDTO() {
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
	public List<ProductidDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductidDTO> products) {
		this.products = products;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
