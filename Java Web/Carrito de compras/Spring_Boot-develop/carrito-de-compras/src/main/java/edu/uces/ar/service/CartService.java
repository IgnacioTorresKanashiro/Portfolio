package edu.uces.ar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.uces.ar.model.CarritoProcesado;
import edu.uces.ar.model.Cart;
import edu.uces.ar.model.dto.CartDTO;
import edu.uces.ar.model.dto.CartProductDTO;
import edu.uces.ar.model.dto.ProductDTO;

@Service
public interface CartService {
	
	List<CartDTO> getAllCarts();
		
	CartDTO getCartByCartId(Long id);
		
	Long putCart(CartDTO Cart);

	void deleteById(long id);
	
	Cart getCart(long id);

	List<ProductDTO> getProductsFromCart(long id);
	
	CartDTO postCart(CartDTO cartDTO);
	
	String postProductToCart(long id, CartProductDTO cartproductDTO);
	
	String checkout(long id);
	
	void deleteProduct(long cartId, long productId);
	
	CarritoProcesado ProcessCarts();
		
}


