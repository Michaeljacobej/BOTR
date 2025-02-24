package com.sysem.BOTR.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorConstant {
    REQUEST_SUCCESS("BOTR940-00-000", "Sukses", "Success", HttpStatus.OK),

    USER_REGISTERED_FAILED("BOTR940-00-001", "User sudah terdaftar", "User have already registered", HttpStatus.BAD_REQUEST),

    INCORRECT_PASSWORD("BOTR940-00-002", "Password salah", "Incorrect password", HttpStatus.BAD_REQUEST),

    EMAIL_NOT_FOUND("BOTR940-00-003", "Email tidak ditemukan", "Email not found", HttpStatus.NOT_FOUND),

    EMAIL_REQUIRED("BOTR940-00-004", "Email wajib diisi", "Email is required", HttpStatus.BAD_REQUEST),

    PASSWORD_REQUIRED("BOTR940-00-005", "Password wajib diisi", "Password is required", HttpStatus.BAD_REQUEST),

    USER_NEED_TO_BE_REGISTERED("BOTR940-00-006", "User harus didaftarkan terlebih dahulu", "User needs to be registered", HttpStatus.BAD_REQUEST),

    INVALID_TOKEN("BOTR940-00-007", "Token tidak valid", "Token is not valid", HttpStatus.BAD_REQUEST),

    EMAIL_HAVE_BEEN_REGISTERED("BOTR940-00-008", "Email have benn registered", "Email sudah terdaftar", HttpStatus.BAD_REQUEST),

    VOTE_LIMIT_EXCEEDED("BOTR940-00-009", "Vote sudah melebihi limit.", "Vote limit exceeded for today.", HttpStatus.BAD_REQUEST);

    private String errorCode;
    private String errorMessageIndonesian;
    private String errorMessageEnglish;
    private HttpStatus httpStatus;

    ErrorConstant(String errorCode, String errorMessageIndonesian, String errorMessageEnglish, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessageIndonesian = errorMessageIndonesian;
        this.errorMessageEnglish = errorMessageEnglish;
        this.httpStatus = httpStatus;
    }
}
