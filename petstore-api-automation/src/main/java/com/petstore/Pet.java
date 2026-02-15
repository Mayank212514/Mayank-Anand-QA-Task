package com.petstore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Pet {
    private Long id;
    private String name;
    private String status;
    private List<String> photoUrls;

    // Empty Constructor
    public Pet() {
    }

    // Constructor with fields
    public Pet(Long id, String name, String status, List<String> photoUrls) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.photoUrls = photoUrls;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

}
