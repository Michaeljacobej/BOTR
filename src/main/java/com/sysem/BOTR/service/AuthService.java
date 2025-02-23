package com.sysem.BOTR.service;

import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Users;

public interface AuthService {
    ResponseOutput loginUser( Users users) throws Exception;

    ResponseOutput registerUser(Users users) throws Exception;

    ResponseOutput logoutUser(String token)  throws Exception;

}
