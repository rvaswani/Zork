package com.bayviewglen.ZorkGame;

public class item {
	private String name;
	private double hitPoint;
	private double attackDamage;
	private double armorPenetration;
	private double lifeSteal;
	private double critChance;
	private double armor;
	private double movementSpeed;
	private String specialAbility;
	private int goldValue;
	private String description;
	private int weight;
	
	public item()	{
		
	}
	
	// Constructor for items that are not equipment (Notes, keys, etc)
	public item(String name, String description, int goldValue, int weight){
		this.name = name;
		this.description = description;
		this.goldValue = goldValue;
		this.weight = weight;
		hitPoint = 0;
		attackDamage = 0;
		armorPenetration = 0;
		lifeSteal = 0;
		critChance = 0;
		armor = 0;
		movementSpeed = 0;
		specialAbility = "";
	}

	
	// Constructor for equipment type items (Swords, armor, etc)
	public item(String name, double hitPoint, double attackDamage,
			double armorPenetration, double lifeSteal, double critChance,
			double armor, double movementSpeed, String specialAbility, 
			int goldValue, String description, int weight) {
		super();
		this.name = name;
		this.hitPoint = hitPoint;
		this.attackDamage = attackDamage;
		this.armorPenetration = armorPenetration;
		this.lifeSteal = lifeSteal;
		this.critChance = critChance;
		this.armor = armor;
		this.movementSpeed = movementSpeed;
		this.specialAbility = specialAbility;
		this.goldValue = goldValue;
		this.description = description;
		this.weight = weight;
	}

	
	// Various getters. Note that there is no setter because the stats of an item is not supposed to be changed.
	
	public String getName() {
		return name;
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

	public String getSpecialAbility() {
		return specialAbility;
	}
	
	public int getGoldValue() {
		return goldValue;
	}

	public String getDescription() {
		return description;
	}

	public int getWeight() {
		return weight;
	}
	
	
}
