import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class UnitTest {
	
	GiftCard card1 = new GiftCard("Entertainment", "Disney", 5.00);
	GiftCard card2 = new GiftCard("Retail","eBay",5.00);
	GiftCard card3 = new GiftCard("Gas","Exxon",150.00);
	GiftCard card4 = new GiftCard("Restaurant","Cheesecake Factory",50.00);
	GiftCard card5 = new GiftCard("OnlineShopping","DoorDash",10.00);
	GiftCard card6 = new GiftCard("BeautyAndSpa","Whole Beauty",100.00);
	GiftCard card7 = new GiftCard("Sports","Paragon Sports",40.00);


	@Test
	void testGetCategory()
	{
		assertEquals("Entertainment", card1.getCategory());
	}
	
	@Test
	void testGetCompany()
	{
		assertNotEquals("Disney", card2.getCompany());
	}
	
	@Test 
	void testGetmonetaryValue()
	{
		assertEquals(5.00, card1.getMonetaryValue());
	}
	
	@Test
	void testAssignCategoryPoints()
	{
		assertEquals(10, card1.assignCategoryPoints(card1.getCategory()));
		assertEquals(6, card2.assignCategoryPoints(card2.getCategory()));
		assertEquals(7, card3.assignCategoryPoints(card3.getCategory()));
		assertEquals(12, card4.assignCategoryPoints(card4.getCategory()));
		assertEquals(9, card5.assignCategoryPoints(card5.getCategory()));
		assertEquals(10, card6.assignCategoryPoints(card6.getCategory()));
		assertEquals(8, card7.assignCategoryPoints(card7.getCategory()));
	}
	
	Barrel barrel = new Barrel();
	@Test
	void testCreateGiftCards() 
			throws FileNotFoundException  
	{
		barrel.createGiftCards();
		assertNotNull(barrel.giftCards);
		assertFalse(barrel.giftCards.isEmpty());
	}
	
	@Test
	void testShuffleGiftCards() 
			throws FileNotFoundException 
	{
		barrel.createGiftCards();
		ArrayList<GiftCard> originalList = new ArrayList<GiftCard>(barrel.giftCards);
		Collections.shuffle(barrel.giftCards);
		
		assertNotNull(barrel.giftCards, "not null after shuffle");
		assertEquals(originalList.size(), barrel.giftCards.size(), "same size");
	}
	
	@Test
	void testGetCard() 
			throws FileNotFoundException 
	{
		barrel.createGiftCards();
		int originalSize = barrel.giftCards.size();
		GiftCard giftCard1 = barrel.getGiftCard();
		
		assertNotNull(giftCard1);
		assertEquals(originalSize - 1, barrel.giftCards.size(), "decrease by 1");
		
	}
	
	Participant person1 = new Participant();
	Participant person2 = new Participant("Jack", "Bell");
	
	@Test
	void testGetLastName()
	{
		person1.setLastName("Smith");
		assertNotNull(person1.getLastName());
		assertEquals("Smith", person1.getLastName());
		assertNotEquals("Garcia", person2.getLastName());
	}
	
	@Test
	void testGetFirstName()
	{
		assertNull(person1.getFirstName());
		assertEquals("Jack", person2.getFirstName());
		assertNotEquals("Evelin", person2.getFirstName());
	}
	@Test
	void testGetTotalValueInHand() 
			throws FileNotFoundException
	{
		Barrel brl = new Barrel();
		brl.createGiftCards();
		brl.shuffleGiftCards();
		Participant part = new Participant();
		ArrayList<GiftCard> cardsInHand = new ArrayList<GiftCard>();
		// part.assignGiftCards();
		
		assertNotNull(cardsInHand);
	}
	
	@Test
	void testPrintFullName()
	{
		Scanner snr = new Scanner(System.in);
		Participant player = new Participant();
		player.setFirstName("Evelin");
		player.setLastName("Garcia");
		
		snr.close();
		
		assertEquals("Evelin Garcia", player.getFullName());
	}
	
	@Test
	void testContinueGame()
	{
		GameMaster master = new GameMaster();
		
		assertTrue(master.continueGame("yes"));
		assertTrue(master.continueGame("YES"));
		assertFalse(master.continueGame("NO"));
		assertFalse(master.continueGame("Well well well"));
	}
	
	@Test
    void testEliminatePlayer() {
        
        GameMaster master = new GameMaster();

        Participant p1 = new Participant("Eve", "Gar");
        Participant p2 = new Participant("Mar", "Gar");
        Participant p3 = new Participant("Yen", "Gar");

        // Simulate values
        p1.setTotalValueInHand(2500); 
        p2.setTotalValueInHand(3500); 
        p3.setTotalValueInHand(1000); 

        master.addPlayers(p1);
        master.addPlayers(p2);
        master.addPlayers(p3);
        master.eliminatePlayer();

        ArrayList<Participant> remaining = master.getPlayerList();

        assertEquals(2, remaining.size());
        assertTrue(remaining.contains(p1));
        assertTrue(remaining.contains(p3));
        assertFalse(remaining.contains(p2));
    }
	
    @Test
    void testSwitchGiftCards()
    {
    	GameMaster master = new GameMaster();
    	Barrel barrel = new Barrel();
    	
    	ArrayList<GiftCard> cards = new ArrayList<>();
    	cards.add(card1);
    	cards.add(card2);
    	cards.add(card3);
    	
    	GiftCard originalCard = cards.get(0);
    	
    	master.switchGiftCards(0, cards, barrel);
    	
    	assertNotEquals(originalCard, cards.get(0));
    }
    
    @Test
    void testCheckGiftCardsCommon()
    {
    	GiftCard card1 = new GiftCard("Entertainment", "Disney", 5.00);
    	GiftCard card2 = new GiftCard("Entertainment", "Disney", 5.00);
    	GiftCard card3 = new GiftCard("Entertainment", "Disney", 5.00);
    	ArrayList<GiftCard> cards = new ArrayList<GiftCard>();
    	cards.add(card1);
    	cards.add(card2);
    	cards.add(card3);
    	
    	Participant p = new Participant("John", "Smith", cards);
    	
    	GameMaster master = new GameMaster();
    	ArrayList<Participant> players = new ArrayList<Participant>();
    	players.add(p);
    	
    	master.checkGiftCardsCommon(players);
    	
    	assertEquals(0, players.size());
    }
}
