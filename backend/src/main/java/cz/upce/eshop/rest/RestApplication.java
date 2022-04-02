package cz.upce.eshop.rest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RestApplication {

  public static void main(String[] args) {
    SpringApplication.run(RestApplication.class, args);
  }

  @RestController
  @CrossOrigin
  class Endpoint {

    private static final String IMAGE_URL = "https://fakeimg.pl/200x200/ff0000%2C128/000%2C255/";

    @GetMapping("/api/products/{id}")
    @ResponseBody
    public Product get(@PathVariable Integer id) {
      return new Product(id, "My product", IMAGE_URL, BigDecimal.valueOf(10));
    }

    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> getAll() {
      return Arrays.asList(
          new Product(1, "LG", IMAGE_URL, BigDecimal.valueOf(5)),
          new Product(2, "Samsung", IMAGE_URL, BigDecimal.valueOf(10.5))
      );
    }
  }

  @Data
  @AllArgsConstructor
  class Product {

    private Integer id;
    private String name;
    private String image;
    private BigDecimal price;

  }
}
