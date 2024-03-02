package pers.nefedov.selsuptestapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.nefedov.selsuptestapp.dto.UserCreationDto;
import pers.nefedov.selsuptestapp.services.PhoneService;
import pers.nefedov.selsuptestapp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final PhoneService phoneService;
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
            description = "При создании пользователя задаются его логин, пароль, ФИО, дата рождения в формате дд.мм.гггг, " +
                    "и баланс его счета)"
    )
    @PatchMapping("/addPhone")
    @ResponseStatus(HttpStatus.OK)
    public List<String> addPhoneNumber(@Schema(description = "Номер телефона", example = "+99999999999") @Size(min = 12, max = 12, message = "Номер телефона должен состоять из 12 знаков") @RequestParam String phoneNumber) {
        return userService.addPhoneNumber(phoneNumber);
    }
}
