package com.example.project;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ProjectApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;



	String kategorie="{ \"nazwa\":\"Laptopy\"}";

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext,
					  RestDocumentationContextProvider restDocumentation) {

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();

//		kategorieList=Stream.of(new KategorieTest("Laptopy"))
//				.collect(Collectors.toList());
	}

	@Test
	@Order(2)
	public void getCategories() throws Exception {
		mockMvc.perform(get("/kategorie")
				.contentType("application/json")).andDo(print())
				.andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(kategorieList)))
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}
	@Test
	@Order(4)
	public void updateCategory() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.put("/kategorie/{id}", 1)
				.content("{\"nazwa\":\"Komputery stacjonarne\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nazwa").value("Komputery stacjonarne"))
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));;
	}
	@Test
	@Order(3)
	public void getCategoryById() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders
				.get("/kategorie/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));;
	}
	@Test
	@Order(1)
	public void addCategory() throws Exception {
//		String ordersJson=new ObjectMapper().writeValueAsString(kategorieList);
		mockMvc.perform(MockMvcRequestBuilders
				.post("/kategorie")
				.content(kategorie)
				.contentType(MediaType.APPLICATION_JSON))  //.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}

	@Test
	@Order(5)
	public void deleteCategory() throws Exception
	{
		mockMvc.perform( MockMvcRequestBuilders.delete("/kategorie/{id}", 1) )
				.andExpect(status().isOk())
				.andDo(document("{methodName}",
						preprocessRequest(prettyPrint()),
						preprocessResponse(prettyPrint())));
	}
}