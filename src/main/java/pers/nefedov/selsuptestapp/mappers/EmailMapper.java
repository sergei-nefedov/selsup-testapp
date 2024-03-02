package pers.nefedov.selsuptestapp.mappers;

import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Email;

public interface EmailMapper {
    Email mapToEmail(UserCreationDto userCreationDto);
}

