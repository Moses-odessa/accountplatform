package ua.moses.microservices.companies.service;

import ua.moses.microservices.companies.model.Company;

import java.util.List;

public interface CompaniesService {

    List<Company> getAllCompanies(String ownerId);

    Company insertCompany(Company company);

    Company deleteCompany(Company company);

    Company updateCompany(Company company);

    Company getCompanyById(String ownerId, String companyId);
}
