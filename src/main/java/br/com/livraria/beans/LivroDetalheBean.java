package br.com.livraria.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.daos.LivroDAO;
import br.com.livraria.models.Livro;

@Model
public class LivroDetalheBean {
	
	@Inject
	private LivroDAO dao;
	private Livro livro;
	private Integer id;
	
	public void carregaDetalhe() {
		this.livro = dao.buscarPorId(id);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}