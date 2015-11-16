package opdracht_1d;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datum.Datum;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class BoekingGUI  extends JFrame{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textFieldDatumVanaf;
	public JTextField textFieldKlantNaam;
	public JTextField textFieldAantalNachten;
	public JTextField textFieldKlantVoornaam;
	private JLabel lblBeschikbareHuisjes;

	public JButton btnDatumKiezen;
	public JButton btnReserveren;
	private ArrayList<String> keuze = new ArrayList<String>();	
	public JComboBox comboBoxKeuze = new JComboBox(keuze.toArray());
	public JLabel lblReserveringGeslaagd;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoekingGUI frame = new BoekingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoekingGUI() {
		setTitle("BOEK HUISJE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 220, 36);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		contentPane.add(panel);
		
		JLabel lblDatumVanaf = new JLabel("Datum Vanaf");
		panel.add(lblDatumVanaf);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 54, 220, 36);
		contentPane.add(panel_1);
		
		textFieldDatumVanaf = new JTextField();
		panel_1.add(textFieldDatumVanaf);
		textFieldDatumVanaf.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 176, 220, 42);
		contentPane.add(panel_2);
		
		JButton btnDatumKiezen = new JButton("Check datum");
		panel_2.add(btnDatumKiezen);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(5, 261, 220, 42);
		contentPane.add(panel_3);
				
		
		panel_3.add(comboBoxKeuze);
		comboBoxKeuze.setVisible(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(5, 415, 220, 36);
		contentPane.add(panel_4);

		textFieldKlantNaam = new JTextField();
		textFieldKlantNaam.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(textFieldKlantNaam);
		textFieldKlantNaam.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(5, 382, 221, 36);
		contentPane.add(panel_5);
		
		JLabel lblKlantNaam = new JLabel("Naam klant");
		panel_5.add(lblKlantNaam);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(4, 464, 221, 42);
		contentPane.add(panel_6);
		
		btnReserveren = new JButton("RESERVEER");
		btnReserveren.setEnabled(true);
	
		panel_6.add(btnReserveren);
		btnReserveren.setEnabled(false);

		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(5, 519, 221, 45);
		contentPane.add(panel_7);
		
		JLabel lblReserveringGeslaagd = new JLabel("RESERVERING GESLAAGD!");
		panel_7.add(lblReserveringGeslaagd);
		lblReserveringGeslaagd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblReserveringGeslaagd.setVisible(false);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(5, 577, 220, 45);
		contentPane.add(panel_8);
		
		JButton btnNieuweReserveringMaken = new JButton("NIEUWE RESERVERING MAKEN");
		btnNieuweReserveringMaken.setEnabled(true);
		btnNieuweReserveringMaken.setVisible(false);
		panel_8.add(btnNieuweReserveringMaken);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(5, 304, 225, 26);
		contentPane.add(panel_9);
		
		JLabel lblVoornaamKlant = new JLabel("Voornaam klant");
		panel_9.add(lblVoornaamKlant);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(5, 103, 225, 26);
		contentPane.add(panel_10);
		
		JLabel lblAantalNachten = new JLabel("Aantal nachten");
		panel_10.add(lblAantalNachten);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(5, 142, 225, 26);
		contentPane.add(panel_11);
		
		textFieldAantalNachten = new JTextField();
		panel_11.add(textFieldAantalNachten);
		textFieldAantalNachten.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBounds(0, 343, 225, 26);
		contentPane.add(panel_12);
		
		textFieldKlantVoornaam = new JTextField();
		panel_12.add(textFieldKlantVoornaam);
		textFieldKlantVoornaam.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(5, 231, 220, 19);
		contentPane.add(panel_13);
		
		JLabel lblBeschikbareHuisjes = new JLabel("Beschikbare huisjes:");
		lblBeschikbareHuisjes.setVisible(false);
		panel_13.add(lblBeschikbareHuisjes);
		
		btnDatumKiezen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				if (BoekingApp.verifieerDatum(getDatumVanaf()) == false)
				{
					JOptionPane.showMessageDialog(null, "Gelieve een correcte datum vanaf morgen in te geven.");
				}
				else if (BoekingApp.verifieerInteger(getAantalNachtenGekozen()) == false)
				{
					JOptionPane.showMessageDialog(null, "Gelieve een aantal dagen in cijfers in te geven.");
				}
				else if (BoekingApp.verifieerMaxEnMinAantalDagen(Integer.parseInt(getAantalNachtenGekozen())) == false)
				{
					JOptionPane.showMessageDialog(null, "Gelieve minimum 1 en maximaal 14 dagen te boeken.");
				}
				else
				{
					ArrayList<String> Beschikbaar = new ArrayList<String>();
					Beschikbaar = BoekingApp.checkBeschikbareHuisjes
					(new Datum (getDatumVanaf()), Integer.parseInt(getAantalNachtenGekozen()));
					
					if (Beschikbaar.isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Helaas, er zijn geen huisjes voor deze periode beschikbaar.");
					}
					
					else{
						keuze.clear();
					for (String beschikbaar : Beschikbaar)
					{
						keuze.add(beschikbaar);
					}
					DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(keuze.toArray());
				    comboBoxKeuze.setModel(cboNewModel);
				    comboBoxKeuze.setVisible(true);
				    btnReserveren.setEnabled(true);
				    lblBeschikbareHuisjes.setVisible(true);
				    textFieldDatumVanaf.setEnabled(false);
				    textFieldAantalNachten.setEnabled(false);
					}
					
				}
				}});
		
		btnReserveren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
					Boeking nieuweBoeking = new Boeking (comboBoxKeuze.getSelectedItem().toString(),
							new Datum(getDatumVanaf()), Integer.parseInt(getAantalNachtenGekozen()),
							getAchternaam(),
							getVoornaam());
					if (BoekingApp.boekingWegschrijven(nieuweBoeking) == true)
					{
						JOptionPane.showMessageDialog(null, "Uw boeking is geregistreerd!");
						lblReserveringGeslaagd.setVisible(true);
						btnReserveren.setEnabled(false);
						btnDatumKiezen.setEnabled(false);
						btnNieuweReserveringMaken.setEnabled(true);
						btnNieuweReserveringMaken.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Helaas, er ging iets mis met de registratie.");
					}
				}

		});
		
		btnNieuweReserveringMaken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event){
				textFieldDatumVanaf.setText(null);
				textFieldDatumVanaf.setEnabled(true);
				textFieldAantalNachten.setText(null);
				textFieldAantalNachten.setEnabled(true);
				lblBeschikbareHuisjes.setVisible(false);
				lblReserveringGeslaagd.setVisible(false);
				btnDatumKiezen.setEnabled(true);
				btnReserveren.setEnabled(false);
				btnNieuweReserveringMaken.setVisible(false);
				comboBoxKeuze.setVisible(false);

				}

		});
		
	}
	

	
	public String getAantalNachtenGekozen()
	{
		return textFieldAantalNachten.getText();
	}
	public String getVoornaam()
	{
		return textFieldKlantVoornaam.getText();
	}
	public String getAchternaam()
	{
		return textFieldKlantNaam.getText();
	}
	
	public String getDatumVanaf()
	{
		return textFieldDatumVanaf.getText();
	}

}
