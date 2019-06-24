package com.unifiedbookcatalog.util;

import javax.inject.Inject;

import com.unifiedbookcatalog.daos.LivroDAO;

public class IdGenerator {
	
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMERIC_STRING = "0123456789";
	
	public static String generateId(LivroDAO dao) {
		StringBuilder builder = new StringBuilder();
		
		do {
			builder.setLength(0);
			int count = 3;
			while (count-- != 0) {
				int character = (int)(Math.random()*ALPHA_STRING.length());
				builder.append(ALPHA_STRING.charAt(character));
			}
	
			builder.append("-");
			
			count = 3;
			while (count-- != 0) {
				int character = (int)(Math.random()*NUMERIC_STRING.length());
				builder.append(NUMERIC_STRING.charAt(character));
			}
			
		}while(dao.buscarPorCodigo(builder.toString()) != null);
		
		return builder.toString();
	}

}
