package com.upgrad.quora.service.business;

import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SignUpBusinessService {

    @Autowired
    UserAdminService userAdminService;

    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signUp(UserEntity userEntity) throws SignUpRestrictedException {
        return userAdminService.createUser(userEntity);
    }


}
