package br.com.livraria.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.livraria.daos.AutorDAO;
import br.com.livraria.models.Autor;

@Model
public class AdminAutorBean {
	
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
		return "/livros/lista?faces-redirect=true";
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

//	@Transactional
//	public String editar(Livro livro) {
//		this.setLivro(dao.buscarPorId(livro.getId()));
//		return "/livros/form";
//	}
//	
//	@Transactional
//	public String deletar(Livro livro) {
//		dao.deletar(livro);
//		//fazer deleção do arquivo
//		
//		context.getExternalContext().getFlash().setKeepMessages(true);
//		context.addMessage(null, new FacesMessage("Livro deletado"));
//		
//		return "/livros/lista?faces-redirect=true";
//	}
	
}