package cz.tix.upce.backend;

public class ConversionService {


  public static ProductDto toProductDto(Product product) {
    ProductDto productDto = new ProductDto();
    productDto.setId(product.getId());
    productDto.setName(product.getName());
    productDto.setImage(product.getImage());
    productDto.setPrice(product.getPrice());
    return productDto;
  }

  public static Product toProduct(ProductDto product) {
    Product productEntity = new Product();
    productEntity.setId(product.getId());
    productEntity.setName(product.getName());
    productEntity.setImage(product.getImage());
    productEntity.setPrice(product.getPrice());
    return productEntity;
  }

}
