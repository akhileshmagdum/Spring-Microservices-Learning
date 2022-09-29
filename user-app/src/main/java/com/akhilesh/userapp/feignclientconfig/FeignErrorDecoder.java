package com.akhilesh.userapp.feignclientconfig;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 404:
                System.out.println(methodKey);
                System.out.println("You should probably check if the server is up!");
                break;
            default:
                System.out.println(methodKey);
                System.out.println("God knows what happened");
        }
        return new Exception();
    }
}
