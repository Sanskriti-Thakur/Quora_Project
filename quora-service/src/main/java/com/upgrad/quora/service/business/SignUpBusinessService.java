package com.upgrad.quora.service.business;

import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class used for user creation.
 */
@Service
public class SignUpBusinessService {

    @Autowired
    UserAdminService userAdminService;


    /**
     * Method used to add a user.
     * @throws SignUpRestrictedException exception thrown in case username of email id are same.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public UserEntity signUp(UserEntity userEntity) throws SignUpRestrictedException {
        return userAdminService.createUser(userEntity);
    }


}
