package sv.ues.fia.moviles.modelo;

public class Pregunta {
	private int id;
	private String pregunta;

	public Pregunta(int id, String pregunta) {
		super();
		this.id = id;
		this.pregunta = pregunta;
	}

	public Pregunta() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

}
