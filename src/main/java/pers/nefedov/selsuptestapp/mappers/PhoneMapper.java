package pers.nefedov.selsuptestapp.mappers;

import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Phone;

public interface PhoneMapper {
    Phone mapToPhone(UserCreationDto userCreationDto);
}
