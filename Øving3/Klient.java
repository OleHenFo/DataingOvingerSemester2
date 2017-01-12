import static javax.swing.JOptionPane.*;

class Klient{
	public static Object[] options = {"Nytt rom","Ny reservasjon","Vis rom","Vis alle","Avslutt"};
	public static void main(String[] args){
		Konferansesenter k = new Konferansesenter();

		while (true){
			int ch = showOptionDialog(null,String.format("Velg"),"Konferansesenter",DEFAULT_OPTION,PLAIN_MESSAGE,null,options,options[0]);
			switch (ch){
				case 0:
					int str;
					try {
						str = Integer.parseInt(showInputDialog(null,"Hvor mange er det plass til?"));
					} catch (Exception e){
						showMessageDialog(null,"Bruk et gyldig tall!");
						break;
					}
					try {
						Rom rom = k.nyttRom(str);
						showMessageDialog(null,"Rom nr "+rom.getNr()+" er registrert!");
					} catch (Exception e){
						showMessageDialog(null,e.toString().split(": ")[1]);
						break;
					}
					break;
				case 1:
					int rnr;
					Rom rm;
					String navn;
					String tlf;
					Long fra = 0L;
					Long til = 0L;
					try {
						rnr = Integer.parseInt(showInputDialog(null,"Hvilket rom?"));
					} catch (Exception e){
						showMessageDialog(null,"Bruk et gyldig tall!");
						break;
					}
					try {
						rm = k.getRom(rnr);
					} catch (Exception e){
						showMessageDialog(null,e.toString().split(": ")[1]);
						break;
					}
					navn = showInputDialog(null,"Navn på kunde:");
					tlf = showInputDialog(null,"Tlf nr til kunde:");
					try {
						fra = Long.parseLong(showInputDialog(null,"Oppgi FRA tidspunkt på formen \"ååååmmddttmm\""));
						til = Long.parseLong(showInputDialog(null,"Oppgi TIL tidspunkt på formen \"ååååmmddttmm\""));
					} catch (Exception e){
						showMessageDialog(null,"Skriv tidspunktet på riktig form!");
						break;
					}
					try {
						k.nyReservasjon(rm,new Tidspunkt(fra),new Tidspunkt(til),new Kunde(navn,tlf));
					} catch (Exception e){
						showMessageDialog(null,e.toString().split(": ")[1]);
						break;
					}
					showMessageDialog(null,"Reservasjon lagt til!");
					break;
				case 2:
					int nr;
					try {
						nr = Integer.parseInt(showInputDialog(null,"Angi romnr"));
					} catch (Exception e){
						showMessageDialog(null,"Bruk et gyldig tall!");
						break;
					}
					try {
						Rom rom = k.getRom(nr);
						showMessageDialog(null,rom.toString());
					} catch (Exception e){
						showMessageDialog(null,e.toString().split(": ")[1]);
						break;
					}
					break;
				case 3:
					showMessageDialog(null,k.toString());
					break;
				case 4:
					System.exit(1);
				default:
					break;
			}
		}
	}
}