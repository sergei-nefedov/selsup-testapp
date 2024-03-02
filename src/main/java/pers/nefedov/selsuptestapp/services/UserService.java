package pers.nefedov.selsuptestapp.services;

import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.User;

import java.util.List;

public interface UserService {
    UserCreationDto addUser(UserCreationDto userCreationDto);
    User getUserByLogin(String login);

    List<String> addPhoneNumber(String phoneNumber);
}


