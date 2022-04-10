package cz.tix.upce.backend;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

  private Integer id;
  @NotNull
  private String image;
  @Size(min = 3)
  private String name;
  @Min(1)
  private BigDecimal price;

}
