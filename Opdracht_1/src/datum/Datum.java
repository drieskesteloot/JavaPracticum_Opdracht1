package datum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Datum {

	private int dag;
	private int maand;
	private int jaar;
	
	/* -- maand(maandnr) --
	 * Maand met uitzondering: februari(2)
	 * -- (schrikkeljaar = deelbaar door 4)
	 * Maanden met 30 dagen: april(4), juni(6), september(9), november(11)
	 * Maanden met 31 dagen: januari(1), maart(3), mei(5), juli(7), augustus(8), oktober(10), december(12)
	 */
	
	private static final int[] MaandenMet31Dagen = new int[] {1,3,5,7,8,10,12};
	private static final String[] Maanden = new String[] { "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};
	
	public Datum() {
		Calendar Vandaag = Calendar.getInstance();
		setDatum(Vandaag.get(Calendar.DAY_OF_MONTH), Vandaag.get(Calendar.MONTH) + 1, Vandaag.get(Calendar.YEAR));
	}
	
	public Datum(Datum d) {
		setDatum(d.dag, d.maand, d.jaar);
	}
	
	public Datum(int d, int m, int j) {
		setDatum(d, m, j);
	}
	
	public Datum(String Dat) {
		String DatumString[] = Dat.split("/");
		String sDag = DatumString[0];
		String sMaand = DatumString[1];
		String sJaar = DatumString[2];
		if (sMaand.substring(0, 1) == "0") { sMaand = sMaand.substring(0,2); }		
		int Dag = Integer.parseInt(sDag);
		int Maand = Integer.parseInt(sMaand);
		int Jaar = Integer.parseInt(sJaar);
		setDatum(Dag,Maand,Jaar);
	}
	
	/*
	 * Getters en setters
	 */
	
	private boolean setDatum(int dag, int maand, int jaar) throws IllegalArgumentException {
		
		int TestJaar = jaar % 4; // resultaat is 0 indien deelbaar door 4
		int JaarIsEeuw = jaar % 100;
		int TestJaarEeuw = jaar % 400;
		if (dag < 1 || dag > 31) throw new IllegalArgumentException ("Een maand telt nooit meer dan 31 dagen.");
		if (maand < 1 || maand > 12) throw new IllegalArgumentException ("Een jaar telt nooit meer dan 12 maanden.");
		if (JaarIsEeuw == 0) {
			if (dag == 29 && maand == 02 && TestJaarEeuw != 0) throw new IllegalArgumentException ("De maand februari telt in het jaar " + jaar + " geen 29 dagen.");
		} else {
			if (dag == 29 && maand == 02 && TestJaar != 0) throw new IllegalArgumentException ("De maand februari telt in het jaar " + jaar + " geen 29 dagen.");
		}
		if (dag > 29 && maand == 02) throw new IllegalArgumentException ("De maand februari telt nooit meer dan 29 dagen.");
		if (dag == 31 && !Arrays.asList(MaandenMet31Dagen).contains(maand)) throw new IllegalArgumentException ("Deze maand telt geen 31 dagen.");
		
		setDag(dag);
		setMaand(maand);
		setJaar(jaar);
		
		return true;
		
	}
	// Dag
	private int getDag() {
		return this.dag;
	}
	private void setDag(int dag) {
		this.dag = dag;
	}
	// Maand
	private int getMaand() {
		return this.maand;
	}
	private void setMaand(int maand) {
		this.maand = maand;
	}
	// Jaar
	private int getJaar() {
		return this.jaar;
	}
	private void setJaar(int jaar) {
		this.jaar = jaar;
	}
	
	// Getters formaten
	public String getDatumInAmerikaansFormaat(Datum d) {
		String AmerikaanseDatum = Integer.toString(getMaand()) + "/" + Integer.toString(getDag()) + "/" + Integer.toString(getJaar());
		return AmerikaanseDatum;
	}
	public String getDatumInEuropeesFormaat(Datum d) {
		String EuropeseDatum = Integer.toString(getDag()) + "/" + Integer.toString(getMaand()) + "/" + Integer.toString(getJaar());
		return EuropeseDatum;
	}
	
	// functies
	
	public boolean kleinerDan(Datum d) {
		String datumInString = Integer.toString(d.jaar) + Integer.toString(d.maand) + Integer.toString(d.dag);
		String huidigeInString = Integer.toString(this.jaar) + Integer.toString(this.maand) + Integer.toString(this.dag);
		int datum = Integer.parseInt(datumInString);
		int huidig = Integer.parseInt(huidigeInString);
		if (datum < huidig)  {
			return true;
		} else {
		return false;
		}
	}
	
	public void veranderDatum(int aantalDagen) {
		int dag = getDag();
		int maand = getMaand();
		int jaar = getJaar();
		int dagenInMaand;
		for (int d = aantalDagen; d < 0; d--) {
			if (Arrays.asList(MaandenMet31Dagen).contains(maand)) { 
				dagenInMaand = 31; 
			} else if (maand == 2) {
				int TestJaar = jaar % 4, JaarIsEeuw = jaar % 100, TestJaarEeuw = jaar % 400;
				if (JaarIsEeuw == 0) {
					if (TestJaarEeuw != 0) { dagenInMaand = 28; } else { dagenInMaand = 29; }
				} else {
					if (TestJaar != 0) { dagenInMaand = 28; } else { dagenInMaand = 29; }
				} 
			} else {
				dagenInMaand = 30;
			}
			dag++;
			int overMaand = dagenInMaand + 1;
			if (dag == overMaand) {
				maand++;
				dag = 1;
				if (maand == 13) {
					jaar++;
					maand = 1;
				}
			}
		}
		setDatum(dag,maand,jaar);
	}
	
	// Kleine test in de main om de constructoren te testen.
	
	public static void main(String[] args) {
		try 
		{
			int dagen = 10;
			Datum date = new Datum(21,10,2015);
			//Datum date2 = new Datum(20/09/2015);
			System.out.println("Huidige datum: " + date);
			date.veranderDatum(dagen);
			System.out.println("Dagen bij te tellen: " + dagen);
			System.out.println("Aangepaste datum: " + date);
		}
		catch (IllegalArgumentException ex){System.out.println(ex.getMessage());}
	}
	
	@Override
	public String toString() {
		return Integer.toString(dag) + " " + Maanden[maand-1] +" "+ Integer.toString(jaar);
	}
	
	
}
