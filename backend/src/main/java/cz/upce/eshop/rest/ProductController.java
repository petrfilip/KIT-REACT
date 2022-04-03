package cz.upce.eshop.rest;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin
public class ProductController {

  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/{id}")
  public Product get(@PathVariable Integer id) {
    return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
  }

  @GetMapping
  public Page<Product> getAll(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(defaultValue = "id") String sortBy,
      @RequestParam(defaultValue = "ASC") Direction sortDirection
  ) {
    PageRequest pagination = PageRequest.of(page, pageSize, sortDirection, sortBy);
    return productRepository.findAll(pagination);
  }


  @PostMapping
  public Product create(@RequestBody Product product) {
    return productRepository.save(product);
  }

  @Operation(description = "Delete product by ID")
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    productRepository.deleteById(id);
  }


}
