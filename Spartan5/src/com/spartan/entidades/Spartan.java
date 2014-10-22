package com.spartan.entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Representa la clase principal de la aplicacion
 * 
 * @author hellspawn
 */
public class Spartan {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Archivo donde se guarda el nombre del usuario para evitar futuros
	 * ingresos
	 */
	public final static String USERNAME_FILE = "username.txt";

	/**
	 * Cacheo de eventos
	 */
	public final static String EVENTS_FILE = "events.txt";

	/**
	 * Cacheo de equipos
	 */
	public final static String TEAMS_FILE = "teams.txt";

	/**
	 * Cacheo de torneos
	 */
	// public final static String TOURNAMENTS_FILE = "tournaments.txt";

	/**
	 * Cacheo de jugadores seleccionados
	 */
	// public final static String SELECTED_PLAYERS_FILE = "selectedPlayers.txt";

	/**
	 * Cacheo de asistencias
	 */
	public final static String ASSISTANCES_FILE = "assistances.txt";

	/**
	 * Cacheo de eventos
	 */
	public final static String USER_SCORE_FILE = "score.txt";

	/**
	 * Formato de fecha
	 */
	public final static String FORMAT = "MM/dd/yyyy hh:mm:ss";

	/**
	 * Formato para la comparacion
	 */
	public final static String FORMAT_COMPARE = "MM/dd/yyyy";
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Catalgo de eventos publicos de la aplicacion
	 */
	private ArrayList<Evento> catalogoEventos;

	/**
	 * Instancia de la aplicacion
	 */
	public static Spartan instanciaSpartan;

	/**
	 * Usuario
	 */
	private Usuario user;

	/**
	 * Relacion con el componente de localizacion
	 */
	private LocationComponent location;

	/**
	 * Contexto de la aplicacion
	 */
	private Context contexto;

	private String userLoginName;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea una nueva instancia de la clase
	 * 
	 * @param c
	 *            - Es el contexto de la aplicacion
	 */
	public Spartan(Context c) {
		userLoginName = null;
		contexto = c;
		user = new Usuario(c);
		catalogoEventos = new ArrayList<Evento>();
		loadEvents(c);
		// Eventos
		// ID=1;deporte=Fútbol;titulo=Cursos de fútbol libres en
		// uniandes;lugar=Centro deportivo Universidad de los
		// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014
		// 15:30:00
		// ID=2;deporte=Baloncesto;titulo=Cursos de baloncesto libres en
		// uniandes;lugar=Centro deportivo Universidad de los
		// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014
		// 15:30:00
		// ID=3;deporte=Voleibol;titulo=Cursos de voleybol libres en
		// uniandes;lugar=Centro deportivo Universidad de los
		// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014
		// 15:30:00
		// ID=4;deporte=Tenis;titulo=Cursos de tenis libres en
		// uniandes;lugar=Centro deportivo universidad de los
		// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014
		// 15:30:00
		// ID=5;deporte=Fútbol;titulo=Cursos de soccer libres en
		// uniandes;lugar=Centro deportivo universidad de los
		// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014
		// 15:30:00
		// ID=6;deporte=Tenis;titulo=Cursos de tenis libres en
		// uniandes;lugar=Centro deportivo universidad de los
		// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014
		// 15:30:00
		loadAss(c);

		// userdata
		// score=2.8;futbol=2;basket=5;voley=2;tenis=5
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String nUserLoginName) {
		userLoginName = nUserLoginName;
	}
	
	public void deleteUserLoginName()
	{
		userLoginName=null;
	}

	private void loadAss(Context c) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(c
					.getResources().getAssets().open("events_data.txt")));
			String linea = br.readLine();
			// ID=1;IdEvento=1;estado=1;Invitado=Diva Martinez

			int idA;
			String idEvento;
			String estado;
			String invitado;
			Asistencia a;
			boolean es = false;

			while (linea != null) {

				idA = Integer.parseInt(linea.split(";")[0].split("=")[1]);
				idEvento = linea.split(";")[1].split("=")[1];
				estado = linea.split(";")[2].split("=")[1];
				invitado = linea.split(";")[3].split("=")[1];
				int est = Integer.parseInt(estado);

				es = (est != 0);
				Evento corr = getEventById(idEvento);
				if (corr != null) {

					Asistencia as = new Asistencia(corr, invitado, idA);
					user.agregarAsistencia(as);
				}

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Retorna la instancia de la clase
	 * 
	 * @param c
	 *            - Es el contexto
	 * @return la unica instancia de la clase
	 */
	public static Spartan darInstancia(Context c) {
		if (instanciaSpartan == null) {
			instanciaSpartan = new Spartan(c);
		}
		return instanciaSpartan;
	}

	/**
	 * Retorna al usuario
	 * 
	 * @return
	 */
	public Usuario darUsuario() {
		return user;
	}

	/**
	 * Carga los eventos del archivo de eventos
	 */
	private void loadEvents(Context c) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(c
					.getResources().getAssets().open("events_data.txt")));
			String linea = br.readLine();
			// ID=2;deporte=Baloncesto;titulo=Cursos de baloncesto libres en
			// uniandes;lugar=Centro deportivo Universidad de los
			// Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014-15:30
			String idEvento;
			String tipoEvento;
			String tituloEvento;
			String strLugarEvento;
			String organizador;
			Double latitud;
			Double longitud;
			Date fechaEvento;
			String strFecha;
			SimpleDateFormat dt = new SimpleDateFormat(FORMAT);
			Evento event;

			while (linea != null) {
				idEvento = linea.split(";")[0].split("=")[1];
				tipoEvento = linea.split(";")[1].split("=")[1];
				tituloEvento = linea.split(";")[2].split("=")[1];
				strLugarEvento = linea.split(";")[3].split("=")[1];
				organizador = linea.split(";")[4].split("=")[1];
				latitud = Double.parseDouble(linea.split(";")[5].split("=")[1]
						.split(",")[0]);
				longitud = Double.parseDouble(linea.split(";")[5].split("=")[1]
						.split(",")[1]);
				strFecha = linea.split(";")[6].split("=")[1];
				try {
					fechaEvento = dt.parse(strFecha);
					event = new Evento(idEvento, tipoEvento, tituloEvento,
							strLugarEvento, organizador, latitud, longitud,
							fechaEvento);
					catalogoEventos.add(event);
					linea = br.readLine();

				} catch (Exception ex) {
					Log.d("HELLSPAWN", "OLA KE ASE");
				}

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca en la lista de eventos sin tener en cuenta la localizacion
	 * 
	 * @param key
	 *            - Es el deporte
	 * @param D
	 *            - Es la fecha
	 * @return Lista con los resultados
	 */
	public ArrayList<Evento> searchEventNoLocation(String key, Date D) {
		ArrayList<Evento> ans = new ArrayList<Evento>();
		return ans;
	}

	/**
	 * Busca en la lista de eventos sin tener en cuenta la localizacion ni fecha
	 * 
	 * @param key
	 *            - Es el deporte
	 * @return lista con los resultados
	 */
	public ArrayList<Evento> searchByKey(String key) {
		ArrayList<Evento> ans = new ArrayList<Evento>();
		Evento e;
		for (int i = 0; i < catalogoEventos.size(); i++) {
			e = catalogoEventos.get(i);
			if (e.getTipoEvento().equals(key)) {
				ans.add(e);
			}
		}
		return ans;
	}

	/**
	 * Busca los eventos por fecha y por key
	 * 
	 * @param key
	 *            - Es la key
	 * @param D
	 *            - Es la fecha
	 * @return - Lista de eventos
	 */
	public ArrayList<Evento> searchEventBySportAndDate(String key, String D) {
		ArrayList<Evento> ans = new ArrayList<Evento>();
		Evento e = null;
		String dateEvento = "";
		for (int i = 0; i < catalogoEventos.size(); i++) {
			e = catalogoEventos.get(i);
			if (e.getTipoEvento().equals(key)) {
				dateEvento = this.formatDateToString(e.getFechaEvento());
				if (D.equals(dateEvento)) {
					ans.add(e);
				}
			}
		}
		return ans;
	}

	public ArrayList<Evento> searchByDate(String D) {
		ArrayList<Evento> ans = new ArrayList<Evento>();
		Evento e = null;
		String dateEvento = "";
		for (int i = 0; i < catalogoEventos.size(); i++) {
			e = catalogoEventos.get(i);
			dateEvento = this.formatDateToString(e.getFechaEvento());
			if (D.equals(dateEvento)) {
				ans.add(e);
			}

		}
		return ans;

	}

	/**
	 * Formatea una fecha con el formato dado
	 * 
	 * @param d
	 *            - Es la fecha
	 * @return - Un String con la fecha del evento
	 */
	public String formatDateToString(Date d) {
		SimpleDateFormat dt = new SimpleDateFormat(FORMAT_COMPARE);
		String ans = "";
		ans = dt.format(d);
		return ans;
	}

	/**
	 * Retorna un evento dado su id
	 * 
	 * @param id
	 *            - Id del evento
	 * @return - El evento o null si no se encontro
	 */
	public Evento getEventById(String id) {
		Evento e = null;
		for (int i = 0; i < catalogoEventos.size(); i++) {
			e = catalogoEventos.get(i);
			if (e.getIdEvento().equals(id)) {
				return e;
			}
		}
		return e;
	}

	/**
	 * Busca eventos sin tener en cuenta el deporte, la ubicacion y la hora
	 * 
	 * @return
	 */
	public ArrayList<Evento> searchAnySportAnyWhereAnyTime() {
		return catalogoEventos;
	}

	/**
	 * Retorna todo el catalogo de eventos
	 * 
	 * @return
	 */
	public ArrayList<Evento> getCatalogo() {
		return catalogoEventos;
	}

	/**
	 * Retorna la lista en strings de los eventos del resultado
	 * 
	 * @param results
	 *            - Es la lista con los resultados
	 * @return - Lista de strings
	 */
	public String[] getEventListData(ArrayList<Evento> results) {
		String[] ans = new String[results.size()];
		Evento ev;
		for (int i = 0; i < results.size(); i++) {
			ev = results.get(i);
			ans[i] = ev.getInformationStr();
		}
		return ans;
	}

	/**
	 * Retorna la lista en strings de los eventos del resultado
	 * 
	 * @param results
	 *            - Es la lista con los resultados
	 * @return - Lista de strings
	 */
	public String[] getAssistenceListData(ArrayList<Asistencia> asis) {
		String[] ans = new String[asis.size()];
		Asistencia As;
		for (int i = 0; i < asis.size(); i++) {
			As = asis.get(i);
			ans[i] = As.getStringRepresentation();
		}
		return ans;
	}

	/**
	 * Escribe los datos de una asistencia
	 * 
	 * @param a
	 */
	// TODO
	public void registrarAsistencias(Asistencia a) {
		Log.d("whogirl", "ingreso a registrar asistencia");
		try {
			// FileWriter filewriter = new FileWriter(file,true);
			// PrintStream fileStream = new PrintStream(new File("file.txt"));
			// fileStream.println("your data");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					contexto.getResources().getAssets()
							.open("assistence_data.txt")));
			String linea = br.readLine();
			String toSave = "";
			Log.d("whogirl", "buffer creado");

			while (linea != null) {
				// Concatena la informacion
				toSave = toSave + linea;
				linea = br.readLine();
			}
			Log.d("whogirl", "lineas re concatenadas");

			FileWriter out = new FileWriter(new File(contexto.getFilesDir(),
					"assistence_data.txt"));
			String data = "\n" + a.getStringToSave();
			out.write(data);
			Log.d("whogirl", "escrito");
			out.flush();
			Log.d("whogirl", "flusheado");
			out.close();
			Log.d("whogirl", "done");
		} catch (Exception e) {
			Log.d("whogilr", "problema de persistencia");
		}
	}

	/**
	 * Retorna una asistencia dado su id
	 * 
	 * @param idAsistencia
	 *            - Es el identificador
	 * @return La asistencia o null si no la encontro
	 */
	public Asistencia buscarAsistencia(int idAsistencia) {
		Asistencia A;
		for (int i = 0; i < user.getAsistencias().size(); i++) {
			A = user.getAsistencias().get(i);
			if (A.getIdAsistencia() == idAsistencia) {
				return A;
			}
		}
		return null;
	}

	/**
	 * Cambia el estado de la asistencia Modifica los demas aspectos del usuario
	 * 
	 * @param A
	 *            - Es la asistencia
	 */
	public void CheckInAsistencia(Asistencia A) {
		if (!A.isEstado()) {
			A.setEstado(true);
			String actividad = A.getEvento().getTipoEvento();
			updateScore(actividad);
		}
		// Deje asi
	}

	/**
	 * Elimina una asistencia dada
	 * 
	 * @param A
	 */
	public void EliminarAsistencia(Asistencia A) {
		user.getAsistencias().remove(A);
	}

	/**
	 * Actualiza el score
	 * 
	 * @param actividad
	 */
	public void updateScore(String actividad) {
		if (actividad.equals(com.spartan.recursos.Eventos.SOCCER)) {
			user.setFutbol(user.getFutbol() + 1);
		} else if (actividad.equals(com.spartan.recursos.Eventos.BASKET)) {
			user.setBasket(user.getBasket() + 1);
		} else if (actividad.equals(com.spartan.recursos.Eventos.VOLEY)) {
			user.setVoley(user.getVoley() + 1);
		} else {
			user.setTennis(user.getTennis() + 1);
		}
		user.recalcularScore();
	}

	/**
	 * Guarda cada vez que se agrega un evento y/o asistencia
	 * 
	 * @param e
	 * @param a
	 * @return si se pudo agregar o no
	 */
	public boolean guardarCambio(Evento e, Asistencia a) {

		if (e != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						contexto.getResources().getAssets()
								.open("events_data.txt")));
				String linea = br.readLine();
				String toSave = "";

				while (linea != null) {
					// Concatena la informacion
					toSave = toSave + linea;
					linea = br.readLine();
				}

				FileWriter out = new FileWriter(new File(
						contexto.getFilesDir(), "events_data.txt"));
				String data = "\n" + e.getStringToSave();
				out.write(data);
				out.flush();
				out.close();
			} catch (Exception ex) {
				Log.d("whogilr", "problema de persistencia en evento");
				return false;
			}
		}

		if (a != null) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						contexto.getResources().getAssets()
								.open("assistence_data.txt")));
				String linea = br.readLine();
				String toSave = "";

				while (linea != null) {
					// Concatena la informacion
					toSave = toSave + linea;
					linea = br.readLine();
				}

				FileWriter out = new FileWriter(new File(
						contexto.getFilesDir(), "assistence_data.txt"));
				String data = "\n" + a.getStringToSave();
				out.write(data);
				out.flush();
				out.close();
			} catch (Exception ex) {
				Log.d("whogilr", "problema de persistencia");
				return false;
			}
		}
		return true;
	}

	public void save(String usernameFile, String nombre) {
		// TODO Auto-generated method stub

	}

	public void resetFile(Context c, String f) {
		String dir = c.getFilesDir().getAbsolutePath();
		File f0 = new File(dir, f);
		boolean del = f0.delete();
	}

}
