package pers.nefedov.selsuptestapp.services;

import pers.nefedov.selsuptestapp.dto.RegisteredUserDto;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.User;

import java.util.List;

public interface UserService {
    UserCreationDto addUser(UserCreationDto userCreationDto);
    User getUserByLogin(String login);

    List<String> addPhoneNumber(String phoneNumber);

    List<String> deletePhoneNumber(String phoneNumber);

    List<String> addEmail(String email);

    List<String> deleteEmail(String email);

    RegisteredUserDto searchByEmail(String email);

    RegisteredUserDto searchByPhone(String phoneNumber);
}





