package cz.tix.upce.backend;

import java.math.BigDecimal;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoadProductTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void getProductSuccess() throws Exception {

		Product productEntity = new Product();
		productEntity.setImage("myImage.jpg");
		productEntity.setName("myProductName");
		productEntity.setPrice(BigDecimal.valueOf(10));
		productRepository.save(productEntity);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
						.contentType(MediaType.APPLICATION_JSON))

				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(productEntity.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.image", Is.is(productEntity.getImage())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(10.00)))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}


	@Test
	void productNotFound() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/products/10")
						.contentType(MediaType.APPLICATION_JSON))


				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}


}
