import java.time.Year;

public class Restaurant{
	private String navn;
	private int ar;
	private Bord bord;
	public Restaurant(String n,int a,int b){
		this.navn = n;
		this.ar = a;
		this.bord = new Bord(b);
	}

	public String getNavn(){
		return this.navn;
	}

	public boolean setNavn(String n){
		this.navn = n;
		if (this.navn.equals(n)){
			return true;
		} else {
			return false;
		}
	}

	public int getAr(){
		return this.ar;
	}

	public int getAlder(){
		int na = Year.now().getValue();
		return (na-this.ar);
	}

	public int ledigeBord(){
		return this.bord.antallLedig();
	}

	public int opptattBord(){
		return this.bord.antallOpptatt();
	}

	public String reserverBord(String n,int a){
		if (this.bord.antallLedig()>a){
			int i = 1;
			int b = 1;
			while (i<=a){
				if (this.bord.ledig(b)){
					this.bord.reserverBord(b,n);
					b++;
					i++;
				} else {
					b++;
				}
			}
			return a+" bord reservert til: "+n+"!";
		} else {
			return "Ikke nok ledige bord!";
		}
	}

	public int[] reserverteBord(String n){
		int c = 0;
		for (int i=1;i<=this.bord.antallBord();i++){
			if (this.bord.getNavn(i).equals(n)){
				c++;
			}
		}
		int[] b = new int[c];
		c=0;
		for (int i=1;i<=this.bord.antallBord();i++){
			if (this.bord.getNavn(i).equals(n)){
				b[c] = i;
				c++;
			}
		}
		return b;
	}

	public String toString(){
		return this.bord.toString();
	}

	public boolean frigiBord(int[] f){
		for (int b:f){
			this.bord.frigiBord(b);
		}
		return true;
	}
}