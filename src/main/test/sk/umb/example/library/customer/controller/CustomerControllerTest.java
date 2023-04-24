package sk.umb.example.library.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import sk.umb.example.library.authentication.core.WebSecurityConfig;
import sk.umb.example.library.customer.service.CustomerDetailDto;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // NOTE: @MockBean is a spring class. Not a Mockito class
    @MockBean
    private CustomerService customerService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    @WithMockUser
    public void searchCustomer() throws Exception {
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();

        customerDetailDto.setFirstName("John");

        when(customerService.getAllCustomers())
                .thenReturn(List.of(customerDetailDto));

        var result = mockMvc.perform(
                get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].firstName",
                                    Matchers.equalTo("John")));

        verify(customerService, times(1)).getAllCustomers();
    }
}