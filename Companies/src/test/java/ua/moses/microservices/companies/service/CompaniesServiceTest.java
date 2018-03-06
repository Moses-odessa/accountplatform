package ua.moses.microservices.companies.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.moses.microservices.companies.model.Company;
import ua.moses.microservices.companies.repository.CompaniesRepository;
import ua.moses.microservices.companies.service.impl.CompaniesServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CompaniesServiceTest {

    private CompaniesService companiesService;

    @MockBean
    private CompaniesRepository companiesRepository;

    @Before
    public void init() {
        companiesService = new CompaniesServiceImpl(companiesRepository);
    }

    @Test
    public void getAllCompanysTest() {
        String ownerId = "owner";
        List<Company> expectedList = Arrays.asList(new Company(ownerId,"company1"), new Company(ownerId, "company2"));
        when(companiesRepository.getAllCompanies(ownerId)).thenReturn(expectedList);
        List<Company> result = companiesService.getAllCompanies(ownerId);
        assertEquals(expectedList, result);
    }

    @Test
    public void getCompanyByIdTest() {
        String companyId = "5a981eaba3e33c120c2c67bf";
        String ownerId = "owner";
        Company expected = new Company(companyId, ownerId, "company1", false);
        when(companiesRepository.findByOwnerIdAndId(ownerId, companyId)).thenReturn(expected);
        Company result = companiesService.getCompanyById(ownerId, companyId);
        assertEquals(expected, result);
    }

    @Test
    public void insertCompanyTest() {
        Company expected = new Company("owner","company1");
        when(companiesRepository.insert(expected)).thenReturn(expected);
        Company result = companiesService.insertCompany(expected);
        assertEquals(expected, result);
    }


    @Test
    public void deleteCompanyTest() {
        Company given = new Company("owner","company1");
        Company expected = new Company("owner","company1");
        expected.setDeleted(true);
        when(companiesRepository.exists(given.getId())).thenReturn(true);
        Company result = companiesService.deleteCompany(given);
        assertEquals(expected, result);
    }

    @Test
    public void updateCompanyTest() {
        Company expected = new Company("owner","company1");
        expected.setDeleted(true);
        when(companiesRepository.exists(expected.getId())).thenReturn(true);
        when(companiesRepository.save(expected)).thenReturn(expected);
        Company result = companiesService.updateCompany(expected);
        assertEquals(expected, result);
    }

}
