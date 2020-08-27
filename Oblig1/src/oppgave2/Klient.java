package oppgave2;

import java.util.ArrayList;

public class Klient {

	public static void main(String[] args) {
		
		BurgerID id = new BurgerID();
		
		Object kokkLock = new Object();
		Object servitorLock = new Object();
		Rutsjebane rb = new Rutsjebane(5);
		
		Kokk kokk1 = new Kokk("Kokk1", rb, kokkLock, servitorLock, id);
		Kokk kokk2 = new Kokk("Kokk2", rb, kokkLock, servitorLock, id);
		Kokk kokk3 = new Kokk("Kokk3", rb, kokkLock, servitorLock, id);
		
		Servitor servitor1 = new Servitor("Servitør1", rb, kokkLock, servitorLock);
		Servitor servitor2 = new Servitor("Servitør2", rb, kokkLock, servitorLock);
		kokk1.start();
		kokk2.start();
		kokk3.start();
		servitor1.start();
		servitor2.start();

	}

}