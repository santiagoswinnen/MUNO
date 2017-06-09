package com.mygdx.game;

public enum Cards {
	BLUE_ZERO("blue", 0), BLUE_ONE("blue", 1), BLUE_TWO("blue", 2), BLUE_THREE("blue", 3), BLUE_FOUR("blue", 4),
	BLUE_FIVE("blue", 5), BLUE_SIX("blue", 6), BLUE_SEVEN("blue", 7), BLUE_EIGHT("blue", 8), BLUE_NINE("blue", 9),
	BLUE_SKIP("blue_skip", -1), BLUE_SWITCH("blue_switch", -1), BLUE_TAKE2("blue_take2", -1),
	GREEN_ZERO("green", 0), GREEN_ONE("green", 1), GREEN_TWO("green", 2), GREEN_THREE("green", 3),
	GREEN_FOUR("green", 4), GREEN_FIVE("green", 5), GREEN_SEVEN("green", 7), GREEN_EIGHT("green", 8),
	GREEN_NINE("green", 9), GREEN_SKIP("green_skip", -1), GREEN_SWITCH("green_switch", -1), GREEN_TAKE2("green_take2", -1),
	RED_ZERO("red", 0), RED_ONE("red", 1), RED_TWO("red", 2), RED_THREE("red", 3), RED_FOUR("red", 4),
	RED_FIVE("red", 5), RED_SIX("red", 6), RED_SEVEN("red", 7), RED_EIGHT("red", 8), RED_NINE("red", 9),
	RED_SKIP("red_skip", -1), RED_SWITCH("red_switch", -1), RED_TAKE2("red_take2", -1),	YELLOW_ZERO("yellow", 0),
	YELLOW_ONE("yellow", 1), YELLOW_TWO("yellow", 2), YELLOW_THREE("yellow", 3), YELLOW_FOUR("yellow", 4),
	YELLOW_FIVE("yellow", 5), YELLOW_SIX("yellow", 6), YELLOW_SEVEN("yellow", 7), YELLOW_EIGHT("yellow", 8),
	YELLOW_NINE("yellow", 9), YELLOW_SKIP("yellow_skip", -1), YELLOW_SWITCH("yellow_switch", -1),
	YELLOW_TAKE2("yellow_take2", -1), CHANGE_COLOR("change_color", -1), CHANGE_COLOR_TAKE2("change_color_take2", -1);
	
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
