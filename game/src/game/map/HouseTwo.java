package game.map;

import game.items.ItemKey;
import game.util.GameUtil;

public class HouseTwo implements House {

	@Override
	public boolean openDoor(ItemKey itemKey) {
		if (itemKey.getDescription().equals(KEY_TWO)) {
			System.out.println(OPEN_DOOR_MSG);
			return true;
		}
		return false;
	}

	@Override
	public ItemKey solveEnigma(String answer) {
		if (answer.equalsIgnoreCase((ANSWER_ENGIMA_HOUSE_TWO)) ) {
			System.out.println(" ");
			System.out.println("[...] Yes, you are right! You got the Key for the Third House \n");
			GameUtil.loadStory();
			System.out.println("[...] You're going back to the florest. Run and find the next House. \n");
			GameUtil.loadStory();
			return new ItemKey(KEY_THREE, "Key Three for the Third House");
		}
		return null;
	}

	@Override
	public String getEnigma() {
		return ENIGMA_HOUSE_TWO;
	}
	
	@Override
	public String gethint() {
		return "Starts with the letter R \n";
	}

}
