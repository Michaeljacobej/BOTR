package com.sysem.BOTR.models.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorSchema {

    private String errorCode;
    private ErrorMessage errorMessage;

    public ErrorSchema(String errorCode,ErrorMessage errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
