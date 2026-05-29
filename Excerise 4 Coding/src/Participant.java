import java.util.ArrayList;
public class Participant 
{
	private String firstName;
	private String lastName;
	private ArrayList<GiftCard> cardsInHand = new ArrayList<GiftCard>();
	private int valueOfCards;
	
	public Participant()
	{
		
	}
	
	public Participant(String fName, String lName)
	{
		firstName = fName;
		lastName = lName;
	}
	public Participant (String fName, String lName, ArrayList<GiftCard> cards)
	{
		firstName = fName;
		lastName = lName;
		cardsInHand = cards;
	}
	
	// Sets the first name of a player
	public void setFirstName(String fName)
	{
		firstName = fName;
	}
	
	// Sets the last name of a player
	public void setLastName(String lName)
	{
		lastName = lName;
	}
	
	// Sets the cards of the player
	public void setCardsInHand(ArrayList<GiftCard> cards)
	{
		this.cardsInHand = new ArrayList<>(cards);
	}
	
	// Gets the first name of the player
	public String getFirstName()
	{
		return firstName;
	}
	
	// Gets the last name of the player
	public String getLastName()
	{
		return lastName;
	}
	
	// Gets the cards of the player
	public ArrayList<GiftCard> getCardsInHand()
	{
		return cardsInHand;
	}
	
	// Gets the full name of the player
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	// Calculates the total value of cards in the player's hand
	public void calculateTotalValueInHand()
	{
		int total = 0;
		for(GiftCard card : cardsInHand)
		{
			double value = card.getMonetaryValue();
			double points = card.assignCategoryPoints(card.getCategory());
			
			total += (value * points);
		}
		
		this.valueOfCards = total;
	}
	
	// Gets the total value in hand
	public int getTotalValueInHand()
	{
		return valueOfCards;
	}
	
	// Sets the value in hand. Only used for testing
	public void setTotalValueInHand(int value)
	{
		valueOfCards = value;
	}
}
