package pers.nefedov.selsuptestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pers.nefedov.selsuptestapp.dto.RegisteredUserDto;
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

    @Override
    public RegisteredUserDto mapToRegisterdUserDto(User user) {
        if (user == null) return null;
        RegisteredUserDto registeredUserDto = new RegisteredUserDto();
        registeredUserDto.setLogin(user.getLogin());
        registeredUserDto.setName(user.getName());
        registeredUserDto.setDateOfBirth(user.getDateOfBirth());
        registeredUserDto.setAccountBalance(user.getAccountBalance());
        return registeredUserDto;
    }
}
