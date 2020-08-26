package oppgave2;

import java.util.ArrayList;
import java.util.Random;

public class Servitor extends Thread {
	
	private ArrayList<String> bestillinger;
	private Rutsjebane rb;
	private Object servitorLock;
	private Object kokkLock;
	
	public Servitor(String navn, Rutsjebane rb, ArrayList<String> bestillinger, Object kokkLock, Object servitorLock) {
		super(navn);
		this.rb = rb;
		this.bestillinger = bestillinger;
		this.kokkLock = kokkLock;
		this.servitorLock = servitorLock;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		System.out.println(getName() + " har startet å jobbe");
			
		while(true) {
			long tall = (long)((r.nextInt(4) + 2.5) * 1000);
			try {
				System.out.println(getName() + " tar bestilling i " + tall/1000 + " sekunder");
				Thread.sleep(tall);
			} 
			catch (InterruptedException e) {}
			
			synchronized(bestillinger) {
				bestillinger.add("");
				bestillinger.notifyAll();
			}
			
			while(rb.erTom()) {
				try {
					synchronized(servitorLock) {
						System.out.println("### " + getName() + " vil ta en hamburger, men rutsjebanen er tom. Venter! ###");
						servitorLock.wait();
					}
				} catch (InterruptedException e) {}
			}
			
			Burger b = rb.taUt();
			System.out.println(getName() + " tar av en hamburger   (" + b.getNr() + ")   =>   " + rb.toString());
			
			synchronized(kokkLock) {
				kokkLock.notify();
			}
		}
	}

}