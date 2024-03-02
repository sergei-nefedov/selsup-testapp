package pers.nefedov.selsuptestapp.services;

import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Email;

public interface EmailService {
    Email addEmail(UserCreationDto userCreationDto);
}
