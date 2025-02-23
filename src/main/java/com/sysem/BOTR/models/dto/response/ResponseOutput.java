package com.sysem.BOTR.models.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseOutput {

    private ErrorSchema errorSchema;
    private Object outputSchema;

    @JsonIgnore
    private JsonObject jsonObject;

    public ResponseOutput(ErrorSchema errorSchema , Object outputSchema) {
        this.errorSchema = errorSchema;
        this.outputSchema = outputSchema;
    }
    public ResponseOutput(ErrorSchema errorSchema , JsonObject outputSchema) {
        this.errorSchema = errorSchema;
        this.outputSchema = outputSchema;
    }
}
