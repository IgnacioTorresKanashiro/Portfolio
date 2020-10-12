package edu.uces.ar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartProductKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "cart_id")
    Long cartId;
 
    @Column(name = "product_id")
    Long productId;

	public CartProductKey() {
		super();
	}

	public CartProductKey(Long cartId, Long productId) {
		super();
		this.cartId = cartId;
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	

}
