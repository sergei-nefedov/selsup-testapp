package pers.nefedov.selsuptestapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredUserDto {

    private String login;

    private String name;

    private String dateOfBirth;

    private double accountBalance;

//    private List <String> phoneNumbers;
//
//    private List <String> emails;
}
