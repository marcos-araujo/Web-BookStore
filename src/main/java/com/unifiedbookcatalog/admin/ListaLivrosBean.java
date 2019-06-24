package com.unifiedbookcatalog.admin;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.unifiedbookcatalog.daos.LivroDAO;
import com.unifiedbookcatalog.models.Livro;

@Model
public class ListaLivrosBean {
	
	@Inject
	private LivroDAO dao;
	
	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = dao.listar();
		return livros;
	}

}