package com.profiles.app.profile_manager_app.services;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.models.UserModel;
import com.profiles.app.profile_manager_app.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user
     * 
     * @param UserModel
     * @throws UserException
     * @throws IllegalArgumentException
     * @throws OptimisticLockingFailureException
     * @returns UserModel
     * 
     **/

    public UserModel createUser(UserModel user) {

        try {

            if (isStringLengthSupported(user.getUsername(), 50) == false) {
                throw new UserException("USERNAME_TOO_LONG:USER_ERROR");
            }

            if (isStringLengthSupported(user.getEmail(), 50) == false) {
                throw new UserException("EMAIL_TOO_LONG:USER_ERROR");
            }

            if (user.getImage1() == null || user.getImage1().trim().isEmpty()) {
                throw new UserException("AT_LEAST_ONE_IMAGE_SHOULD_BE_ADDED:USER_ERROR");
            }

            user.setUserId(generateId(15));

            user = userRepository.save(user);
            return user;
        } catch (RuntimeException e) {
            throw new UserException(e.getMessage());
        }

    }

    private boolean isStringLengthSupported(String string, int maxLength) {
        return string.length() <= maxLength;
    }



    /**
     * Find a user by its email.
     * @param email The email of the user to find.
     * @return The user with the given email, or null if no such user exists.
     */



    public Optional<UserModel> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * Generates an unique id with letters and numbers
     * @param length the length of the UID
     * @return An String UID 
     */
    private String generateId(int length){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();

    }

}
