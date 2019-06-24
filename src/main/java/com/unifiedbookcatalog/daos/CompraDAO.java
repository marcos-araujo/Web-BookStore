package com.unifiedbookcatalog.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.unifiedbookcatalog.models.Compra;

public class CompraDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
    private EntityManager manager;

    public void salvar(Compra compra) {
        manager.persist(compra);
    }

	public Compra buscaPorUuid(String uuid) {
		return manager.createQuery("select c from Compra c where c.uuid = :uuid", Compra.class)
				.setParameter("uuid", uuid)
				.getSingleResult();
	}
	
}