package com.unifiedbookcatalog.websockets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.unifiedbookcatalog.models.Promo;

@ServerEndpoint(value = "/canal/promos")
public class PromosEndpoint {
	
	@Inject
    private UsuariosSession usuarios;

	@OnOpen
	public void onMessage(Session session) {
		usuarios.add(session);
	}
	
	public void send(Promo promo) {
		List<Session> sessions = usuarios.getUsuarios();
		for (Session session : sessions) {
			if(session.isOpen()) {
				try {
					session.getBasicRemote().sendText(promo.toJson());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@OnClose
	public void OnClose(Session session, CloseReason closeReason) {
		usuarios.remove(session);
	}
	
}