package sv.ues.fia.moviles.bean;

public class BeanPregunta {

	int id_categoria;
	String pregunta;
	
	
	
	public BeanPregunta(int id_categoria, String pregunta) {
		setId_categoria(id_categoria);
		setPregunta(pregunta);
	}
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public String toString(){
		return pregunta;
		
	}
}
