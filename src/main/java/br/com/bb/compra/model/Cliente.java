package br.com.bb.compra.model;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.bb.compra.model.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    public Cliente(Long id, String nome,  String cpf, String email) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
    }

    private Long id;
    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotNull(message = "CPF nao pode ser null")
    @NotEmpty(message = "CPF nao pode ser vazio")
    private String cpf;

    @NotNull(message = "Email nao pode ser null")
    @NotEmpty(message = "Email nao pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    @JsonIgnoreProperties(allowGetters = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;

    private List<Endereco> enderecos;
}
