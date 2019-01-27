package game.action;

public enum Action {

	// Directional
	N ("NORTH", TypeAction.TYPE_DIRECTIONAL), 
	S ("SOUTH", TypeAction.TYPE_DIRECTIONAL),
	E("EAST", TypeAction.TYPE_DIRECTIONAL),
	W ("WEST", TypeAction.TYPE_DIRECTIONAL),
	
	// Batlle
	A ("ATTACK", TypeAction.TYPE_BATTLE),
	R ("RUN", TypeAction.TYPE_BATTLE),
	
	P ("PRESERVE", TypeAction.TYPE_GAME),
	
	NO_ACTION("", TypeAction.TYPE_UNKNOWN);
	
	private String command;
	private TypeAction typeAction;
	
	private Action(String command, TypeAction typeAction) {
		this.command = command;
		this.typeAction = typeAction;
	}

	public String getCommand() {
		return command;
	}

	public TypeAction getTypeAction() {
		return typeAction;
	}
	
	
	
}
