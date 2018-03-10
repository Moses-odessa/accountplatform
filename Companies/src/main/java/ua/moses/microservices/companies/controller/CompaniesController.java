package ua.moses.microservices.companies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.moses.microservices.companies.model.Company;
import ua.moses.microservices.companies.service.CompaniesService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${companies.endpoint.url}", produces = "application/json;charset=UTF-8")
public class CompaniesController {
    @Inject
    private CompaniesService companiesService;

    @GetMapping(value = "{ownerId}")
    public ResponseEntity<List<Company>> getAllCompanies(@PathVariable String ownerId) {
        List<Company> result = companiesService.getAllCompanies(ownerId);
        if (result == null) {
            result = new ArrayList<>(0);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "{ownerId}/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String ownerId, @PathVariable String companyId) {
        Company result = companiesService.getCompanyById(ownerId, companyId);
        return ResponseEntity.ok(result);
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Company> insertCompany(@Valid @RequestBody Company company) {
        Company result = companiesService.insertCompany(company);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Company> deleteCompany(@Valid @RequestBody Company company) {
        Company result = companiesService.deleteCompany(company);
        return ResponseEntity.ok(result);
    }

    @PutMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company company) {
        Company result = companiesService.updateCompany(company);
        return ResponseEntity.ok(result);
    }

}
