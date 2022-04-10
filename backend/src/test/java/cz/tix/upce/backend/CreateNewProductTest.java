package cz.tix.upce.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class CreateNewProductTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createNewProductSuccess() throws Exception {

		ProductDto productDto = new ProductDto();
		productDto.setImage("myImage.jpg");
		productDto.setName("myProductName");
		productDto.setPrice(BigDecimal.valueOf(10));
		String testData = objectMapper.writeValueAsString(productDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/products")
						.content(testData)
						.contentType(MediaType.APPLICATION_JSON))

				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is(productDto.getName())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.image", Is.is(productDto.getImage())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price", Is.is(10)));

	}

	@Test
	void createNewProductFailedBecauseInvalidImageName() throws Exception {

		ProductDto productDto = new ProductDto();
		productDto.setImage(null);
		productDto.setName("myProductName");
		productDto.setPrice(BigDecimal.valueOf(10));
		String s = objectMapper.writeValueAsString(productDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/products")
						.content(s)
						.contentType(MediaType.APPLICATION_JSON))

				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}



}
