package com.upgrad.quora.service.dao;


import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {

    //Constants
    private static final String USER_BY_EMAIL = "userByEmail";
    private static final String USER_AUTH_TOKEN_BY_ACCESS_TOKEN = "userAuthTokenByAccessToken";
    private static final String USER_BY_UUID = "userByUuid";
    private static final String USER_BY_USER_NAME = "userByUserName";
    private static final String DELETE_AUTH_TOKEN_BY_UUID = "deleteAuthTokenByUuid";
    private static final String CHECK_AUTH_TOKEN = "checkAuthToken";

    @PersistenceContext
    private EntityManager entityManager;


    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    public UserEntity checkUserName(final String username) {
        try {
            return entityManager.createNamedQuery(USER_BY_USER_NAME, UserEntity.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }



    public UserEntity checkEmailid(String emailid) {
        try {
            return entityManager.createNamedQuery(USER_BY_EMAIL, UserEntity.class).setParameter("email", emailid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }


    public UserAuthEntity getUserAuthToken(final String authorizationToken) {
        try {
            return entityManager
                    .createNamedQuery(USER_AUTH_TOKEN_BY_ACCESS_TOKEN, UserAuthEntity.class).setParameter("accessToken", authorizationToken)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }


    public UserEntity getUser(String userUUid) {
        try {
            return entityManager.createNamedQuery(USER_BY_UUID, UserEntity.class).setParameter("uuid", userUUid).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public UserEntity getUserByUserName(String username) {
        try {
            return entityManager.createNamedQuery(USER_BY_USER_NAME, UserEntity.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public UserAuthEntity createAuthToken(final UserAuthEntity userAuthTokenEntity) {
        entityManager.persist(userAuthTokenEntity);
        return userAuthTokenEntity;
    }

    public UserAuthEntity getUserByAccessToken(String accessToken) {
        try {
            return entityManager.createNamedQuery(USER_AUTH_TOKEN_BY_ACCESS_TOKEN, UserAuthEntity.class).setParameter("accessToken", accessToken)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void updateLogOutDate(UserAuthEntity userAuthEntity) {
        entityManager.persist(userAuthEntity);
    }

    public void deleteUser(UserEntity user) {
        entityManager.remove(user);
    }

    public void deleteExistingAuthDetailsForUser(String uuid) {
        try {
            entityManager.createNamedQuery(DELETE_AUTH_TOKEN_BY_UUID, UserAuthEntity.class).setParameter("uuid", uuid);
        } catch (Exception e) {
            return;
        }
    }

    public UserAuthEntity checkAuthToken(final String authorizationToken) {
        try {

            return entityManager.createNamedQuery(CHECK_AUTH_TOKEN, UserAuthEntity.class).setParameter("accessToken", authorizationToken)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
