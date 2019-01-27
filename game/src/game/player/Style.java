package game.player;

public enum Style {

	WARRIOR (1, "Warrior"), 
	MAGICIAN (2, "Magician"), 
	ARCHER (3, "Archer");
	
	private Integer value;
	private String description;
	
	private Style (Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	
}
