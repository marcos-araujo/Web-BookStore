package br.com.casadocodigo.loja.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.loja.daos.CompraDAO;
import br.com.casadocodigo.loja.models.Compra;

@Path("/pagamento")
public class PagamentoService {
	
	@Inject
    private CompraDAO compraDAO;
	
	@Inject
	private PagamentoGateway pagamentoGateway;
	
	@Context
	private ServletContext context;

	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	@POST
	public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Compra compra = compraDAO.buscaPorUuid(uuid);

	    executor.submit(() -> {
	    	 try {
		    	pagamentoGateway.pagar(compra.getTotal());	
		    	URI responseUri= UriBuilder.fromPath("http://localhost:8080" + context.getContextPath() + "/index.xhtml").queryParam("msg", "Compra Realizada com Sucesso").build();
		    	Response response = Response.seeOther(responseUri).build();
		    	ar.resume(response);
	    	 } catch(Exception e) {
	    		 ar.resume(new WebApplicationException(e));
	    	 }
	    });

	}
}