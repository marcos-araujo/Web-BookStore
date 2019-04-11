package br.com.livraria.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.models.Autor;

public class AutorDAO {
	
    @PersistenceContext
    private EntityManager manager;

    public void salvar(Autor autor) {
    	if(autor.getId() == null)
    		manager.persist(autor);
    	else
    		manager.merge(autor);
    }
    
    public void deletar(Autor autor) {
    	autor = manager.find(Autor.class, autor.getId());
    	manager.remove(autor);
    }
    
    public List<Autor> listar(){
        return manager.createQuery("select a from Autor a", Autor.class).getResultList();        
    }
    
	public Autor buscarPorId(Integer id) {
	    String jpql = "select a from Autor a where a.id = :id";
	    return manager.createQuery(jpql, Autor.class).setParameter("id", id).getSingleResult();
	}

}