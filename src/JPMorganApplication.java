import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JPMorganApplication {

	Unit unitOne = new Unit((float) 100.25);
	Unit unitTwo = new Unit((float) 150.5);
	Currency AED = new Currency((float) 0.22, "AED");
	Currency SGP = new Currency((float) 0.50, "SGP");
	Currency SAR = new Currency((float)0.65, "SAR");
	Instruction instruction = new Instruction();
	Entity foo = new Entity("foo");
	Entity bar = new Entity("bar");
	Calendar c = Calendar.getInstance();
	
	public JPMorganApplication(){}
	
	
    public void bubbleSort(pair array[]) {
        int length = array.length;
        int j;
        for (int m = length; m >= 0; m--) {
            for (int i = 0; i < length - 1; i++) {
                j = i + 1;
                if (array[i].getValue() < array[j].getValue()) {
                    pair temp;
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    
    
    public void Run()
    {
		bar.Buy(unitOne, 200, "01 Jan 2016", "AED", AED, c);
		foo.Buy(unitOne, 200, "09 Jan 2016", "AED", AED, c);
		foo.Buy(unitTwo, 200, "09 Jan 2016", "SGP", SGP, c);
		foo.Buy(unitTwo, 200, "09 Jan 2016", "SGP", SGP, c);
		bar.Buy(unitTwo, 200, "09 Jan 2016", "SGP", SGP, c);
		bar.Buy(unitTwo, 200, "09 Jan 2016", "AED", AED, c);
		bar.Buy(unitTwo, 200, "8 Jan 2016", "SAR", SAR, c);

		bar.Sell(unitTwo, 200, "09 Jan 2016", "AED", AED, c);
		float[][] fooOutgoing = foo.getOutgoing();
		float[][] barOutgoing = bar.getOutgoing();
		float[][] fooIncoming = foo.getIncoming();
		float[][] barIncoming = bar.getIncoming();
		pair[] outgoingRankings = new pair[2];
		pair[] incomingRankings = new pair[2];
		
		outgoingRankings[0] = new pair();
		outgoingRankings[1] = new pair();
		incomingRankings[0] = new pair();
		incomingRankings[1] = new pair();

		try {
			c.setTime(new SimpleDateFormat("dd MMM yyyy").parse("03 Jan 2016"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 1; i < 3; i++) {
			for (int j = 1; j < 8; j++) {
				// work around for setWeekDate not giving correct day. week 1,
				// day 1 returns week 2's sunday
				if (j == 1) {
					i -= 1;
				}
				c.setWeekDate(2016, i, j);
				if (j == 1) {
					i += 1;
				}
				System.out.println(c.getTime().toString());
				System.out.println(
						"--------------------------------------------- OUTGOING ---------------------------------------------");
				System.out.println(
						"foo outgoing: " + fooOutgoing[i][j] + " USD " + "\n" + "bar outgoing: " + barOutgoing[i][j]
								+ " USD " + "\n" + "Total Outgoing " + (fooOutgoing[i][j] + barOutgoing[i][j]) + " USD");
				System.out.println(
						"--------------------------------------------- INCOMING ---------------------------------------------");
				System.out.println(
						"foo incoming: " + fooIncoming[i][j] + " USD " + "\n" + "bar incoming: " + barIncoming[i][j]
								+ " USD " + "\n" + "Total Outgoing " + (fooIncoming[i][j] + barIncoming[i][j]) + " USD");
				System.out.println(
						"--------------------------------------------- OUTGOING RANKS ---------------------------------------------");
				outgoingRankings[0].setName("foo");
				outgoingRankings[1].setName("bar");
				outgoingRankings[0].setValue(fooOutgoing[i][j]);
				outgoingRankings[1].setValue(barOutgoing[i][j]);
				bubbleSort(outgoingRankings);
				for(int k = 0; k < outgoingRankings.length; k++)
				{
					System.out.println(outgoingRankings[k].getName());
				}
				System.out.println(
						"--------------------------------------------- INCOMING RANKS ---------------------------------------------");
				incomingRankings[0].setName("foo");
				incomingRankings[1].setName("bar");
				incomingRankings[0].setValue(fooIncoming[i][j]);
				incomingRankings[1].setValue(barIncoming[i][j]);
				bubbleSort(incomingRankings);
				for(int k = 0; k < incomingRankings.length; k++)
				{
					System.out.println(incomingRankings[k].getName());
				}
				System.out.println("");
			}
		}
    }
    
	
}
