package sv.ues.fia.moviles.db;

import sv.ues.fia.moviles.modelo.AsignaCiclo;
import sv.ues.fia.moviles.modelo.Asignatura;
import sv.ues.fia.moviles.modelo.Categoria;
import sv.ues.fia.moviles.modelo.Ciclo;
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
	private static final String[] camposDocente = new String[] { "ID_DOCENTE",
			"EMAIL", "PASSWORD", "NOMBRE", "APELLIDO1", "APELLIDO2",
			"FECHA_NAC", "TELEFONO", "ESTADO" };
	private static final String[] camposAsignatura = new String[] {
			"id_asignatura", "nombre", "codigo", "descripcion" };
	private static final String[] camposCiclo = new String[] { "id_ciclo",
			"anio", "numero" };

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

				db.execSQL("create table docente (id_docente integer not null, email varchar(50) not null, password varchar(255) not null, nombre varchar(50) not null, apellido1 varchar(50) not null, apellido2 varchar(50), fecha_nac date, telefono char(10), estado integer not null, primary key (id_docente));");
				db.execSQL("CREATE TABLE ciclo (id_ciclo integer not null, anio integer not null, numero integer not null, primary key (id_ciclo));");
				db.execSQL("CREATE TABLE asignatura (id_asignatura integer not null, nombre varchar(50) not null, codigo varchar(8) not null, descripcion varchar(255) not null, estado integer not null, primary key (id_asignatura));");
				db.execSQL(" CREATE TABLE asigna_ciclo (id integer not null, id_asignatura integer not null, id_docente integer not null, id_ciclo integer not null, primary key (id));");
				db.execSQL("CREATE TABLE pregunta(id INTEGER PRIMARY KEY ,pregunta VARCHAR(255) NOT NULL);");
				db.execSQL("CREATE TABLE categoria(id INTEGER NOT NULL PRIMARY KEY,nombre VARCHAR(50),descripcion VARCHAR(255));");
				db.execSQL("CREATE TABLE detalle_categoria(id_pregunta INTEGER NOT NULL PRIMARY KEY,id_categoria INTEGER NOT NULL PRIMARY KEY);");
				db.execSQL("create table docente (id_docente integer not null, email varchar(50) not null, password varchar(255) not null, nombre varchar(50) not null, apellido1 varchar(50) not null, apellido2 varchar(50), fecha_nac date, telefono char(10), estado integer not null, primary key (id_docente));");

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
		String regInsertados = "Registro Insertado N�= ";
		long contador = 0;
		ContentValues docen = new ContentValues();

		docen.put("ID_DOCENTE", docente.getId());
		docen.put("EMAIL", docente.getEmail());
		docen.put("PASSWORD", docente.getPassword());
		docen.put("NOMBRE", docente.getNombre());
		docen.put("APELLIDO1", docente.getApellido1());
		docen.put("APELLIDO2", docente.getApellido2());
		docen.put("FECHA_NAC", docente.getFechaNac());
		docen.put("TELEFONO", docente.getTelefono());
		docen.put("ESTADO", docente.getEstado());

		contador = db.insert("DOCENTE", null, docen);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserci�n";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String actualizar(Docente docente) {
		if (verificarIntegridad(docente, 4)) {
			String[] id = { Integer.toString(docente.getId()) };
			ContentValues docen = new ContentValues();

			docen.put("ID_DOCENTE", docente.getId());
			docen.put("EMAIL", docente.getEmail());
			docen.put("PASSWORD", docente.getPassword());
			docen.put("NOMBRE", docente.getNombre());
			docen.put("APELLIDO1", docente.getApellido1());
			docen.put("APELLIDO2", docente.getApellido2());
			docen.put("FECHA_NAC", docente.getFechaNac());
			docen.put("TELEFONO", docente.getTelefono());
			docen.put("ESTADO", docente.getEstado());
			db.update("DOCENTE", docen, "id_docente = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Cliente " + docente.getId() + " no existe";
		}
	}

	public String eliminar(Docente docente) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(docente, 3)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); �
		} else {
			// borrar los registros de cliente
			contador += db.delete("docente", "id_docente='" + docente.getId()
					+ "'", null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public Docente consultarDocente(String id_cliente) {

		String[] id = { id_cliente };
		Cursor cursor = db.query("DOCENTE", camposDocente, "id_docente = ?",
				id, null, null, null);

		if (cursor.moveToFirst()) {
			Docente docente = new Docente();

			docente.setId(cursor.getInt(0));
			docente.setEmail(cursor.getString(1));
			docente.setNombre(cursor.getString(3));
			docente.setApellido1(cursor.getString(4));
			docente.setApellido2(cursor.getString(5));
			docente.setTelefono(cursor.getString(7));

			return docente;
		} else {
			return null;
		}
	}

	public String insertar(Ciclo ciclo) {
		String regInsertados = "Registro Insertado N�= ";
		long contador = 0;
		ContentValues cicl = new ContentValues();

		cicl.put("id_ciclo", ciclo.getId());
		cicl.put("anio", ciclo.getAnio());
		cicl.put("numero", ciclo.getNumero());

		contador = db.insert("ciclo", null, cicl);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserci�n";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	public String actualizar(Ciclo ciclo) {
		if (verificarIntegridad(ciclo, 8)) {
			String[] id = { Integer.toString(ciclo.getId()) };
			ContentValues cicl = new ContentValues();

			cicl.put("id_ciclo", ciclo.getId());
			cicl.put("anio", ciclo.getAnio());
			cicl.put("numero", ciclo.getNumero());

			db.update("ciclo", cicl, "id_ciclo = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Ciclo " + ciclo.getId() + " no existe";
		}
	}

	public String eliminar(Ciclo ciclo) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(ciclo, 7)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); �
		} else {
			// borrar los registros de cliente
			contador += db.delete("ciclo", "id_ciclo='" + ciclo.getId() + "'",
					null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public Ciclo consultarCiclo(String id_ciclo) {

		String[] id = { id_ciclo };
		Cursor cursor = db.query("ciclo", camposCiclo, "id_ciclo = ?", id,
				null, null, null);

		if (cursor.moveToFirst()) {
			Ciclo ciclo = new Ciclo();

			ciclo.setId(cursor.getInt(0));
			ciclo.setAnio(cursor.getInt(1));
			ciclo.setNumero(cursor.getInt(2));

			return ciclo;
		} else {
			return null;
		}
	}

	public String insertar(Asignatura asignatura) {

		String regInsertados = "Registro Insertado N�= ";
		long contador = 0;
		ContentValues asig = new ContentValues();

		asig.put("id_asignatura", asignatura.getId());
		asig.put("nombre", asignatura.getNombre());
		asig.put("codigo", asignatura.getCodigo());
		asig.put("descripcion", asignatura.getDescripcion());
		asig.put("estado", asignatura.getEstado());

		contador = db.insert("asignatura", null, asig);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserci�n";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}
	
	public String actualizar(Asignatura asignatura) {
		if (verificarIntegridad(asignatura, 12)) {
			String[] id = { Integer.toString(asignatura.getId()) };
			ContentValues asig = new ContentValues();

			asig.put("id_asignatura", asignatura.getId());
			asig.put("nombre", asignatura.getNombre());
			asig.put("codigo", asignatura.getCodigo());
			asig.put("descripcion", asignatura.getDescripcion());

			db.update("asignatura", asig, "id_asignatura = ?", id);
			return "Registro Actualizado Correctamente";
		} else {
			return "Registro Asignatura " + asignatura.getId() + " no existe";
		}
	}
	
	public String eliminar(Asignatura asignatura) {
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(asignatura, 11)) {
			regAfectados = "0";
			// aplica para cascada
			// borrar registros de cliente_vehiculo
			// contador += db.delete("cliente_vehiculo",
			// "id_cliente='"+cliente.getIdCliente()+"'",
			// null); �
		} else {
			// borrar los registros de cliente
			contador += db.delete("asignatura", "id_asignatura='" + asignatura.getId()
					+ "'", null);
			regAfectados += contador;
		}
		return regAfectados;
	}

	public Asignatura consultarAsignatura(String id_asignatura) {

		String[] id = { id_asignatura };
		Cursor cursor = db.query("asignatura", camposAsignatura,
				"id_asignatura = ?", id, null, null, null);

		if (cursor.moveToFirst()) {
			Asignatura asignatura = new Asignatura();

			asignatura.setId(cursor.getInt(0));
			asignatura.setNombre(cursor.getString(1));
			asignatura.setCodigo(cursor.getString(2));
			asignatura.setDescripcion(cursor.getString(3));

			return asignatura;
		} else {
			return null;
		}
	}
	
	public String insertar(AsignaCiclo asignaciclo) {
		String regInsertados = "Registro Insertado N�= ";
		long contador = 0;
		ContentValues asigcicl = new ContentValues();

		asigcicl.put("id", asignaciclo.getId());
		asigcicl.put("id_asignatura", asignaciclo.getIdAsignatura());
		asigcicl.put("id_docente", asignaciclo.getIdDocente());
		asigcicl.put("id_ciclo", asignaciclo.getIdCiclo());

		contador = db.insert("asigna_ciclo", null, asigcicl);
		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserci�n";
		} else {
			regInsertados = regInsertados + contador;
		}
		return regInsertados;
	}

	/** Insertar **/
	public String insertar(Pregunta pregunta) {
		String regInsertados = "Registro Insertado N�= ";
		long contador = 0;
		ContentValues preg = new ContentValues();

		preg.put("id", pregunta.getId());
		preg.put("pregunta", pregunta.getPregunta());

		contador = db.insert("pregunta", null, preg);

		if (contador == -1 || contador == 0) {
			regInsertados = "Error al Insertar el registro, Registro	Duplicado. Verificar inserci�n";
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
		String regAfectados = "filas afectadas= ";
		int contador = 0;
		if (verificarIntegridad(pregunta, 99)) {

			// aplica para cascada borrar registros de notas

			contador += db.delete("pregunta", "id='" + pregunta.getId() + "'",
					null);

			regAfectados += contador;
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

	// ***verificar integridad***//
	private boolean verificarIntegridad(Object dato, int relacion)
			throws SQLException {
		switch (relacion) {

		case 3: {
			// verifica que no existan registros hijos de docente
			// Docente docente2 = (Docente) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;
		}

		case 4: {
			// verificar que exista Docente
			Docente docente2 = (Docente) dato;
			String[] id = { Integer.toString(docente2.getId()) };
			abrir();
			Cursor c2 = db.query("DOCENTE", null, "id_docente = ?", id, null,
					null, null);
			if (c2.moveToFirst()) {
				// Se encontro Docente
				return true;
			}
			return false;
		}

		case 7: {
			// verifica que no existan registros hijos de ciclo
			// Ciclo ciclo2 = (Ciclo) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;
		}

		case 8: {
			// verificar que exista Ciclo
			Ciclo ciclo2 = (Ciclo) dato;
			String[] id = { Integer.toString(ciclo2.getId()) };
			abrir();
			Cursor c2 = db.query("ciclo", null, "id_ciclo = ?", id, null, null,
					null);
			if (c2.moveToFirst()) {
				// Se encontro Ciclo
				return true;
			}
			return false;
		}
		
		case 11: {
			// verifica que no existan registros hijos de Asignatura
			// Asignatura asignatura2 = (asignatura) dato;
			return false;
			// Cursor c = db.query(true, "cliente_vehiculo",
			// new String[] { "id_docente" },
			// "id_docente='" + docente2.getId() + "'", null, null,
			// null, null, null);
			// if (c.moveToFirst())
			// return true;
			// else
			// return false;	
		}
		
		case 12: {
			// verificar que exista Asignatura
			Asignatura asignatura2 = (Asignatura) dato;
			String[] id = { Integer.toString(asignatura2.getId()) };
			abrir();
			Cursor c2 = db.query("asignatura", null, "id_asignatura = ?", id, null, null,
					null);
			if (c2.moveToFirst()) {
				// Se encontro Ciclo
				return true;
			}
			return false;
		}
 			

		case 99: {
			// verificar que al insertar nota exista carnet del alumno y el
			// codigo de materia
			// Nota nota = (Nota) dato;
			Pregunta pregunta = (Pregunta) dato;
			String[] id1 = { String.valueOf(pregunta.getId()) };
			abrir();
			Cursor cursor1 = db.query("pregunta", null, "id = ?", id1, null,
					null, null);

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
				"�Podr�an sumarse los infin�tos n�meros que hay entre el 0 y el 1?",
				"�como se llaman las principales �reas culturales del continente americano? ",
				"�Qu� es una etopeya?",
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

	public int getCount(String tabla, String id) {

		// String sql = "select ? from ? order by ? desc limit 1 ;";

		// abrir();
		// Cursor cursor = db.rawQuery(sql, new String[]{id, tabla, id});
		// cerrar();
		// if(cursor.moveToFirst()) {
		// System.out.println("El valor es: " + cursor.getInt(0));
		// return cursor.getInt(0) + 1;
		// } else {
		// return 1;
		// }
		return 1;
	}

}