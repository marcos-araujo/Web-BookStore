package com.unifiedbookcatalog.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.unifiedbookcatalog.models.Usuario;

public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Usuario usuario) {
	    manager.persist(usuario);
	}

}