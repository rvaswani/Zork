package com.bayviewglen.ZorkGame;

import java.util.Scanner;


public class TESTBATTLE {
	
	public static final Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		Player Daniel = new Player();
		Daniel.printOptions();
		Daniel.setCharacter();
		Monster Rishi = new Monster("Rishi", 1, "He is stupid");
		Battle epic = new Battle(Daniel,Rishi);
		epic.fight();
		
		

	}

}
