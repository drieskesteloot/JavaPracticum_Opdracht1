package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ui.BoekingUi;

public class BoekingApp {

	private static Map <String,List<Boeking>> Boekingen = new HashMap<>();
	private static ArrayList<String> Huisjes = new ArrayList <String>();



	public static void main(String[] args) throws FileNotFoundException {

		try{
			Huisjes = bestaandeHuisjesOphalen();
			Boekingen = bestaandeBoekingenOphalen(Huisjes);

			BoekingUi boekingenVenster = new BoekingUi();
			boekingenVenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			boekingenVenster.setSize(250,750);
			boekingenVenster.setVisible(true);
			boekingenVenster.setResizable(false);
		}

		catch (FileNotFoundException f)
		{
			JOptionPane.showMessageDialog(null, "Helaas, de databankgegevens kunnen niet opgehaald worden."
					+ "\nDe toepassing kan niet worden gestart."
					+"\n(De txt bestanden kunnen niet worden gevonden.)");
		}



	}


	private static ArrayList<String> bestaandeHuisjesOphalen() throws FileNotFoundException
	{
		ArrayList<String> HuisjesOphalen = new ArrayList<String>();
		//beschikbaar vastgoed ophalen
		File texth = new File("bin/huisjes.txt");

		Scanner scnrh = new Scanner(texth);
		while(scnrh.hasNextLine())
		{
			String line = scnrh.nextLine();
			String huisje = line;
			HuisjesOphalen.add(huisje);
		}

		//sluit scanner
		scnrh.close();
		return HuisjesOphalen;
	}

	private static Map <String, List<Boeking>> bestaandeBoekingenOphalen(List<String> BeschikbareHuisjes) throws FileNotFoundException
	{
		Map <String,List<Boeking>> BoekingenOphalen = new HashMap<>();

		for (String nummerHuisje : BeschikbareHuisjes)
		{
			File text = new File("bin/boekingen.txt");
			Scanner scnr = new Scanner(text);
			List <Boeking> BoekingenPerHuisje = new ArrayList<Boeking>();
			while(scnr.hasNextLine())
			{
				String line = scnr.nextLine();
				String BoekingString[] = line.split("[,>]");
				String huisje = BoekingString[0];

				if (huisje.equals(nummerHuisje))
				{

					Datum vanDatum = new Datum (BoekingString[1]);
					Integer aantalDagen = Integer.parseInt(BoekingString[2]);
					String familieNaam = BoekingString[3];
					String voorNaam = BoekingString[4];
					Boeking nieuweBoeking = new Boeking (huisje,vanDatum,aantalDagen, familieNaam,voorNaam);
					BoekingenPerHuisje.add(nieuweBoeking);
				}
			}
			if (BoekingenPerHuisje.size() != 0)
			{

				BoekingenOphalen.put(nummerHuisje, BoekingenPerHuisje);
			}
			scnr.close();
		}

		return BoekingenOphalen;
	}

	public static boolean boekingWegschrijven (Boeking nieuweBoeking)
	{


		try
		{
			String wegTeSchrijven= "\n" + nieuweBoeking.getHuisje() + "," + nieuweBoeking.getStartDatum().getDatumInEuropeesFormaat(nieuweBoeking.getStartDatum())
					+"," + nieuweBoeking.getAantalDagen() + ">" +nieuweBoeking.getAchternaamKlant() + ","+
					nieuweBoeking.getVoornaamKlant();
			Path FILE_PATH = Paths.get("bin", "boekingen.txt");

			//Bijschrijven in bestaand bestand
			try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) 
			{
				writer.write(wegTeSchrijven);
			} 

			catch (IOException e) 
			{
				e.printStackTrace();
			}

			if (Boekingen.get(nieuweBoeking.getHuisje()) == null)
			{
				List<Boeking> empty = new ArrayList<Boeking>();
				Boekingen.put(nieuweBoeking.getHuisje(),empty);
			}
			(Boekingen.get(nieuweBoeking.getHuisje())).add(nieuweBoeking);		
			return true;
		}
		catch (Exception e)
		{
			return false;
		}

	}

	public static ArrayList<String> checkBeschikbareHuisjes(Datum vanaf, Integer nachten)
	{


		Boeking checkBoeking = new Boeking (vanaf, nachten);
		ArrayList<String> beschikbareHuisjes = new ArrayList<String>();

		for (String huisje: Huisjes) {

			boolean beschikbaar = true;

			List<Boeking> boekingenGeregistreerd = Boekingen.get(huisje);
			if (Boekingen.get(huisje) != null)

			{
				for (Boeking bestaandeBoeking : boekingenGeregistreerd)
				{

					if (checkBoeking.heeftOverlap(bestaandeBoeking) == true)
					{beschikbaar = false;}
				}
			}


			if (beschikbaar == true){ beschikbareHuisjes.add(huisje);}

		}

		return beschikbareHuisjes;
	}

	public static boolean verifieerInteger (String teVerifieren)
	{
		try
		{
			Integer nieuw = Integer.parseInt (teVerifieren);
			return true;
		}

		catch (Exception e)
		{
			return false;
		}
	}

	public static boolean verifieerMaxEnMinAantalDagen (Integer teVerifieren)
	{
		if (teVerifieren <= 14 && teVerifieren > 0)
		{
			return true;
		}
		else return false;
	}


	public static boolean verifieerDatum(String teVerifieren)
	{
		try
		{
			Datum nieuw = new Datum (teVerifieren);
			Datum nu = new Datum();
			if (nu.compareTo(nieuw) < 0)
			{
				return true;
			}
			else return false;
		}

		catch (Exception e)
		{
			return false;
		}
	}



}
