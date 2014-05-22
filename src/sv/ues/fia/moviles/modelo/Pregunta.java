package sv.ues.fia.moviles.modelo;

public class Pregunta {
	private int id_preg;
	private String pregunta;
	public int getId_preg() {
		return id_preg;
	}
	public void setId_preg(int id_preg) {
		this.id_preg = id_preg;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String  toString(){
		return ""+id_preg;
		
	}
	
}
