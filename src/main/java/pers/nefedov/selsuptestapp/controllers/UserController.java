package pers.nefedov.selsuptestapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.nefedov.selsuptestapp.dto.RegisteredUserDto;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.services.UserService;

import java.util.List;

@RestController
@SecurityRequirement(name = "user-api")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Добавление пользователя",
            description = "При создании пользователя задаются его логин, пароль, ФИО, дата рождения в формате дд.мм.гггг, " +
                    "и баланс его счета)"
    )
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto addUser(@Valid @RequestBody UserCreationDto userCreationDto) {
        return userService.addUser(userCreationDto);
    }

    @Operation(
            summary = "Добавление номера телефона",
            description = "Новый номер телефона добавляется к уже существующим"
    )
    @PatchMapping("/add_phone")
    @ResponseStatus(HttpStatus.OK)
    public List<String> addPhoneNumber(@Schema(description = "Номер телефона", example = "+99999999999") @Size(min = 12, max = 12, message = "Номер телефона должен состоять из 12 знаков") @RequestParam String phoneNumber) {
        return userService.addPhoneNumber(phoneNumber);
    }

    @Operation(
            summary = "Удаление номера телефона",
            description = "Если переданный в качестве параметра номер был сохранен ранее, он удаляется. Последний номер удалить нельзя."
    )
    @PatchMapping("/delete_phone")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deletePhoneNumber(@Schema(description = "Номер телефона", example = "+99999999999") @Size(min = 12, max = 12, message = "Номер телефона должен состоять из 12 знаков") @RequestParam String phoneNumber) {
        return userService.deletePhoneNumber(phoneNumber);
    }

    @Operation(
            summary = "Добавление адреса электронной почты",
            description = "Новый адрес электронной почты телефона добавляется к уже существующим"
    )
    @PatchMapping("/add_email")
    @ResponseStatus(HttpStatus.OK)
    public List<String> addEmail(@Schema(description = "Адрес электронной почты", example = "addr@somepost.com") @Email(message = "Неверный формат адреса электронной почты.") @RequestParam String email) {
        return userService.addEmail(email);
    }

    @Operation(
            summary = "Удаление адреса электронной почты",
            description = "Если переданный в качестве параметра адрес электронной почты был сохранен ранее, он удаляется. Последний адрес электронной почты удалить нельзя."
    )
    @PatchMapping("/delete_email")
    @ResponseStatus(HttpStatus.OK)
    public List<String> deleteEmail(@Schema(description = "Адрес электронной почты", example = "addr@somepost.com") @Email(message = "Неверный формат адреса электронной почты.") @RequestParam String email) {
        return userService.deleteEmail(email);
    }

    @Operation(
            summary = "Поиск пользователя по адресу электронной почты",
            description = "Адрес электронной почты передается через параметр запроса."
    )
    @GetMapping("/search/by_email")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegisteredUserDto> searchByEmail(@Schema(description = "Адрес электронной почты", example = "addr@somepost.com") @Email(message = "Неверный формат адреса электронной почты.") @RequestParam String email) {
        RegisteredUserDto registeredUserDto = userService.searchByEmail(email);
        if (registeredUserDto != null) {
            return new ResponseEntity<RegisteredUserDto>(registeredUserDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Поиск пользователя по номеру телефона",
            description = "Номер телефона передается через параметр запроса."
    )
    @GetMapping("/search/by_phone")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegisteredUserDto> searchByPhone(@Schema(description = "Номер телефона", example = "+99999999999") @Size(min = 12, max = 12, message = "Номер телефона должен состоять из 12 знаков") @RequestParam String phoneNumber) {
        RegisteredUserDto registeredUserDto = userService.searchByPhone(phoneNumber);
        if (registeredUserDto != null) {
            return new ResponseEntity<RegisteredUserDto>(registeredUserDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Operation(
            summary = "Поиск пользователя по ФИО",
            description = "Фамилия, имя и отчество передаются через параметр запроса, возвращается список пользователей, " +
                    "ФИО которых соответствуют (like) запрошенному без учета регистра."
    )
    @GetMapping("/search/by_name")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RegisteredUserDto>> searchByName(@Schema(description = "Фамилия, имя и отчество пользователя", example = "Иванов Иван Иванович") @Size(min = 5, max = 64, message = "Длина ФИО не должна быть меньше 5 и больше 64 знаков.") @RequestParam String name) {
        List<RegisteredUserDto> registeredUserDtoList = userService.searchByName(name);
        return new ResponseEntity<>(registeredUserDtoList, HttpStatus.OK);

    }
    @Operation(
            summary = "Поиск пользователя по дате рождения",
            description = "Дата рождения передается через параметр запроса, возвращается список пользователей, " +
                    "дата рождения которых позже запрошенной."
    )
    @GetMapping("/search/by_birthdate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RegisteredUserDto>> searchByBirthdate(@Schema(description = "Дата рождения пользователя в формате дд.мм.гггг", example = "01.01.2000") @DateTimeFormat(pattern = "dd.MM.yyyy") @Size(min = 10, max = 10, message = "Дата рождения должна состоять из 10 знаков") @RequestParam String date) {
        List<RegisteredUserDto> registeredUserDtoList = userService.searchByBirthdate(date);
        return new ResponseEntity<>(registeredUserDtoList, HttpStatus.OK);

    }
}
