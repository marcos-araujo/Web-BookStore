package br.com.livraria.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.daos.LivroDAO;
import br.com.livraria.models.CarrinhoCompras;
import br.com.livraria.models.CarrinhoItem;
import br.com.livraria.models.Livro;

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