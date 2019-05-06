package br.com.livraria.util;

public class IdGenerator {
	
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMERIC_STRING = "0123456789";
	
	static String generateId() {
		
		StringBuilder builder = new StringBuilder();
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
		
		return builder.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			System.out.println(IdGenerator.generateId());
		}
	}
	
}
