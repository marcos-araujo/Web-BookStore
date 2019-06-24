package com.unifiedbookcatalog.admin;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.unifiedbookcatalog.daos.AutorDAO;
import com.unifiedbookcatalog.models.Autor;

@Model
public class AutorBean {
	
	@Inject
	private AutorDAO autorDAO;
	
	private Autor autor = new Autor();
	
	@Inject
	private FacesContext context;
	
	private List<Autor> autores = new ArrayList<Autor>();
	
	@Transactional
	public String salvar() {
		autorDAO.salvar(autor);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Autor cadastrado com sucesso"));
		return "/autor/form?faces-redirect=true";
	}
	
	public List<Autor> getAutores(){
        this.autores = autorDAO.listar();
		return autores;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Transactional
	public String deletar(Autor autor) {
		autorDAO.deletar(autor);
		return "/autor/form?faces-redirect=true";
	}

	@Transactional
	public String editar(Autor autor) {
		this.setAutor(autorDAO.buscarPorId(autor.getId()));
		return "/autor/form";
	}
	
}