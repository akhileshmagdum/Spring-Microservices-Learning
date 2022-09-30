package com.akhilesh.userapp.feignclientconfig;

import com.akhilesh.userapp.model.dto.AlbumResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ALBUM-APP", fallbackFactory = AlbumFallbackFactory.class) //Application name from which the apis are accessed
public interface AlbumAppClient {

    @GetMapping("/users/{id}/albums") //URL at which the particular api is located
    public List<AlbumResponse> getAlbums(@PathVariable("id") String id);
}
