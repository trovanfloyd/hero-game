package game.map;

import game.items.ItemKey;

public interface House {

	String KEY_TWO = "KEY_TWO";
	String KEY_THREE = "KEY_THREE";
	String KEY_FOUR = "KEY_FOUR";
	
	String END = "END";
	
	String OPEN_DOOR_MSG = "The Door has been opened!";
	
	String ENIGMA_HOUSE_ONE = "Joe’s father had three sons – Snap, Crackle and …? \n";
	String ANSWER_ENGIMA_HOUSE_ONE = "Joe";
	String ENIGMA_HOUSE_TWO = "What comes down but never goes up? \n";
	String ANSWER_ENGIMA_HOUSE_TWO = "rain";
	String ENIGMA_HOUSE_THREE = "What word is spelled wrong in all the dictionaries? \n";
	String ANSWER_ENGIMA_HOUSE_THREE = "wrong";
	String ENIGMA_HOUSE_FOUR = "What breaks when you say it? \n";
	String ANSWER_ENGIMA_HOUSE_FOUR = "silence";
	
	
	boolean openDoor(ItemKey itemKey);
	String getEnigma();
	ItemKey solveEnigma(String answer);
	String gethint();
	
}
