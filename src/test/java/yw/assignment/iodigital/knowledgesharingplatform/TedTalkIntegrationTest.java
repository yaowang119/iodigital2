package yw.assignment.iodigital.knowledgesharingplatform;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import yw.assignment.iodigital.ioplatform.TedTalkApplication;
import yw.assignment.iodigital.ioplatform.model.TedTalk;
import yw.assignment.iodigital.ioplatform.service.TedTalkService;

@SpringBootTest
@ContextConfiguration(classes = TedTalkApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class TedTalkIntegrationTest {
		
		 private MockMvc mockMvc;
		 
		  @Autowired
		  private WebApplicationContext applicationContext;
		  
		  @Autowired
		  private TedTalkService service;
		 
		 @BeforeEach
		  void setup() {
		    this.mockMvc = MockMvcBuilders
		            .webAppContextSetup(applicationContext)
		            .build();
		  }
		 
		@Test
		@Order(1)
		public void createNewTedTalkReturnsHttpStatusCreated() throws Exception {
			TedTalk tt=new TedTalk();
			tt.setAuthor("Test Author");
			tt.setTitle("Test Title");
			tt.setDate("April 2022");
			tt.setViews(new BigInteger("45678"));
			tt.setLikes(new BigInteger("12345"));
			tt.setLink("test_link");
			this.mockMvc.perform(MockMvcRequestBuilders
				      .post("/tedtalk")
				      .content(asJsonString(tt))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isCreated())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		}
		
		public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		}
		
		@Test
		@Order(2)
		public void updateTedTalkReturnsHttpStatusOK() throws Exception {
			TedTalk tt=service.findTedTalkById(1);
			tt.setAuthor("Test Author");			
			this.mockMvc.perform(MockMvcRequestBuilders
				      .put("/tedtalk/{id}", 1)
				      .content(asJsonString(tt))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().isOk())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Test Author"));
		}
		
		@Test
		@Order(3)
		public void deleteTedTalkReturnsHttpStatusNoContent() throws Exception{
			 this.mockMvc.perform( MockMvcRequestBuilders.delete("/tedtalk/{id}", 1) )
		        .andExpect(status().isNoContent());
		}
		
		@Test
		@Order(4)
		public void getTedTalkByTitleReturnsHttpStatusOK() throws Exception{
			 this.mockMvc.perform(get("/tedtalks/title")
				      .param("title", "dive"))			 
		     		  .andExpect(status().isOk())
		     		  .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		     		  .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", containsStringIgnoringCase("dive")));
		           
		}
		
		@Test
		@Order(5)
		public void getTedTalkByAuthorReturnsHttpStatusOK() throws Exception{
			 this.mockMvc.perform(get("/tedtalks/author")
				      .param("author", "daniel"))			 
		     		  .andExpect(status().isOk())
		     		  .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		     		  .andExpect(MockMvcResultMatchers.jsonPath("$[0].author", containsStringIgnoringCase("daniel")));
		           
		}
		
		@Test
		@Order(5)
		public void getTedTalkByViewsReturnsHttpStatusOK() throws Exception{
			 this.mockMvc.perform(get("/tedtalks/views")
				      .param("views", "404000"))			 
		     		  .andExpect(status().isOk())
		     		  .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		     		  .andExpect(MockMvcResultMatchers.jsonPath("$[0].views").value("404000"));
		           
		}
		
		@Test
		@Order(5)
		public void getTedTalkByLikesReturnsHttpStatusOK() throws Exception{
			 this.mockMvc.perform(get("/tedtalks/likes")
				      .param("likes", "12000"))			 
		     		  .andExpect(status().isOk())
		     		  .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
		     		  .andExpect(MockMvcResultMatchers.jsonPath("$[0].likes").value("12000"));
		           
		}
		
}
