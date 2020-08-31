package oppgave3;

public class BurgerID {
	
	private int id;
	
	public BurgerID() {
		this.id = 1;
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void setId(int id) {
		this.id = id;
	}

}