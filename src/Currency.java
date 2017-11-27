
public class Currency {

	private float agreedFX;
	private String currencyName;
	
	public Currency(float agreedFX, String currencyName){
		this.agreedFX = agreedFX;
		this.currencyName = currencyName;
		
	}
	
	public void SetAgreedFX(float agreedFX){
		this.agreedFX = agreedFX;
	}
	
	public float GetAgreedFX(){
		return agreedFX;
	}
	
	public void SetCurrencyName(String currencyName){
		this.currencyName = currencyName;
	}
	
	public String GetCurrencyName(){
		return currencyName;
	}
	
}
