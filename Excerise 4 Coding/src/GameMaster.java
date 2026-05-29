import java.util.ArrayList;

public class GameMaster 
{
	int maxParticipants = 4;
	int numOfParticipants = 0;
	int maxCardValue = 3000;
	String winnerName; 
	ArrayList<Participant> currentPlayers = new ArrayList<Participant>();
	
	// Sets the number of players
	public void setNumOfPlayers(int num)
	{
		numOfParticipants = num;
	}
	
	// Gets the number of players
	public int getNumOfPlayers()
	{
		return numOfParticipants;
	}
	
	// Adds player to the currentPlayers list
	public void addPlayers(Participant playerName)
	{
		currentPlayers.add(playerName);
	}
	
	// Gets the name of the players in the list
	public void getNameOfPlayers()
	{
		for (Participant p : currentPlayers)
		{
			System.out.println(p.getFirstName() + " " + p.getLastName());
		}
	}
	
	// Gets the currentPlayers list
	public ArrayList<Participant> getPlayerList()
	{
		return currentPlayers;
	}
	
	// Assigns 3 gift cards to player
	public void assignGiftCards(ArrayList<GiftCard> cards, Barrel barrel)
	{
		cards.add(barrel.getGiftCard());
		cards.add(barrel.getGiftCard());
		cards.add(barrel.getGiftCard());
	}
	
	// Prints the gift cards for all the players
	public void printGiftCards()
	{
		for (int i = 0; i < currentPlayers.size(); i++)
		{
			System.out.println("Cards in Hand for " + currentPlayers.get(i).getFirstName() + " " + currentPlayers.get(i).getLastName() + ": ");
			for(GiftCard c : currentPlayers.get(i).getCardsInHand())
			{
				System.out.println(c.getCategory() + " " + c.getCompany() + " " + c.getMonetaryValue());
			}
			System.out.println();
		}
	}
	
	// Gets the value of the gift card value for each player
	public void getGiftCardValue()
	{
		for (int i = 0; i < currentPlayers.size(); i++)
		{
			currentPlayers.get(i).calculateTotalValueInHand();
		}
	}
	
	// Prints out the total value in hand of all players
	public void printTotalValueInHand()
	{
		for (Participant p : currentPlayers)
		{
			p.calculateTotalValueInHand();
			System.out.println(p.getFullName() + "'s total value in had is " + p.getTotalValueInHand());
		}
	}
	
	// Eliminates players based on total value in hand
	public void eliminatePlayer()
	{
		for(int i =  currentPlayers.size() - 1; i >= 0; i--)
		{
			if(currentPlayers.get(i).getTotalValueInHand() > maxCardValue)
			{
				System.out.println("Player: " + currentPlayers.get(i).getFullName() + " has been eliminated.");
				currentPlayers.remove(i);
			}
			else
			{
				System.out.println("Player: " + currentPlayers.get(i).getFullName() + " is still in the game.");
			}
		}
	}
	
	// Switches two gift cards
	public void switchGiftCards(int position, ArrayList<GiftCard> cards, Barrel barrel)
	{
		cards.set(position, barrel.getGiftCard());
	}
	
	// Checks if the cards in the player's hand have commonalities with company or category and
	// will remove player from game
	public void checkGiftCardsCommon(ArrayList<Participant> players)
	{
		for (int i = players.size() - 1; i >= 0; i--) // iterates backwards should be done when removing items in a list in a loop
		{
			Participant player = players.get(i);
			ArrayList<GiftCard> cards = player.getCardsInHand();
			
			GiftCard card1 = cards.get(0);
			GiftCard card2 = cards.get(1);
			GiftCard card3 = cards.get(2);
			
			boolean sameCompany = 
					card1.getCompany().equals(card2.getCompany()) ||
					card1.getCompany().equals(card3.getCompany()) ||
					card2.getCompany().equals(card3.getCompany());
			
			boolean sameCategory = 
					card1.getCategory().equals(card2.getCategory()) ||
					card1.getCategory().equals(card3.getCategory()) ||
					card2.getCategory().equals(card3.getCategory());
			
			if(sameCompany)
			{
				System.out.println(player.getFullName() + " has 2 or more cards with the same company");
				System.out.println(player.getFullName() + " has been eliminated.");
				System.out.println();
				players.remove(i);
			}
			else if (sameCategory)
			{
				System.out.println(player.getFullName() + " has 2 or more cards with the same category");
				System.out.println(player.getFullName() + " has been eliminated.");
				System.out.println();
				players.remove(i);
			}
			else
			{
				System.out.println(player.getFullName() + " has not been eliminated.");
				System.out.println();
			}
		}
	}
	
	// Determines the winner
	public void determineWinner(ArrayList<Participant> players)
	{
		if (players == null || players.isEmpty())
		{
			winnerName = "No Winner";
			return;
		}
		
		for (int i = players.size() - 1; i >= 0; i--)
		{
			Participant player = players.get(i);
			ArrayList<GiftCard> cards = player.getCardsInHand();
			
			GiftCard card1 = cards.get(0);
			GiftCard card2 = cards.get(1);
			GiftCard card3 = cards.get(2);
			
			boolean sameMonetaryValue = 
					card1.getMonetaryValue() == card2.getMonetaryValue() ||
					card1.getMonetaryValue() == card3.getMonetaryValue() ||
					card2.getMonetaryValue() == card3.getMonetaryValue();
			if (sameMonetaryValue)
			{
				players.remove(i);
				System.out.println(player.getFullName() + " has been elimnated!");
			}
		}
			
		if (players.isEmpty())
		{
			winnerName = "No Winner";
			return;
		}
		
		Participant winner = players.get(0);
		winner.calculateTotalValueInHand();
		int winnerValue = winner.getTotalValueInHand();
			
		for (int i = 1; i < players.size(); i++)
		{
			Participant currPlayer = players.get(i);
			currPlayer.calculateTotalValueInHand();
			int currValue = currPlayer.getTotalValueInHand();
			
			if (currValue < winnerValue)
			{
				winner = currPlayer;
				winnerValue = currValue;
			}
		}
		
		winnerName = winner.getFullName();
	}
	
	// Displays the winner's name
	public String displayWinner()
	{
		return "The winner is " + winnerName;
	}
	
	// Prints out the cards of the winner
	public void printWinnerCards(ArrayList<Participant> players)
	{
	    if (players.isEmpty()) {
	        return;
	    }

	    Participant winner = players.get(0);

	    for (GiftCard card : winner.getCardsInHand())
	    {
	        System.out.println(card.getCategory() + " " +
	                           card.getCompany() + " " +
	                           card.getMonetaryValue());
	    }
	}
	
	// Checks if the players would like to continue
	public boolean continueGame(String choice)
	{
		if (choice.equalsIgnoreCase("yes"))
		{
			return true;
		}
		else
		{
			return false;
		} 
	}
}
