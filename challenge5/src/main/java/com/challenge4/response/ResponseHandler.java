package com.challenge4.response;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object object){
        Map<String, Object> map =new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", object);

        return new ResponseEntity<Object>(map, status);
    }
}
