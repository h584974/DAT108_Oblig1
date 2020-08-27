package oppgave2;

import java.util.ArrayList;

public class Klient {

	public static void main(String[] args) {
		
		BurgerID id = new BurgerID();
		
		Rutsjebane rb = new Rutsjebane(5);
		
		Kokk kokk1 = new Kokk("Kokk1", rb, id);
		Kokk kokk2 = new Kokk("Kokk2", rb, id);
		Kokk kokk3 = new Kokk("Kokk3", rb, id);
		
		Servitor servitor1 = new Servitor("Servitør1", rb);
		Servitor servitor2 = new Servitor("Servitør2", rb);
		kokk1.start();
		kokk2.start();
		kokk3.start();
		servitor1.start();
		servitor2.start();

	}

}