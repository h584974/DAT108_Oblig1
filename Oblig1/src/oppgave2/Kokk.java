package oppgave2;

import java.util.Random;

public class Kokk extends Thread {
	
	private Rutsjebane rb;
	private BurgerID burgerID;
	
	public Kokk(String navn, Rutsjebane rb, BurgerID burgerID) {
		super(navn);
		this.rb = rb;;
		this.burgerID = burgerID;
	}
	
	@Override
	public void run() {
		Burger b = null;
		Random r = new Random();
		System.out.println(getName() + " har startet å jobbe");
		
		while(true) {
				
			long tall = (long)((r.nextInt(5) + 2) * 1000);
			try {
				Thread.sleep(tall);
			} catch (InterruptedException e) {}
				
				
			b = new Burger(burgerID.getId());
			burgerID.setId(burgerID.getId() + 1);
				
			synchronized(rb) {
				while(rb.erFull()) {
					try {
						System.out.println("### " + getName() + " vil legge en hamburger på rutsjebanen, men den er full. Venter! ###");
						rb.wait();
					} catch (InterruptedException e) {}
				}
				rb.leggTil(b);
				rb.notifyAll();
			}
				
			System.out.println(getName() + " legger på en hamburger   (" + b.getNr() + ")   =>   " + rb.toString());
		}
	}

}