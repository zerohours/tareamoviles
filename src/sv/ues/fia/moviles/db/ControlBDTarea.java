package sv.ues.fia.moviles.db;

import sv.ues.fia.moviles.modelo.Categoria;
import sv.ues.fia.moviles.modelo.DetalleCategoria;
import sv.ues.fia.moviles.modelo.Docente;
import sv.ues.fia.moviles.modelo.Pregunta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDTarea {

	private static final String[] camposCategoria = new String[] { "nombre",
			"descripcion" };
	private static final String[] camposPregunta = new String[] { "pregunta" };
	private static final String[] camposDetalleCategoria = new String[] {
			"idpregunta", "idcategoria" };

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public ControlBDTarea(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String BASE_DATOS = "tareamovil.s3db";
		private static final int VERSION = 1;

		public DatabaseHelper(Context context) {
			super(context, BASE_DATOS, null, VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				// Creamos toda la estructura de las tablas que tendra la base
				// de datos.
				db.execSQL("CREATE TABLE docente (id INTEGER NOT NULL PRIMARY KEY);");
				db.execSQL("CREATE TABLE pregunta(id INTEGER PRIMARY KEY ,pregunta VARCHAR(255) NOT NULL);");
				db.execSQL("CREATE TABLE categoria(id INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(50),descripcion VARCHAR(255));");
				db.execSQL("CREATE TABLE detalle_categoria(id_pregunta INTEGER NOT NULL PRIMARY KEY,id_categoria INTEGER NOT NULL PRIMARY KEY);");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}

	}

	public void abrir() throws SQLException {
		db = DBHelper.getWritableDatabase();
	}

	public void cerrar() {
		DBHelper.close();
	}

	public String insertar(Docente docente) {
		return null;
	}

	public String actualizar(Docente docente) {
		return null;
	}

	public String eliminar(Docente docente) {
		return null;
	}

	/** Insertar **/
	public String insertar(Pregunta pregunta) {
		String regInsertados = "Registro Insertado Nº= ";
		long contador = 0;
		ContentValues preg = new ContentValues();

		preg.put("id", pregunta.getId());
		preg.put("pregunta", pregunta.getPregunta());

		contador = db.insert("pregunta", null, preg);

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro	Duplicado. Verificar inserción";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String insertar(Categoria categoria) {
		return null;
	}

	public String insertar(DetalleCategoria detallecategoria) {
		return null;
	}

	/** Eliminar **/

	public String eliminar(Pregunta pregunta) {
		String regAfectados="filas afectadas= ";
		int contador=0;
		if (verificarIntegridad(pregunta,99)) {
		
		//aplica para cascada borrar registros de notas
		
		contador+=db.delete("pregunta",	"id='"+pregunta.getId()+"'", null);
		
		regAfectados+=contador;
		}
		return regAfectados;
	}

	public String eliminar(Categoria categoria) {
		return null;
	}

	public String eliminar(DetalleCategoria detallecategoria) {
		return null;
	}

	/** Actualizar **/

	public String actualizar(Pregunta pregunta) {

		if (verificarIntegridad(pregunta, 99)) {

			String[] id = { String.valueOf(pregunta.getId()) };

			ContentValues cv = new ContentValues();
			cv.put("pregunta", pregunta.getPregunta());

			db.update("pregunta", cv, "id = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro con ID " + pregunta.getId() + " no existe";
		}

	}

	public String actualizar(Categoria categoria) {
		return null;

	}

	public String actualizar(DetalleCategoria detallecategoria) {
		return null;

	}

	/** Consultar **/
	public Categoria consultarCategoria(int id, String nombre) {// verifcar
																// parametros
		return null;
	}

	public Pregunta consultarPregunta(String ids) {// verificar parametros
		String[] id = { ids };
		Cursor cursor = db.query("pregunta", camposPregunta, "id = ?", id,
				null, null, null);

		if (cursor.moveToFirst()) {

			Pregunta pregunta = new Pregunta();
			pregunta.setId(cursor.getInt(0));
			pregunta.setPregunta(cursor.getString(1));

			return pregunta;
		} else {
			return null;
		}
	}

	
	
	
	//***verificar integridad***//
	private boolean verificarIntegridad(Object dato, int relacion)
			throws SQLException {
		switch (relacion) {
		case 99: {
			// verificar que al insertar nota exista carnet del alumno y el
			// codigo de materia
			// Nota nota = (Nota) dato;
			Pregunta pregunta=(Pregunta) dato;
			String[] id1 = {String.valueOf(pregunta.getId()) };
		    abrir();
			Cursor cursor1 = db.query("pregunta", null, "id = ?", id1,
			 null, null, null);
			
			//
			 if (cursor1.moveToFirst()) {
			// // Se encontraron datos
			 return true;
			}
			 return false;
		}
		default:
			return false;
		}

	}

	public String llenarBDCarnet() {

		final int[] VCid = { 1, 2, 3, 4 };
		final String[] VCnombre = { "MATEMATICA", "SOCIALES", "LENGUAJE",
				"INGLES" };
		final String[] VCdescripcion = { "NUMEROS", "MAPAS XXX", "LETRAS",
				"IDOMA INGLES" };

		final String[] VPid = { "11", "22", "33", "44" };
		final String[] VPpregunta = {
				"¿Podrían sumarse los infinítos números que hay entre el 0 y el 1?",
				"¿como se llaman las principales áreas culturales del continente americano? ",
				"¿Qué es una etopeya?",
				"We were having a good time at the party, weren't we?" };
		final int[] VDCidpregunta = { 11, 22, 33, 44 };
		final int[] VDCidcategoria = { 1, 2, 3, 4 };

		abrir();
		db.execSQL("DELETE FROM categoria");
		db.execSQL("DELETE FROM pregunta");
		db.execSQL("DELETE FROM detalle_categoria");

		Pregunta pregunta = new Pregunta();
		for (int i = 0; i < 4; i++) {
			pregunta.setPregunta(VPpregunta[i]);
			insertar(pregunta);
		}
		Categoria categoria = new Categoria();
		for (int i = 0; i < 4; i++) {
			categoria.setId(VCid[i]);
			categoria.setNombre(VCnombre[i]);
			categoria.setDescripcion(VCdescripcion[i]);
			insertar(categoria);

		}
		DetalleCategoria detcat = new DetalleCategoria();
		for (int i = 0; i < 4; i++) {
			detcat.setId_categoria(VDCidcategoria[i]);
			detcat.setId_pregunta(VDCidpregunta[i]);
			insertar(detcat);
		}

		cerrar();
		return "Guardo Correctamente";
	}

}