package com.unifiedbookcatalog.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.unifiedbookcatalog.daos.LivroDAO;
import com.unifiedbookcatalog.models.Livro;

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