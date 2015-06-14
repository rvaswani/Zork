package com.bayviewglen.ZorkGame;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Battle implements Serializable {
	
	private Player player;
	private Monster monster;
	
	private double playerHP;
	private double monsterHP;
	private int roundCount;
	private boolean escape;
	
	private int result;
	
	
	public Battle() {
		
	}
	
	public Battle(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		playerHP = player.getHitPoint();
		monsterHP = monster.getHitPoint();
		roundCount = 0;
		escape = player.getMovementSpeed() > monster.getMovementSpeed();
	}
	
	public void fight() {
		boolean done = false;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~BATTLE BEGINS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		while (!done) {
			roundCount++;
			boolean dodge = false;
			System.out.println("Player HP: " + playerHP);
			System.out.println("Monster HP: " + monsterHP);
			
			displayAbilities();
			System.out.print("\n> ");
			double damage = readInput();
			if (damage == 0) {
				escapeBattle();
			}else if (damage == 1) {
				// Do nothing since player took action already
			}else if (damage == -1){
				dodge = true;
			}else{
				dealDamage(damage, dodge, done);
			}
			
			if (result == 1) 
				done = true;
			if (result == 2)
				done = true;
			if (result == 3)
				done = true;
			
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END OF BATTLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	
	// Getter for battle result
	public int getResult() {
		return result;
	}
	
	
	//Various methods for the battle
	
	private void dealDamage(double damage, boolean dodge, boolean done) {
		
		// Player deal damage to monster
		if ((int)(Math.random() * (100 / (100 * player.getCritChance()))) == 0){
			damage *= 2;
		}
		if (player.getChoice().equals("Master Yi") && roundCount % 3 == 0) {
				monsterHP -= (damage - monster.getArmor() * (1 - player.getArmorPenetration()));
				monsterHP -= (damage - monster.getArmor() * (1 - player.getArmorPenetration()));
				if (playerHP + 0.1 * player.getMaxHitPoint() > player.getMaxHitPoint()) {
					playerHP = player.getMaxHitPoint();
					System.out.println("You healed to max HP!");
				}else{
					playerHP += 0.1 * player.getMaxHitPoint();
					System.out.println("You healed 10%!");
				}
				System.out.println("You successfully dealt " + 2 * (damage - monster.getArmor() * (1 - player.getArmorPenetration())) + " damage to the monster!");
		}else{
			if (damage - monster.getArmor() * (1 - player.getArmorPenetration()) < 0) {
				System.out.println("Your attack can't even break through monster's armor!");
			}else{
				monsterHP -= (damage - monster.getArmor() * (1 - player.getArmorPenetration()));
				System.out.println("You successfully dealt " + (damage - monster.getArmor() * (1 - player.getArmorPenetration())) + " damage to the monster!");
			}
		}
		
		if ((playerHP + damage * player.getLifeSteal()) >= player.getMaxHitPoint()) {
			playerHP = player.getMaxHitPoint();
		}else{
			playerHP += damage * player.getLifeSteal();
		}
		
		if (monsterHP <= 0) {
			victory();
		}
		
		if (monsterHP > 0) {
			// Monster deal damage to player
			if (!dodge) {
				if (monster.getAttackDamage() - player.getArmor() * (1 - monster.getArmorPenetration()) < 0) {
					System.out.println("Monster's attack can't even break through your armor!");
				}else{
					playerHP -= (monster.getAttackDamage() - player.getArmor() * (1 - monster.getArmorPenetration()));
					System.out.println("Monster dealt " + player.getArmor() * (1 - monster.getArmorPenetration()) + " damage");
				}
			}else{
				System.out.println("You dodged the attack!");
			}
			if (playerHP <= 0) {
				defeat();
			}
		}
	}

	private void defeat() {
		System.out.println("You lost...");
		System.out.println("You will now go back to the beginning of this floor.");
		player.setHitPoint(player.getMaxHitPoint());
		result = 3;
		
	}

	private void victory() {
		System.out.println("You won!");
		player.setExp(monster.getExp());
		System.out.println("You gained " + monster.getExp() + " exp!");
		while (player.getExp() > player.getLevel() * 100) {
			player.levelUp();
			System.out.println("You leveled up! Level " + (player.getLevel() - 1) + " --> Level " + player.getLevel() + ".");
		}
		player.setHitPoint(playerHP);
		result = 1;
		monster.die();
	}

	private void escapeBattle() {
		System.out.println("You escaped.");
		result = 2;
		
	}

	private double readInput() {
		Scanner input = new Scanner(System.in);
		double x = readAction(input);			// The x here represent the action number
		double playerDamage = interpretDamage(x);
		return playerDamage;
	}

	private double interpretDamage(double x) {
		if (x == 0) {
			return 0;
		}else if (x == 0.05) {
			System.out.println("You used Gold Card! You scored some style points, although they don't really do anything...");
			return 1;
		}else if (x == 0.01) {
			player.blackCard();
			System.out.println("You used Black Card! Your stats increased 1% permanently!");
			return 1;
		}else if (x == -0.2) {
			if (playerHP + 0.2 * player.getMaxHitPoint() > player.getMaxHitPoint()) {
				playerHP = player.getMaxHitPoint();
				System.out.println("You used Green Card! You healed to max HP!");
				return 1;
			}else{
				playerHP += 0.2 * player.getMaxHitPoint();
				System.out.println("You used Green Card! You healed 20%!");
				return 1;
			}
		}else if (x == -0.1) {
			if (playerHP + 0.1 * player.getMaxHitPoint() > player.getMaxHitPoint()) {
				playerHP = player.getMaxHitPoint();
				System.out.println("You healed to max HP!");
				return 1;
			}else{
				playerHP += 0.1 * player.getMaxHitPoint();
				System.out.println("You healed 10%!");
				return 1;
			}
		}else if (x == -100) {
			System.out.println("You used blue card! You dodged monster's next attack!");
			return -1;
		}else if (x == 0.2) {
			System.out.println("You used Red Card! You dealt 20% enemy health as physical damage.");
			return monsterHP*0.2;
		}else{
			return x;
		}
			
		
	}

	private double readAction(Scanner input) {
		boolean done = false;
		while (!done) {
			String action = input.nextLine();
			if (action.equalsIgnoreCase("Attack")) {
				return player.getAttackDamage();
			}else if (player.getChoice().equals("Master Yi") && action.equalsIgnoreCase("Meditate")) {
				return -0.1;	// Negative damage means heal to player
				
			}else if (player.getChoice().equals("Twisted Fate") && action.equalsIgnoreCase("Pick A Card")) {
				int x = (int)(Math.random() * 100) + 1;
				if (x <= 15) {
					return -0.2;		// Heal 20%
				}else if (x <= 35) {
					return 0.2;			// Deal 20% Damage
				}else if (x <= 65) {
					return 0.05;		
				}else if (x < 85) {
					return 0.01;		// 1% permanent stats
				}else{
					return -100;		// Dodge next attack
				}
				
			}else if (player.getChoice().equals("Nasus") && action.equalsIgnoreCase("Siphoning Strike")) {
				double damage = 0.5 * player.getAttackDamage() + 3 * player.getSiphoningStrikeCount();
				player.addSiphoningStrikeCount();
				return damage;
				
			}else if (action.equalsIgnoreCase("Run")) {
				if (escape) {
					return 0;	// 0 damages means player wants to run away
				}else{
					System.out.println("You cannot escape this battle!");
				}
			}else{
				System.out.println("Please enter a proper action.");
			}
		}
		
		return 0;
	}

	private void displayAbilities() {
		System.out.println("What are you going to do?\n");
		System.out.println("1. Attack");
		System.out.println("2. Special Ability");
		if (player.getChoice().equals("Master Yi")) {
			System.out.println(" - Focus (Passive)");
			System.out.println(" - Double Strike (Passive)");
			System.out.println(" - Meditate (Active)");
		}
		if (player.getChoice().equals("Twisted Fate")) {
			System.out.println(" - Pick A Card (Active)");
		}
		if (player.getChoice().equals("Nasus")) {
			System.out.println(" - Thirst (Passive)");
			System.out.println(" - Siphoning Strike (Active)");
		}
		System.out.println("3. Run!");
	}
	

}
