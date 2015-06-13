package com.bayviewglen.ZorkGame;

import java.io.Serializable;

public class Item implements Serializable {
	private String name;
	private boolean weapon;
	private double hitPoint;
	private double attackDamage;
	private double armorPenetration;
	private double lifeSteal;
	private double critChance;
	private double armor;
	private double movementSpeed;
	private String description;
	private int weight;
	private int amount;
	
	
	// Various constructors for item class.
	
	public Item()	{
		
	}
	
	// Constructor for items that are not equipment (Notes, keys, etc)
	public Item(String name, String description, int weight){
		this.name = name;
		weapon = false;
		this.description = description;
		this.weight = weight;
		hitPoint = 0;
		attackDamage = 0;
		armorPenetration = 0;
		lifeSteal = 0;
		critChance = 0;
		armor = 0;
		movementSpeed = 0;
		amount = 1;
	}

	
	// Constructor for equipment type items (Swords, armor, etc)
	public Item(String name, boolean weapon, double hitPoint, double attackDamage,
			double armorPenetration, double lifeSteal, double critChance,
			double armor, double movementSpeed,	String description, int weight) {
		super();
		this.name = name;
		this.weapon = weapon;
		this.hitPoint = hitPoint;
		this.attackDamage = attackDamage;
		this.armorPenetration = armorPenetration;
		this.lifeSteal = lifeSteal;
		this.critChance = critChance;
		this.armor = armor;
		this.movementSpeed = movementSpeed;
		this.description = description;
		this.weight = weight;
		this.amount = 1;
	}

	
	// Various getters. Note that there is no setter because the stats of an item is not supposed to be changed.
	
	public String getName() {
		return name;
	}
	
	public boolean getWeapon() {
		return weapon;
	}

	public double getHitPoint() {
		return hitPoint;
	}

	public double getAttackDamage() {
		return attackDamage;
	}

	public double getArmorPenetration() {
		return armorPenetration;
	}

	public double getLifeSteal() {
		return lifeSteal;
	}

	public double getCritChance() {
		return critChance;
	}

	public double getArmor() {
		return armor;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public String getDescription() {
		return description;
	}

	public int getWeight() {
		return weight;
	}
	
	public int getAmount() {
		return amount;
	}
	
	
	// Various methods that interact with the item
	
	public void addAmount(int n) {
		amount += n;
	}
	
	public void decreaseAmount(int n) {
		amount -= n;
	}
	
}
