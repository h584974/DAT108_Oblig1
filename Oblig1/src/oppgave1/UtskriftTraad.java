package oppgave1;

public class UtskriftTraad extends Thread {
	
	private boolean fortsette = true;
	private String ord = "";
	
	@Override
	public void run() {
		while(fortsette) {
			if(!ord.isEmpty()) {
				System.out.println(ord);
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopp() {
		this.fortsette = false;
	}
	
	public void setOrd(String ord) {
		this.ord = ord;
	}

}
