package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDAO;
import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Named(value="adminLivrosBean")
@RequestScoped
public class AdminLivrosBean {
	
	@Inject
	private LivroDAO dao;
	
	@Inject
	private AutorDAO autoDAO;
	
	private Livro livro = new Livro();
	
	@Inject
	private FacesContext context;
	

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	@Transactional
	public String salvar() {
		dao.salvar(livro);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso"));
		return "/livros/lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores(){
        return autoDAO.listar();
	}

}