package br.com.livraria.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.daos.LivroDAO;
import br.com.livraria.models.Livro;

@Model
public class HomeBean {

    @Inject
    private LivroDAO dao;

    public List<Livro> ultimosLancamentos() {
        return dao.ultimosLancamentos();
    }

    public List<Livro> demaisLivros() {
        return dao.demaisLivros();
    }
    
}