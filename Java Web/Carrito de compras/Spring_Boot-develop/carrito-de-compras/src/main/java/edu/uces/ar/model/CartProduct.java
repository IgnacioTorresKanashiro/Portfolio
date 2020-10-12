package edu.uces.ar.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CartProduct {
	
	@EmbeddedId
    CartProductKey id;
	
	@ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    Cart cart;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;
	
	int cantidad;
	
	BigDecimal precio;
	
	public CartProduct() {
		super();
	}

	public CartProductKey getId() {
		return id;
	}

	public void setId(CartProductKey id) {
		this.id = id;
	}

	public CartProduct(CartProductKey id) {
		super();
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
	
		if (obj instanceof CartProduct) {
			
			CartProduct cartProduct = (CartProduct) obj;
			
			return ((cartProduct.id.cartId == this.id.cartId) && (cartProduct.id.productId == this.id.productId));
			
		}
		
		return false;
	}

}
