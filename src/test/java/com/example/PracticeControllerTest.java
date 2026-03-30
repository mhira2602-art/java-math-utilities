package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PracticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeShouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }

    @Test
    void calculatorPostShouldReturnResult() throws Exception {
        mockMvc.perform(post("/calculator")
                .param("a", "10")
                .param("b", "5")
                .param("operation", "add"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("result", 15.0))
            .andExpect(model().attribute("operation", "add"));
    }

    @Test
    void calculatorPostShouldShowErrorWhenServiceThrows() throws Exception {
        mockMvc.perform(post("/calculator")
                .param("a", "10")
                .param("b", "5")
                .param("operation", "mod"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("error", "Unknown operation selected."));
    }

    @Test
    void factorialPostShouldReturnResult() throws Exception {
        mockMvc.perform(post("/factorial")
                .param("number", "5"))
            .andExpect(status().isOk())
            .andExpect(view().name("factorial"))
            .andExpect(model().attribute("result", 120L));
    }

    @Test
    void primePostShouldReturnResult() throws Exception {
        mockMvc.perform(post("/prime")
                .param("number", "13"))
            .andExpect(status().isOk())
            .andExpect(view().name("prime"))
            .andExpect(model().attribute("result", true));
    }

    @Test
    void arrayPostShouldReturnParsedValuesAndResult() throws Exception {
        mockMvc.perform(post("/array")
                .param("numbers", "2,4,6")
                .param("operation", "sum"))
            .andExpect(status().isOk())
            .andExpect(view().name("array"))
            .andExpect(model().attribute("parsed", "[2, 4, 6]"))
            .andExpect(model().attribute("result", 12));
    }

    @Test
    void arrayPostShouldShowErrorWhenServiceThrows() throws Exception {
        mockMvc.perform(post("/array")
                .param("numbers", "")
                .param("operation", "sum"))
            .andExpect(status().isOk())
            .andExpect(view().name("array"))
            .andExpect(model().attribute("error", "Please enter at least one number."));
    }
}
