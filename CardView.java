package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Card extends Actor {
	public final Cards cards;
	private final Sprite cardSprite;

	public Card(Cards cards, Sprite cardSprite){
		this.cards = cards;
		this.cardSprite = cardSprite;
	}
	
	public void draw(Batch batch, float x, float y){
		cardSprite.setPosition(x, y);
		cardSprite.draw(batch);
	}
	
	public String getName(){
		return cards.getName();
	}
	
	public int getIndex(){
		return cards.getIndex();
	}
	
	public Sprite getSprite(){
		return cardSprite;
	}
}
