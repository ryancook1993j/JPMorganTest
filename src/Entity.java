import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Entity {

	private String entityName; 
	private float incoming[][] = new float[52][8];
	private float outgoing[][] = new float[52][8];
	private int dayOfWeek;
	private int week = 0;
	private float amountToSettle;
	private Instruction instruction = new Instruction();
	
	public Entity(String entityName)
	{
		this.entityName = entityName;
	}
	
	public boolean checkDay(String currencyName, String instructionDate, Calendar c)
	{
		boolean confirmTrade = false;
		try {		
			c.setTime(new SimpleDateFormat("dd MMM yyyy").parse(instructionDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//c.setFirstDayOfWeek(1);
		dayOfWeek = c.get(c.DAY_OF_WEEK);
		week = c.get(c.WEEK_OF_YEAR);
		if(currencyName.equals("AED") || currencyName.equals("SAR"))
		{
			return checkSpecialCaseDay(dayOfWeek);
		}
		
		if(dayOfWeek >1 && dayOfWeek < 7)
		{
			confirmTrade = true;
		}
		return confirmTrade;
		
	}
	
	
		private boolean checkSpecialCaseDay(int dayOfWeek)
		{
			if(dayOfWeek < 6)
			{
				return true;
			}
			else return false;
			
		}

		
	public void Buy(Unit unit, float numberOfUnits, String settleDate, String currencyName, Currency currency, Calendar c)
	{
		float valueOfBuy = instruction.Buy(unit, numberOfUnits, currencyName, currency);
		boolean alreadyRolled = false;
		if(checkDay(currencyName, settleDate, c) == true)
		{
			outgoing[week][dayOfWeek] += valueOfBuy;
		}else
		{
			if(week>52){week = 1; alreadyRolled = true;}
 				if(currencyName == "AED" || currencyName == "SAR")
				{
 					if(alreadyRolled == false)
 					{
 						week = week+1;
 					}
					outgoing[week][1] += valueOfBuy;
				}else
				{
					outgoing[week+1][2]+= valueOfBuy;
				}
			
		}
	}
	
	
	public void Sell(Unit unit, float numberOfUnits, String settleDate, String currencyName, Currency currency, Calendar c)
	{
		float valueOfBuy = instruction.Sell(unit, numberOfUnits, currencyName, currency);
		boolean alreadyRolled = false;
		if(checkDay(currencyName, settleDate, c) == true)
		{
			incoming[week][dayOfWeek] += valueOfBuy;
		}else
		{
			if(week>52){week = 1; alreadyRolled = true;}
 				if(currencyName == "AED" || currencyName == "SAR")
				{
 					if(alreadyRolled == false)
 					{
 						week = week+1;
 					}
 					incoming[week][1] += valueOfBuy;
				}else
				{
					incoming[week+1][2]+= valueOfBuy;
				}
			
		}
	}

	public float getAmountToSettle()
	{
		return amountToSettle;
	}
	
	public float[][] getOutgoing()
	{
		return outgoing;
	}
	
	public float[][] getIncoming()
	{
		return incoming;
	}
	
	
}
