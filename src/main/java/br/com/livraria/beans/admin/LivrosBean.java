package br.com.livraria.beans.admin;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.livraria.daos.AutorDAO;
import br.com.livraria.daos.LivroDAO;
import br.com.livraria.infra.FileSaver;
import br.com.livraria.models.Autor;
import br.com.livraria.models.Livro;
import br.com.livraria.util.IdGenerator;

@Model
public class LivrosBean {
	
	@Inject
	private LivroDAO livroDAO;
	
	@Inject
	private AutorDAO autorDAO;
	
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
		
		if(livro.getId() == null && (capaLivro != null))
			livro.setCapaPath(fileSaver.write(capaLivro, "livros"));
		else
			if(capaLivro != null) {
				String capaPath = livroDAO.buscarPorId(livro.getId()).getCapaPath();
				fileSaver.delete(capaPath);
				
				livro.setCapaPath(fileSaver.write(capaLivro, "livros"));
			} else {
				livro.setCapaPath(livroDAO.buscarPorId(livro.getId()).getCapaPath());
			}
		
		livro.setCodigo(IdGenerator.generateId(livroDAO));
		
		livroDAO.salvar(livro);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso"));
		return "/livros/lista?faces-redirect=true";
	}

	@Transactional
	public String editar(Livro livro) {
		this.setLivro(livroDAO.buscarPorId(livro.getId()));
		return "/livros/form";
	}
	
	@Transactional
	public String deletar(Livro livro) {
		FileSaver fileSaver = new FileSaver();
		
		livroDAO.deletar(livro);
		
		fileSaver.delete(livro.getCapaPath());
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro deletado"));
		
		return "/livros/lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores(){
        return autorDAO.listar();
	}

}