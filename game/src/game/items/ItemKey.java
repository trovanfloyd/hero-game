package game.items;

public class ItemKey extends Item {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemKey(String description, String detailDescription) {
		super(description, detailDescription);
	}

	public void open() {
		System.out.println("open");
	}
}
