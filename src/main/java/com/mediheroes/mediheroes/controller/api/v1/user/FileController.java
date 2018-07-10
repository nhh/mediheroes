package com.mediheroes.mediheroes.controller.api.v1.user;

import com.mediheroes.mediheroes.dto.DocumentResponse;
import com.mediheroes.mediheroes.exception.EntityNotFoundException;
import com.mediheroes.mediheroes.exception.FileNotFoundException;
import com.mediheroes.mediheroes.service.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/v1/users")
@RestController
@Transactional
public class FileController {

    private final UserService userService;

    public FileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}/files")
    public ResponseEntity uploadFile(
        @PathVariable Long id,
        @RequestBody MultipartFile file
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        userService.addFile(user, file, user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/files")
    public ResponseEntity<DocumentResponse[]> getDocuments(@PathVariable Long id){
        var sender = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var user = userService
            .find(id)
            .orElseThrow(EntityNotFoundException::new);

        var response = userService.getFiles(user, sender)
            .stream()
            .map(DocumentResponse::new)
            .toArray(DocumentResponse[]::new);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/files/{id}")
    public ResponseEntity getUploadedFile(
        @PathVariable Long userId,
        @PathVariable String id
    ){
        var user = userService
            .getCurrentUser()
            .orElseThrow(EntityNotFoundException::new);

        var resource = userService
            .getUploadedFileResource(id)
            .orElseThrow(FileNotFoundException::new);

        try {
            return ResponseEntity
                .ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.asMediaType(MimeType.valueOf(resource.getContentType())))
                .body(new InputStreamResource(resource.getInputStream()));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
