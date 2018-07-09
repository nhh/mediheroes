package com.mediheroes.mediheroes.dto;

import com.mediheroes.mediheroes.domain.user.File;

public class DocumentResponse {

    private final Long id;
    private final String fileId;
    private final String filename;

    public DocumentResponse(File file){
        this.id = file.getId();
        this.fileId = file.getFileId();
        this.filename = file.getFilename();
    }

    public Long getId() {
        return id;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFilename(){
        return filename;
    }

}
