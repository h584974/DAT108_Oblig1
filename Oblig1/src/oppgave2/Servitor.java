package oppgave2;

import java.util.Random;

public class Servitor extends Thread {
	
	private Rutsjebane rb;
	private Object servitorLock;
	private Object kokkLock;
	
	public Servitor(String navn, Rutsjebane rb, Object kokkLock, Object servitorLock) {
		super(navn);
		this.rb = rb;
		this.kokkLock = kokkLock;
		this.servitorLock = servitorLock;
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
			} 
			catch (InterruptedException e) {}
			
			synchronized(rb) {
				while(rb.erTom()) {
					try {
						System.out.println("### " + getName() + " vil ta en hamburger, men rutsjebanen er tom. Venter! ###");
						rb.wait();
					} catch (InterruptedException e) {}
				}
				
				b = rb.taUt();
				rb.notifyAll();
			}
			
			System.out.println(getName() + " tar av en hamburger   (" + b.getNr() + ")   =>   " + rb.toString());
			
		}
	}

}