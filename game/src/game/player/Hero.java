package game.player;

import java.util.List;

import game.action.Action;
import game.items.Item;
import game.util.GameUtil;

public class Hero extends Character {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Style style;
	private List<Item> items;
	
	
	public void move(Action a) {
		System.out.println(a.getCommand());
		GameUtil.load();
	}
	
	public Item dropItem(Item item) {
		if(this.items.remove(item)) {
			return item;
		}
		else {
			return null;
		}
	}
	
	public boolean pickupItem(Item item){ 

		this.items.add(item);
		return true;
	}
	
	public String printItems() {
		StringBuilder sb = new StringBuilder();
		if (!getItems().isEmpty()) {
			getItems().forEach(i -> {
				sb.append(i.getDescription());
				sb.append(",");
			});
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
		
		return "";
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Hero [Name=" + getName() + ", Style=" + getStyle()
				+ ", Strength=" + getStrength() + ", Defense=" + getDefense()
				+ ", Life=" + getLife() + ", Experience=" + getExperience() + ", Items="  + printItems() + "]";
	}
	
	
}
