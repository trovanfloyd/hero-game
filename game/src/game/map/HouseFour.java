package game.map;

import game.items.ItemKey;
import game.util.GameUtil;

public class HouseFour implements House {

	@Override
	public boolean openDoor(ItemKey itemKey) {
		if (itemKey.getDescription().equals(KEY_FOUR)) {
			System.out.println(OPEN_DOOR_MSG);
			return true;
		}
		return false;
	}

	@Override
	public ItemKey solveEnigma(String answer) {
		if (answer.equalsIgnoreCase((ANSWER_ENGIMA_HOUSE_FOUR)) ) {
			System.out.println(" ");
			System.out.println("Yes, you are right! You've saved The Princess!!");
			GameUtil.loadStory();
			return new ItemKey(END, "END");
		}
		return null;
	}

	@Override
	public String getEnigma() {
		return ENIGMA_HOUSE_FOUR;
	}
	
	@Override
	public String gethint() {
		return "Starts with the letter S \n";
	}

}
