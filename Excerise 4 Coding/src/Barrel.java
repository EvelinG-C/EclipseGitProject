import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Barrel 
{
	private String filePath = "GiftCardData.txt";
	public ArrayList<GiftCard> giftCards = new ArrayList<GiftCard>();
	
	// Gets data from text file, creates gift cards, and adds them to a list
	public void createGiftCards() 
			throws FileNotFoundException
	{
		try(BufferedReader br = new BufferedReader(new FileReader(filePath)))
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				String[] parts = line.split(",");
				String category = parts[0];
				String company = parts[1];
				double monetaryValue = Double.parseDouble(parts[2]);
				
				GiftCard card = new GiftCard(category, company, monetaryValue);
				giftCards.add(card);
			}
			br.close();
		}
		catch (IOException e)
		{
			System.out.println("Error reading file.");
		}
	}
	
	// Shuffles the gift cards
	public void shuffleGiftCards() 
	{
		Collections.shuffle(giftCards);
	}
	
	// Gets a gift card from the list and removes it from the list
	public GiftCard getGiftCard()
	{
		GiftCard topCard = giftCards.get(0);
		giftCards.remove(0);
		return topCard;
	}
	
}
