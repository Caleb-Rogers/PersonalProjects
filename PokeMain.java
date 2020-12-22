import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PokeMain 
{
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) 
		{
		// Initialization
		String name = "User";
		String fileName;
		File myFile;
		String menuChoice;
		char menu;
		Stack tableDeck = new Stack();
		Stack player1Deck = new Stack();
		Stack player2Deck = new Stack();
		String ready = "Neither";
		String ending = "Neither";
		
		// Greeting
		System.out.println("Welcome to a Pokemon Battle Game!!");
		System.out.println("What is your name?");
		name = keyboard.next();
		System.out.println("Hello " + name + "!!");
		System.out.println("This game was created by Caleb Rogers using the Java programming language");
		System.out.println("Some tips to know:");
		System.out.println("No clicking is required, just input what is necessary and hit enter. If lost, click the line");
		System.out.println("below the lowest line of text. Uppercase is never necessary when entering words, though is allowed.");
		System.out.println("Before playing, make sure you enter an input file of Pokemon. 'Gen1Favorites' is suggested.");
		System.out.println("Finally, if you notice anything that could improve my game or programming skills, let me know!");
		System.out.println("Choose from the menu below and enjoy!!"); //add a greeting????
		 
		do 
			{
			printMenu();
			
			// Menu Choice
			System.out.print("Enter your menu choice: ");
			menuChoice = keyboard.next();
			menu = menuChoice.charAt(0);
			menu = Character.toUpperCase(menu);
			System.out.println();
			
			// Menu Selection
			switch (menu)
				{
				case '1': System.out.println("An input file will be required to import new Pokemon");
						System.out.print("Enter a filename (with suffix '.txt'): ");
						fileName = keyboard.next();
						myFile = new File(fileName);
						
						if(tableDeck.isEmpty())
							{				
							fileInput(myFile, tableDeck);
							if(countCards(tableDeck) > 0)
								System.out.println("A new deck has been imported!");
							else
								{
								System.out.println("There has been an issue! NO decks have been imported");
								System.out.println("Try file name 'Gen1Favorites'");
								} // else
							} // if
						else
							{
							System.out.println("A deck of cards has already been imported!");
							System.out.println("Remove this deck before importing a new deck");
							} // else
						break;
						
				case '2': 
						if((countCards(tableDeck) > 7) && (countCards(tableDeck) % 2 == 0))
							shuffle(tableDeck);
						else if((countCards(tableDeck) > 2) && (countCards(tableDeck) % 2 == 0))
							simpleShuffle(tableDeck);
						System.out.println("Your deck of cards are now shuffled");
						break;
				
				case '3': reviewCards(tableDeck);
						break;
						
				case '4': intro();
						ready = keyboard.next();
						if(ready.equalsIgnoreCase("YES"))
							{
							System.out.println("\nLet's get started");
							pokeGame(tableDeck, player1Deck, player2Deck, name);
							System.out.println("Would you like to return to the main menu or quit?");
							System.out.println("Type 'RETURN' or 'QUIT':  ");
							ending = keyboard.next();
							if(ending.equalsIgnoreCase("RETURN"))
								System.out.println("Okay!");
							else if(ending.equalsIgnoreCase("Quit"))
								{
								menu = '0';	
								System.out.println("I hope you enjoyed my program!!");
								System.out.println("Goodbye");
								} // else if
							else
								System.out.println("Error, either type in 'RETURN' to return to main menu or 'QUIT' to exit program");
							} // if ready
						
						else if(ready.equalsIgnoreCase("NO"))
							System.out.println("Okay");
						else
							System.out.println("Error, either type in 'YES' to start the game or 'NO' to return to menu");
						break;
						
				case '5': // Remove all pokemon in stack
						
				case '0': System.out.println("Thanks for participating in Caleb's experimental program!");
						System.out.println("Goodbye");
						keyboard.close();
						break;
						
				default: System.out.println("Invalid input! Enter an option from the menu ");
						break;
				} // switch
			} // do
		while (menu != '0');
		} // main

	public static void printMenu()
		{
		System.out.println();
		System.out.println("{Select a choice from the following menu}");
		System.out.println("'1': Import a Deck of Cards");
		System.out.println("'2': Shuffle Cards");
		System.out.println("'3': Review Cards");
		System.out.println("'4': PLAY GAME");
		System.out.println("'5': Remove Imported Deck of Cards");
		System.out.println("'0': Quit Program");
		} // menu
	
	public static void fileInput(File theFile, Stack theDeck)
		{
		Scanner input;
		String inputName = "None";
		String inputType = "None";
		int inputHealth = 1000;
		PokeCards inputPoke = null;
		
		try
			{
			input = new Scanner(theFile);
			while(input.hasNext())
				{
				inputName = input.next();
				inputType = input.next();			
				inputPoke = new PokeCards(inputName, inputType, inputHealth);
				theDeck.push(inputPoke);
				} // while			
			input.close();
			} // try
		
		catch(FileNotFoundException ex)
			{
			System.out.println("Failed to find the file: " + theFile.getAbsolutePath());
			} // catch
		catch(InputMismatchException ex)
			{
			System.out.println("Type mismatch for the number I tried to read!");
			System.out.println(ex.getMessage());
			} // catch
		catch(NumberFormatException ex)
			{
			System.out.println("Failed to convert string text into integer value!");
			System.out.println(ex.getMessage());
			} // catch
		catch(NullPointerException ex)
			{
			System.out.println("Null pointer exception");
			System.out.println(ex.getMessage());
			} // catch
		catch(Exception ex)
			{
			System.out.println("There was an error!");
			ex.printStackTrace();
			} // catch
		} // fileInput
	
	public static void shuffle(Stack theDeck) // make an oddShuffle!!!!!!!!!!
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck1 = new Stack();
		Stack tempDeck2 = new Stack();
		Stack tempDeck3 = new Stack();
		Stack tempDeck4 = new Stack();
		
		while(!theDeck.isEmpty())
			{
			tempCard = theDeck.pop();
			tempDeck1.push(tempCard);
			tempCard = theDeck.pop();
			tempDeck2.push(tempCard);
			tempCard = theDeck.pop();
			tempDeck3.push(tempCard);
			tempCard = theDeck.pop();
			tempDeck4.push(tempCard);
			} // while
		while(!tempDeck1.isEmpty())
			{
			tempCard = tempDeck1.pop();
			theDeck.push(tempCard);
			} // while
		while(!tempDeck3.isEmpty())
			{
			tempCard = tempDeck3.pop();
			theDeck.push(tempCard);
			} // while
		while(!tempDeck2.isEmpty())
			{
			tempCard = tempDeck2.pop();
			theDeck.push(tempCard);
			} // while
		while(!tempDeck4.isEmpty())
			{
			tempCard = tempDeck4.pop();
			theDeck.push(tempCard);
			} // while
		} // shuffle
	
	public static void simpleShuffle(Stack theDeck)
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck1 = new Stack();
		Stack tempDeck2 = new Stack();
		
		while(!theDeck.isEmpty())
			{
			tempCard = theDeck.pop();
			tempDeck1.push(tempCard);
			tempCard = theDeck.pop();
			tempDeck2.push(tempCard);
			} // while
		while(!tempDeck1.isEmpty())
			{
			tempCard = tempDeck1.pop();
			theDeck.push(tempCard);
			} // while
		while(!tempDeck2.isEmpty())
			{
			tempCard = tempDeck2.pop();
			theDeck.push(tempCard);
			} // while
		} // simpleShuffle
	
	public static void reviewCards(Stack theDeck)
		{
		String menuInput;
		char subMenu;
		String searchPoke = "None";
		PokeCards foundPoke = null;
		String remove = "None";
		boolean deleted = false;
		
		do
			{
			System.out.println("Review Cards");
			System.out.println("------------------");
			System.out.println("'1': Count Cards");
			System.out.println("'2': View All Cards");
			System.out.println("'3': Search a Card");
			System.out.println("'4': Remove a Card");
			System.out.println("'5': Return to Main Menu");
			System.out.print("Enter your menu choice: ");
			menuInput = keyboard.next();
			subMenu = menuInput.charAt(0);
			subMenu = Character.toUpperCase(subMenu);
			System.out.println();
		
			switch (subMenu)
				{
				case '1': System.out.println("There are " + countCards(theDeck) + " Pokemon in the deck");
						break;
						
				case '2': System.out.println("Here are all the Pokemon in your deck:");
						printCards(theDeck);
						break;
						
				case '3': System.out.print("Enter the name of the Pokemon you want to find: ");
						searchPoke = keyboard.next();
						foundPoke = findPoke(searchPoke, theDeck);
						if(foundPoke != null)
							{
							System.out.println("\nYour Pokemon was found! Here is your Pokemon");
							System.out.println(foundPoke.toString());
							} // if
						else
							System.out.println("\nThere was an issue! " + searchPoke + " was unable to be found");
						break;
						
				case '4': System.out.print("Enter the name of the Pokemon you want to delete: ");
						remove = keyboard.next();
						deleted = deletePoke(remove, theDeck);
						if(deleted == true)
							System.out.println("The Pokemon " + remove + " has been removed from the deck");
						else
							System.out.println("There was an issue! The Pokemon " + remove + " was NOT removed from the deck");
						break;
				case '5': break;
					
				default: System.out.println("Invalid input! Enter an option from the menu ");
						break;	
				} // switch
			} // do
		while(subMenu != '5');
		} // reviewCards
	
	public static int countCards(Stack theDeck)
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck = new Stack();
		int count = 0;
		
		while(!theDeck.isEmpty())
			{
			tempCard = theDeck.pop();
			count++;
			tempDeck.push(tempCard);
			} // while
	
		while(!tempDeck.isEmpty())
			{
			tempCard = tempDeck.pop();
			theDeck.push(tempCard);
			} // while
		
		return count;
		} // countCards
	
	public static void printCards(Stack deck)
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck = new Stack();
		int counter = 1;
		
		while(!deck.isEmpty())
			{
			tempCard = deck.pop();
			
			System.out.println("~~~ (" + counter + ") ~~~");
			System.out.println(tempCard.toString());
			counter++;
			
			tempDeck.push(tempCard);
			} // while
	
		while(!tempDeck.isEmpty())
			{
			tempCard = tempDeck.pop();
			deck.push(tempCard);
			} // while
		} // printCards
	
	public static PokeCards findPoke(String retrieve, Stack theDeck)
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck = new Stack();
		PokeCards thePoke = null;
		
		while(!theDeck.isEmpty())
			{
			tempCard = theDeck.pop();
			if(retrieve.compareToIgnoreCase(tempCard.getName()) == 0)
				thePoke = tempCard;
			tempDeck.push(tempCard);
			} // while
		
		while(!tempDeck.isEmpty())
			{
			tempCard = tempDeck.pop();
			theDeck.push(tempCard);
			} // while
		
		return thePoke;
		} // retrieve
	
	public static boolean deletePoke(String delPoke, Stack theDeck)
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck = new Stack();
		boolean removed = false;
		
		while(!theDeck.isEmpty())
			{
			tempCard = theDeck.pop();
			if(delPoke.compareToIgnoreCase(tempCard.getName()) != 0)
				tempDeck.push(tempCard);
			else
				removed = true;
			} // while
		
		while(!tempDeck.isEmpty())
			{
			tempCard = tempDeck.pop();
			theDeck.push(tempCard);
			} // while
		
		return removed;
		} // deletePoke
	
	public static void intro()
		{
		// Game Starts 
		System.out.println("Game Rules:");
		System.out.println("A deck of cards will be delt between two players (user and cpu). Both players will chose between");
		System.out.println("a vicious and a cautious attack. A vicious attack is riskier with a wider variance among hit damage,");
		System.out.println("but has potential for higher hits. A cautious attack has less variance, with less chance of strong");
		System.out.println("and weak hits. Cautious attacks, additionally, come with guaranteed defense against hits. There are");
		System.out.println("many Pokemon types. Different Pokemon types in battle result in different effectiveness that determine");
		System.out.println("hit damage and defense effectiveness. Choose Wisely. Pokemon that survive are transfered to their");
		System.out.println("MedBay, while Pokemon that faint, fall to the GraveYard. A player plays from their main deck until");
		System.out.println("empty. Then the player must play with their injured Pokemon from their MedBay. A player wins when the");
		System.out.println("opposing player's Pokemon have all fallen to the GraveYard. Be wary of a LastStand.");
		
		System.out.println("\nMake sure you have an even deck imported and that the deck is shuffled");
		System.out.println("Are you ready for Battle?");
		System.out.print("'YES' or 'NO'?:  ");
		} // intro
	
	public static void pokeGame(Stack mainDeck, Stack userDeck, Stack cpuDeck, String userName)
		{
		// Initialization
		PokeCards userCard = new PokeCards();
		PokeCards cpuCard = new PokeCards();
		boolean userAttack = false;
		boolean cpuAttack = false;
		int round = 0;
		Stack graveYard = new Stack();
		Stack userMedBay = new Stack();
		Stack cpuMedBay = new Stack();
		boolean activeLastStand = true;
		PokeCards revivedPoke = null;
		
		// Game Starts
		System.out.println("Press ENTER to continue");
		try {        
			System.in.read();
			} // try
		catch(Exception e)
			{	
			e.printStackTrace();
			} // catch
		
		// Deals Cards
		System.out.println("-------------------------");
		deal(mainDeck, userDeck, cpuDeck);
		System.out.println("The dealer deals a deck of " + countCards(userDeck) + " cards to " + userName);
		System.out.println("and deals a deck of " + countCards(cpuDeck) + " cards the Oppenent");
		System.out.println("-------------------------");
		
		while(((userDeck.isEmpty()==false)||(userMedBay.isEmpty()==false)) && ((cpuDeck.isEmpty()==false)||(cpuMedBay.isEmpty()==false)) && (round != 250))
			{
			// Transfers cards from MedBay to Decks
			if(userDeck.isEmpty() == true)
				{
				System.out.println(userName + ", you have ran out of cards in your deck");
				System.out.println("The cards in your MedBay have been shuffled and moved into your deck");
				moveMedBay(userMedBay, userDeck);
				} // if
			if(cpuDeck.isEmpty() == true)
				{
				System.out.println("Your Opponent has run out of cards in their deck");
				System.out.println("The cards in their deck have been shuffled and moved into their deck");
				moveMedBay(cpuMedBay, cpuDeck);
				} // else if
			
			round++;
			System.out.println("\n~~~~~ ROUND " + round + ", FIGHT!! ~~~~~");
			
			// User's Turn
			userCard = userDeck.pop();
			cpuCard = cpuDeck.pop();
			userAttack = playCard(userCard, cpuCard, userName);
	
			// Opponent's Turn
			System.out.println("\n" + userName + ": '" + userCard.getName() + " GO!!!!'");
			System.out.println("Opponent: '" + cpuCard.getName() + " GO!!!!'");
			System.out.println("\nYour opponent played " + cpuCard.getName());
			System.out.println(cpuCard.getName() + " is a " + cpuCard.getType() + " type");
			cpuAttack = opponent();
			
			// Battle
			System.out.println("\n-------------------------");
			System.out.println("\n[Pokemon are in battle...]");
			System.out.println("\nPress ENTER for the battle's outcome");
			try {        
				System.in.read();
				} // try
			catch(Exception e)
				{	
				e.printStackTrace();
				} // catch
			battle(userCard, cpuCard, userAttack, cpuAttack);
			System.out.println("\n-------------------------");
			System.out.println("...After battle, your Opponent's Pokemon has " + cpuCard.getHealth() + " health"); // validate!!!!!!!
			System.out.println("and your Pokemon has " + userCard.getHealth() + " health");
			
			// GraveYard
			System.out.println("-------------------------");
			if(userCard.getHealth() <= 0)
				{
				System.out.println(userCard.getName() + " has fallen to the GraveYard");
				graveYard.push(userCard);
				} // if
			if(cpuCard.getHealth() <= 0)
				{
				System.out.println(cpuCard.getName() + " has fallen to the GraveYard");
				graveYard.push(cpuCard);
				} // if
			
			// MedBay
			if(userCard.getHealth() > 0)
				{
				System.out.println("\n" + userCard.getName() + " survived!");
				System.out.println("Your Pokemon has been placed in your MedBay");
				userMedBay.push(userCard);
				} // if
			if(cpuCard.getHealth() > 0)
				{
				System.out.println("\n" + cpuCard.getName() + " survived");
				System.out.println("Your Opponent's Pokemon has been placed in their MedBay");
				cpuMedBay.push(cpuCard);
				} // if
			
			System.out.println("\nROUND " + round + " IS OVER...");
			
			// Last Stand
			if(((userDeck.isEmpty()==true)&&(userMedBay.isEmpty()==true))&&(activeLastStand==true))
				{
				activeLastStand = false;
				revivedPoke = userLastStand(graveYard);
				revivedPoke.setHealth(750);
				userDeck.push(revivedPoke);
				System.out.println(revivedPoke.getName() + " has been added to your deck");
				System.out.println("Make this Pokemon count! Goodluck!");
				} // if
			if(((cpuDeck.isEmpty()==true)&&(cpuMedBay.isEmpty()==true))&&(activeLastStand==true))
				{
				activeLastStand = false;
				System.out.println("Your Opponent's Pokemon have all fallen to the GraveYard");
				System.out.println("but they activate a LASTSTAND!");
				revivedPoke = cpuLastStand(graveYard);
				revivedPoke.setHealth(750);
				cpuDeck.push(revivedPoke);
				System.out.println(revivedPoke.getName() + " has been added to their deck");
				System.out.println("GoodLuck!");
				} // if
			
			System.out.println("\nPress ENTER to continue to the next round");
			try {        
				System.in.read();
				} // try
			catch(Exception e)
				{	
				e.printStackTrace();
				} // catch
			} // while
		
		// Summary
		System.out.println("THE GAME IS OVER!!");
		System.out.println("Summary...");
		} // pokeGame
	
	
	public static void deal(Stack theDeck, Stack p1Deck, Stack p2Deck)
		{
		PokeCards tempCard = new PokeCards();
		while(!theDeck.isEmpty())
			{
			tempCard = theDeck.pop();
			p1Deck.push(tempCard);
			tempCard = theDeck.pop();
			p2Deck.push(tempCard);
			} // while
		} // deal
	
	public static boolean playCard(PokeCards user, PokeCards cpu, String name)
		{
		int attack = 0;
		boolean vicious = false;
		boolean cautious = false;
		
		System.out.println("\n" + name + ", you take a card, you have...");
		System.out.println(user.toString());
		System.out.println(user.getName() + " has " + user.getHealth() + " health");
		
		while((!vicious) && (!cautious))
			{
			System.out.println("Do you choose a VICIOUS ('1') or CAUTIOUS ('2') Attack?");
			attack = keyboard.nextInt();
			if(attack == 1)
				vicious = true;
			else if(attack == 2)
				cautious = true;
			else
				System.out.println("Error, either type in '1' for VICIOUS or '2' for CAUTIOUS");
			} // while
		
		if(vicious == true)
			{
			System.out.println("You chose VICIOUS!");
			System.out.println("-------------------------");
			System.out.print("\nPress ENTER to play your card against your opponent");
			try {        
				System.in.read();
				} // try
			catch(Exception e)
				{	
				e.printStackTrace();
				} // catch
			} // if
		else if(cautious == true)
			{
			System.out.println("You chose CAUTIOUS!");
			System.out.println("-------------------------");
			System.out.print("\nPress ENTER to play your card against your opponent");
			try {        
				System.in.read();
				} // try
			catch(Exception e)
				{	
				e.printStackTrace();
				} // catch
			} // else if
		
		return vicious;
		} // playCard
	
	public static boolean opponent()
		{ 
		Random random = new Random();
		int num = 0;
		boolean cpuVicious = false;
		
		num = random.nextInt(2);
		if(num == 0)
			{
			cpuVicious = true;
			System.out.println("\nYour Opponent chose VICIOUS attack");
			} // if
		else
			{
			System.out.println("\nYour Opponent chose CAUTIOUS attack");
			} // else
		
		return cpuVicious;
		} // battle
	
	public static void battle(PokeCards user, PokeCards cpu, boolean userVicious, boolean cpuVicious) // validate EVERYTHING!!!!!!
		{
		// Initialization
		int userHit = 0;
		String userEffect = "Normal";
		int userHealth = 0;
		int userBoost = 0;
		boolean userWeak = false;
		boolean userImmune = false;
		int cpuHit = 0;
		String cpuEffect = "Normal";
		int cpuHealth = 0;
		int cpuBoost = 0;
		boolean cpuWeak = false;
		boolean cpuImmune = false;
		
		
		// User Vicious Attacks
		if(userVicious == true)
			{
			// Bug
			if((user.getType().equalsIgnoreCase("Bug")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Psychic"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Bug")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Ghost")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Bug"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Bug")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Electric
			if((user.getType().equalsIgnoreCase("Electric")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Water"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Electric")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Electric")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Electric"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Electric")) && ((cpu.getType().equalsIgnoreCase("Ice")) || (cpu.getType().equalsIgnoreCase("Dragon")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Fairy
			if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Dragon")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Fairy"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Steel"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				userImmune = true;
				} // if Immune
			
			// Fighting
			if((user.getType().equalsIgnoreCase("Fighting")) && ((cpu.getType().equalsIgnoreCase("Normal")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Ice")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Fighting")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Psychic")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Fighting"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Fighting")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Psychic")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Fire
			if((user.getType().equalsIgnoreCase("Fire")) && ((cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Fire")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Fire"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Fire")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Water"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Flying
			if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Flying"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Electric")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Ground"))))
				{
				userImmune = true;
				} // if Immune
			
			// Grass
			if((user.getType().equalsIgnoreCase("Grass")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Water"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Grass")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Grass"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Grass")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Ground
			if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Ground"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Electric"))))
				{
				userImmune = true;
				} // if Immune
			
			// Ice
			if((user.getType().equalsIgnoreCase("Ice")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Ice")) && ((cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Ice"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Ice")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Normal
			if((user.getType().equalsIgnoreCase("Normal")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Normal"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			
			// Poison
			if((user.getType().equalsIgnoreCase("Poison")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Poison")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Ghost"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Poison"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Poison")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Psychic"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Psychic
			if((user.getType().equalsIgnoreCase("Psychic")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Poison"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Psychic")) && ((cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Psychic"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Psychic"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Psychic")) && ((cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Ghost")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Rock
			if((user.getType().equalsIgnoreCase("Rock")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Rock")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Steel"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Rock"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Rock")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Steel
			if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Ice")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Steel"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Poison"))))
				{
				userImmune = true;
				} // if Immune
			
			// Water
			if((user.getType().equalsIgnoreCase("Water")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				userEffect = "SUPER";
				userHit += hitGeneratorVicious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Water")) && ((cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				userEffect = "NOT VERY";
				userHit += hitGeneratorVicious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Water"))
				{
				userEffect = "NORMAL";
				userHit += hitGeneratorVicious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Water")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			} // if userVicious
		
		// User Cautious Attacks
		else
			{
			// Bug
			if((user.getType().equalsIgnoreCase("Bug")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Psychic"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Bug")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Ghost")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Bug"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Bug")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Electric
			if((user.getType().equalsIgnoreCase("Electric")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Water"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Electric")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Electric")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Electric"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Electric")) && ((cpu.getType().equalsIgnoreCase("Ice")) || (cpu.getType().equalsIgnoreCase("Dragon")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			 
			// Fairy
			if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Dragon")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Fairy"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Steel"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Fairy")) && ((cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				userImmune = true;
				} // if Immune
			
			// Fighting
			if((user.getType().equalsIgnoreCase("Fighting")) && ((cpu.getType().equalsIgnoreCase("Normal")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Ice")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Fighting")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Psychic")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Fighting"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Fighting")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Psychic")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Fire
			if((user.getType().equalsIgnoreCase("Fire")) && ((cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Fire")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Fire"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Fire")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Water"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Flying
			if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Flying"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Electric")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Flying")) && ((cpu.getType().equalsIgnoreCase("Ground"))))
				{
				userImmune = true;
				} // if Immune
				
			// Grass
			if((user.getType().equalsIgnoreCase("Grass")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Water"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Grass")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Grass"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Grass")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Ground
			if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Ground"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Ground")) && ((cpu.getType().equalsIgnoreCase("Electric"))))
				{
				userImmune = true;
				} // if Immune
			
			// Ice
			if((user.getType().equalsIgnoreCase("Ice")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Ice")) && ((cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Ice"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Ice")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Normal
			if((user.getType().equalsIgnoreCase("Normal")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Steel"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Normal"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			
			// Poison
			if((user.getType().equalsIgnoreCase("Poison")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Poison")) && ((cpu.getType().equalsIgnoreCase("Poison")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Ghost"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Poison"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Poison")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Psychic"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Psychic
			if((user.getType().equalsIgnoreCase("Psychic")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Poison"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Psychic")) && ((cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Psychic"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Psychic"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Psychic")) && ((cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Ghost")) || (cpu.getType().equalsIgnoreCase("Dark"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Rock
			if((user.getType().equalsIgnoreCase("Rock")) && ((cpu.getType().equalsIgnoreCase("Flying")) || (cpu.getType().equalsIgnoreCase("Bug")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Ice"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Rock")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Steel"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Rock"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Rock")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Grass"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			// Steel
			if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Ice")) || (cpu.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Steel")) || (cpu.getType().equalsIgnoreCase("Fire")) || (cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Steel"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Fighting")) || (cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			else if((user.getType().equalsIgnoreCase("Steel")) && ((cpu.getType().equalsIgnoreCase("Poison"))))
				{
				userImmune = true;
				} // if Immune
			
			// Water
			if((user.getType().equalsIgnoreCase("Water")) && ((cpu.getType().equalsIgnoreCase("Ground")) || (cpu.getType().equalsIgnoreCase("Rock")) || (cpu.getType().equalsIgnoreCase("Fire"))))
				{
				cpuHit -= 750;
				userBoost = 750;
				userEffect = "SUPER";
				userHit += hitGeneratorCautious(userEffect);
				} // if SuperEffective
			else if((user.getType().equalsIgnoreCase("Water")) && ((cpu.getType().equalsIgnoreCase("Water")) || (cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuHit -= 250;
				userBoost = 250;
				userEffect = "NOT VERY";
				userHit += hitGeneratorCautious(userEffect);
				} // if NotVeryEffective
			else if(user.getType().equalsIgnoreCase("Water"))
				{
				cpuHit -= 500;
				userBoost = 500;
				userEffect = "NORMAL";
				userHit += hitGeneratorCautious(userEffect);
				} // if NormalEffective
			if((user.getType().equalsIgnoreCase("Water")) && ((cpu.getType().equalsIgnoreCase("Grass")) || (cpu.getType().equalsIgnoreCase("Electric"))))
				{
				cpuHit += 250;
				userWeak = true;
				} // if Weak
			
			} // else userCautious
		
		
		// CPU Vicious Attacks
		if(cpuVicious == true)
			{
			// Bug
			if((cpu.getType().equalsIgnoreCase("Bug")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Psychic"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Bug")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Ghost")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Bug"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Bug")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Electric
			if((cpu.getType().equalsIgnoreCase("Electric")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Electric")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Electric")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Electric"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Electric")) && ((user.getType().equalsIgnoreCase("Ice")) || (user.getType().equalsIgnoreCase("Dragon")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Fairy
			if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Dragon")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Fairy"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Steel"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Fighting
			if((cpu.getType().equalsIgnoreCase("Fighting")) && ((user.getType().equalsIgnoreCase("Normal")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Ice")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Fighting")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Psychic")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Fighting"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Fighting")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Psychic")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Fire
			if((cpu.getType().equalsIgnoreCase("Fire")) && ((user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Fire")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Fire"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Fire")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Flying
			if((cpu.getType().equalsIgnoreCase("Flying")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Flying")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Flying"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Flying")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Electric")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Ground"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Grass
			if((cpu.getType().equalsIgnoreCase("Grass")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Grass")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Grass"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Grass")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Ground
			if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Ground"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Electric"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Ice
			if((cpu.getType().equalsIgnoreCase("Ice")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Ice")) && ((user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Ice"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Ice")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Normal
			if((cpu.getType().equalsIgnoreCase("Normal")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Normal"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			
			// Poison
			if((cpu.getType().equalsIgnoreCase("Poison")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Poison")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Ghost"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Poison"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Poison")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Psychic"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Psychic
			if((cpu.getType().equalsIgnoreCase("Psychic")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Poison"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Psychic")) && ((user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Psychic"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Psychic"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Psychic")) && ((user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Ghost")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Rock
			if((cpu.getType().equalsIgnoreCase("Rock")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Rock")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Steel"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Rock"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Rock")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Steel
			if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Ice")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Steel"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Poison"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Water
			if((cpu.getType().equalsIgnoreCase("Water")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Water")) && ((user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Water"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Water")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			} // if cpuVicious
		
		// CPU Cautious Attacks
		else
			{
			// Bug
			if((cpu.getType().equalsIgnoreCase("Bug")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Psychic"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Bug")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Ghost")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Bug"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Bug")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Electric
			if((cpu.getType().equalsIgnoreCase("Electric")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Electric")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Electric")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Electric"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Electric")) && ((user.getType().equalsIgnoreCase("Ice")) || (user.getType().equalsIgnoreCase("Dragon")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Fairy
			if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Dragon")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Fairy"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Steel"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Dragon"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Fighting
			if((cpu.getType().equalsIgnoreCase("Fighting")) && ((user.getType().equalsIgnoreCase("Normal")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Ice")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Fighting")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Psychic")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Fighting"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Fighting")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Psychic")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
				
			// Fire
			if((cpu.getType().equalsIgnoreCase("Fire")) && ((user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Fire")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Fire"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Fire")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Flying
			if((cpu.getType().equalsIgnoreCase("Flying")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Flying")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Flying"))
				{
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorVicious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Flying")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Electric")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Fairy")) && ((user.getType().equalsIgnoreCase("Ground"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Grass
			if((cpu.getType().equalsIgnoreCase("Grass")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Grass")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Grass"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Grass")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Water"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Ground
			if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Ground"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Ground")) && ((user.getType().equalsIgnoreCase("Electric"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Ice
			if((cpu.getType().equalsIgnoreCase("Ice")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Ice")) && ((user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Ice"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Ice")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Normal
			if((cpu.getType().equalsIgnoreCase("Normal")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Steel"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Normal"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			
			// Poison
			if((cpu.getType().equalsIgnoreCase("Poison")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Poison")) && ((user.getType().equalsIgnoreCase("Poison")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Ghost"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Poison"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Poison")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Psychic"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Psychic
			if((cpu.getType().equalsIgnoreCase("Psychic")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Poison"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Psychic")) && ((user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Psychic"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Psychic"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Psychic")) && ((user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Ghost")) || (user.getType().equalsIgnoreCase("Dark"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Rock
			if((cpu.getType().equalsIgnoreCase("Rock")) && ((user.getType().equalsIgnoreCase("Flying")) || (user.getType().equalsIgnoreCase("Bug")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Ice"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Rock")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Steel"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Rock"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Rock")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Grass"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			// Steel
			if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Ice")) || (user.getType().equalsIgnoreCase("Fairy"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Steel")) || (user.getType().equalsIgnoreCase("Fire")) || (user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Steel"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Fighting")) || (user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			else if((cpu.getType().equalsIgnoreCase("Steel")) && ((user.getType().equalsIgnoreCase("Poison"))))
				{
				cpuImmune = true;
				} // if Immune
			
			// Water
			if((cpu.getType().equalsIgnoreCase("Water")) && ((user.getType().equalsIgnoreCase("Ground")) || (user.getType().equalsIgnoreCase("Rock")) || (user.getType().equalsIgnoreCase("Fire"))))
				{
				userHit -= 750;
				cpuBoost = 750;
				cpuEffect = "SUPER";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if SuperEffective
			else if((cpu.getType().equalsIgnoreCase("Water")) && ((user.getType().equalsIgnoreCase("Water")) || (user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Dragon"))))
				{
				userHit -= 250;
				cpuBoost = 250;
				cpuEffect = "NOT VERY";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NotVeryEffective
			else if(cpu.getType().equalsIgnoreCase("Water"))
				{
				userHit -= 500;
				cpuBoost = 500;
				cpuEffect = "NORMAL";
				cpuHit += hitGeneratorCautious(cpuEffect);
				} // if NormalEffective
			if((cpu.getType().equalsIgnoreCase("Water")) && ((user.getType().equalsIgnoreCase("Grass")) || (user.getType().equalsIgnoreCase("Electric"))))
				{
				userHit += 250;
				cpuWeak = true;
				} // if Weak
			
			} // else cpuCautious
		
		
		// CPU Implement Attacks
		if(!userImmune)
			{
			System.out.println(cpu.getName() + " is " + cpuEffect + " effective against " + user.getName());
			
			// Weak bonus
			if(userWeak == true)
				{
				System.out.println(user.getName() + " is weak against " + cpu.getName());
				System.out.println("Your Opponent recieves a 250 damage bonus against " + user.getName());
				} // if Weak
			
			// Cautious bonus
			if(cpuBoost > 0)
				{
				System.out.println("Your Opponent chose CAUTIOUS attack and " + cpu.getName() + " blocked " + cpuBoost + " damage against " + user.getName());
				} // if HealthBoost
			
			userHealth = user.getHealth();
			
			// Attack
			if(cpuHit > 0)
				{
				userHealth -= cpuHit;
				System.out.println("Your Opponent inflicts " + cpuHit + " damage to your " + user.getName());
				} // if cpuHit
			else
				System.out.println("Your Opponent had no affect on " + user.getName());
		
			user.setHealth(userHealth);
			} // if NotImmune
	
		// User Immune to CPU
		else
			{
			System.out.println(user.getName() + " is immune to " + cpu.getName());
			System.out.println("Your Opponent had no affect on your Pokemon");
			} // else Immune
		
		
		// User Implement Attacks
		if(!cpuImmune)
			{
			System.out.println("\nYour Pokemon responds with its attack");
			System.out.println(user.getName() + " is " + userEffect + " effective against " + cpu.getName());
			
			// Weak bonus
			if(cpuWeak == true)
				{
				System.out.println(cpu.getName() + " is weak against " + user.getName());
				System.out.println("Your Pokemon recieves a 250 damage bonus against " + cpu.getName());
				} // if Weak
			
			// Cautious bonus
			if(userBoost > 0)
				{
				System.out.println("You chose CAUTIOUS attack and " + user.getName() + " blocked " + userBoost + " damage against " + cpu.getName());
				} // if HealthBoost
			
			cpuHealth = cpu.getHealth();
			
			// Attack
			if(userHit > 0)
				{
				cpuHealth -= userHit;
				System.out.println("You inflict " + userHit + " damage to " + cpu.getName());
				} // if userHit
			else
				System.out.println("You had no affect on " + cpu.getName());
			
			cpu.setHealth(cpuHealth);
			} // if NotImmune
		
		// CPU Immune to User
		else
			{
			System.out.println(cpu.getName() + " is immune to " + user.getName());
			System.out.println("You had no affect on your Opponent's Pokemon");
			} // else Immune
		
		} // battle
	
	public static int hitGeneratorVicious(String effect)
		{
		int hit = 0;
		
		if(effect.equalsIgnoreCase("SUPER"))
			{
			hit = ThreadLocalRandom.current().nextInt(750, 1500 + 1);
			} // if Super
		else if(effect.equalsIgnoreCase("NOT VERY"))
			{
			hit = ThreadLocalRandom.current().nextInt(0, 600 + 1);
			} // if NotVery
		else if(effect.equalsIgnoreCase("NORMAL"))
			{
			hit = ThreadLocalRandom.current().nextInt(400, 1000 + 1);
			} // if Normal
		
		return hit;
		} // hitGenerator
	
	public static int hitGeneratorCautious(String effect)
		{
		int hit = 0;
		
		if(effect.equalsIgnoreCase("SUPER"))
			{
			hit = ThreadLocalRandom.current().nextInt(600, 900 + 1);
			} // if Super
		else if(effect.equalsIgnoreCase("NOTVERY"))
			{
			hit = ThreadLocalRandom.current().nextInt(50, 300 + 1);
			} // if NotVery
		else if(effect.equalsIgnoreCase("NORMAL"))
			{
			hit = ThreadLocalRandom.current().nextInt(250, 550 + 1);
			} // if Normal
		
		return hit;
		} // hitGenerator
	
	public static void moveMedBay(Stack theMed, Stack theDeck)
		{
		PokeCards tempCard = new PokeCards();
		
		System.out.println("\nTHE MEDBAY BEFORE SHUFFLE AND MOVE");
		printCards(theMed);
		
		if((countCards(theMed) > 3) && (countCards(theMed) % 2 == 0))
			simpleShuffle(theMed);
		
		while(!theMed.isEmpty())
			{
			tempCard = theMed.pop();
			theDeck.push(tempCard);
			} // while
		
		System.out.println("\nTHE DECK AFTER SHUFFLE AND MOVE");
		printCards(theDeck);
		} // moveMedBay
	
	public static PokeCards userLastStand(Stack grave)
		{
		String view = "Neither";
		String revive = "None";
		PokeCards thePoke = null;
		
		System.out.println("Your Pokemon have all fallen to the GraveYard");
		System.out.println("but you activate a LASTSTAND!");
		System.out.println("Would you like to view the cards in the GraveYard?");
		
		System.out.print("'YES' or 'NO'?:  ");
		view = keyboard.next();
		if(view.equalsIgnoreCase("YES"))
			printCards(grave);
		else if(view.equalsIgnoreCase("NO"))
			System.out.println("Okay!");
		else
			System.out.println("Error, either type in 'YES' to view cards or 'NO' to skip");
		
		System.out.println("\nNow enter the name of the card you wish to revive from the GraveYard");
		revive = keyboard.next();
		thePoke = findPoke(revive, grave);
		while(thePoke == null)
			{
			System.out.println("Your Pokemon was NOT found");
			System.out.println("The following are the cards in the GraveYard");
			printCards(grave);
			System.out.println("\nEnter the name of a Pokemon from the list above");
			revive = keyboard.next();
			thePoke = findPoke(revive, grave);
			} // while
		
		return thePoke;
		} // userLastStand
	
	public static PokeCards cpuLastStand(Stack grave)
		{
		PokeCards tempCard = new PokeCards();
		Stack tempDeck = new Stack();
		Random random = new Random();
		int numCards = 0;
		int index = 0;
		int i = 0;
		PokeCards thePoke = new PokeCards();
		
		numCards = countCards(grave);
		index = random.nextInt(numCards);
		
		for(i = 0; i < index+1; i++)
			{
			tempCard = grave.pop();
			if(i == index)
				thePoke = tempCard;
			tempDeck.push(tempCard);
			} // for
		
		return thePoke;
		} // cpuLastStand
	
} // PokeMain
