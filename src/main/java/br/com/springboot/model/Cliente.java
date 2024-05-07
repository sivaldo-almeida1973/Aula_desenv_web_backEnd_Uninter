package br.com.springboot.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente {
	
	// Atributos
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Informe o nome corretamente!")
	@Size(min = 3, max = 50)	
	private String nome;
	
	@Column(length = 14)
	@CPF(message = "CPF Inválido!")
	private String cpf;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(nullable=false, name = "dataDeNascimento", columnDefinition = "DATE")
    @NotNull(message = "Informe a data de nascimento!")
	private LocalDate dataDeNascimento;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Informe o sexo")
	private Sexo sexo;
	
	@Column(length = 14)
	private String telefone;
	@Column(length = 14)
	private String celular;
	
	@Column(length = 50)
	@Email
	private String email;
	private boolean ativo;


	
	public Cliente() {
		this.ativo = true;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getCelular() {
		return celular;
	}
	public String getEmail() {
		return email;
	}
	public boolean isAtivo() {
		return ativo;
	}
	
	
}

