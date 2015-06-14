package com.bayviewglen.ZorkGame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Player implements Serializable {
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
	private String description;
	private HashMap<String, Item> inventory;
	private int inventorySpace;
	private static double levelUpIndex = 1.2;
	private int siphoningStrikeCount;			// Exclusive skill to character "Nasus"
	
	
	
	// 3 character options for player to choose from
	public void printOptions(Scanner scanner) {
		// Scanner input = new Scanner(System.in);
		// input.reset();
		System.out.println("\nBefore you start the game, you have 3 characters to choose from: ");
		
		System.out.println("1. Master Yi");
		System.out.println("- Passive Ability \"Focus\": Attacks ignore 10% of enemy armor.");
		System.out.println("- Passive Ability \"Double Strike\":On every 3rd round, attack 2 times in a row and trigger \"Meditate\".");
		System.out.println("- Passive Ability \"Meditate\": Regain 10% health.");
		
		System.out.println("2. Twisted Fate");
		System.out.println("- Special Ability \"Pick A Card\": Draws a card from his card deck.");
		System.out.println("\tGreen Card --> Gain 20% health. (20% Chance)");
		System.out.println("\tRed Card --> Deal 20% enemy max health as physical damage. (20% Chance)");
		System.out.println("\tGold Card --> Gain some style points. Style point is a sign that you are good at this game. Be proud. (30% Chance)");
		System.out.println("\tBlack Card --> Gain 1% increase to all stats permanently. (20% Chance)");
		System.out.println("\tBlue Card --> Dodge enemy next attack/special ability. (15% Chance)");
		
		System.out.println("3. Nasus");
		System.out.println("- Passive Ability \"Thirst\": Has 10% life steal on any damage dealt to an enemy.");
		System.out.println("- Special Ability \"Siphoning Strike\": Deals 50% attack damage, but gain 3 attack damage permanently to this ability after everytime it is used.");
		
		System.out.println("\nWhat's your choice?");
		
		boolean done = false;
		while (!done) {
			String nextline = scanner.nextLine();
			if (nextline.equalsIgnoreCase("Master Yi")) {
				choice = "Master Yi";
				done = true;
			}else if (nextline.equalsIgnoreCase("Twisted Fate")) {
				choice = "Twisted Fate";
				done = true;
			}else if (nextline.equalsIgnoreCase("Nasus")) {
				choice = "Nasus";
				done = true;
			}else{
				System.out.println("Please enter a proper choice of character!");
			}
		}
		
		//input.close();
	}
	
	
	// Constructor of player class
	public Player(Scanner scanner) {
		//Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name: ");
		name = scanner.nextLine();
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
		description = "";
		inventory = new HashMap<String, Item>();
		inventorySpace = 20;
		
		//input.close();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, Item> getInventory() {
		return inventory;
	}

	public void setInventory(HashMap<String, Item> inventory) {
		this.inventory = inventory;
	}
	
	public int getSiphoningStrikeCount() {
		return siphoningStrikeCount;
	}
	
	public void addSiphoningStrikeCount() {
		siphoningStrikeCount++;
	}
	
	
	

	// Various methods that interact with the player
	
	// Check stats
	public void checkStats() {
		System.out.println("Character Type: " + choice);
		System.out.println("Level: " + level);
		System.out.println("Exp: " + exp + "/" + level * 100);
		System.out.println("HP: " + hitPoint + "/" + maxHitPoint);
		System.out.println("Armor Penetration: " + armorPenetration + "%");
		System.out.println("Life Steal: " + lifeSteal + "%");
		System.out.println("Crit. Chance: " + critChance + "%");
		System.out.println("Armor: " + armor);
		System.out.println("Movement Speed: " + movementSpeed);
		System.out.println("Special Abilities: " + specialAbility);
		if (choice.equals("Nasus")) {
			System.out.println("Siphoning Strike Stacks: " + siphoningStrikeCount);
		}
		System.out.println("");
	}
	
	// Level up 
	public void levelUp() {
		maxHitPoint *= levelUpIndex;
		hitPoint = maxHitPoint;
		attackDamage *= levelUpIndex;
		armor *= levelUpIndex;
		movementSpeed *= levelUpIndex;
		exp =- level * 10;
		level += 1;
	}
	
	// Obtain an item with specified amount. If item is weapon, add stat to player
	public int addItem(Item i, int n) {
		inventorySpace -= n;
		if (inventorySpace < 0) {
			System.out.println("You cannot take this item because there is no space left in your bag!");
			return 0;
		} else {
			if (inventory.containsKey(i.getName())) {
				inventory.get(i.getName()).addAmount(n);
			}else{
				inventory.put(i.getName(), i);
				inventory.get(i.getName()).addAmount(n - 1);
			}
			
			if (i.getWeapon()) {
				 maxHitPoint += i.getHitPoint();
				 attackDamage += i.getAttackDamage();
				 armorPenetration += i.getArmorPenetration();
				 lifeSteal += i.getLifeSteal();
				 critChance += i.getCritChance();
				 armor += i.getArmor();
				 movementSpeed += i.getMovementSpeed();
			}
			
			return 1;
		}
	}
	
	// Use an item with specified amount. This method is specifically for items like potions and keys
	public void useItem(Item i, int n) {
		if (i.getWeapon()) {
			System.out.println("This item is a weapon, not a consumeable item.");
			return;
		}
		
		if (!inventory.containsKey(i.getName())) {
			System.out.println("You do not have this item!");
			return;
		}else if (inventory.get(i.getName()).getAmount() != 1 && inventory.get(i.getName()).getAmount() >= n) {
			inventory.get(i.getName()).decreaseAmount(n);
			inventorySpace += n;
			System.out.println("You used " + n + " unit(s) of " + i.getName() + "!");
		}else if (inventory.get(i.getName()).getAmount() != 1 && inventory.get(i.getName()).getAmount() < n) {
			System.out.println("You do not have enough of this item to use!");
			return;
		}else{
			if (n > 0) {
				inventory.remove(i.getName());
				System.out.println("You used all of your " + i.getName() + "!");
				inventorySpace += n;
			}
		}
		
		if ((hitPoint + i.getHitPoint() * n) > maxHitPoint) {
			hitPoint = maxHitPoint;
		}else{
			hitPoint += i.getHitPoint() * n;
		}
		
		maxHitPoint += i.getMaxHitPoint() * n;
		attackDamage += i.getAttackDamage() * n;
		armorPenetration += i.getArmorPenetration() * n;
		lifeSteal += i.getLifeSteal() * n;
		critChance += i.getCritChance() * n;
		armor += i.getArmor() * n;
		movementSpeed += i.getMovementSpeed() * n;
	}
	
	
	
	public void throwAwayItem(Item i, int n) {
		if (i.getWeapon()) {
			if (n != 1) {
				System.out.println("You do not have more than 1 unit of this item.");
				return;
			}
			maxHitPoint -= i.getMaxHitPoint();
			attackDamage -= i.getAttackDamage();
			armorPenetration -= i.getArmorPenetration();
			lifeSteal -= i.getLifeSteal();
			critChance -= i.getCritChance();
			armor -= i.getArmor();
			movementSpeed -= i.getMovementSpeed();
			inventorySpace += i.getWeight();
			
			System.out.println("You successfully throw your " + i.getName() + " away!");
			inventory.remove(i.getName());
			
		}else{
			if (!inventory.containsKey(i.getName())) {
				System.out.println("You do not have this item!");
				return;
			}else if (inventory.get(i.getName()).getAmount() != 1 && inventory.get(i.getName()).getAmount() >= n) {
				inventory.get(i.getName()).decreaseAmount(n);
				inventorySpace += n;
				System.out.println("You threw away " + n + " unit(s) of " + i.getName() + "!");
			}else if (inventory.get(i.getName()).getAmount() != 1 && inventory.get(i.getName()).getAmount() < n) {
				System.out.println("You do not have enough of this item to throw away!");
				return;
			}else{
				if (n > 0) {
					inventory.remove(i.getName());
					System.out.println("You threw away all of your " + i.getName() + "!");
					inventorySpace += n;
				}
			}
		}
	}
	
	// "Twisted Fate" special ability
	public void blackCard() {
		double ratio = 1.01;
		maxHitPoint *= ratio;
		attackDamage *= ratio;
		armorPenetration *= ratio;
		lifeSteal *= ratio;
		critChance *= ratio;
		armor *= ratio;
		movementSpeed *= ratio;		
	}
	
}
