package edu.uces.ar.model.dto;

import javax.validation.constraints.Min;

public class CartProductDTO {

	private Long productId;
	@Min(1)
	private Integer quantity;
	
	public CartProductDTO() {
		super();
	}

	public Long getproductId() {
		return productId;
	}

	public void setproductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
