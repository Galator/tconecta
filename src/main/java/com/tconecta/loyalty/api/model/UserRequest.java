package com.tconecta.loyalty.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @NotNull
    @Size(min = 3, max = 15)
    private String userName;

    @NotNull
    @Size(min = 3)
    private String userPassword;
}
