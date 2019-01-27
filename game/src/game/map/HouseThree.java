package game.map;

import game.items.ItemKey;
import game.util.GameUtil;

public class HouseThree implements House {

	@Override
	public boolean openDoor(ItemKey itemKey) {
		if (itemKey.getDescription().equals(KEY_THREE)) {
			System.out.println(OPEN_DOOR_MSG);
			return true;
		}
		return false;
	}

	@Override
	public ItemKey solveEnigma(String answer) {
		if (answer.equalsIgnoreCase((ANSWER_ENGIMA_HOUSE_THREE)) ) {
			System.out.println(" ");
			System.out.println("[...] Yes, you are right! You got the Key for the Forth House \n");
			GameUtil.loadStory();
			System.out.println("[...] You're going back to the florest. Run and find the next House. \n");
			GameUtil.loadStory();
			return new ItemKey(KEY_FOUR, "Key four for the Forth House");
		}
		return null;
	}

	@Override
	public String getEnigma() {
		return ENIGMA_HOUSE_THREE;
	}

	@Override
	public String gethint() {
		return "Starts with the letter W \n";
	}

}
