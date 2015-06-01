package com.bayviewglen.ZorkGame;

import java.util.HashMap;
import java.util.Scanner;

public class Player {
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
	private String Description;
	private HashMap<String, item> inventory;
	
	public void printOptions() {
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
	}
	
	public Player() {
		
	}

	public Player(double maxHitPoint, double hitPoint, double attackDamage,
			double armorPenetration, double lifeSteal, double critChance,
			double armor, double movementSpeed, String specialAbility,
			int level, int exp, int wallet, String description,
			HashMap<String, item> inventory) {
		super();
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		name = input.nextLine();
		this.maxHitPoint = maxHitPoint;
		this.hitPoint = hitPoint;
		this.attackDamage = attackDamage;
		this.armorPenetration = armorPenetration;
		this.lifeSteal = lifeSteal;
		this.critChance = critChance;
		this.armor = armor;
		this.movementSpeed = movementSpeed;
		this.specialAbility = specialAbility;
		this.level = level;
		this.exp = exp;
		this.wallet = wallet;
		Description = description;
		this.inventory = inventory;
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
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public HashMap<String, item> getInventory() {
		return inventory;
	}

	public void setInventory(HashMap<String, item> inventory) {
		this.inventory = inventory;
	}
	
	
	
	
}
