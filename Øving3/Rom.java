import java.util.ArrayList;

class Rom{
	private ArrayList<Reservasjon> res;
	private int nr;
	private int str;

	public Rom(int nr,int str){
		res = new ArrayList<Reservasjon>();
		this.nr = nr;
		this.str = str;
	}

	public Rom(){
		res = new ArrayList<Reservasjon>();
		this.nr = 0;
		this.str = 0;
	}

	public int getNr(){
		return this.nr+1;
	}
	public int getStr(){
		return this.str;
	}

	public void nyReservasjon(Tidspunkt fra,Tidspunkt til,Kunde kunde) throws IllegalArgumentException{
		for (Reservasjon i:res){
			if (i.overlapp(fra,til)){
				throw new IllegalArgumentException("Tidspunktet overlapper med annen reservasjon");
			}
		}
		res.add(new Reservasjon(fra,til,kunde));
	}

	@Override
	public String toString(){
		String ut = "Rom nr: "+(this.nr+1)+"\n";
		ut += "Størrelse: "+this.str+"\n";
		for (Reservasjon r:res){
			ut += r.toString()+"\n";
		}
		return ut;
	}


	public static void main(String[] args){
		Rom rom1 = new Rom(15,150);
		Rom rom2 = new Rom(16,110);
		Rom rom3 = new Rom(17,70);

		rom1.nyReservasjon(new Tidspunkt(200312042030L),new Tidspunkt(200312042200L),new Kunde("Jens","41434343"));
		rom1.nyReservasjon(new Tidspunkt(200310192000L),new Tidspunkt(200310192300L),new Kunde("Snorre","99999999"));
		rom3.nyReservasjon(new Tidspunkt(200307012030L),new Tidspunkt(200307012200L),new Kunde("Ole","123123123"));
		//rom3.nyReservasjon(new Tidspunkt(200307012030L),new Tidspunkt(200307012200L),new Kunde("Pål","66666666"));
		System.out.println(rom1.toString());
		System.out.println(rom2.toString());
		System.out.println(rom3.toString());
	}
}