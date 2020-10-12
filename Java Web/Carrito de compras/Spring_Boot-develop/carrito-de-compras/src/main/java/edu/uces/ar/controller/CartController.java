package edu.uces.ar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uces.ar.model.CarritoProcesado;
import edu.uces.ar.model.dto.CartDTO;
import edu.uces.ar.model.dto.CartProductDTO;
import edu.uces.ar.model.dto.ProductDTO;
import edu.uces.ar.service.CartService;

@RestController
@Validated
public class CartController {
	
	private final CartService service;
	
	public CartController(CartService service) {
        super();
        this.service = service;
    }

	@GetMapping(path = "/carts")
	public ResponseEntity<List<CartDTO>> getCarts() {
		return new ResponseEntity<>(service.getAllCarts(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/carts/{id}")
	public ResponseEntity<CartDTO> getCart(@PathVariable long id) {
		
		return new ResponseEntity<>(service.getCartByCartId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/carts/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsFromCart(@PathVariable long id) {
		
        return new ResponseEntity<List<ProductDTO>>(service.getProductsFromCart(id), HttpStatus.OK);
    }
	
	@PostMapping(path = "/carts")
	public ResponseEntity<CartDTO> postCart(@RequestBody CartDTO cartDTO) {
	
		return new ResponseEntity<CartDTO>(service.postCart(cartDTO), HttpStatus.OK);
	}
	
	@PostMapping(path = "/carts/{id}/products")
	public ResponseEntity<Object> postProductToCart(@PathVariable long id, @RequestBody CartProductDTO cartproductDTO) {
		
		return new ResponseEntity<>(service.postProductToCart(id, cartproductDTO), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/carts/{id}/checkout")
    public ResponseEntity<Object> checkout(@PathVariable long id) { 
		
    	return new ResponseEntity<>(service.checkout(id), HttpStatus.OK);
    }
	
	@DeleteMapping(value = "/carts/{cartId}/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long cartId, @PathVariable long productId) {
		service.deleteProduct(cartId, productId);
		return new ResponseEntity<String>("El producto se borró exitosamente", HttpStatus.OK);
    }
	
	@DeleteMapping(path = "/carts/{id}")
	public void deleteCart(@PathVariable long id) {
		service.deleteById(id);
	}
	
	@PutMapping(path = "/carts/{id}")
	public ResponseEntity<Object> putCart(@Valid @RequestBody CartDTO cartDTO){
		Long id = service.putCart(cartDTO);
		return new ResponseEntity<>("Carrito updateado con exito. Id: " + id, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/batch/processCarts")
	public ResponseEntity<CarritoProcesado> processCarts() {
		return new ResponseEntity<>(service.ProcessCarts(), HttpStatus.OK);
	}
	
}
