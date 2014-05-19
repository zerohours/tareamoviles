package sv.ues.fia.moviles.modelo;

public class Asignatura {
	
	private int idAsignatura;
	private String nombre;
	private String codigo;
	private String descripcion;
	private int estado;
	
	public int getId() {
		return idAsignatura;
	}

	public void setId(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
