package br.com.livraria.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.models.Usuario;

public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Usuario usuario) {
	    manager.persist(usuario);
	}

}