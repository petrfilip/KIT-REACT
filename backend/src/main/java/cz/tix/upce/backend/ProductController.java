package cz.tix.upce.backend;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("")
  public List<ProductDto> getAllProduct(
      @RequestParam(defaultValue = "0") Integer pageNumber,
      @RequestParam(defaultValue = "2") Integer pageSize,
      @RequestParam(defaultValue = "id") String sortBy
  ) {

    return productService.findAll(pageNumber, pageSize, sortBy).stream().map(ConversionService::toProductDto).collect(Collectors.toList());
  }


  @GetMapping("/{id}")
  public ProductDto getProductById(@PathVariable Integer id) {
    Product byId = productService.findById(id);
    return ConversionService.toProductDto(byId);
  }

  @PostMapping("")
  public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {

    Product productToProcess = ConversionService.toProduct(productDto);
    Product save = productService.save(productToProcess);
    return ConversionService.toProductDto(save);
  }

  @PutMapping("/{id}")
  public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {

    Product product = ConversionService.toProduct(productDto);
    Product save = productService.update(id, product);
    return ConversionService.toProductDto(save);

  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Integer id) {
    productService.deleteById(id);
  }

}
