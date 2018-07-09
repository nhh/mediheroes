package com.mediheroes.mediheroes.domain.user;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String fileId;

    @Column
    private String filename;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public File(String fileId) {
        this.fileId = fileId;
    }

    public File(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String documentId) {
        this.fileId = documentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) &&
            Objects.equals(fileId, file.fileId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fileId);
    }
}
