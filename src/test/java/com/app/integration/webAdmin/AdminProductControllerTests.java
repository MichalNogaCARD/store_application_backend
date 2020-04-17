package com.app.integration.webAdmin;

import com.app.PrimaPlatformaApplication;
import com.app.model.Company;
import com.app.model.Product;
import com.app.repository.CompanyRepository;
import com.app.repository.ProductRepository;
import com.app.validators.GeneralUserInputDtoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PrimaPlatformaApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.tests.properties")
public class AdminProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CompanyRepository companyRepository;

    @SpyBean
    private GeneralUserInputDtoValidator generalUserInputDtoValidator;

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("all")
    void test10() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/products/all").contentType(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

    @Test
    @DisplayName("addCode/{id}")
    void test20() throws Exception {

        Product product = Product.builder().id(1L).build();
        Optional<Product> productOptional = Optional.of(product);

        Mockito
                .when(productRepository.findById(product.getId()))
                .thenReturn(productOptional);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/products/addCode/{id}", 1L)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userInput", "TEST_CODE")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @DisplayName("removeCode/{id}")
    void test30() throws Exception {

        Product product = Product.builder().id(1L).build();
        Optional<Product> productOptional = Optional.of(product);

        Mockito
                .when(productRepository.findById(product.getId()))
                .thenReturn(productOptional);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/admin/products/removeCode/{id}", 1L)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userInput", "TEST_CODE")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @DisplayName("hideAll/{id}")
    void test40() throws Exception {
        Company company = Company.builder().id(1L).build();
        Optional<Company> companyOptional = Optional.of(company);
        Product product = Product.builder().id(2L).hidden(false).build();

        Mockito
                .when(companyRepository.findById(company.getId()))
                .thenReturn(companyOptional);

        Mockito
                .when(productRepository.findAll())
                .thenReturn(List.of(product));
    }
}