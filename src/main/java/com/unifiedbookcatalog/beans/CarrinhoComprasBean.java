package com.unifiedbookcatalog.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.unifiedbookcatalog.daos.LivroDAO;
import com.unifiedbookcatalog.models.CarrinhoCompras;
import com.unifiedbookcatalog.models.CarrinhoItem;
import com.unifiedbookcatalog.models.Livro;

@Model
public class CarrinhoComprasBean {

    @Inject
    private LivroDAO livroDAO;

    @Inject
    private CarrinhoCompras carrinho;

    public String add(Integer id) {
        Livro livro = livroDAO.buscarPorId(id);
        CarrinhoItem item = new CarrinhoItem(livro);
        carrinho.add(item);
        return "carrinho?faces-redirect=true";
    }

    public List<CarrinhoItem> getItens() {
        return carrinho.getItens();
    }
 
    public void remover(CarrinhoItem item) {
    	carrinho.remover(item);
    }
    
}