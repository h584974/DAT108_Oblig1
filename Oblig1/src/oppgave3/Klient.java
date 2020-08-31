package oppgave3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Klient {

	public static void main(String[] args) {
		
		BurgerID id = new BurgerID();
		
		BlockingQueue<Burger> queue = new ArrayBlockingQueue<Burger>(5);
		
		Kokk kokk1 = new Kokk("Kokk1", queue, id);
		Kokk kokk2 = new Kokk("Kokk2", queue, id);
		Kokk kokk3 = new Kokk("Kokk3", queue, id);
		
		Servitor servitor1 = new Servitor("Servitør1", queue);
		Servitor servitor2 = new Servitor("Servitør2", queue);
		
		kokk1.start();
		kokk2.start();
		kokk3.start();
		servitor1.start();
		servitor2.start();

	}

}