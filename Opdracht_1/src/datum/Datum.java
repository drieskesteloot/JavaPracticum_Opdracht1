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
	//comment mutlu
	
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
		if (sDag.substring(0, 1) == "0") { sDag = sDag.substring(2,1); }	
		String sMaand = DatumString[1];
		if (sMaand.substring(0, 1) == "0") { sMaand = sMaand.substring(2,1); }	
		String sJaar = DatumString[2];
			
		int Dag = Integer.parseInt(sDag);
		int Maand = Integer.parseInt(sMaand);
		int Jaar = Integer.parseInt(sJaar);
		setDatum(Dag,Maand,Jaar);
	}
	
	/*
	 * Getters en setters
	 */
	
	private boolean setDatum(int dag, int maand, int jaar) throws IllegalArgumentException {
		
		if (dag < 1 || dag > 31) throw new IllegalArgumentException ("Een maand telt nooit meer dan 31 dagen.");
		if (maand < 1 || maand > 12) throw new IllegalArgumentException ("Een jaar telt nooit meer dan 12 maanden.");
		if (jaar < 0) throw new IllegalArgumentException ("De jaartellingen zijn begonnen vanaf 0");
		if (dag == 29 && maand == 02 && !isSchrikkeljaar(jaar)) throw new IllegalArgumentException ("De maand februari telt in het jaar " + jaar + " geen 29 dagen.");
		if (dag > 29 && maand == 02) throw new IllegalArgumentException ("De maand februari telt nooit meer dan 29 dagen.");
		if (dag == 31 && !Arrays.asList(MaandenMet31Dagen).contains(maand)) throw new IllegalArgumentException ("Deze maand telt geen 31 dagen.");
		
		setDag(dag);
		setMaand(maand);
		setJaar(jaar);
		
		return true;
		
	}
	// Dag
	public int getDag() {
		return this.dag;
	}
	private void setDag(int dag) {
		this.dag = dag;
	}
	// Maand
	public int getMaand() {
		return this.maand;
	}
	private void setMaand(int maand) {
		this.maand = maand;
	}
	// Jaar
	public int getJaar() {
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
	
	// Controle functie
	
	private boolean isSchrikkeljaar(int jaar) {
		boolean Schrikkeljaar = false;
		int TestJaar = jaar % 4; // resultaat is 0 indien deelbaar door 4
		int JaarIsEeuw = jaar % 100;
		int TestJaarEeuw = jaar % 400;
		if (JaarIsEeuw == 0) {
			if (TestJaarEeuw == 0) { return true; }
		} else if (TestJaar == 0) { 
			return true;
		} else {
			return false;
		}
		return Schrikkeljaar;
	}
	
	// functies
	
	public boolean kleinerDan(Datum d) {
		
		String str_dDag = d.dag < 10 ? "0" + Integer.toString(d.dag) : Integer.toString(d.dag);
		String str_dMaand = d.maand < 10 ? "0" + Integer.toString(d.maand) : Integer.toString(d.maand);
		String str_hDag = this.dag < 10 ? "0" + Integer.toString(this.dag) : Integer.toString(this.dag);
		String str_hMaand = this.maand < 10 ? "0" + Integer.toString(this.maand) : Integer.toString(this.maand);

		String datumInString = Integer.toString(d.jaar) + str_dMaand + str_dDag;
		String huidigeInString = Integer.toString(this.jaar) + str_hMaand + str_hDag;
		
		int datum = Integer.parseInt(datumInString);
		int huidig = Integer.parseInt(huidigeInString);
		
		if (datum < huidig)  {
			return true;
		} else {
		return false;
		}
	}
	
	public int verschilInJaren(Datum d) {
		
	}
	
	public void veranderDatum(int aantalDagen) {
		int dag = getDag();
		int maand = getMaand();
		int jaar = getJaar();
		int dagenInMaand;
		for (int d = aantalDagen; d < 0; d--) {
			if (Arrays.asList(MaandenMet31Dagen).contains(maand)) { 
				dagenInMaand = 31; 
			} else if (maand == 2 && isSchrikkeljaar(jaar)) { 
				dagenInMaand = 29;
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
			Datum date = new Datum(29,2,1600);
			Datum date2 = new Datum("05/01/2013");
			System.out.println("Huidige datum: " + date);
			date.veranderDatum(dagen);
			System.out.println("Dagen bij te tellen: " + dagen);
			System.out.println("Aangepaste datum: " + date);
			System.out.println("");
			if (date.kleinerDan(date2)) { System.out.println(date2 + " is kleiner dan " + date); } else { System.out.println(date2 + " is groter dan " + date);}
			System.out.println("Datum 2: " + date2);
		}
		catch (IllegalArgumentException ex){System.out.println(ex.getMessage());}
	}
	
	@Override
	public String toString() {
		return Integer.toString(dag) + " " + Maanden[maand-1] +" "+ Integer.toString(jaar);
	}
	
	
} 
