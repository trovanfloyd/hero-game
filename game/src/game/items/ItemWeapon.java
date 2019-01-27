package game.items;

public class ItemWeapon extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemWeapon(String description, String detailDescription) {
		super(description, detailDescription);
		// TODO Auto-generated constructor stub
	}
	
	public void fire() {
		System.out.println("Firing...");
	}

}
