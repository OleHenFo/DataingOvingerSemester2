import static javax.swing.JOptionPane.*;

class Klient{
	public static void main(String[] args){
		Restaurant res = new Restaurant("Rekondos",2008,18);
		Object[] options = {"Reserver bord","Finn reservasjon","Frigi bord","Vis alle","Avslutt"};
		while (!false){
			int ch = showOptionDialog(null,String.format("Valg"),"Bord",DEFAULT_OPTION,PLAIN_MESSAGE,null,options,options[0]);
			switch (ch){
				case 0:
					String navn = showInputDialog(null,"Navn på reservasjon");
					int antall = Integer.parseInt(showInputDialog(null,"Antall bord"));
					res.reserverBord(navn,antall);
					break;
				case 1:
					String n = showInputDialog(null,"Navn på reservasjon");
					int[] reserverte = res.reserverteBord(n);
					String ut = "";
					for (int nr:reserverte){
						ut += nr+", ";
					}
					showMessageDialog(null,n+" har reservert bord nummer: "+ut);
					break;
				case 2:
					int x = Integer.parseInt(showInputDialog(null,"Hvor mange bord?"));
					int[] bor = new int[x];
					for (int i=1;i<=x;i++){
						bor[i-1] = Integer.parseInt(showInputDialog(null,"Oppgi bordnr:"));
					}
					res.frigiBord(bor);
					break;
				case 3:
					showMessageDialog(null,res.toString());
					break;
				case 4:
					System.exit(1);
					break;
			}
		}
	}
}