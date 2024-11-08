package com.mark.amoCrmTask.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContactRequest {
    @NotBlank(message = "Имя не должно быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ]{4,40}$", message = "Некорректное имя контакта")
    private String name;
    @NotBlank(message = "Телефон не может быть пустой")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "Некорректный номер телефона")
    private String phone;
    @NotBlank(message = "Почта не может быть пустой")
    @Pattern(regexp = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)", message = "Некорректный email")
    private String email;
}
