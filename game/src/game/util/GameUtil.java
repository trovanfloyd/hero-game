package game.util;

import java.util.concurrent.ThreadLocalRandom;

public class GameUtil {

	public static void load() {
		try{
			System.out.println("Loading...\n");
			Thread.sleep(3000);
		}
		catch(Exception e1) {
			// pass
		}
	}
	
	public static void loadStory() {
		try{
			System.out.println("...\n");
			Thread.sleep(4000);
		}
		catch(Exception e1) {
			// pass
		}
	}
	
	public static boolean validateInput(String input) {
		if (input == null || input.isEmpty()) {
			System.out.println("Invalid Information!");
			return false;
		}
		return true;
	}
	
	public static int generateNumberWithRange(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}
}
