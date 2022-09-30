package com.akhilesh.userapp.feignclientconfig;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AlbumFallbackFactory implements FallbackFactory<AlbumAppClient> {

    @Override
    public AlbumAppClient create(Throwable cause) {
        return new AlbumFactory(cause);
    }
}
