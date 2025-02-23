package com.sysem.BOTR.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorConstant {
    REQUEST_SUCCESS("BOTR940-00-000", "Sukses", "Success", HttpStatus.OK),

    USER_REGISTERED_FAILED("BOTR940-00-001", "user sudah terdaftar", "user have already registered", HttpStatus.BAD_REQUEST),

    USER_PASSWORD_NOT_MATCHED("BOTR940-00-002", "user dan password tidak cocok", "user and password is not matched", HttpStatus.BAD_REQUEST),

    USER_NEED_TO_BE_REGISTERED("BOTR940-00-003", "user harus didaftarkan terlebih dahulu", "user need to be registered", HttpStatus.BAD_REQUEST),

    INVALID_TOKEN("BOTR940-00-003", "token tidak valid", "token is not valid", HttpStatus.BAD_REQUEST);


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
