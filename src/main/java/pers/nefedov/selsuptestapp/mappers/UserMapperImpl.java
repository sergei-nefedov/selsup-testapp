package pers.nefedov.selsuptestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.User;
@Component
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User mapToUser(UserCreationDto userCreationDto) {
        User user = new User();
        user.setLogin(userCreationDto.getLogin());
        user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
        user.setName(userCreationDto.getName());
        user.setDateOfBirth(userCreationDto.getDateOfBirth());
        user.setAccountBalance(userCreationDto.getAccountBalance());
        return user;
    }

    @Override
    public UserCreationDto mapToUserDto(User user) {
        UserCreationDto userDto = new UserCreationDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword()); //TODO delete
        userDto.setName(user.getName());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setAccountBalance(user.getAccountBalance());
        return userDto;
    }
}
