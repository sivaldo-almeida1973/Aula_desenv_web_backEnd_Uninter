package br.com.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="nota_entrada_itens")
public class NotaEntradaItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//relacionamentos com a classe Produto
	@ManyToOne
	@JoinColumn(name="produto_id")
	@NotNull
	private Produto produto;
	
	//relacionamentos com a classe NotaEntrada
	@ManyToOne
	@JoinColumn(name="nota_entrada_id")
	@NotNull
	private NotaEntrada notaEntrada;
	
	//atributos-------------------
	
	@NotNull(message = "Informe a quantidade!")
	private Integer quantidade;
	
	@NotNull(message = "Informe o valor unitario")
	private Float valorUnitario;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public NotaEntrada getNotaEntrada() {
		return notaEntrada;
	}


	public void setNotaEntrada(NotaEntrada notaEntrada) {
		this.notaEntrada = notaEntrada;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Float getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(Float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


	public float getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}


	private float valorTotal;
	
	
	

}
