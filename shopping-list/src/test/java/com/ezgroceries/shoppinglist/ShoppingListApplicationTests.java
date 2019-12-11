package com.ezgroceries.shoppinglist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.ezgroceries.ShoppingListApplication;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.activation.MimeType;
import java.util.regex.Matcher;

@SpringBootTest(classes=ShoppingListApplication.class)
class ShoppingListApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	void contextLoads() {
	}

	@Test
	void basicShoppingListControllerTest() throws Exception {

		String inputJson = "{" +
				"\"name\": \"Stephanie's birthday\"" +
        		"}";
		mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails","12345")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].uuid", Matchers.is("23b3d85a-3928-41c0-a533-6538a71e17c4")));
	}

	@Test
	void  basicCreateShoppingListTest() throws Exception{
		String inputJson = "{" +
				"\"name\": \"blabla\"" +
				"}";

		mockMvc.perform(post("/shopping-lists")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.shoppingListId", Matchers.is("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915")));
	}

	@Test
	void basticGetShoppingListTest() throws Exception {
		mockMvc.perform(get("/shopping-lists/{shoppingListId}", "1234"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name", Matchers.is("Stephanie's birthday")));
	}

	@Test
	void basicCocktailSearchTest() throws  Exception {

		mockMvc.perform(get("/cocktails?search=Russian"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
				.andExpect(jsonPath("$[0].name", Matchers.is("Black Russian")))
				.andExpect(jsonPath("$[1].name", Matchers.is("White Russian")));
	}

}
