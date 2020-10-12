package edu.uces.ar.service.business.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.uces.ar.CarritoDeComprasApplication;

@ControllerAdvice
public class CartServiceErrorAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CarritoDeComprasApplication.class);
	
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    @ExceptionHandler({CartNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(CartNotFoundException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }
    @ExceptionHandler({CartServiceException.class})
    public ResponseEntity<String> handleDogsServiceException(CartServiceException e){
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }
    private ResponseEntity<String> error(HttpStatus status, Exception e) {
    	logger.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}