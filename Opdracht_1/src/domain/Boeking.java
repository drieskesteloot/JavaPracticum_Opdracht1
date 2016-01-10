package domain;

public class Boeking {
	
	private String voornaamKlant;
	public String getVoornaamKlant()
	{return voornaamKlant;}
	
	private String achternaamKlant;
	public String getAchternaamKlant()
	{return achternaamKlant;}
	
	private String huisje;
	public String getHuisje()
	{ return huisje; }
	
	private Datum startDatum;
	public Datum getStartDatum()
	{ return startDatum;}
	
	private int aantalDagen;
	public int getAantalDagen()
	{ return aantalDagen;}

	
	public Boeking (String huisje, Datum startDatum, int aantalDagen, String achternaamKlant, String voornaamKlant)
	{
		this.achternaamKlant = achternaamKlant;
		this.voornaamKlant = voornaamKlant;
		this.startDatum = startDatum;
		this.aantalDagen = aantalDagen;
		this.huisje = huisje;
	}
	
	public Boeking (Datum startDatum, int aantalDagen)
	{
		this.achternaamKlant = "";
		this.voornaamKlant = "";
		this.startDatum = startDatum;
		this.aantalDagen = aantalDagen;
		this.huisje = "";
	}
	
	public boolean heeftOverlap(Boeking check)
	{
		if (this.startDatum.compareTo(check.getStartDatum()) == -1)
		{
			
			Datum eindDatum = this.startDatum.veranderDatumObj(this.aantalDagen);
			if (eindDatum.compareTo(check.getStartDatum()) <= 0)
			{
				return false;
			}
			else return true;
			
		}
	
		else if (this.startDatum.compareTo(check.getStartDatum()) == 1)
		{
			
			Datum eindDatum = check.getStartDatum().veranderDatumObj(check.getAantalDagen());
			if (this.startDatum.compareTo(eindDatum) >= 0)
			{
				return false;
			}
			else return true;
			
		}
		else return true;
	}

}
