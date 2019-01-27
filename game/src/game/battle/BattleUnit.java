package game.battle;

import game.player.Character;

public class BattleUnit {

	private Character player;
	private Character enemy;
	
	public BattleUnit(Character player, Character enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	public void produceDamage(Character character, int strenghtAttacker) {
		
		if (strenghtAttacker > character.getDefense()) {
			int damage = strenghtAttacker - character.getDefense();
			int life = character.getLife() - damage;
			
			System.out.println("Attack does " + damage + " damage!\n");
		    System.out.println(character.getName().toUpperCase() + "'s Life: " + life + " \n");

		    character.setLife(life);
		} else {
			 System.out.println(character.getName().toUpperCase() + " Defend!! \n");
		}
	    

	  
	}
	
	public Character getPlayer() {
		return player;
	}

	public Character getEnemy() {
		return enemy;
	}
	
	
	
	//ACtion///while, input//dados
}
