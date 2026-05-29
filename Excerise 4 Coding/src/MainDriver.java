import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainDriver {

	public static void main(String[] args) 
			throws FileNotFoundException 
	{
		Scanner scnr = new Scanner(System.in);
		Barrel barrel = new Barrel();
		GameMaster master = new GameMaster();
		
		barrel.createGiftCards();
		barrel.shuffleGiftCards();
		
		System.out.print("How many players will participate(Maximum of 4 players)? ");
		
		while (!scnr.hasNextInt())
		{
			System.out.print("Please enter a valid number(1-4): ");
			scnr.next();
		}
		
		int num = scnr.nextInt();
		
		while ((num > 4) || (num < 0))
		{
			System.out.println("Please enter a valid number.");
			System.out.print("Enter a range between 1 and 4. ");
			num = scnr.nextInt();
			System.out.println();
		}
		
		master.setNumOfPlayers(num);
		
		for (int i = 0; i < master.getNumOfPlayers(); i++)
		{
			Participant player = new Participant();
			ArrayList<GiftCard> tempCards = new ArrayList<GiftCard>();
			
			System.out.println("------------------");
			System.out.print("What is your first name? ");
			String firstName = scnr.next();
			player.setFirstName(firstName);
			
			System.out.print("what is your last name? ");
			String lastName = scnr.next();
			player.setLastName(lastName);
			
			System.out.println();
			
			master.assignGiftCards(tempCards, barrel);
			player.setCardsInHand(tempCards);
			
			master.addPlayers(player);
		}
		
		System.out.println("------------------");
		System.out.println("Current Players: ");
		master.getNameOfPlayers();
		System.out.println();
		
		String yesOrNo;
		do
		{
			System.out.print("Would you like to continue? ");
			yesOrNo = scnr.next();
		}
		while (!master.continueGame(yesOrNo));
		System.out.println();
		
		System.out.println("------------------");
		master.printGiftCards();
		
		System.out.println("------------------");
		master.getGiftCardValue();
		master.printTotalValueInHand();
		System.out.println();
		
		System.out.println("------------------");
		master.eliminatePlayer();
		System.out.println();
		do
		{
			System.out.print("Would you like to continue? ");
			yesOrNo = scnr.next();
		}
		while (!master.continueGame(yesOrNo));
		System.out.println();
		
		System.out.println("------------------");
		for (int i = 0; i < master.currentPlayers.size(); i++)
		{
			System.out.println("For player: " + master.currentPlayers.get(i).getFullName());
			System.out.print("Would you like to switch your cards? (Yes or No) ");
			String answer = scnr.next();
			int attempts = 0;
			while (!answer.equalsIgnoreCase("no") && attempts < 2)
			{
				attempts++;
				System.out.print("Which card would you like to switch? (1-3) ");
				int cardNum = scnr.nextInt();
				switch (cardNum)
				{
				case 1:
					master.switchGiftCards(0, master.currentPlayers.get(i).getCardsInHand(), barrel);
					break;
				case 2:
					master.switchGiftCards(1, master.currentPlayers.get(i).getCardsInHand(), barrel);
					break;
				case 3:
					master.switchGiftCards(2, master.currentPlayers.get(i).getCardsInHand(), barrel);
					break;
				default:
					System.out.println("Not a Valid answer.");
					break;
				}
				
				if (attempts != 2)
				{
					System.out.print("Would you like to switch your cards again?");
					answer = scnr.next();
				}
				
			}
		}
		
		System.out.println("------------------");
		master.printGiftCards();
		
		System.out.println("------------------");
		master.checkGiftCardsCommon(master.getPlayerList());
		
		System.out.println("------------------");
		master.getGiftCardValue();
		do
		{
			System.out.print("Would you like to continue? ");
			yesOrNo = scnr.next();
		}
		while (!master.continueGame(yesOrNo));
		System.out.println();
		master.printTotalValueInHand();
		
		
		System.out.println("------------------");
		master.determineWinner(master.getPlayerList());
		System.out.println(master.displayWinner());
		System.out.println("Winner's cards: ");
		master.printWinnerCards(master.getPlayerList());
		
		scnr.close();
	}
}
