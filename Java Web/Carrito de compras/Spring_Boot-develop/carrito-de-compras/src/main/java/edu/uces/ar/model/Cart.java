package edu.uces.ar.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {

	private @Id @GeneratedValue Long id;
	private String fullName;
	private String email;
	private LocalDate creationDate;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CartProduct> products;
	private BigDecimal total;
	private String status;
	
	public Cart() {
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<CartProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<CartProduct> products) {
		this.products = products;
	}
	
	public boolean BorrarProducto(CartProduct prod){
		return this.products.remove(prod);
	}
	
	
}