package br.com.msusuario.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entidade que representa um usuario
 * @author emanuel developer
 *
 */
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "USUARIO", schema = "MS_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "USUARIO_SEQ", schema = "MS_USUARIO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "RG", nullable = false)
    private String rg;

    @Column(name = "TELEFONE", nullable = false)
    private String telefone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "DATA_ATUALIZACAO", nullable = false)
    private LocalDate dataAtualizacao;

    //TODO: Emanuel: Adicionar o campo foto

    @PreUpdate
    public void preUpdate() {
        this.setDataAtualizacao(LocalDate.now());
    }

    @PrePersist
    public void prePersist() {
        this.setDataCadastro(LocalDate.now());
        this.setDataAtualizacao(LocalDate.now());
    }

}
