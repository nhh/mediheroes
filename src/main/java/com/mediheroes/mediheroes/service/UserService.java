package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.user.Profile;
import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
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

    public Optional<User> getCurrentUser(){
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByEmail(email);
    }

    public Optional<User> find(Long id){
        return this.userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return this.userRepository.findByProfile_Email(email);
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("@userPermission.canUpdateCompany(#updatedCompany)")
    public Company updateCompany(Company updatedCompany, User user){

        if(user.getCompany() == null) {
            throw new EntityNotFoundException();
        }

        companyService.save(updatedCompany);

        return updatedCompany;
    }

    @PreAuthorize("@userPermission.canAddCompany(#user, #newCompany)")
    public Company createCompany(User user, Company newCompany) {
        user.setCompany(newCompany);
        userRepository.save(user);
        return newCompany;
    }

    public long countUsers() {
        return this.userRepository.count();
    }


    @PreAuthorize("@userPermission.isAdmin(#sender) or #sender == #user")
    public void updateProfile(User user, Profile profile, User sender) {
        user.setProfile(profile);
        save(user);
    }
}
