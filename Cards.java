package com.mygdx.game;

public enum Cards {
	CERO_ROJO("red", 0), UNO_AZUL("blue", 1), DOS_AZUL("blue", 2), TRES_AZUL("blue", 3), CUATRO_AZUL("blue", 4),
	CINCO_AZUL("blue", 5), SEIS_AZUL("blue", 6), SIETE_AZUL("blue", 7), OCHO_AZUL("blue", 8), NUEVE_AZUL("blue", 9);
	
	private final String name;
	private final int index;
	
	private Cards(String name, int index){
		this.name = name;
		this.index = index;
	}
	
	public String getName(){
		return name;
	}
	
	public int getIndex(){
		return index;
	}
}
