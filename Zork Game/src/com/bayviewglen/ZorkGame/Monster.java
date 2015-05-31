package com.bayviewglen.ZorkGame;

public class Monster {
	private String name;
	private double hitPoint;
	private double attackDamage;
	private double armorPenetration;
	private double lifeSteal;
	private double critChance;
	private double armor;
	private double movementSpeed;
	private int exp;
	private String description;
	
	public Monster(){
		
	}
	
	// Constructor for monster objects
	public Monster(String name, double hitPoint, double attackDamage,
			double armorPenetration, double lifeSteal, double critChance,
			double armor, double movementSpeed, int exp, String description) {
		super();
		this.name = name;
		this.hitPoint = hitPoint;
		this.attackDamage = attackDamage;
		this.armorPenetration = armorPenetration;
		this.lifeSteal = lifeSteal;
		this.critChance = critChance;
		this.armor = armor;
		this.movementSpeed = movementSpeed;
		this.exp = ((int)(Math.random() * 21) - 10) + exp;	// Randomize the exp gained after killing each monster.
		this.description = description;
	}

	
	// Various getters. Note that there is no setter because values are not supposed to be changed for a monster.
	
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
	
	
	public int getExp()	{
		return exp;
	}


	public String getDescription() {
		return description;
	}
}
