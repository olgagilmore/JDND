package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.test.web.servlet.ResultMatcher;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(PricingController.class)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	PricingService pricingService;

	@Test
	public void contextLoads() {
	}

	/**
	 * Creates pre-requisites for testing, such as an example car.
	 */
	/*@Before
	public void setup() throws PriceException {
		Price price = getPrice();
		given(pricingService.getPrice(1L)).willReturn(price);
		given(pricingService.getPrice(100L)).willThrow(PriceException.class);
	}*/


	@Test
	public void getPricefromService() throws Exception {
		mockMvc.perform(get("/services/price").param("vehicleId","11"))
				.andExpect(status().isOk());

		//verify(pricingService, times(1)).getPrice((long) 11);
	}

	/**
	 * Tests for finding price for a given vehicleId
	 *
	 * @throws Exception when car creation fails in the system
	 */
	@Test
	public void testPriceNotFoundException() throws Exception {
		Price price = getPrice();
		mockMvc.perform(get("/services/price/?vehicleId=100"))  //.param("vehicleId", "100"))
				.andExpect(status().isNotFound());
				//.andDo(print())
				//.andExpect(content().string("Price Not Found"));
		//verify(pricingService, times(1)).getPrice(100L);
	}

	/**
	 * Creates an example Price object for use in testing.
	 *
	 * @return an example Price object
	*/
	private Price getPrice() {
		return new Price("USD", new BigDecimal(20000), 1L);
	}
}
