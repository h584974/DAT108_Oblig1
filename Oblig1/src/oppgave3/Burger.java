package oppgave3;

public class Burger {
	
	private int nr;
	
	public Burger(int nr) {
		this.nr = nr;
	}
	
	public int getNr() {
		return nr;
	}
	
	@Override
	public String toString() {
		return "(" + nr + ")";
	}

}