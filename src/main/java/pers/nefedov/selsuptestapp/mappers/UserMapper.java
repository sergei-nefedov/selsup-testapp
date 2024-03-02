package pers.nefedov.selsuptestapp.mappers;

import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.User;

public interface UserMapper {
    User mapToUser(UserCreationDto userCreationDto);
    UserCreationDto mapToUserDto(User user);
}
