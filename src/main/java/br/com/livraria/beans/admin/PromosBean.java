package br.com.livraria.beans.admin;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.models.Promo;
import br.com.livraria.websockets.PromosEndpoint;

@Model
public class PromosBean {

    private Promo promo = new Promo();
    
    @Inject
    private PromosEndpoint promos;

    public void enviar() {
    	promos.send(promo);
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }
    
}
