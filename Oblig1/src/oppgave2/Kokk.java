package oppgave2;

import java.util.ArrayList;
import java.util.Random;

public class Kokk extends Thread {
	
	private Rutsjebane rb;
	private ArrayList<String> bestillinger;
	private Object kokkLock;
	private Object servitorLock;
	private BurgerID burgerID;
	
	public Kokk(String navn, Rutsjebane rb, ArrayList<String> bestillinger, Object kokkLock, Object servitorLock, BurgerID burgerID) {
		super(navn);
		this.rb = rb;
		this.bestillinger = bestillinger;
		this.kokkLock = kokkLock;
		this.servitorLock = servitorLock;
		this.burgerID = burgerID;
	}
	
	@Override
	public void run() {
		Burger b = null;
		Random r = new Random();
		System.out.println(getName() + " har startet å jobbe");
		
		while(true) {
			
			synchronized(bestillinger) {
				while(bestillinger.isEmpty()) {
					try {
						bestillinger.wait();
					} catch (InterruptedException e) {}
				}
			
				bestillinger.remove(0);
			}
				
			long tall = (long)((r.nextInt(4) + 2.5) * 1000);
			try {
				System.out.println(getName() + " lager hamburger i " + tall/1000 + " sekunder");
				Thread.sleep(tall);
			} catch (InterruptedException e) {}
				
				
			b = new Burger(burgerID.getId());
			burgerID.setId(burgerID.getId() + 1);
				
			synchronized(rb) {
				while(rb.erFull()) {
					try {
						synchronized(kokkLock) {
							System.out.println("### " + getName() + " vil legge en hamburger på rutsjebanen, men den er full. Venter! ###");
							kokkLock.wait();
						}
					} catch (InterruptedException e) {}
				}
				
				rb.leggTil(b);
				System.out.println(getName() + " legger på en hamburger   (" + b.getNr() + ")   =>   " + rb.toString());
				synchronized(servitorLock) {
					servitorLock.notify();
				}
			}
		}
	}

}