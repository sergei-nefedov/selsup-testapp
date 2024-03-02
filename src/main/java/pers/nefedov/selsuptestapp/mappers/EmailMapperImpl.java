package pers.nefedov.selsuptestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.models.Email;
@AllArgsConstructor
@Component
public class EmailMapperImpl implements EmailMapper {
    private final UserMapper userMapper;
    @Override
    public Email mapToEmail(UserCreationDto userCreationDto) {
        Email email = new Email();
        email.setEmail(userCreationDto.getEmail());
        email.setUser(userMapper.mapToUser(userCreationDto));
        return email;
    }
}
