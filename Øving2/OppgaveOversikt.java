import java.util.ArrayList;

class OppgaveOversikt{
	private ArrayList<Student> studenter = new ArrayList<Student>();
	private int antStud = 0;

	public boolean regNyStudent(String navn){
		for (Student s:this.studenter){
			if (s.getNavn().equals(navn)){
				return false;
			}
		}
		studenter.add(new Student(navn));
		antStud++;
		return true;
	}

	public int finnAntStud(){
		return this.antStud;
	}

	public String[] finnAlleNavn(){
		String string = "";
		for (Student s:studenter){
			string += s.getNavn() + ":";
		}
		return string.split(":");
	}

	public int finnAntOppgaver(String n){
		int r = 0;
		for (Student s:studenter){
			if (s.getNavn().equals(n)){
				r = s.getAntOppg();
			}
		}
		return r;
	}

	public void økAntOppg(String navn,int nr){
		for (Student s:studenter){
			if (s.getNavn().equals(navn)){
				s.setAntOppg(s.getAntOppg() + nr);
			}
		}
	}

	@Override
	public String toString(){
		String ut = "";
		for (Student s:studenter){
			ut+=s.getNavn()+": "+s.getAntOppg()+"\n";
		}
		return ut;
	}
}