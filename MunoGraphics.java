package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import java.util.ArrayList;
import java.util.LinkedList;

public class MunoGraphics extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture cardBack;
	private Texture background;
	private Sprite topCard;
	private ArrayList<Sprite> visibleSprites;

	@Override
	public void create () {

		cardBack= new Texture("munocard.jpg");
		background= new Texture("background.jpg");
		batch = new SpriteBatch();
		topCard = new Sprite(cardBack);

		topCard.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		topCard.setSize(66,99);



	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,1,0,1);
		batch.begin();
			topCard.draw(batch);
		batch.end();
	}


	//Mueve las cartas de lugar cada vez que se saca o se agrega una
	public void reposition(ArrayList<Sprite> visibleSprites){
		int amount=/** Pedir la cantidad de cartas del jugador humano*/
		for(i=0;i<amount;i++){
			visibleSprites.getNext().setPosition(Gdx.graphics.getWidth()*i/(amount),0);
		}
	}

	//Pide las cartas que le tocaron al jugador y arma la lista de Sprites (llamamos a la carta: "numerotipo.jpg")
	public void setInitialCards(ArrayList<Card> cards, ArrayList<Sprite> visibleSprites ){
		Card actual;
		while(cards.hasNext()){
			actual=cards.getNext()
			visibleSprites.add(/**Nombre del sprite que hay que agregar**/)
		}

	}

	//Hace visible la carta de descarte segun la carta que tiro
	public void setDiscardedCard(Card card){
		
	}

}
