package com.bayviewglen.ZorkGame;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Monster implements Serializable{
	private int level;
	private double hitPoint;
	private double attackDamage;
	private double armorPenetration;
	private double lifeSteal;
	private double critChance;
	private double armor;
	private double movementSpeed;
	private int exp;
	
	private boolean dead;
	
	public Monster(){
		
	}
	
	// Constructor for monster objects
	public Monster(int level) {
		this.level = level;
		hitPoint = level * 10;
		attackDamage = hitPoint + 5;
		armorPenetration = (level / 5) * 0.1;
		lifeSteal = 0;
		critChance = 0.01;
		armor = hitPoint / 2;
		movementSpeed = (level - 2) * 20 + 100;
		exp = ((int)(Math.random() * 10) + 1) + level * 50;	// Randomize the experience gained after killing each monster.
		dead = false;
	}

	
	// Various getters. Note that there is no setter because values are not supposed to be changed for a monster.
	
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
