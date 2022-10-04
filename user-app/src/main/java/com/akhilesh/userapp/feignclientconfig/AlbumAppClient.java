package com.akhilesh.userapp.feignclientconfig;

import com.akhilesh.userapp.model.dto.AlbumResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "ALBUM-APP" /*, fallbackFactory = AlbumFallbackFactory.class*/) //Application name from which the apis are accessed
public interface AlbumAppClient {

    @GetMapping("/users/{id}/albums") //URL at which the particular api is located
    @CircuitBreaker(name = "ALBUM-APP", fallbackMethod = "getAlbumsFallback") //The fallback method name
    public List<AlbumResponse> getAlbums(@PathVariable("id") String id);

    /**
     * Fallback method
     * @param id
     * @param throwable
     * @return Fallback object
     */
    default List<AlbumResponse> getAlbumsFallback(String id, Throwable throwable) {
        System.out.println("Circuit breaker executed");
        return new ArrayList<>();
    }
}
