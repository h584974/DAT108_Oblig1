package oppgave2;

public class Rutsjebane {
	
	private Burger[] burgere;
	private int antall;
	private int foran;
	private int bak;
	
	public Rutsjebane(int antallPlasser) {
		this.burgere = new Burger[antallPlasser];
		this.antall = 0;
		foran = 0;
		bak = 0;
	}
	
	public boolean erFull() {
		return antall == burgere.length;
	}
	
	public boolean erTom() {
		return antall == 0;
	}
	
	public boolean leggTil(Burger burger) {
		if(!erFull()) {
			burgere[bak] = burger;
			bak = (bak + 1) % burgere.length;
			antall++;
			return true;
		}
		return false;
	}
	
	public Burger taUt() {
		if(!erTom()) {
			Burger ut = burgere[foran];
			foran = (foran + 1) % burgere.length;
			antall--;
			return ut;
		}
		return null;
	}

}