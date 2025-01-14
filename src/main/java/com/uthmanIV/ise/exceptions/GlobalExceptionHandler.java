package com.uthmanIV.ise.exceptions;

import com.uthmanIV.ise.api_response.ApiError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ApiError handleAuthenticationException(AuthenticationException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ApiError handleBadRequestException(BadRequestException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ApiError handleInternalServerErrorException(InternalServerErrorException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiError handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ApiError handleInsufficientFundsException(InsufficientFundsException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ApiError handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiError handleGenericException(Exception ex) {
        return new ApiError("An unexpected error occurred.");
    }
}
