package com.nebula.wisys.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    
    @JsonProperty("status")
    private HttpStatus status;
    
    @JsonProperty("message") @JsonInclude(Include.NON_NULL) 
    private String message;

    @JsonProperty("payload") @JsonInclude(Include.NON_NULL)
    private Object payload;


    public ApiResponse(HttpStatus status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public int getStatus() {
        if (status == null) {
            return 0;
        }
        return status.value();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
