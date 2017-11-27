

public class Instruction {

	private String instructionFlag;
	
	public Instruction(){}
			
	
		public float Buy(Unit unit, float numberOfUnits, String currencyName, Currency currency)
		{
			int tradeValue;
			if(currencyName == currency.GetCurrencyName())
			{
				return valueOfTrade(unit.GetValue(), numberOfUnits, currency.GetAgreedFX());
			}
			else
			{
				System.out.println(" currency name did not match currency type. ");
				return 0;
			}
		}
		
		public float Sell(Unit unit, float numberOfUnits, String currencyName, Currency currency)
		{
			int tradeValue;
			if(currencyName == currency.GetCurrencyName())
			{
				return valueOfTrade(unit.GetValue(), numberOfUnits, currency.GetAgreedFX());
			}
			else
			{
				System.out.println(" currency name did not match currency type. ");
				return 0;
			}
		}
		
		public float valueOfTrade(float valueOfUnit, float numberOfUnits ,float agreedFX)
		{
			return valueOfUnit*numberOfUnits*agreedFX;
		}
}
