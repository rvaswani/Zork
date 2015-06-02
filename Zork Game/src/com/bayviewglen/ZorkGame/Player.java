package com.bayviewglen.ZorkGame;

import java.util.HashMap;
import java.util.Scanner;

public class Player {
	private static String choice;
	private String name;
	private double maxHitPoint;
	private double hitPoint;
	private double attackDamage;
	private double armorPenetration;
	private double lifeSteal;
	private double critChance;
	private double armor;
	private double movementSpeed;
	private String specialAbility;
	private int level;
	private int exp;
	private int wallet;
	private String description;
	private HashMap<String, item> inventory;
	private int inventorySpace;
	private static double levelUpIndex = 1.2;
	
	
	
	// 3 character options for player to choose from
	public void printOptions() {
		Scanner input = new Scanner(System.in);
		System.out.println("Before you start the game, you have 3 characters to choose from: ");
		
		System.out.println("1. Master Yi");
		System.out.println("- Passive Ability \"Focus\": Attacks ignore 10% of enemy armor.");
		System.out.println("- Passive Ability \"Double Strike\":On every 3rd round, attack 2 times in a row and trigger \"Meditate\".");
		System.out.println("- Passive Ability \"Meditate\": Regain 10% health.");
		
		System.out.println("2. Twisted Fate");
		System.out.println("- Special Ability \"Pick A Card\": Draws a card from his card deck.");
		System.out.println("\tGreen Card --> Gain 20% max health. (20% Chance)");
		System.out.println("\tRed Card --> Deal 20% enemy max health as physical damage. (20% Chance)");
		System.out.println("\tGold Card --> Gain 5% extra gold after winning the battle. This effect can stack. (30% Chance)");
		System.out.println("\tBlack Card --> Gain 1% increase to all stats permanently. (20% Chance)");
		System.out.println("\tBlue Card --> Dodge enemy next attack/special ability. (15% Chance)");
		
		System.out.println("3. Nasus");
		System.out.println("- Passive Ability \"Thirst\": Has 10% life steal on any damage dealt to an enemy.");
		System.out.println("- Special Ability \"Siphoning Strike\": Deals 50% attack damage, but gain 3 attack damage permanently to this ability after everytime it is used.");
		
		boolean done = false;
		while (!done) {
			if (input.nextLine().equalsIgnoreCase("Master Yi") || Integer.parseInt(input.nextLine()) == 1) {
				choice = "Master Yi";
				done = true;
			}else if (input.nextLine().equalsIgnoreCase("Twisted Fate") || Integer.parseInt(input.nextLine()) == 2) {
				choice = "Twisted Fate";
				done = true;
			}else if (input.nextLine().equalsIgnoreCase("Nasus") || Integer.parseInt(input.nextLine()) == 3) {
				choice = "Nasus";
				done = true;
			}else{
				System.out.println("Please enter a proper choice of character!");
			}
		}
		
		input.close();
	}
	
	
	// Constructor of player class
	public Player() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		name = input.nextLine();
		maxHitPoint = 100;
		hitPoint = maxHitPoint;
		attackDamage = 20;
		armorPenetration = 0;
		lifeSteal = 0;
		critChance = 0.01;
		armor = 20;
		movementSpeed = 100;
		specialAbility = "";
		level = 1;
		exp = 0;
		wallet = 100;
		description = "";
		inventory = new HashMap<String, item>();
		inventorySpace = 20;
		
		input.close();
	}
	
	// Setting the character of the player
	public void setCharacter() {
		if (choice.equals("Master Yi")) {
			armorPenetration = 0.1;
			description = "You are Master Yi, the Wuju Bladesman.";
			specialAbility = "Focus, Meditate";
		}else if (choice.equals("Twisted Fate")) {
			description = "You are Twisted Fate, the Card Master.";
			specialAbility = "Pick A Card";
		}else if (choice.equals("Nasus")) {
			lifeSteal = 0.1;
			description = "You are Nasus, the Curator of the Sands.";
			specialAbility = "Siphoning Strike";
		}
	}
	
	
	// Various getters and setters for player stats. 
	
	public String getChoice() {
		return choice;
	}
	
	public String getName() {
		return name;
	}

	public double getMaxHitPoint() {
		return maxHitPoint;
	}

	public void setMaxHitPoint(double maxHitPoint) {
		this.maxHitPoint = maxHitPoint;
	}

	public double getHitPoint() {
		return hitPoint;
	}

	public void setHitPoint(double hitPoint) {
		this.hitPoint = hitPoint;
	}

	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
	}

	public double getArmorPenetration() {
		return armorPenetration;
	}

	public void setArmorPenetration(double armorPenetration) {
		this.armorPenetration = armorPenetration;
	}

	public double getLifeSteal() {
		return lifeSteal;
	}

	public void setLifeSteal(double lifeSteal) {
		this.lifeSteal = lifeSteal;
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public double getArmor() {
		return armor;
	}

	public void setArmor(double armor) {
		this.armor = armor;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public String getSpecialAbility() {
		return specialAbility;
	}

	public void setSpecialAbility(String specialAbility) {
		this.specialAbility = specialAbility;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, item> getInventory() {
		return inventory;
	}

	public void setInventory(HashMap<String, item> inventory) {
		this.inventory = inventory;
	}
	
	
	// Various methods that interact with the player
	
	// Check stats
	public void checkStats() {
		System.out.println("Character Type: " + choice);
		System.out.println("Level: " + level);
		System.out.println("Exp: " + exp + "/" + level * 100);
		System.out.println("You have " + wallet + "gold.");
		System.out.println("/nHP: " + hitPoint + "/" + maxHitPoint);
		System.out.println("Armor Penetration: " + armorPenetration + "%");
		System.out.println("Life Steal: " + lifeSteal + "%");
		System.out.println("Crit. Chance: " + critChance + "%");
		System.out.println("Armor: " + armor);
		System.out.println("Movement Speed: " + movementSpeed);
		System.out.println(specialAbility);
	}
	
	// Level up 
	public void levelUp() {
		maxHitPoint *= levelUpIndex;
		hitPoint = maxHitPoint;
		attackDamage *= levelUpIndex;
		armor *= levelUpIndex;
		movementSpeed *= levelUpIndex;
		exp =- level * 10;
		wallet += 100;
		level += 1;
	}
	
	// Obtain an item with specified amount
	public void addItem(item i, int n) {
		inventorySpace -= n;
		if (inventory.containsKey(i.getName())) {
			inventory.get(i.getName()).addAmount(n);
		}else{
			inventory.put(i.getName(), i);
			inventory.get(i.getName()).addAmount(n - 1);
		}
	}
	
	// Use an item with specified amount. This method is specifically for items like potions and keys
	public void useItem(item i, int n) {
		inventorySpace += n;
		if (!inventory.containsKey(i.getName())) {
			System.out.println("You do not have this item!");
		}else if (inventory.get(i.getName()).getAmount() != 1 && inventory.get(i.getName()).getAmount() >= n) {
			inventory.get(i.getName()).decreaseAmount(n);
		}else if (inventory.get(i.getName()).getAmount() != 1 && inventory.get(i.getName()).getAmount() < n) {
			System.out.println("You do not have enough of this item to use!");
		}else{
			if (n > 0) {
				inventory.remove(i.getName());
			}
		}
		
		if ((hitPoint + item.getHitPoint()) > maxHitPoint) {
			hitPoint = maxHitPoint;
		}else{
			hitPoint += item.getHitPoint();
		}
		
		attackDamage += item.getAttackDamage();
		armorPenetration += item.getArmorPenetration();
		lifeSteal += item.getLifeSteal();
		critChance += item.getCritChance();
		armor += item.getArmor();
		movementSpeed += item.getMovementSpeed();
		
	}
	
}