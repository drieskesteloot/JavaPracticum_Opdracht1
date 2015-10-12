package opdracht_1d;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class ReservatieGUI {

	protected Shell shlReservatietool;
	private Text FirstNameText;
	private Text LastNameText;
	private Text ReserveringText;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReservatieGUI window = new ReservatieGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlReservatietool.open();
		shlReservatietool.layout();
		while (!shlReservatietool.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlReservatietool = new Shell();
		shlReservatietool.setSize(566, 421);
		shlReservatietool.setText("ReservatieTool");
		
		DateTime dateTime = new DateTime(shlReservatietool, SWT.BORDER);
		dateTime.setBounds(65, 98, 85, 24);
		
		DateTime dateTime_1 = new DateTime(shlReservatietool, SWT.BORDER);
		dateTime_1.setBounds(208, 98, 85, 24);
		
		FirstNameText = new Text(shlReservatietool, SWT.BORDER);
		FirstNameText.setBounds(89, 20, 165, 24);
		
		Label lblFrom = new Label(shlReservatietool, SWT.NONE);
		lblFrom.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblFrom.setBounds(21, 107, 37, 15);
		lblFrom.setText("From:");
		
		Label lblTo = new Label(shlReservatietool, SWT.NONE);
		lblTo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblTo.setBounds(166, 107, 37, 15);
		lblTo.setText("to:");
		
		LastNameText = new Text(shlReservatietool, SWT.BORDER);
		LastNameText.setBounds(89, 50, 165, 24);
		
		Label lblFirstName = new Label(shlReservatietool, SWT.NONE);
		lblFirstName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblFirstName.setBounds(10, 29, 73, 15);
		lblFirstName.setText("First Name:");
		
		Label lblLastName = new Label(shlReservatietool, SWT.NONE);
		lblLastName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLastName.setBounds(10, 59, 73, 15);
		lblLastName.setText("Last Name:");
		
		Combo combo = new Combo(shlReservatietool, SWT.NONE);
		combo.setBounds(10, 164, 91, 23);
		
		Label lblVrijstaandeHuisjes = new Label(shlReservatietool, SWT.NONE);
		lblVrijstaandeHuisjes.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblVrijstaandeHuisjes.setBounds(10, 143, 121, 15);
		lblVrijstaandeHuisjes.setText("Huisjes:");
		
		Button btnBevestigReservering = new Button(shlReservatietool, SWT.NONE);
		btnBevestigReservering.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		btnBevestigReservering.setBounds(10, 327, 170, 25);
		btnBevestigReservering.setText("Bevestig reservering");
		
		ReserveringText = new Text(shlReservatietool, SWT.BORDER);
		ReserveringText.setBounds(10, 226, 307, 83);
		
		Label lblReedsBevestigdeReservaties = new Label(shlReservatietool, SWT.NONE);
		lblReedsBevestigdeReservaties.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblReedsBevestigdeReservaties.setBounds(10, 205, 180, 15);
		lblReedsBevestigdeReservaties.setText("Reeds bevestigde reservaties:");
		
		Button btnKijkBeschikbaarheidNa = new Button(shlReservatietool, SWT.NONE);
		btnKijkBeschikbaarheidNa.setBounds(336, 97, 155, 25);
		btnKijkBeschikbaarheidNa.setText("Kijk beschikbaarheid na:");

	}
}
