// package com.example.minishop;
//
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
// import org.junit.jupiter.api.Test;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
// @SpringBootTest
// @AutoConfigureMockMvc
// class ProductControllerTest {
//
// @Autowired
// private MockMvc mockMvc;
//
// @Test
// public void testGetAll() throws Exception {
// mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
// .andExpect(status().isOk())
// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
// .andExpect(jsonPath("$[0].id").value(1))
// .andExpect(jsonPath("$[0].name").value("Product 1"))
// .andExpect(jsonPath("$[0].description").value("Description 1"))
// .andExpect(jsonPath("$[0].price").value(10.0))
// .andExpect(jsonPath("$[1].id").value(2))
// .andExpect(jsonPath("$[1].name").value("Product 2"))
// .andExpect(jsonPath("$[1].description").value("Description 2"))
// .andExpect(jsonPath("$[1].price").value(20.0));
// }
// }
