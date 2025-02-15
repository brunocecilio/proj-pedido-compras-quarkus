package br.com.bb.compra.model.login;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @NotNull(message = "Email nao pode ser NULL")
    private String email;

    @NotNull(message = "Senha nao pode ser NULL")
    private String senha;

}
