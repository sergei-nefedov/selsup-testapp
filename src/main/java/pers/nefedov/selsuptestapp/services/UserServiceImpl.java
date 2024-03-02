package pers.nefedov.selsuptestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.exceptions.ForbiddenException;
import pers.nefedov.selsuptestapp.mappers.UserMapper;
import pers.nefedov.selsuptestapp.models.User;
import pers.nefedov.selsuptestapp.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private  final PhoneService phoneService;
    private final EmailService emailService;

    @Override
    @Transactional
    public UserCreationDto addUser(UserCreationDto userCreationDto) {
        if(userRepository.existsById(userCreationDto.getLogin())) throw new ForbiddenException();
        User user = userRepository.save(userMapper.mapToUser(userCreationDto));
        phoneService.addPhone(userCreationDto);
        emailService.addEmail(userCreationDto);
        return userMapper.mapToUserDto(user);
    }
    @Override
    public User getUserByLogin(String login) {
       return userRepository.findByLogin(login);
    }

    @Override
    public List<String> addPhoneNumber(String phoneNumber) {
        String login = "FirstUser"; // TODO get login from the current user
        User user = getUserByLogin(login);
        return phoneService.addPhoneNumber(user, phoneNumber);
    }
}
