package ru.gb.Ex.webApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UserDTO {


    @NotNull(message = "Введите никнэйм!")
    @Length(min = 1, max = 25, message = "Название никнэйма должно быть от 1 до 25 символов")
    @Pattern(regexp = "[а-яА-ЯЁё0-9 ]+", message = "Название никнэйма состоять только из букв и цифр")
    private String username;

    @NotNull(message = "Введите пароль!")
    @Length(min = 6, max = 100, message = "Пароль должен содержать не менее 6 и не более 100 символов")
    private String password;

    @NotNull(message = "Введите адрес электронной почты")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Некорректный адрес электронной почты")
    private String email;



}
