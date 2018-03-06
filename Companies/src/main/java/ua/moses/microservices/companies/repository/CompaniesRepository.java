package ua.moses.microservices.companies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.moses.microservices.companies.model.Company;

import java.util.List;

public interface CompaniesRepository extends MongoRepository<Company, String> {
    @Query("{'$and':[{'ownerId': ?0},{'deleted': false}]}")
    List<Company> getAllCompanies(String ownerId);

    Company findByOwnerIdAndId(String ownerId, String warehousesId);

}
