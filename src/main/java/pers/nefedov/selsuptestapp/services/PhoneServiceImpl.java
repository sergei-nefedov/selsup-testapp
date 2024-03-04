package pers.nefedov.selsuptestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.exceptions.ForbiddenException;
import pers.nefedov.selsuptestapp.mappers.PhoneMapper;
import pers.nefedov.selsuptestapp.models.Email;
import pers.nefedov.selsuptestapp.models.Phone;
import pers.nefedov.selsuptestapp.models.User;
import pers.nefedov.selsuptestapp.repositories.PhoneRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    @Override
    public Phone addPhone(UserCreationDto userCreationDto) {
        if (phoneRepository.existsById(userCreationDto.getPhoneNumber())) throw new ForbiddenException();
        return phoneRepository.save(phoneMapper.mapToPhone(userCreationDto));
    }


    @Override
    public List<String> addPhoneNumber(User user, String phoneNumber) {
        if (phoneRepository.existsById(phoneNumber)) throw new ForbiddenException();
        phoneRepository.save(new Phone(phoneNumber, user));
        List<Phone> phoneList = phoneRepository.findByUser_Login(user.getLogin());
        return phoneList.stream().map(Phone::getNumber).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<String> deletePhoneNumber(User user, String phoneNumber) {
        if (!phoneRepository.existsById(phoneNumber)) throw new ForbiddenException();
        if (phoneRepository.countByUser_Login(user.getLogin()) < 2) throw new ForbiddenException();
        Phone phone = new Phone(phoneNumber, user);
        phoneRepository.delete(phone);
        return phoneRepository.findByUser_Login(user.getLogin()).stream().map(Phone::getNumber).collect(Collectors.toList());
    }

    @Override
    public User findUserByPhone(String phoneNumber) {
        Phone phone = phoneRepository.findByNumber(phoneNumber);
        if (phone == null) return null;
        return phone.getUser();
    }
}
