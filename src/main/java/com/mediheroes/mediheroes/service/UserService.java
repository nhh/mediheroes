package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.User;
import com.mediheroes.mediheroes.dto.CompanyRequest;
import com.mediheroes.mediheroes.exception.EntityAlreadyExistsException;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.permission.UserPermission;
import com.mediheroes.mediheroes.repository.CompanyRepository;
import com.mediheroes.mediheroes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyService companyService;

    public UserService(
        UserRepository userRepositoryImpl,
        CompanyService companyServiceImpl)
    {
        userRepository = userRepositoryImpl;
        companyService = companyServiceImpl;
    }

    public User getCurrentUser(){
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByEmail(email);
    }

    public Optional<User> find(Long id){
        return this.userRepository.findById(id);
    }

    public User findByEmail(String email){
        return this.userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @PreAuthorize("@userPermission.canUpdateCompany(#updatedCompany)")
    public Company updateCompany(Company updatedCompany){
        var user = getCurrentUser();

        if(user.getCompany() == null) {
            throw new EntityNotFoundException();
        }

        companyService.save(updatedCompany);

        return updatedCompany;
    }

    @Transactional
    @PreAuthorize("@userPermission.canAddCompany(#user, #newCompany)")
    public Company createCompany(User user, Company newCompany) {
        user.setCompany(newCompany);
        userRepository.save(user);
        return newCompany;
    }

}
