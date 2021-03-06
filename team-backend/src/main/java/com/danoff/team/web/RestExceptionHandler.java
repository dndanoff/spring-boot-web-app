package com.danoff.team.web;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.danoff.common.dto.error.ApiError;
import com.danoff.common.dto.error.ValidationError;
import com.danoff.common.web.BaseRestExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends BaseRestExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {

		logger.info(ex.getClass().getName());
		//
		
		final ValidationError validationError = new ValidationError();
        for (final FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            final String localizedErrorMessage = fieldError.getDefaultMessage();
            validationError.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
        	validationError.addFieldError(error.getObjectName(), error.getDefaultMessage());
		}
        
        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
        
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, validationError);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		logger.info(ex.getClass().getName());
		//
		final String error = ex.getRequestPartName() + " part is missing";
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {

		logger.info(ex.getClass().getName());
		//
		final String error = ex.getParameterName() + " parameter is missing";
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	//
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {

		logger.info(ex.getClass().getName());
		//
		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, message, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 405
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		
		logger.info(ex.getClass().getName());
		//
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, message,
				builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	// 415
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		
		logger.info(ex.getClass().getName());
		//
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message,
				builder.substring(0, builder.length() - 2));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 500
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		logger.info(ex.getClass().getName());
		logger.error("error", ex);
		//
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getLocalizedMessage();
		final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}