package com.bayviewglen.ZorkGame;

public class Monster {
	private String name;
	private int level;
	private double hitPoint;
	private double attackDamage;
	private double armorPenetration;
	private double lifeSteal;
	private double critChance;
	private double armor;
	private double movementSpeed;
	private int exp;
	private String description;
	private int goldValue;
	
	private boolean dead;
	
	public Monster(){
		
	}
	
	// Constructor for monster objects
	public Monster(String name, int level, String description) {
		this.name = name;
		this.level = level;
		hitPoint = level * 10;
		attackDamage = hitPoint + 5;
		armorPenetration = (level / 5) * 0.1;
		lifeSteal = 0;
		critChance = 0.01;
		armor = hitPoint / 2;
		movementSpeed = (level - 2) * 20 + 100;
		exp = ((int)(Math.random() * 21) - 10) + (level - 1) * 50;	// Randomize the experience gained after killing each monster.
		this.description = description;
		goldValue = ((int)(Math.random() * 11) - 5) + level * 10;	// Randomize the amount of gold gained after killing each monster.
		dead = false;
	}

	
	// Various getters. Note that there is no setter because values are not supposed to be changed for a monster.
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLevel() {
		return level;
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
	
	public int getExp()	{
		return exp;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}


	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getGoldValue() {
		return goldValue;
	}
	
	public boolean getDead() {
		return dead;
	}
	
	public void die() {
		dead = true;
	}
	
	public void revive() {
		dead = false;
	}
	
}
