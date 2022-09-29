package com.akhilesh.userapp.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumResponse {
    private String name;
    private String description;
}
