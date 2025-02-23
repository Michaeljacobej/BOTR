package com.sysem.BOTR.util;

import com.sysem.BOTR.constant.ErrorConstant;
import com.sysem.BOTR.models.dto.response.ErrorMessage;
import com.sysem.BOTR.models.dto.response.ErrorSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HelperResponseOutput {
    private static final Logger logger = LogManager.getLogger(HelperResponseOutput.class);
    public ErrorSchema errorSchema(ErrorConstant errorConstants){
        ErrorMessage errorMessage = new ErrorMessage(errorConstants.getErrorMessageEnglish(),errorConstants.getErrorMessageIndonesian());
        ErrorSchema errorSchema = new ErrorSchema(errorConstants.getErrorCode(),errorMessage);
        return errorSchema;
    }





}