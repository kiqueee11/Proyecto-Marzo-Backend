package com.profiles.app.profile_manager_app.services;

import org.springframework.stereotype.Service;

import com.profiles.app.profile_manager_app.Exceptions.UserException;
import com.profiles.app.profile_manager_app.models.UserModel;
import com.profiles.app.profile_manager_app.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;
    LanguagesServices languagesServices;

    public UserService(UserRepository userRepository, LanguagesServices languagesServices) {
        this.userRepository = userRepository;
        this.languagesServices = languagesServices;
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

    public UserModel createUser(UserModel user)  {

        try {
            if (languagesServices.areLanguagesSupported(user.getLearningLanguages()) == false|| languagesServices.areLanguagesSupported(user.getSpeakedLanguages()) == false) {
                throw new UserException("LANGUAGES_NOT_SUPPORTED:USER_ERROR");
            }

            if (isStringLengthSupported(user.getUsername(), 50) == false) {
                throw new UserException("USERNAME_TOO_LONG:USER_ERROR");
            }

            if (isStringLengthSupported(user.getEmail(), 50) == false) {
                throw new UserException("EMAIL_TOO_LONG:USER_ERROR");
            }

            if (isStringLengthSupported(user.getPassword(), 255) == false) {
                throw new UserException("PASSWORD_TOO_LONG:USER_ERROR");

            }

            if (user.getSpeakedLanguages().size() > 10) {
                throw new UserException("TOO_MANY_SPEAKED_LANGUAGES:USER_ERROR");
            }

            if (user.getLearningLanguages().size() > 10) {
                throw new UserException("TOO_MANY_LEARNING_LANGUAGES:USER_ERROR");
            }
            user = userRepository.save(user);
            return user;
        } catch (RuntimeException e) {
            throw new UserException(e.getMessage());
        }

    }

    private boolean isStringLengthSupported(String string, int maxLength) {
        return string.length() <= maxLength;
    }
}
