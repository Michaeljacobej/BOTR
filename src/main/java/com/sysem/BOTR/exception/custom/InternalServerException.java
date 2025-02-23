package com.sysem.BOTR.exception.custom;


import com.sysem.BOTR.models.dto.response.ErrorSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerException extends  RuntimeException{

    private ErrorSchema errorSchema;

    private Object outputSchema;




}
