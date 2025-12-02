package com.management.userManagement.exception;

import com.management.userManagement.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ================================================================
 *  GlobalExceptionHandler
 * ================================================================
 *  This class provides centralized exception handling across
 *  all controllers in the application.
 * ================================================================
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    /**
     * Handles cases where a duplicate resource is being created.
     * Example: Trying to insert an email with a name that already exists
     * (violates a unique constraint).
     *
     * HTTP Status: 409 CONFLICT
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse> handleDuplicateResource(DuplicateResourceException ex) {
        logger.error("Duplicate resource: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse(409, ex.getMessage(), null));
    }

}
