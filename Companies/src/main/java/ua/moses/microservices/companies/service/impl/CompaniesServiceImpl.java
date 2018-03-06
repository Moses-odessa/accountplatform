package ua.moses.microservices.companies.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ua.moses.microservices.companies.model.Company;
import ua.moses.microservices.companies.repository.CompaniesRepository;
import ua.moses.microservices.companies.service.CompaniesService;

import javax.inject.Inject;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CompaniesServiceImpl implements CompaniesService {
    @Inject
    private CompaniesRepository companiesRepository;

    @Override
    public List<Company> getAllCompanies(String ownerId) {
        return companiesRepository.getAllCompanies(ownerId);
    }

    @Override
    public Company insertCompany(Company company) {
        return companiesRepository.insert(company);
    }

    @Override
    public Company deleteCompany(Company company) {
        if (companiesRepository.exists(company.getId())) {
            company.setDeleted(true);
            companiesRepository.save(company);
            return company;
        } else {
            return null;
        }
    }

    @Override
    public Company updateCompany(Company company) {
        if (companiesRepository.exists(company.getId())) {
            return companiesRepository.save(company);
        } else {
            return null;
        }
    }

    @Override
    public Company getCompanyById(String ownerId, String companyId) {
        return companiesRepository.findByOwnerIdAndId(ownerId, companyId);
    }
}
