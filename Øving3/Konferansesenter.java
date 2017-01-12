import java.util.ArrayList;

class Konferansesenter{
	private ArrayList<Rom> rom;

	public Konferansesenter(){
		rom = new ArrayList<Rom>();
	}

	public Rom nyttRom(int str){
		if (str<=0){
			throw new IllegalArgumentException("Størrelse må være over 0");
		} else {
			rom.add(new Rom(rom.size(),str));
			return rom.get(rom.size()-1);
		}
	}

	public int antallRom(){
		return rom.size();
	}

	public Rom getRom(int nr){
		if (nr>0&&nr<=rom.size()){
			return rom.get(nr-1);
		} else {
			throw new IllegalArgumentException("Rommet eksisterer ikke!");
		}
	}

	public void nyReservasjon(Rom r,Tidspunkt fra,Tidspunkt til,Kunde kunde) throws IllegalArgumentException{
		r.nyReservasjon(fra,til,kunde);
	}

	@Override
	public String toString(){
		String ut = "";
		for (Rom r:rom){
			ut+=r.toString()+"\n";
		}
		return ut;
	}
}