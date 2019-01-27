package game.map;

import game.items.ItemKey;
import game.util.GameUtil;

public class HouseOne implements House {
	
	

	@Override
	public boolean openDoor(ItemKey itemKey) {
		System.out.println(OPEN_DOOR_MSG);
		return true;
	}

	@Override
	public ItemKey solveEnigma(String answer) {
		if (answer.equalsIgnoreCase((ANSWER_ENGIMA_HOUSE_ONE)) ) {
			System.out.println(" ");
			System.out.println("[...] Yes, you are right!! You got the Key for the Second House \n");
			GameUtil.loadStory();
			System.out.println("[...] You're going back to the florest. Run and find the next House. \n");
			GameUtil.loadStory();
			return new ItemKey(KEY_TWO, "Key two for the Second House");
		}
		return null;
	}

	@Override
	public String getEnigma() {
		return ENIGMA_HOUSE_ONE;
	}

	@Override
	public String gethint() {
		return "Starts with the letter J \n";
	}

}
