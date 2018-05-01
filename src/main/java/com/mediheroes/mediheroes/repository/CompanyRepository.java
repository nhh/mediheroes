package com.mediheroes.mediheroes.repository;

import com.mediheroes.mediheroes.domain.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
