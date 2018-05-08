package com.mediheroes.mediheroes.controller.api.v1;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.dto.CompanyRequest;
import com.mediheroes.mediheroes.dto.CompanyResponse;
import com.mediheroes.mediheroes.service.CompanyService;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.service.LocationService;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/companies")
@Transactional
public class CompanyController {

    private final CompanyService companyService;
    private final LocationService locationService;
    private final UserService userService;

    public CompanyController(
        CompanyService companyService,
        LocationService locationService,
        UserService userService
    ) {
        this.companyService = companyService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("")
    public ArrayList<CompanyResponse> companies() {
        var companyList = new ArrayList<CompanyResponse>();
        companyService.findAll().forEach(company -> companyList.add(new CompanyResponse(company)));
        return companyList;
    }

    @GetMapping("/{id}")
    public CompanyResponse getOneCompany(@PathVariable Long id) {
        return companyService.find(id)
                            .map(CompanyResponse::new)
                            .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("")
    public ResponseEntity<CompanyResponse> createCompany (
        @Valid @RequestBody CompanyRequest companyParams
    ){
        var company = new Company();
        company.setVerified(true);
        company.setActive(true);
        company.setEmail(companyParams.getEmail());
        company.setName(companyParams.getName());
        var user = userService.find(companyParams.getOwnerId()).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(new CompanyResponse(userService.createCompany(user, company)), HttpStatus.CREATED);
    }
}
