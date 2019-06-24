package com.unifiedbookcatalog.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.unifiedbookcatalog.daos.LivroDAO;
import com.unifiedbookcatalog.models.Livro;

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