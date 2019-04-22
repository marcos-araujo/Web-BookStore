package br.com.livraria.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.livraria.models.Autor;
import br.com.livraria.models.Livro;

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
    	List<Livro> livros = autor.getLivros();
    	if(livros.isEmpty())
    		manager.remove(autor);
    	else {
    		Query query = manager.createNativeQuery("DELETE FROM LIVRO_AUTOR WHERE AUTORES_ID = ?");
    		query.setParameter(1, autor.getId());
    		query.executeUpdate();
    		
    		manager.remove(autor);
    	}
    		
    }
    
    public List<Autor> listar(){
        return manager.createQuery("select a from Autor a", Autor.class).getResultList();        
    }
    
	public Autor buscarPorId(Integer id) {
	    String jpql = "select a from Autor a where a.id = :id";
	    return manager.createQuery(jpql, Autor.class).setParameter("id", id).getSingleResult();
	}

}