package sv.ues.fia.moviles.modelo;

public class DetalleCategoria {
	private int id_categoria;
	private int id_pregunta;
	
	
	public DetalleCategoria(){
		
	}
	
	public DetalleCategoria(int id_categoria, int id_pregunta) {
		super();
		this.id_categoria = id_categoria;
		this.id_pregunta = id_pregunta;
	}
	

	
	
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public int getId_pregunta() {
		return id_pregunta;
	}
	public void setId_pregunta(int id_pregunta) {
		this.id_pregunta = id_pregunta;
	}
		

}
