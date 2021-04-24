package com.nebula.wisys.web.errorhandler;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.mongodb.MongoException;
import com.nebula.wisys.web.response.ApiResponse;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    final static Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

        // wrap exception body in ApiResponse body
        ApiResponse apiResponse = new ApiResponse(status, null, null);
        if (body == null) {
            String errorMessage = String.format("%s for %s - %s", ex.getClass().getName(), request.toString(), ex.getLocalizedMessage());
            apiResponse.setMessage(errorMessage);
        } else if (body instanceof ApiResponse) {
            apiResponse = (ApiResponse) body;
        } else if (body instanceof String) {
            apiResponse.setMessage((String) body);
        } else {
            apiResponse.setMessage(body.toString());
        }

        logger.error("[API Exception] " + apiResponse.getMessage());
		return new ResponseEntity<>(apiResponse, headers, status);
	}


    // HttpStatus.NOT_FOUND 404
    @Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}


	// HttpStatus.BAD_REQUEST 400
    @Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(
			HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(
			MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleServletRequestBindingException(
			ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}


	// HttpStatus.METHOD_NOT_ALLOWED 405
    @Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}

        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(String.format("%s for %s - %s", ex.getClass().getName(), request.toString(), ex.getLocalizedMessage()));
        errorMessageBuilder.append(" <Allow");
        supportedMethods.forEach(method -> errorMessageBuilder.append(" " + method));
        errorMessageBuilder.append(">");

		return handleExceptionInternal(ex, errorMessageBuilder.toString(), headers, status, request);
	}


	// HttpStatus.NOT_ACCEPTABLE 406
    @Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}


	// HttpStatus.UNSUPPORTED_MEDIA_TYPE 415
    @Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if (!CollectionUtils.isEmpty(mediaTypes)) {
			headers.setAccept(mediaTypes);
		}

        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(String.format("%s for %s - %s", ex.getClass().getName(), request.toString(), ex.getLocalizedMessage()));
        errorMessageBuilder.append(" <Allow");
        mediaTypes.forEach(media -> errorMessageBuilder.append(" " + media));
        errorMessageBuilder.append(">");

		return handleExceptionInternal(ex, errorMessageBuilder.toString(), headers, status, request);
	}


	// HttpStatus.INTERNAL_SERVER_ERROR 500
    @Override
	protected ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    @Override
	protected ResponseEntity<Object> handleConversionNotSupported(
			ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, null, headers, status, request);
	}

    
	// HttpStatus.SERVICE_UNAVAILABLE 503
    @Override
	@Nullable
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
			AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

		if (webRequest instanceof ServletWebRequest) {
			ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
			HttpServletResponse response = servletWebRequest.getResponse();
			if (response != null && response.isCommitted()) {
				if (logger.isWarnEnabled()) {
					logger.warn("Async request timed out");
				}
				return null;
			}
		}

		return handleExceptionInternal(ex, null, headers, status, webRequest);
	}


    // MongoDB exception handler - HttpStatus.INTERNAL_SERVER_ERROR 500
    @ExceptionHandler({ MongoException.class })
    public ResponseEntity<Object> handleMongoException(final Exception ex, final WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String errorMessage = String.format("%s for %s - %s", ex.getClass().getName(), request.toString(), ex.getLocalizedMessage());

        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }


    // Default exception handler - HttpStatus.INTERNAL_SERVER_ERROR 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleDefaultException(final Exception ex, final WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        String errorMessage = String.format("%s for %s - %s", ex.getClass().getName(), request.toString(), ex.getLocalizedMessage());

        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }
}
