package com.bayviewglen.ZorkGame;

import java.util.Scanner;

public class Battle {
	
	private Player player;
	private Monster monster;
	
	private double playerHP;
	private double monsterHP;
	private int roundCount;
	private boolean escape;
	private int stacksOfGold;
	
	private boolean result;
	
	
	public Battle() {
		
	}
	
	public Battle(Player player, Monster monster) {
		this.player = player;
		this.monster = monster;
		playerHP = player.getHitPoint();
		monsterHP = monster.getHitPoint();
		roundCount = 0;
		stacksOfGold = 0;
		escape = player.getMovementSpeed() > monster.getMovementSpeed();
	}
	
	public void fight() {
		System.out.println(player.getName() + "\t" + monster.getName());
		boolean done = false;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~BATTLE BEGINS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		while (!done) {
			roundCount++;
			boolean dodge = false;
			System.out.println("Player HP: " + playerHP);
			System.out.println("Monster HP: " + monsterHP);
			
			displayAbilities();
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
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END OF BATTLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

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
			monsterHP -= (damage - monster.getArmor() * (1 - player.getArmorPenetration()));
			System.out.println("You successfully dealt " + (damage - monster.getArmor() * (1 - player.getArmorPenetration())) + " damage to the monster!");
		}
		if ((playerHP + damage * player.getLifeSteal()) >= player.getMaxHitPoint()) {
			playerHP = player.getMaxHitPoint();
		}else{
			playerHP += damage * player.getLifeSteal();
		}
		if (monsterHP <= 0) {
			victory();
		}
		
		
		// Monster deal damage to player
		if (!dodge) {
			playerHP -= (monster.getAttackDamage() - player.getArmor() * (1 - monster.getArmorPenetration()));
			System.out.println("Monster dealt " + player.getArmor() * (1 - monster.getArmorPenetration()) + " damage");
		}else{
			System.out.println("You dodged the attack!");
		}
		if (playerHP <= 0) {
			defeat();
		}
		
	}

	private void defeat() {
		// TODO Auto-generated method stub
		
	}

	private void victory() {
		System.out.println("You won!");
		player.setExp(monster.getExp());
		while (player.getExp() > player.getLevel() * 10) {
			player.levelUp();
			System.out.println("You leveled up! Level " + (player.getLevel() - 1) + " --> Level " + player.getLevel() + ".");
		}
		System.out.println("You won some amount of gold!");
		player.setWallet((int)(player.getWallet() + monster.getGoldValue() * stacksOfGold * 1.05)); 
		player.setHitPoint(playerHP);
		monster.die();
	}

	private void escapeBattle() {
		// TODO Auto-generated method stub
		
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
			stacksOfGold++;
			System.out.println("You used Gold Card! You increased your gold gained for this battle!");
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
			if (input.nextLine().equalsIgnoreCase("Attack") || Integer.parseInt(input.nextLine()) == 1) {
				return player.getAttackDamage();
			}else if (input.nextLine().equalsIgnoreCase("Special Ability") || Integer.parseInt(input.nextLine()) == 2) {
				System.out.println("Which ability?");
				if (player.getChoice().equals("Master Yi")) {
					if (input.nextLine().equalsIgnoreCase("Meditate"))
						return -0.1;	// Negative damage means heal to player
				}
				if (player.getChoice().equals("Twisted Fate")) {
					if (input.nextLine().equalsIgnoreCase("Pick A Card")) {
						int x = (int)(Math.random() * 100) + 1;
						if (x <= 15) {
							return -0.2;		// Heal 20%
						}else if (x <= 35) {
							return 0.2;			// Deal 20% Damage
						}else if (x <= 65) {
							return 0.05;		// 5% bonus gold
						}else if (x < 85) {
							return 0.01;		// 1% permanent stats
						}else{
							return -100;		// Dodge next attack
						}
					}
				}
				if (player.getChoice().equals("Nasus")) {
					if (input.nextLine().equalsIgnoreCase("Siphoning Strike")){
						return 0.5 * player.getAttackDamage() + 3 * player.getSiphoningStrikeCount();
					}
				}
			
			}else if (input.nextLine().equalsIgnoreCase("Run") || Integer.parseInt(input.nextLine()) == 3) {
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
