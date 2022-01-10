package br.com.msusuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {


    private Long id;

    @NotEmpty(message = "O campo Nome não pode ser vazio.")
    @Length(min = 3, max = 200, message = "O campo Nome deve conter entre 3 e 200 caracteres.")
    private String nome;

    @NotEmpty(message = "O campo rg não pode ser vazio.")
    @Length(min = 6, max = 15, message = "O campo RG deve conter entre 6 e 15 caracteres.")
    private String rg;

    @NotEmpty(message = "O campo telefone não pode ser vazio.")
    @Length(min = 10, max = 11, message = "O campo telefone deve conter entre 10 e 11 caracteres.")
    private String telefone;

    @NotEmpty(message = "O campo Nome não pode ser vazio.")
    @Email
    private String email;

    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;

}
