package oppgave1;

import javax.swing.JOptionPane;

public class Klient {

	public static void main(String[] args) {

		UtskriftTraad t = new UtskriftTraad();
		
		t.start();
		
		while(true) {
			String ord = JOptionPane.showInputDialog(null,"Skriv inn ord: ", "Skriv 'quit' for å avslutte");
			if(ord.toLowerCase().equals("quit")) {
				t.stopp();
				System.out.println("Program avsluttet");
				break;
			}
			else {
				t.setOrd(ord);
			}
		}

	}

}