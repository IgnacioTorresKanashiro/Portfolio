package edu.uces.ar.model;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CarritoProcesado {
	
	public int getTotalCartsProcessed() {
		return totalCartsProcessed;
	}
	public void setTotalCartsProcessed(int totalCartsProcessed) {
		this.totalCartsProcessed = totalCartsProcessed;
	}
	public int getTotalCartsFailed() {
		return totalCartsFailed;
	}
	public void setTotalCartsFailed(int totalCartsFailed) {
		this.totalCartsFailed = totalCartsFailed;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public Set<Product> getWithoutStockProducts() {
		return withoutStockProducts;
	}
	public void setWithoutStockProducts(Set<Product> products) {
		this.withoutStockProducts = products;
	}
	
	private @Id @GeneratedValue Long id;
	private int totalCartsProcessed;
	private int totalCartsFailed;
	private BigDecimal profit;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Product> withoutStockProducts;
}
