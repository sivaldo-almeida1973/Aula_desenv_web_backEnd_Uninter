package br.com.springboot.model;

public enum Categoria {
	
	CELULARES("CELULARES"),
	ELETRODOMESTICO("Eletrodomésticos"),
	INFORMATICA("Informatica"),
	MOVEIS("Moveis");
	
	
	private String descricao;
	
	Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
