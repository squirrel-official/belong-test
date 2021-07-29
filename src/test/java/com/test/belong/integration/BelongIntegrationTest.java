package com.test.belong.integration;

import com.test.belong.BelongApplication;
import com.test.belong.exception.CustomerNotFoundException;
import com.test.belong.exception.InvalidPhoneNumberException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BelongApplication.class)
@AutoConfigureMockMvc
public class BelongIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test_all_phone_numbers() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/phone-number/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "  \"+919868825750\",\n" +
                        "  \"+919868825751\",\n" +
                        "  \"+61469366771\",\n" +
                        "  \"+61469366778\",\n" +
                        "  \"+61469366782\",\n" +
                        "  \"+61469366785\",\n" +
                        "  \"+61469365790\"\n" +
                        "]"));

    }


    @Test
    public void activate_phone_number_test() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/phone-number/activate/+61469365790")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void user_phone_numbers_test() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/1/phone-numbers")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[\"+919868825750\",\"+919868825751\",\"+61469366771\"]"));
    }

    @Test
    public void activate_phone_number_test_error() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/phone-number/activate/61469365790")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Invalid format/structure of phone number entered."))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidPhoneNumberException));
    }

    @Test
    public void user_phone_numbers_test_error() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/7/phone-numbers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("The customer is not found."))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomerNotFoundException));
    }
}
