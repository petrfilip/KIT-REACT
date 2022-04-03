package cz.upce.eshop.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ProductExceptionHandler {


  @ExceptionHandler(value = {NotFoundException.class})
  public ResponseEntity<Object> handleBusinessException(NotFoundException ex) {
    log.error("Business Exception: {}", ex.getMessage());
    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

}
