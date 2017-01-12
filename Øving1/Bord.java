class Bord {
	public static String[] bord;
	public static final int INDEX_OUT_OF_BOUNDS = 1;
	public static final int INVALID_INDEX = 2;

	public Bord(int i){
		this.bord = new String[i];
	}

	public Bord(){
		this.bord = new String[50];
	}

	public int antallBord(){
		return bord.length;
	}

	public int antallLedig(){
		int c = 0;
		for (String b : bord ){
			if (b==null){
				c++;
			}
		}
		return c;
	}

	public int antallOpptatt(){
		int c = 0;
		for (String b : bord ){
			if (b!=null){
				c++;
			}
		}
		return c;
	}

	public boolean ledig(int i){
		if (this.bord[i-1]==null){
			return true;
		} else {
			return false;
		}
	}

	public String getNavn(int i){
		if (bord[i-1]!=null){
			return bord[i-1];
		} else {
			return "Err";
		}
	}

	public int frigiBord(int i){
		if (i<1 || i>bord.length){
			return INDEX_OUT_OF_BOUNDS;
		} else if (bord[i-1]==null){
			return INVALID_INDEX;
		} else {
			bord[i-1]=null;
			return 0;
		}
	}

	public int reserverBord(int i,String n){
		if (i<1 || i>bord.length){
			return INDEX_OUT_OF_BOUNDS;
		} else if (bord[i-1]!=null){
			return INVALID_INDEX;
		} else {
			bord[i-1]=n;
			return 0;
		}
	}

	public String toString(){
		String out = "Bord:\n";
		int c = 0;
		for (String n:bord){
			c++;
			if (n==null){
				out += "Nr. "+c+": Ledig\n";
			} else {
				out += "Nr. "+c+": "+n+"\n";
			}
		}
		return out;
	}
}