package com.mediheroes.mediheroes.repository;

import com.mediheroes.mediheroes.domain.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LocationRepository extends CrudRepository<Location, Long> {

    ArrayList<Location> findALLByCompanyId(Long companyId);
}
