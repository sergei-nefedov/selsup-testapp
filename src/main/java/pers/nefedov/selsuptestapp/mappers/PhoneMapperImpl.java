package pers.nefedov.selsuptestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Phone;
@AllArgsConstructor
@Component
public class PhoneMapperImpl implements PhoneMapper {
    private final UserMapper userMapper;
    @Override
    public Phone mapToPhone(UserCreationDto userCreationDto) {
        Phone phone = new Phone();
        phone.setNumber(userCreationDto.getPhoneNumber());
        phone.setUser(userMapper.mapToUser(userCreationDto));
        return phone;
    }
}
