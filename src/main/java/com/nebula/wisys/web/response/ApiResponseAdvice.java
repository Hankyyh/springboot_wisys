package com.nebula.wisys.web.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

	@Nullable
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, 
    Class<? extends HttpMessageConverter<?>> selectedConverterType, 
    ServerHttpRequest request, ServerHttpResponse response) {
        Object apiResponse;
        HttpStatus httpResponseStatus = HttpStatus.valueOf(((ServletServerHttpResponse) response).getServletResponse().getStatus());
        if (!(body instanceof ApiResponse) || body == null) {
            // [api response] construct unified struct
            // status: reuse http response status code
            // message: reuse http response status description
            // payload: input body
            apiResponse = new ApiResponse(httpResponseStatus, httpResponseStatus.getReasonPhrase(), body);
        } else {
            // override http response status code if status code in api response body is in unified struct
            apiResponse = body;
            int apiResponseStatusCode = ((ApiResponse)apiResponse).getStatus();
            if (apiResponseStatusCode > 0) {
                response.setStatusCode(HttpStatus.valueOf(apiResponseStatusCode));
            }
        }
        return apiResponse;
    }
    
}
