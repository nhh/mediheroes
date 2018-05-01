package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository companyRepositoryImpl) {
        repository = companyRepositoryImpl;
    }

    public Optional<Company> find(long id) {
        return repository.findById(id);
    }

    public Iterable<Company> findAll(){
        return repository.findAll();
    }

    public Company save(Company company) {
        return repository.save(company);
    }
}
