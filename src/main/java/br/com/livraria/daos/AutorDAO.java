package br.com.livraria.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.models.Autor;

public class AutorDAO {
	
    @PersistenceContext
    private EntityManager manager;

    public void salvar(Autor autor) {
    	manager.persist(autor);
    }
    
    public List<Autor> listar(){
        return manager.createQuery("select a from Autor a", Autor.class).getResultList();        
    }

}
