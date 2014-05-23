package sv.ues.fia.moviles.modelo;

public class AsignaPregunta {
	
	private int id;
	private int idPregunta;
	private int idCuestionario;
	private int porcentaje;
	private String respuesta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public int getIdCuestionario() {
		return idCuestionario;
	}

	public void setIdCuestionario(int idCuestionario) {
		this.idCuestionario = idCuestionario;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
