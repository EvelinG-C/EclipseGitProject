public class GiftCard 
{
	private String category;
	private String company;
	private double monetaryValue;
	
	public GiftCard()
	{
		
	}
	
	public GiftCard(String categoryGiftCard, String companyGiftCard, double monetaryValueGiftCard)
	{
		category = categoryGiftCard;
		company = companyGiftCard;
		monetaryValue = monetaryValueGiftCard;
	}
	
	// Returns the category of the card
	public String getCategory()
	{
		return category;
	}
	
	// Assigns points to each category
	public int assignCategoryPoints(String category)
	{
		int points = 0;
		switch (category)
		{
			case "Entertainment":
			{
				points = 10;
				break;
			}
			case "Restaurant":
			{
				points = 12;
				break;
			}
			case "Retail":
			{
				points = 6;
				break;
			}
			case "Gas":
			{
				points = 7;
				break;
			}
			case "OnlineShopping":
			{
				points = 9;
				break;
			}
			case "BeautyAndSpa":
			{
				points = 10;
				break;
			}
			case "Sports":
			{
				points = 8;
				break;
			}
			default:
			{
				points = -1;
				break;
			}
		}
		return points;
	}
	
	// Returns the company of the card
	public String getCompany()
	{
		return company;
	}
	
	// Returns the monetary value of the card
	public double getMonetaryValue()
	{
		return monetaryValue;
	}

}
