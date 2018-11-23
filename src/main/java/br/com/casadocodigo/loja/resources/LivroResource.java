package br.com.casadocodigo.loja.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import br.com.casadocodigo.loja.daos.LivroDAO;
import br.com.casadocodigo.loja.models.Livro;

@Path("livros")
public class LivroResource {

	@Inject
	private LivroDAO dao;

	@GET
	@Path("json")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Livro> ultimosLancamentosJson() {
        return dao.ultimosLancamentos();
    }
    
	@GET
	@Path("xml")
	@Wrapped(element = "livros")
	@Produces({MediaType.APPLICATION_XML})
	public List<Livro> ultimosLancamentosXML() {
        return dao.ultimosLancamentos();
    }
	
}