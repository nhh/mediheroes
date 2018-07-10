package com.mediheroes.mediheroes.service;

import com.mediheroes.mediheroes.domain.Company;
import com.mediheroes.mediheroes.domain.user.Address;
import com.mediheroes.mediheroes.domain.user.File;
import com.mediheroes.mediheroes.domain.user.Profile;
import com.mediheroes.mediheroes.domain.user.User;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.exception.FileNotFoundException;
import com.mediheroes.mediheroes.exception.FileUploadException;
import com.mediheroes.mediheroes.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final GridFsTemplate gridFsTemplate;
    private final GridFSBucket gridFsBucket;

    public UserService(
        GridFSBucket gridFsBucket,
        UserRepository userRepositoryImpl,
        CompanyService companyServiceImpl,
        GridFsTemplate gridFsTemplate
    )
    {
        this.gridFsBucket = gridFsBucket;
        this.gridFsTemplate = gridFsTemplate;
        this.userRepository = userRepositoryImpl;
        this.companyService = companyServiceImpl;
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

    @PreAuthorize("@userPermission.isAdmin(#sender) or #sender == #user")
    public String updateProfileImage(User user, MultipartFile file, User sender) {

        var metaData = new BasicDBObject();
        metaData.put("user", user.getId());
        metaData.put("sender", sender.getId());

        var id = storeFile(file, metaData);

        var profile = user.getProfile();
        profile.setImageId(id);
        user.setProfile(profile);

        save(user);
        return id;
    }

    @PreAuthorize("@userPermission.isAdmin(#sender) or #sender == #user")
    public void addFile(User user, MultipartFile file, User sender) {

        var metaData = new BasicDBObject();
        metaData.put("user", user.getId());
        metaData.put("sender", sender.getId());

        var uploadedFile = new File(storeFile(file, metaData));
        uploadedFile.setFilename(file.getOriginalFilename());
        user.addFile(uploadedFile);

        save(user);
    }

    // TODO implement getResource from https://jira.spring.io/browse/DATAMONGO-2020
    public Optional<GridFsResource> getUploadedFileResource(String id) {

        var file = Optional
            .ofNullable(this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id))))
            .orElseThrow(FileNotFoundException::new);
        GridFSDownloadStream stream = gridFsBucket.openDownloadStream(file.getObjectId());
        var resource = new GridFsResource(file, stream);
        return Optional.of(resource);
    }

    @PreAuthorize("@userPermission.isAdmin(#sender) or #sender == #user")
    public Set<File> getFiles(User user, User sender) {
        return user.getFiles();
    }

    private String storeFile(MultipartFile file, BasicDBObject metaData) {
        try {
            return gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData).toString();
        } catch(IOException e){
            throw new FileUploadException();
        }
    }

    @PreAuthorize("@userPermission.isAdmin(#sender) or #sender == #user")
    public void updateAddress(User user, Address address, User sender) {
        user.setAddress(address);
        save(user);
    }

    public Optional<Address> getAddress(User user, User sender) {
        return Optional.ofNullable(user.getAddress());
    }
}
