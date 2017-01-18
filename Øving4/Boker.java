import static javax.swing.JOptionPane.*;
import java.sql.*;

class Boker{
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public Boker(String url,String user,String pass){
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException ex) {
			System.out.println("1. "+ex);
		}
		System.out.println("Connected!");
	}

	public String tableToString(String table){
		String out = "";
		try {
			st = this.con.createStatement();
			rs = st.executeQuery("SELECT * FROM "+table);
			ResultSetMetaData md = rs.getMetaData();
			int size = md.getColumnCount();

			while (rs.next()) {
				for (int i = 1;i<=size;i++){
					if (i==size){
						out+=rs.getString(i);
					} else {
						out+=rs.getString(i)+" - ";
					}
				}
				out+="\n";
			}
		} catch (Exception ex) {
			System.out.println("2. "+ex);
		}
		return out;
	}

	@Override
	public String toString(){
		String out = "";
		try {
			st = this.con.createStatement();
			ResultSet rs2 = st.executeQuery("SELECT * FROM eksemplar");
			ResultSetMetaData md = rs2.getMetaData();
			int size = md.getColumnCount();

			while (rs2.next()) {
				String isbn = rs2.getString("isbn");
				out+="\""+this.getTittel(isbn)+"\" av "+this.getForfatter(isbn)+", er ";
				String n = rs2.getString("laant_av");
				if (n!=null){
					out+="lånt av "+n+".";
				} else {
					out+="ledig.";
				}
				out+="\n";
			}
			return out;
		} catch (Exception ex) {
			System.out.println("2. "+ex);
		}
		return out;
	}

	public boolean update(String r){
		try {
			st = this.con.createStatement();
			int rs = st.executeUpdate(r);
			if (rs==0){
				return true;
			} else {
				return true;
			}
		} catch (Exception ex){
			return false;
		}
	}

	public ResultSet query(String q){
		try {
			this.st = this.con.createStatement();
			this.rs = st.executeQuery(q);
			return rs;
		} catch (Exception ex){
			return null;
		}
	}

	public String getForfatter(String isbn){
		try {
			rs = this.query("SELECT forfatter FROM boktittel WHERE isbn = '"+isbn+"'");
			rs.next();
			return rs.getString("forfatter");
		} catch (Exception ex){
			return null;
		}
	}
	public String getTittel(String isbn){
		try {
			rs = this.query("SELECT tittel FROM boktittel WHERE isbn = '"+isbn+"'");
			rs.next();
			return rs.getString("tittel");
		} catch (Exception ex){
			return null;
		}
	}
	public int getEksemplarer(String isbn){
		try {
			rs = this.query("SELECT count(*) antall FROM eksemplar WHERE isbn = '"+isbn+"'");
			rs.next();
			return rs.getInt("antall");
		} catch (Exception ex){
			return 0;
		}
	}

	public static void main(String[] args){
		String url = "jdbc:mysql://mysql.stud.iie.ntnu.no/olehenfo";
		String user = "olehenfo";
        String pass = "r7Q4Z1mX";
        Boker bok = new Boker(url,user,pass);
		//System.out.println(bok.tableToString("boktittel"));
		Object[] opts = {"Hent info","Lån ut bok","Vis utlånstatus","Avslutt"};
		String isbn = "";
		String navn = "";
		ResultSet rs = null;
		ResultSet rs2 = null;
		int ch = 0;
		while (ch!=3){
			ch = showOptionDialog(null,String.format("Velg"),"SQL",DEFAULT_OPTION,PLAIN_MESSAGE,null,opts,opts[0]);
			switch (ch){
				case 0:
					isbn = showInputDialog(null,"Skriv inn ISBN");
					String forf = bok.getForfatter(isbn);
					String titt = bok.getTittel(isbn);
					int eks = bok.getEksemplarer(isbn);
									showMessageDialog(null,"Forfatter: "+forf+"\nTittel: "+titt+"\nEksemplarer: "+eks);
					break;
				case 1:
					isbn = showInputDialog(null,"Skriv inn ISBN");
					navn = showInputDialog(null,"Skriv inn navn på låner");
					String em = showInputDialog(null,"Exemplar nr?");
					boolean check = bok.update("UPDATE eksemplar SET laant_av = '"+navn+"' WHERE isbn = '"+isbn+"' AND eks_nr = "+em+" AND laant_av IS null;");
					if (check){
						showMessageDialog(null,bok.getTittel(isbn)+" av "+bok.getForfatter(isbn)+"\nLånt ut av "+navn);
					} else {
						showMessageDialog(null,"Noe gikk galt!");
					}
					break;
				case 2:
					showMessageDialog(null,bok.toString());
					break;
				case 3:
					System.exit(1);
					break;
				default:
					break;
			}
		}
	}
}