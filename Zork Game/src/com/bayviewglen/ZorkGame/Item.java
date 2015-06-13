package com.bayviewglen.ZorkGame;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable {
	private String name;
	private boolean weapon;
	private double hitPoint;
	private double maxHitPoint;
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
		maxHitPoint = 0;
		attackDamage = 0;
		armorPenetration = 0;
		lifeSteal = 0;
		critChance = 0;
		armor = 0;
		movementSpeed = 0;
		amount = 1;
	}

	
	// Constructor for equipment type items (Swords, armor, etc)
	public Item(String name, boolean weapon, double hitPoint, double maxHitPoint,
			double attackDamage, double armorPenetration, double lifeSteal, 
			double critChance, double armor, double movementSpeed,	
			String description, int weight) {
		super();
		this.name = name;
		this.weapon = weapon;
		this.hitPoint = hitPoint;
		this.maxHitPoint = maxHitPoint;
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
	
	// Constructor for specific items
	
	// Constructor for a "key"
	public Item(String item) {
		if (item.equals("Key")) {
			name = "Key";
			weapon = false;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 0;
			description = "Key";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("HP Potion")) {
			name = "HP Potion";
			weapon = false;
			hitPoint = 1000000;	// To refill player HP back to full
			maxHitPoint = 0;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 0;
			description = "A clear bottle with bright red liquid inside...It's a HP Potion that can refill your HP back to full.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("AD Potion")) {
			name = "AD Potion";
			weapon = false;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 30;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 0;
			description = "A clear bottle with blue liquid inside...It's an AD Potion that grants you increased attack damage permanently.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("Max HP Potion")) {
			name = "Max HP Potion";
			weapon = false;
			hitPoint = 0;
			maxHitPoint = 30;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 0;
			description = "A clear bottle with dark red liquid... It's a Max HP Potion that grants you increased maximum HP permanently.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("Armor Potion")) {
			name = "Armor Potion";
			weapon = false;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 30;
			movementSpeed = 0;
			description = "A clear bottle with golden liquid... It's an Armor Potion that grants you increased armor permanently.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("Movement Speed Potion")) {
			name = "Movement Speed Potion";
			weapon = false;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 100;
			description = "A clear bottle with nothing inside?! Open it, and you will feel the power of the wind, giving you increased movement speed permanently.";
			weight = 1;
			amount = 1;
		}
		
		
		
		if (item.equals("M16")) {
			name = "M16";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 20;
			armorPenetration = 0;
			lifeSteal = 0.1;
			critChance = 0;
			armor = 0;
			movementSpeed = 0;
			description = "A rookie rifle. However, you know that CF God Rishi Vaswani used it before. Therefore this weapon is blessed by him. You have nothing to fear.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("Pantheon's Helmet")) {
			name = "Pantheon's Helmet";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 50;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 20;
			movementSpeed = 10;
			description = "Helmet from Pantheon, the Artisan of War.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("Combat Axe")) {
			name = "Combat Axe";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 50;
			attackDamage = 45;
			armorPenetration = 0;
			lifeSteal = 0.15;
			critChance = 0;
			armor = 0;
			movementSpeed = -20;
			description = "A strong axe that can aid you during fights. CF God Rishi Vaswani recommended...This axe must be awesome.";
			weight = 3;
			amount = 1;
		}
		
		if (item.equals("Blade of the Ruined King")) {
			name = "Blade of the Ruined King";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 80;
			armorPenetration = 0.2;
			lifeSteal = 0.15;
			critChance = 0;
			armor = 0;
			movementSpeed = 30;
			description = "This is a blade, from a ruined king...";
			weight = 2;
			amount = 1;
		}
		
		if (item.equals("Braum's Shield")) {
			name = "Braum's Shield";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 60;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 50;
			movementSpeed = -50;
			description = "This huge heavy shield from Braum, the Heart of Freljord, is so strong that can almost protect you from any danger.";
			weight = 3;
			amount = 1;
		}
		
		if (item.equals("Boots of Swiftness")) {
			name = "Boots of Swiftness";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 1000;
			description = "Boots of swiftness can take you anywhere with in a matter of seconds.";
			weight = 1;
			amount = 1;
		}
		
		if (item.equals("Warmog's Armor")) {
			name = "Warmog's Armor";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 300;
			attackDamage = 0;
			armorPenetration = 0;
			lifeSteal = 0.15;
			critChance = 0;
			armor = 50;
			movementSpeed = -50;
			description = "A necessity for brave warrior like you :)";
			weight = 3;
			amount = 1;
		}
		
		if (item.equals("M4A1")) {
			name = "M4A1";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 90;
			armorPenetration = 0.7;
			lifeSteal = 0;
			critChance = 0;
			armor = 0;
			movementSpeed = 0;
			description = "\"A sophisticated rifle with good fire power and firing rate. Recoil is good and can pierce through armor well.\" From CF God Rishi Vaswani";
			weight = 4;
			amount = 1;
		}
		
		if (item.equals("AWM")) {
			name = "AWM";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 100;
			armorPenetration = 0.5;
			lifeSteal = 0;
			critChance = 0.3;
			armor = 0;
			movementSpeed = -50;
			description = "A powerful sniper rifle from CF God Rishi Vaswani. You can feel its power in your hands...";
			weight = 5;
			amount = 1;
		}
		
		if (item.equals("Infinity Edge")) {
			name = "Infinity Edge";
			weapon = true;
			hitPoint = 0;
			maxHitPoint = 0;
			attackDamage = 1000;
			armorPenetration = 1;
			lifeSteal = 0;
			critChance = 0.8;
			armor = 0;
			movementSpeed = 0;
			description = "The best weapon that can prepare you for the final battle...against the evil king Mr D.";
			weight = 5;
			amount = 1;
		}
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
	
	public double getMaxHitPoint() {
		return maxHitPoint;
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
