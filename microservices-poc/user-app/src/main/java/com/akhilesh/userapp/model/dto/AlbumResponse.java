package com.akhilesh.userapp.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlbumResponse {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
