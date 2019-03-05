package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDAO;
import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class AdminLivrosBean {
	
	@Inject
	private LivroDAO dao;
	
	@Inject
	private AutorDAO autoDAO;
	
	private Livro livro = new Livro();
	
	@Inject
	private FacesContext context;
	
	private Part capaLivro;

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

	@Transactional
	public String salvar() {
		FileSaver fileSaver = new FileSaver();
		if(capaLivro != null)
			livro.setCapaPath(fileSaver.write(capaLivro, "livros"));
		else
			livro.setCapaPath(dao.buscarPorId(livro.getId()).getCapaPath());
		dao.salvar(livro);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso"));
		return "/livros/lista?faces-redirect=true";
	}

	@Transactional
	public String editar(Livro livro) {
		this.setLivro(dao.buscarPorId(livro.getId()));
		return "/livros/form";
	}
	
	@Transactional
	public String deletar(Livro livro) {
		dao.deletar(livro);
		//fazer deleção do arquivo
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro deletado"));
		
		return "/livros/lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores(){
        return autoDAO.listar();
	}

}