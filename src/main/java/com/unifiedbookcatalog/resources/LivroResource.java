package com.unifiedbookcatalog.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import com.unifiedbookcatalog.daos.LivroDAO;
import com.unifiedbookcatalog.models.Livro;

@Path("livros")
public class LivroResource {

	@Inject
	private LivroDAO dao;

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("lancamentos")
	@Wrapped(element = "livros")
	public List<Livro> ultimosLancamentos() {
        return dao.listar();
    }
    
}