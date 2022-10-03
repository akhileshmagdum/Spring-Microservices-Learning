/*package com.akhilesh.userapp.feignclientconfig;

import com.akhilesh.userapp.model.dto.AlbumResponse;

import java.util.ArrayList;
import java.util.List;

public class AlbumFactory implements AlbumAppClient {

    private final Throwable throwable;
    public AlbumFactory(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public List<AlbumResponse> getAlbums(String id) {
        System.out.println(throwable.getLocalizedMessage());
        return new ArrayList<>();
    }
}*/
