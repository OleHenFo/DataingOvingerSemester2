class Student{
	private final String navn;
	private int antOppg;

    public Student(String navn){
		this.navn = navn;
		this.antOppg = 0;
	}

    public String getNavn(){
		return this.navn;
	}
	public int getAntOppg(){
		return this.antOppg;
	}
	public void setAntOppg(int antOppg){
		this.antOppg = antOppg;
	}
	public String toString(){
		return navn+": "+antOppg;
	}
}