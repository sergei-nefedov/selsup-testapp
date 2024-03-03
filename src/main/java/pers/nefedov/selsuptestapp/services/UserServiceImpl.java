package pers.nefedov.selsuptestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.selsuptestapp.dto.RegisteredUserDto;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.exceptions.ForbiddenException;
import pers.nefedov.selsuptestapp.mappers.DateMapper;
import pers.nefedov.selsuptestapp.mappers.UserMapper;
import pers.nefedov.selsuptestapp.models.User;
import pers.nefedov.selsuptestapp.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PhoneService phoneService;
    private final EmailService emailService;
    private final DateMapper dateMapper;

    @Override
    @Transactional
    public UserCreationDto addUser(UserCreationDto userCreationDto) {
        if(userRepository.existsById(userCreationDto.getLogin())) throw new ForbiddenException();
        User user = userRepository.save(userMapper.mapToUser(userCreationDto));
        phoneService.addPhone(userCreationDto);
        emailService.addEmail(userCreationDto);
        return userMapper.mapToUserCreationDto(user);
    }
    @Override
    public User getUserByLogin(String login) {
       return userRepository.findByLogin(login);
    }

    @Override
    public List<String> addPhoneNumber(String phoneNumber) {
        User currentUser = getCurrentUser();
        return phoneService.addPhoneNumber(currentUser, phoneNumber);
    }

    @Override
    public List<String> deletePhoneNumber(String phoneNumber) {
        User currentUser = getCurrentUser();
        return phoneService.deletePhoneNumber(currentUser, phoneNumber);
    }

    @Override
    public List<String> addEmail(String email) {
        User currentUser = getCurrentUser();
        return emailService.addEmail(currentUser, email);
    }

    @Override
    public List<String> deleteEmail(String email) {
        User currentUser = getCurrentUser();
        return emailService.deleteEmail(currentUser, email);
    }

    @Override
    public RegisteredUserDto searchByEmail(String email) {
        return userMapper.mapToRegisterdUserDto(emailService.findUserByEmail(email));
    }

    @Override
    public RegisteredUserDto searchByPhone(String phoneNumber) {
        return userMapper.mapToRegisterdUserDto(phoneService.findUserByPhone(phoneNumber));
    }

    @Override
    public List<RegisteredUserDto> searchByName(String name) {
        return userRepository.findByNameLikeIgnoreCase(name).stream().map(userMapper::mapToRegisterdUserDto).collect(Collectors.toList());
    }

    @Override
    public List<RegisteredUserDto> searchByBirthdate(String date) {
        Date requestDate = dateMapper.mapToDate(date);
        return userRepository.findByDateOfBirthGreaterThan(requestDate).stream().map(userMapper::mapToRegisterdUserDto).collect(Collectors.toList());
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
