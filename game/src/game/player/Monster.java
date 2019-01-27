package game.player;

public class Monster extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Monster [Name=" + getName() + ", Strength=" + getStrength() + ", Defense=" + getDefense()
				+ ", Life=" + getLife()  + "]";
	}
}
