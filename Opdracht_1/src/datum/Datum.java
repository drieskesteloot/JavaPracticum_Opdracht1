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
	private static final int[] MaandenMetDagen = new int[] {0,31,28,31,30,31,30,31,31,30,31,30,31};
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
		if ( dag == 31 && dag != MaandenMetDagen[maand]) throw new IllegalArgumentException ("Deze maand telt geen "+dag+" dagen.");
		
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
		String AmerikaanseDatum = Integer.toString(getJaar()) + "/" + Integer.toString(getMaand()) + "/" + Integer.toString(getDag());
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
	
	private int datumSamenvoegen(Datum d, String volgorde) {
		
		String str_dDag = d.dag < 10 ? "0" + Integer.toString(d.dag) : Integer.toString(d.dag);
		String str_dMaand = d.maand < 10 ? "0" + Integer.toString(d.maand) : Integer.toString(d.maand);
		String str_dJaar = Integer.toString(d.jaar);
		String datumInString = "";
		switch (volgorde) {
			case "ddmmjjjj":
				datumInString = str_dDag + str_dMaand + str_dJaar;
				break;
			case "mmddjjjj":
				datumInString = str_dMaand + str_dDag + str_dJaar;
				break;
			case "jjjjmmdd":
				datumInString = str_dJaar + str_dMaand + str_dDag;
				break;
			default:
				datumInString = str_dJaar + str_dMaand + str_dDag;
				break;
		}
		
		int datum = Integer.parseInt(datumInString);
		
		return datum;
	}
	
	private int datumSamenvoegen(int d, int m, int j, String volgorde) {
		
		String str_dDag = d < 10 ? "0" + Integer.toString(d) : Integer.toString(d);
		String str_dMaand = m < 10 ? "0" + Integer.toString(m) : Integer.toString(m);
		String str_dJaar = Integer.toString(j);
		String datumInString = "";
		switch (volgorde) {
			case "ddmmjjjj":
				datumInString = str_dDag + str_dMaand + str_dJaar;
				break;
			case "mmddjjjj":
				datumInString = str_dMaand + str_dDag + str_dJaar;
				break;
			case "jjjjmmdd":
				datumInString = str_dJaar + str_dMaand + str_dDag;
				break;
			default:
				datumInString = str_dJaar + str_dMaand + str_dDag;
				break;
		}
		
		int datum = Integer.parseInt(datumInString);
		
		return datum;
	}
	
	// functies
	
	public boolean kleinerDan(Datum d) { //jjjjmmdd
		
		int datum = datumSamenvoegen(d, "jjjjmmdd");
		int huidig = datumSamenvoegen(this, "jjjjmmdd");
		
		if (datum < huidig)  {
			return true;
		} else {
		return false;
		}
	}
	
	public int verschilInJaren(Datum d) { //jjjjmmdd
		
		int datum = datumSamenvoegen(d, "jjjjmmdd");
		int huidig = datumSamenvoegen(this,"jjjjmmdd");
		
		int huidigeDag = getDag();
		int huidigeMaand = getMaand();
		int huidigJaar = getJaar();
		
		int dDag = d.dag;
		int dMaand = d.maand;
		int dJaar = d.jaar;

		int aantalJaren = 0;
		
		if (datum > huidig) {
			do {
				huidigJaar++;
				aantalJaren++;
			} while (dJaar > huidigJaar);
			if (huidigJaar == dJaar) {
				if (huidigeMaand > dMaand) { 
					aantalJaren--; 
				} else if (huidigeDag > dDag) {
					aantalJaren--;
				}
			}
		} else if (datum < huidig) {
			do {
				dJaar++;
				aantalJaren++;
			} while (dJaar < huidigJaar);
			if (huidigJaar == dJaar) {
				if (huidigeMaand > dMaand) { 
					aantalJaren--; 
				} else if (huidigeDag < dDag) {
					aantalJaren--;
				}
			}
		}
		return aantalJaren;
	}
	
	public int verschilInMaanden(Datum d) {

		return 0;
		
	}
	
	public int verschilInDagen(Datum d) {
		
		int datum = datumSamenvoegen(d, "jjjjmmdd");
		int huidig = datumSamenvoegen(this,"jjjjmmdd");
		
		int huidigeDag = getDag();
		int huidigeMaand = getMaand();
		int huidigJaar = getJaar();
		
		int dDag = d.dag;
		int dMaand = d.maand;
		int dJaar = d.jaar;

		int dv = 0;
		if (huidigJaar != dJaar) {
			for (int i = d.jaar-1; i> jaar; i--){
				if(isSchrikkeljaar(i)){
					dv++;
				}
				dv += 365;
			}
			dv+=dagVanHetJaar(d.dag, d.maand, d.jaar);
			dv+=(365-dagVanHetJaar(dag, maand, jaar));
			if(isSchrikkeljaar(jaar)) {dv++;}
		} else if (huidigJaar == dJaar) {
			if (huidigeMaand == dMaand) {
				dv = dDag - huidigeDag;
			} else {
				dv += (dDag - huidigeDag);
				for (int m = huidigeMaand; m < dMaand; m++) {
					dv += MaandenMetDagen[m];
					if (isSchrikkeljaar(huidigJaar) && m == 2) {
						dv++;
					}
				}
			}
		}
		return dv;
	}
	
	 public int dagVanHetJaar( int dag, int maand, int jaar){

		 int dagen = dag;
		 for(int i = 1; i < maand; i++){
		 dagen += MaandenMetDagen[i];
		 }
		 if(isSchrikkeljaar(jaar)&&maand>1){dagen++;}
		 return dagen;

		 }

	
	public void veranderDatum(int aantalDagen) {
		int dag = getDag();
		int maand = getMaand();
		int jaar = getJaar();
		int dagenInMaand;
		for (int d = aantalDagen; d > 0; d--) {
			if (maand == 2 && isSchrikkeljaar(jaar)) { 
				dagenInMaand = 29;
			} else {
				dagenInMaand = MaandenMetDagen[maand];
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
	
	public Datum veranderDatumObj(int aantalDagen) {
		int dag = getDag();
		int maand = getMaand();
		int jaar = getJaar();
		int dagenInMaand;
		for (int d = aantalDagen; d > 0; d--) {
			if (maand == 2 && isSchrikkeljaar(jaar)) { 
				dagenInMaand = 29;
			} else {
				dagenInMaand = MaandenMetDagen[maand];
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
		Datum date = new Datum(dag,maand,jaar);
		return date;
	}
	
	// Kleine test in de main om de constructoren te testen.
	
	public static void main(String[] args) {
		try 
		{
			Datum date = new Datum(10,12,2000);
			Datum date2 = new Datum("02/12/2000");
			System.out.println("Huidige datum: " + date.getDatumInAmerikaansFormaat(date));
			System.out.println("Datum 2: " + date2);
			System.out.println("Deze 2 datums verschillen met " + date.verschilInDagen(date2) +  " dagen");
		}
		catch (IllegalArgumentException ex){System.out.println(ex.getMessage());}
	}
	
	@Override
	public String toString() {
		return Integer.toString(dag) + " " + Maanden[maand-1] +" "+ Integer.toString(jaar);
	}
	
	@Override
	public boolean equals(Object oD) {
		
		if (this == oD) return true;
		if (!(oD instanceof Datum)) return false;
		
		Datum d = (Datum)oD;
		
		int dDag = d.dag;
		int dMaand = d.maand;
		int dJaar = d.jaar;
		
		if (dDag != getDag()) return false;
		if (dMaand != getMaand()) return false;
		if (dJaar != getJaar()) return false;
		
		return true;
		
	}
	
	public int compareTo(Datum d) {
		int verschil = datumSamenvoegen(this, "yyyymmdd") - datumSamenvoegen(d,"yyyymmdd");
		if (verschil < 0) return -1;
		if (verschil > 0) return 1;
		return 0;
	}
	
} 
