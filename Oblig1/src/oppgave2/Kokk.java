package oppgave2;

import java.util.ArrayList;
import java.util.Random;

public class Kokk extends Thread {
	
	private Rutsjebane rb;
	private ArrayList<String> bestillinger;
	private Object kokkLock;
	private Object servitorLock;
	
	public Kokk(String navn, Rutsjebane rb, ArrayList<String> bestillinger, Object kokkLock, Object servitorLock) {
		super(navn);
		this.rb = rb;
		this.bestillinger = bestillinger;
		this.kokkLock = kokkLock;
		this.servitorLock = servitorLock;
	}
	
	@Override
	public void run() {
		int antall = 0;
		Burger b = null;
		Random r = new Random();
		System.out.println(getName() + " har startet å jobbe");
		
		synchronized(rb) {
			while(true) {
				while(bestillinger.isEmpty()) {
					synchronized(bestillinger) {
						try {
							bestillinger.wait();
						} catch (InterruptedException e) {}
					}
				}
				
				System.out.println(getName() + "har mottatt bestilling");
				
				bestillinger.remove(0);
				
				long tall = (long)((r.nextInt(4) + 2) * 1000);
				try {
					Thread.sleep(tall);
				} catch (InterruptedException e) {}
				
				
				b = new Burger(antall);
				
				while(rb.erFull()) {
					try {
						synchronized(kokkLock) {
							kokkLock.wait();
						}
					} catch (InterruptedException e) {}
				}
				
				rb.leggTil(b);
				rb.skrivUt();
				synchronized(servitorLock) {
					servitorLock.notifyAll();
				}
				
				antall++;
			}
		}
	}

}