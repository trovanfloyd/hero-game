package game.player;

import java.io.Serializable;

import game.battle.Dice;
import game.util.GameUtil;

public abstract class Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer strength, defense, life, experience;
	
	/**
	 * 
	 * @return true if attack was successful, otherwise false
	 */
	public boolean attack() {
		Dice dice = new Dice();
		int playerDice = dice.roll();
		int enemyDice = dice.roll();
		System.out.println("> " + name.toUpperCase() + "'s Dice: " + playerDice + " \n");
		GameUtil.loadStory();
		System.out.println("> Second Player's Dice: " + enemyDice + " \n");
		GameUtil.loadStory();
		if (playerDice > enemyDice) {
			System.out.println("Hit!! \n");
			return true;
		} else {
			System.out.println("Attack Missed! \n");
			return false;
		}
	}
	
	public boolean isAlive() {
	    return life > 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	
}
