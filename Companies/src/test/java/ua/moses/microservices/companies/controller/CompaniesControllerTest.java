package ua.moses.microservices.companies.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.moses.microservices.companies.model.Company;
import ua.moses.microservices.companies.service.CompaniesService;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CompaniesController.class)
public class CompaniesControllerTest {
    @Value("${companies.endpoint.url}")
    private String companiesEndpointUrl;

    @MockBean
    private CompaniesService companiesService;

    @Inject
    private MockMvc mockMvc;

    public static String convertToJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllCompaniesTest() throws Exception {
        String ownerId = "owner";
        List<Company> expectedList = Arrays.asList(new Company(ownerId, "stock1"), new Company(ownerId, "stock2"));
        when(companiesService.getAllCompanies(ownerId)).thenReturn(expectedList);

        String result = mockMvc.perform(get(companiesEndpointUrl + "/" + ownerId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThatJson(result).when(Option.IGNORING_ARRAY_ORDER).isEqualTo(expectedList);
        verify(companiesService).getAllCompanies(ownerId);
        verifyNoMoreInteractions(companiesService);

    }

    @Test
    public void getCompanyByIdTest() throws Exception {
        String companyId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Company expected = new Company(companyId, ownerId, "stock1", false);
        when(companiesService.getCompanyById(ownerId, companyId)).thenReturn(expected);

        mockMvc.perform(get(companiesEndpointUrl + "/owner/" + companyId)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));
        verify(companiesService).getCompanyById(ownerId, companyId);
        verifyNoMoreInteractions(companiesService);

    }

    @Test
    public void insertCompanyTest() throws Exception {
        Company expected = new Company("owner", "stock1");
        when(companiesService.insertCompany(any(Company.class))).thenReturn(expected);

        mockMvc.perform(post(companiesEndpointUrl)
                .content(convertToJsonString(expected))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(companiesService).insertCompany(any(Company.class));
        verifyNoMoreInteractions(companiesService);
    }

    @Test
    public void deleteCompanyTest() throws Exception {
        Company expected = new Company("owner", "stock1");
        when(companiesService.deleteCompany(any(Company.class))).thenReturn(expected);

        mockMvc.perform(delete(companiesEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(companiesService).deleteCompany(any(Company.class));
        verifyNoMoreInteractions(companiesService);
    }

    @Test
    public void updateCompanyTest() throws Exception {
        Company expected = new Company("owner", "stock1");
        when(companiesService.updateCompany(any(Company.class))).thenReturn(expected);

        mockMvc.perform(put(companiesEndpointUrl)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertToJsonString(expected)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().string(convertToJsonString(expected)));

        verify(companiesService).updateCompany(any(Company.class));
        verifyNoMoreInteractions(companiesService);
    }

}
