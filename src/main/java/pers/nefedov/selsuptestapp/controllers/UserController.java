package pers.nefedov.selsuptestapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
            summary = "Удаление адреса электронной почты",
            description = "Если переданный в качестве параметра адрес электронной почты был сохранен ранее, он удаляется. Последний адрес электронной почты удалить нельзя."
    )
    @GetMapping("/search/by_email")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RegisteredUserDto> searchByEmail(@Schema(description = "Адрес электронной почты", example = "addr@somepost.com") @Email(message = "Неверный формат адреса электронной почты.") @RequestParam String email) {
        RegisteredUserDto registeredUserDto= userService.searchByEmail(email);
        if (registeredUserDto != null) {
            return new ResponseEntity<RegisteredUserDto>(registeredUserDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
