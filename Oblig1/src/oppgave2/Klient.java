package oppgave2;

import java.util.ArrayList;

public class Klient {

	public static void main(String[] args) {
		
		Rutsjebane rb = new Rutsjebane(5);
		
		ArrayList<String> bestillinger = new ArrayList<String>();
		
		Kunde k = new Kunde(bestillinger);
		
		Kokk kokk1 = new Kokk(rb, k, bestillinger);
		Kokk kokk2 = new Kokk(rb, k, bestillinger);
		Kokk kokk3 = new Kokk(rb, k, bestillinger);
		
		Servitor servitor1 = new Servitor(rb);
		Servitor servitor2 = new Servitor(rb);
		
		k.start();
		kokk1.start();
		kokk2.start();
		kokk3.start();
		servitor1.start();
		servitor2.start();

	}

}