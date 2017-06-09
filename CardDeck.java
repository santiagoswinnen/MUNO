package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CardDeck {
	private final ArrayList<Card> deckOfCards;
	
	public CardDeck(TextureAtlas atlas){
		deckOfCards = new ArrayList<Card>();
		for(Cards cards : Cards.values()){
			Sprite cardSprite = atlas.createSprite(cards.getName(), cards.getIndex());
			cardSprite.setScale(0.5f);
			Card card = new Card(cards, cardSprite);
			deckOfCards.add(card);
			deckOfCards.add(card);
		}
		
		
	}
	
	public ArrayList<Card> getCardDeck(){
		return deckOfCards; //Hacer unmodifiable
	}
}