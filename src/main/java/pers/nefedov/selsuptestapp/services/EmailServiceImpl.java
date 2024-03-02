package pers.nefedov.selsuptestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.exceptions.ForbiddenException;
import pers.nefedov.selsuptestapp.mappers.EmailMapper;
import pers.nefedov.selsuptestapp.models.Email;
import pers.nefedov.selsuptestapp.repositories.EmailRepository;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    @Override
    public Email addEmail(UserCreationDto userCreationDto) {
        if(emailRepository.existsById(userCreationDto.getEmail())) throw  new ForbiddenException();
        return emailRepository.save(emailMapper.mapToEmail(userCreationDto));
    }
}
