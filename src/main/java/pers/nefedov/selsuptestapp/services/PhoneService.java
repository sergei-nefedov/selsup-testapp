package pers.nefedov.selsuptestapp.services;

import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Phone;
import pers.nefedov.selsuptestapp.models.User;

import java.util.List;

public interface PhoneService {
    Phone addPhone(UserCreationDto userCreationDto);

    List<String> addPhoneNumber(User user, String phoneNumber);

    List<String> deletePhoneNumber(User user, String phoneNumber);

    User findUserByPhone(String phoneNumber);
}


