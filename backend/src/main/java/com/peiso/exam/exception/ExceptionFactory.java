package com.peiso.exam.exception;

public class ExceptionFactory {

    private ExceptionFactory() {
    }

    public static ApiError getApiError(ExceptionEnum exceptionEnum, String... params) {
        return new ApiError(exceptionEnum.getErrorCode(), exceptionEnum.getHttpStatus(), params);
    }

    public static ApiError getApiError(ExceptionEnum exceptionEnum) {
        return new ApiError(exceptionEnum.getErrorCode(), exceptionEnum.getHttpStatus());
    }

}
