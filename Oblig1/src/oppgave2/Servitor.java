package oppgave2;

public class Servitor extends Thread {
	
	private Rutsjebane rb;
	
	public Servitor(Rutsjebane rb) {
		super();
		this.rb = rb;
	}
	
	@Override
	public void run() {
		synchronized(rb) {
			while(true) {
				while(rb.erTom()) {
					try {
						rb.wait();
					} catch (InterruptedException e) {}
				}
				
				System.out.println(rb.taUt().getNr());
				rb.notifyAll();
			}
		}
	}

}