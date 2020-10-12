package edu.uces.ar.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import edu.uces.ar.model.CarritoProcesado;
import edu.uces.ar.model.Cart;
import edu.uces.ar.model.CartProduct;
import edu.uces.ar.model.CartProductKey;
import edu.uces.ar.model.Product;
import edu.uces.ar.model.dto.CartDTO;
import edu.uces.ar.model.dto.CartProductDTO;
import edu.uces.ar.model.dto.ProductDTO;
import edu.uces.ar.repository.CartProductRepository;
import edu.uces.ar.repository.CartsRepository;
import edu.uces.ar.repository.ProductRepository;
import edu.uces.ar.service.CartService;
import edu.uces.ar.service.business.exception.CartNotFoundException;
import edu.uces.ar.service.business.exception.CartServiceException;
import edu.uces.ar.service.business.exception.ProductServiceException;

@Service
public class CartServiceImpl implements CartService{
	
	private final CartsRepository cartRepo;
	private final ProductRepository productRepo;
	private final CartProductRepository cartProductRepo;
	
	public CartServiceImpl(CartsRepository cartRepo, ProductRepository productRepo, CartProductRepository cartProductRepo) {
		super();
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
		this.cartProductRepo = cartProductRepo;
	}

	@Override
	public List<CartDTO> getAllCarts() {

		List<Cart> carritos = cartRepo.findAll();
		List<CartDTO> carritosDTO = new ArrayList<CartDTO>(carritos.size());
		
		for(int i = 0; i < carritos.size(); i++)
		{
			List<CartProduct> listaIntermedia = new ArrayList<CartProduct>(carritos.get(i).getProducts());
			List<ProductDTO> productos = new ArrayList<ProductDTO>();
			for (CartProduct cartProduct : listaIntermedia) {
				Product prod = cartProduct.getProduct();
				ProductDTO prodDTO = new ProductDTO();
				BeanUtils.copyProperties(prod, prodDTO);
				prodDTO.setUnitPrice(prod.getUnitPrice());
				prodDTO.setStock(cartProduct.getCantidad());
				productos.add(prodDTO);
			}
			CartDTO dto = new CartDTO();
			BeanUtils.copyProperties(carritos.get(i), dto);
			dto.setTotal(carritos.get(i).getTotal().doubleValue());
			dto.setProducts(productos);
			carritosDTO.add(dto);
		}
		
		return carritosDTO;
	}

	@Override
	public CartDTO getCartByCartId(Long id) {
		
		Optional<Cart> cart = cartRepo.findById(id);
		CartDTO dto = new CartDTO();
		
		if (cart.isPresent()) {
			BeanUtils.copyProperties(cart.get(), dto);
		} else {
			//dto.setEmail("null@gmail.com");
			//dto.setFullName("null");
			//postCart(dto);
			throw new CartNotFoundException("Cart " + id + " not found");
		}
		List<CartProduct> listaIntermedia = new ArrayList<CartProduct>(cart.get().getProducts());
		List<ProductDTO> productos = new ArrayList<ProductDTO>();
		for (CartProduct cartProduct : listaIntermedia) {
			Product prod = cartProduct.getProduct();
			ProductDTO prodDTO = new ProductDTO();
			BeanUtils.copyProperties(prod, prodDTO);
			prodDTO.setStock(cartProduct.getCantidad());
			productos.add(prodDTO);
		}	
		dto.setProducts(productos);
		dto.setTotal(cart.get().getTotal().doubleValue());
		return dto;
	}

	@Override
	public Long putCart(CartDTO cartDTO) {

		Cart cart = new Cart();
		
		BeanUtils.copyProperties(cartDTO, cart);
		cart.setTotal(BigDecimal.ZERO);
		cart = cartRepo.save(cart);
		
		return cart.getId();
	}

	@Override
	public void deleteById(long id) {
		cartRepo.deleteById(id);	
		
	}

	@Override
	public Cart getCart(long id) {
		Optional<Cart> carrito = cartRepo.findById(id);
		
		if(carrito.isPresent())
		{
			return carrito.get();
		}
		else
		{
			throw new CartServiceException("Carrito inexistente");
		}
	}
	
	@Override
	public List<ProductDTO> getProductsFromCart(long id){
		
		Optional<Cart> carrito = cartRepo.findById(id);
        
        if(carrito.isPresent())
		{
        	List<CartProduct> products = new ArrayList<CartProduct>(carrito.get().getProducts());
            List<ProductDTO> dtos = new ArrayList<>(products.size());

            for (int i = 0; i < products.size(); i++) {
                ProductDTO productDTO = new ProductDTO();
                BeanUtils.copyProperties(products.get(i).getProduct(), productDTO);
                productDTO.setUnitPrice(products.get(i).getProduct().getUnitPrice());
                productDTO.setStock(products.get(i).getCantidad());
                dtos.add(productDTO);
            }

            return dtos;
		}
        else
        {
        	throw new CartServiceException("Carrito inexistente");
        }
	}

	@Override
	public CartDTO postCart(CartDTO cartDTO) {
		if(cartDTO.getFullName()!=null && cartDTO.getFullName()!="")
		{
			if(cartDTO.getEmail()!=null && cartDTO.getEmail()!="")
			{
				Optional<Cart> carrito = cartRepo.findByEmail(cartDTO.getEmail());
				
				if(carrito.isPresent() && carrito.get().getStatus()!="PROCESSED" && carrito.get().getStatus()!="FAIL")
				{
					BeanUtils.copyProperties(carrito.get(), cartDTO);
		            return cartDTO;
				}
				
				else
				{
					cartDTO.setStatus("NEW");
					cartDTO.setTotal(0.00);
					cartDTO.setCreationDate(LocalDate.now());
					List<ProductDTO> listProducts = new ArrayList<ProductDTO>();  
					//Hashtable<Product , Integer> listProducts = new Hashtable<Product , Integer>();
					cartDTO.setProducts(listProducts);
					Long idnuevo = putCart(cartDTO);
					cartDTO.setId(idnuevo);
					return cartDTO;
				}
			}
			
			else
			{
				throw new CartServiceException("email es requerido");
			}
			
		}
		
		else
		{
			throw new CartServiceException("fullname es requerido");
		}
		
	}
	
	@Override
	public String postProductToCart(long id, CartProductDTO cartproductDTO) {

		Optional<Cart> carrito = cartRepo.findById(id);
		Optional<Product> producto = productRepo.findById(cartproductDTO.getproductId());
		
		if(cartproductDTO.getQuantity()>0)
		{
			boolean esta = false;
			
			if (producto.isPresent())
			{
				if(carrito.isPresent())
				{
					if(producto.get().getStock() >= cartproductDTO.getQuantity())
					{
						Set <CartProduct>listaProductos = carrito.get().getProducts();
						for (CartProduct cartProduct2 : listaProductos)
						{
							if(cartProduct2.getProduct().getId() == cartproductDTO.getproductId()) //Esto se fija si el producto está DENTRO del carrito
							{
								esta = true;
								if((cartproductDTO.getQuantity() + cartProduct2.getCantidad()) > producto.get().getStock())
								{
									throw new ProductServiceException("La cantidad solicitada supera al stock del producto");
								}
								cartProduct2.setCantidad(cartProduct2.getCantidad() + cartproductDTO.getQuantity());				
							}
								
						}
						
						carrito.get().setProducts(listaProductos);
						
						if(!esta) //Evito repetir la foreign key
						{
							CartProduct cp = new CartProduct();
							CartProductKey key = new CartProductKey(carrito.get().getId(),producto.get().getId());
						
							cp.setId(key);
							cp.setCantidad(cartproductDTO.getQuantity());
							cp.setCart(carrito.get());
							cp.setProduct(producto.get());
							cp.setPrecio(producto.get().getUnitPrice());
							carrito.get().getProducts().add(cp);
						}
						
						BigDecimal valor1 = carrito.get().getTotal();
						BigDecimal valor2 = (producto.get().getUnitPrice().multiply(BigDecimal.valueOf(cartproductDTO.getQuantity())));
						carrito.get().setTotal(valor1.add(valor2));
						cartRepo.save(carrito.get());
						
					}
					else 
					{
						//System.out.println("La cantidad que requiere no esta en stock");
						throw new ProductServiceException("La cantidad solicitada supera al stock del producto");
					}
					//return new ResponseEntity<>(serviceCart.getCartByCartId(id), HttpStatus.OK);
					  return"Se agrego el producto al carrito";
				}
				
				else
				{
					throw new CartServiceException("Carrito inexistente");
				}
			}
			
			else 
			{
				throw new ProductServiceException("Producto inexistente");
			}
		}
		
		else 
		{
			throw new ProductServiceException("No se puede agregar una cantidad negativa");
		}
			
	}
	
	@Override
	public String checkout(long id) {

        
        Optional<Cart> carrito = cartRepo.findById(id);
        
        if(carrito.get().getStatus().equals("NEW"))
        {
        	carrito.get().setStatus("READY");

            cartRepo.save(carrito.get());
            
    		return "Checkout realizado";
        }
        else
        {
        	throw new CartServiceException("El Checkout no puede realizarse");
        }
        
	}
	
	@Override
	public void deleteProduct(long cartId, long productId) {

        Optional<Cart> carrito = cartRepo.findById(cartId);
        if(carrito.isPresent())
		{
			boolean success = false;
			List<CartProduct> listaIntermedia = new ArrayList<CartProduct>(carrito.get().getProducts());
			//List<Product> listaProductos = new ArrayList<Product>();
			for (CartProduct cartProduct : listaIntermedia) {
				Product prod = cartProduct.getProduct();
				if(prod.getId() == productId) {
					cartProductRepo.deleteByIdCartIdAndProductId(cartId, productId);
					carrito.get().setTotal(carrito.get().getTotal().subtract(cartProduct.getPrecio().multiply(BigDecimal.valueOf(cartProduct.getCantidad()))));
					success = true;
				}				
			}	
			if(success) {
				cartRepo.save(carrito.get());
			}
			else {
				throw new CartServiceException("Product " +productId + " not found");
			}
		}
		
		else
		{
			throw new CartServiceException("Carrito inexistente");
		}
		
	}
	
	@Override
	public CarritoProcesado ProcessCarts() {
		CarritoProcesado procesados = new CarritoProcesado();
		procesados.setProfit(BigDecimal.ZERO);
		procesados.setTotalCartsFailed(0);
		procesados.setTotalCartsProcessed(0);
		Set<Product> productosSinStock = new HashSet<Product>();
		List<Cart> carritos =  cartRepo.findAll(Sort.by(Sort.Direction.ASC, "creationDate"));
		for(Cart carrito : carritos) {
			if(carrito.getStatus().equals("READY")) {
				List<CartProduct> cartProducts = new ArrayList<CartProduct>(carrito.getProducts());
				boolean success = true;
				for(CartProduct cartP : cartProducts) {
					if(cartP.getCantidad() > cartP.getProduct().getStock()) {
						success = false;
						if(cartP.getProduct().getStock() == 0) {
							productosSinStock.add(cartP.getProduct());
						}
					}
				}
				if(success) {
					for(CartProduct cartP : cartProducts) {
							cartP.getProduct().setStock(cartP.getProduct().getStock() - cartP.getCantidad());
							productRepo.save(cartP.getProduct());
					}
					procesados.setProfit(procesados.getProfit().add(carrito.getTotal()));
					carrito.setStatus("PROCESSED");
				}
				else {
					carrito.setStatus("FAILED");
					procesados.setTotalCartsFailed(procesados.getTotalCartsFailed() + 1);
				}
				procesados.setTotalCartsProcessed(procesados.getTotalCartsProcessed() + 1);
				cartRepo.save(carrito);
				System.out.println(carrito.getId() + " Esta en estado " + carrito.getStatus());
			}
		}
		procesados.setWithoutStockProducts(productosSinStock);
		return procesados;
	}
	
}