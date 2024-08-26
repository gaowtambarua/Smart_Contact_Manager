package com.Gaowtam.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Usrname is required")
    @Size(min=3,message = "Min 3 Characters is required")
    private String name;

    //@Pattern("")
    @NotBlank(message = "Invalid Email Addres")
    private String email;

    @NotBlank(message = "Password is requeird")
    @Size(min = 6,message = "Min 6 Characters is required")
    private String password;

    @Size(min=11,max=11, message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "About is required")
    private String about;
}
