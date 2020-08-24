package oppgave2;

public class Klient {

	public static void main(String[] args) {
		
		Rutsjebane rb = new Rutsjebane(5);
		
		Kunde k = new Kunde();
		
		Kokk kokk = new Kokk(rb, k);
		
		Servitor servitor = new Servitor(rb);
		
		k.start();
		kokk.start();
		servitor.start();

	}

}
