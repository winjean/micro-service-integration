package com.winjean.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorizationUser {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}
