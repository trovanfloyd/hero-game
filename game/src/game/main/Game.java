package game.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import game.action.Action;
import game.battle.BattleUnit;
import game.battle.Dice;
import game.items.Item;
import game.items.ItemKey;
import game.map.House;
import game.map.Map;
import game.player.Hero;
import game.player.Monster;
import game.player.Style;
import game.util.GameUtil;

public class Game {
	
	private static final Item itemKeyTwo = new ItemKey("KEY_TWO", "Key Two for the Second House");
	private static final Item itemKeyThree = new ItemKey("KEY_THREE", "Key Three for the Third House");
	private static final Item itemKeyFour = new ItemKey("KEY_FOUR", "Key Four for the Forth House");
	private static final Item itemKeyEnd = new ItemKey("END", "END");
	
	boolean isPlayerTurnBattle = true;
	
	Scanner scanner;
	Hero player;
	
	public Game() {
		this.scanner = new Scanner(System.in);
		
		Map.history();
		
		if (!verifyLoadHero()) {
			this.player = createHero();
		}
	}
	
	private boolean verifyLoadHero() {
		System.out.println("Do you want to load a game? y/n \n");
		System.out.print("> ");
		String input = this.scanner.nextLine();
		
		if (GameUtil.validateInput(input)) {
			if (input.equalsIgnoreCase("y")) {
				return loadGame();
			}
		}
		return false;
	}
	
	private boolean loadGame() {
		
		try {
			GameUtil.load();
			
			FileInputStream fi = new FileInputStream(new File("hero.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			Hero hero = (Hero) oi.readObject();
			this.player = hero;

			oi.close();
			fi.close();
			
			return true;
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found \n");
			return false;
		} catch (IOException e) {
			System.out.println("Error initializing stream \n");
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void saveGame(Hero player) {
		try {
			FileOutputStream f = new FileOutputStream(new File("hero.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(player);

			o.close();
			f.close();
			
			System.out.println("The game has been saved! \n");
			
		} catch (IOException e) {
			System.out.println("Error initializing stream \n");
		} 
	}
	
	private Hero createHero() {
		
		System.out.println("Please, let's create the Character...");
		
		Hero hero = new Hero();
		
		while(true) {
			
			if (hero.getName() == null || hero.getName().isEmpty()) {
				
				System.out.println("First, please inform your name:\n");
				
				System.out.print("> ");
				String name = this.scanner.nextLine();
				
				if (GameUtil.validateInput(name)) {
					hero.setName(name);
				} else {
					continue;
				}
			}
			
			System.out.println(" ");
			System.out.println("Ok " + hero.getName().toUpperCase() + ", now let's select what kind of player you are...\n");
			for(Style style: Style.values()) {
				System.out.println(style.getValue() + " - " + style.getDescription());
			}
			System.out.println(" ");
			
			System.out.print("> ");
			String style = this.scanner.nextLine();
			
			if (GameUtil.validateInput(style)) {
				Style styleChoosed = validateStyle(style);
				if (styleChoosed != null) {
					hero.setStyle(styleChoosed);
				} else {
					continue;
				}
			} else {
				continue;
			}
			
			setAttributes(hero);
			
			hero.setItems(new ArrayList<Item>());
			
			break;
			
		}
		System.out.println(" ");
		Map.drawCharacter();
		System.out.println(hero.getName().toUpperCase() + " that's your Skills as a " + hero.getStyle().getDescription() + ": \n");
		System.out.println(hero.toString());
		System.out.println(" ");
		
		return hero;
		
	}
	
	private Style validateStyle(String style) {
		if (Style.WARRIOR.getValue().toString().equals(style)) {
			return Style.WARRIOR;
			
		} else if (Style.ARCHER.getValue().toString().equals(style)) {
			return Style.ARCHER;
			
		} else if (Style.MAGICIAN.getValue().toString().equals(style)) {
			return Style.MAGICIAN;
		} else {
			System.out.println("Invalid Information!");
			return null;
		}
	}
	
	private void setAttributes(Hero hero) {
		if (hero.getStyle().compareTo(Style.WARRIOR) == 0) {
			hero.setDefense(20);
			hero.setStrength(30);
		} else if (hero.getStyle().compareTo(Style.ARCHER) == 0) {
			hero.setDefense(15);
			hero.setStrength(25);
			
		} else if (hero.getStyle().compareTo(Style.MAGICIAN) == 0) {
			hero.setDefense(15);
			hero.setStrength(40);
		}
		
		hero.setLife(100);
		hero.setExperience(0);
	}

	public void start() {
		
		GameUtil.load();
		
		String input = "";
		while(input.compareTo("quit") != 0) {
			if (this.player.getItems().contains(itemKeyEnd) ||
					!this.player.isAlive()) {
				break;
			}
			Map.showflorest();
			System.out.print("> ");
			input = this.scanner.nextLine();
			Action a = interpretActionInput(input);
			switch(a.getTypeAction()) {
				case TYPE_DIRECTIONAL:
					move(a);
					explore();
					break;
				case TYPE_GAME:
					saveGame(this.player);
					input = "quit";
					break;
			default:
				break;
			}
		}
		
		System.out.println("The Game has been ended!!!");
		
	}
	
	private Action interpretActionInput(String input) {

		if(input == null || input.equals("")) {
			return Action.NO_ACTION;
		} else {
			try {
				Action action = Action.valueOf(input.toUpperCase());
				return action;
			} catch (Exception e) {
				System.out.println("Invalid value");
			}
			return Action.NO_ACTION;
		}
	}
	
	private void explore() {
		Dice dice = new Dice();
		int rollValue = dice.roll();
		
		if (rollValue < 3) {
			System.out.println("It seems you found a monster!! \n");
			Map.drawMonster();
			GameUtil.load();
			startBattleMode();
			GameUtil.load();
			
		} else if (rollValue > 4) {
			
			System.out.println("It seems you found a house!! \n");
			Map.drawHouse();
			System.out.println(" ");
			List<Item> listItemKey = this.player.getItems().stream().filter(k -> k instanceof ItemKey).collect(Collectors.toList());
			if (listItemKey.contains(itemKeyFour)) {
				exploreHouse(Map.findFourthHouse());
			} else if (listItemKey.contains(itemKeyThree)) {
				exploreHouse(Map.findThirdHouse());
			} else if (listItemKey.contains(itemKeyTwo)) {
				exploreHouse(Map.findSecondHouse());
			} else {
				exploreHouse(Map.findFirstHouse());
			}
			
		} else {
			System.out.println("Keep going!! \n");
		}
	}
	
	private void startBattleMode() {
		System.out.println("Mode battle is starting \n");
		GameUtil.loadStory();
		
		BattleUnit battleUnit = new BattleUnit(this.player, createMonster());
		
		System.out.println(battleUnit.getPlayer().toString());
		System.out.println(" ");
		System.out.println(battleUnit.getEnemy().toString());
		System.out.println(" ");
		
		isPlayerTurnBattle = true;
		String input = "";
		while(verifyBatteStatus(battleUnit)) {
			//RUN
			if (input.equalsIgnoreCase("r")) {
				break;
			}
			if (isPlayerTurnBattle) {
				System.out.println("[...] It's your turn. What do you wanna do? Attack (a) Run (r) \n");
				
				System.out.print("> ");
				input = this.scanner.nextLine();
			} else {
				System.out.println("[...] Monster's turn. \n");
				GameUtil.loadStory();
				input = "A";
			}
			
			Action a = interpretActionInput(input);
			switch(a.getTypeAction()) {
				case TYPE_BATTLE:
					executeBattleAction(a, battleUnit);
					break;
			default:
				break;
			}
		}
		
	}
	
	private Monster createMonster() {
		Monster monster = new Monster();
		monster.setName("Monster");
		monster.setLife(GameUtil.generateNumberWithRange(30, 100));
		monster.setStrength(GameUtil.generateNumberWithRange(15, 40));
		monster.setDefense(GameUtil.generateNumberWithRange(1, 10));
		
		return monster;
	}
	
	private boolean verifyBatteStatus(BattleUnit battleUnit) {
		if (battleUnit.getPlayer().isAlive() && battleUnit.getEnemy().isAlive()) {
			return true;
		} else {
			
			if (!battleUnit.getPlayer().isAlive()) {
				System.out.println("You has Died! The Game has been ended! \n");
			} else if (!battleUnit.getEnemy().isAlive()) {
				System.out.println("You Win!!! The Monster has Died!! \n");
				
				improveExperience();
				
				System.out.println("Your life has been recovered \n");
				this.player.setLife(100);
			}
			return false;
		}
	}
	
	private void improveExperience() {
		System.out.println("You've gotten 10xp experience \n");
		this.player.setExperience(10);
		System.out.println(this.player.getName().toUpperCase() + "'s experience " + this.player.getExperience() + " \n");
		if (this.player.getExperience().intValue() == 50) {
			System.out.println("You've improved your strength +5 \n");
			this.player.setStrength(this.player.getStrength() + 5);
			
			System.out.println("You've improved your defense +5 \n");
			this.player.setDefense(this.player.getDefense() + 5);
			
			this.player.setExperience(0);
		}
	}

	private void executeBattleAction(Action a, BattleUnit battleUnit) {
		
		switch(a) {
			case A:
				if (isPlayerTurnBattle) {
					System.out.println(" ");
					System.out.println("Player is attacking... \n");
					Map.drawCharacterAttack();
					if (battleUnit.getPlayer().attack()) {
						battleUnit.produceDamage(battleUnit.getEnemy(), battleUnit.getPlayer().getStrength());
					}
					isPlayerTurnBattle = false;
				} else {
					System.out.println(" ");
					System.out.println("Monster is attacking... \n");
					Map.drawMonsterAttack();
					if (battleUnit.getEnemy().attack()) {
						battleUnit.produceDamage(battleUnit.getPlayer(), battleUnit.getPlayer().getStrength());
					}
					isPlayerTurnBattle = true;
				}
				break;
			case R:
				System.out.println("You RUN! \n");
				break;
		default:
			System.out.println("Invalid Command!");
			break;
		}
		
	}

	private void exploreHouse(House house) {
		
		System.out.println(house.getEnigma());
		
		int numberAttempts = 0;
		
		while(true) {
			
			System.out.print("> ");
			String answer = this.scanner.nextLine();
			ItemKey itemKey = house.solveEnigma(answer);
			if (itemKey == null) {
				if (numberAttempts == 2) {
					house.gethint();
				}
				System.out.println("Try again.. \n");
				numberAttempts++;
				continue;
			} else {
				this.player.getItems().add(itemKey);
				break;
			}
		}
	}
	
	private void move(Action a) {
		this.player.move(a);
	}
}
