package pers.nefedov.selsuptestapp.services;

import pers.nefedov.selsuptestapp.dto.RegisteredUserDto;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Email;
import pers.nefedov.selsuptestapp.models.User;

import java.util.List;

public interface EmailService {
    Email addEmail(UserCreationDto userCreationDto);

    List<String> deleteEmail(User user, String email);

    List<String> addEmail(User user, String email);

    User findUserByEmail(String email);
}

