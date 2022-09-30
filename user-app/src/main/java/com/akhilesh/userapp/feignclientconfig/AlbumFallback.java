package com.akhilesh.userapp.feignclientconfig;

import com.akhilesh.userapp.model.dto.AlbumResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumFallback implements AlbumAppClient {
    @Override
    public List<AlbumResponse> getAlbums(String id) {
        return new ArrayList<>();
    }
}
