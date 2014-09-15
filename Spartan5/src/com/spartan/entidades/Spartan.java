package com.spartan.entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

/**
 * Representa la clase principal de la aplicacion 
 * @author hellspawn
 */
public class Spartan 
{
	
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	/**
	 * Formato de fecha
	 */
	public final static String FORMAT = "MM/dd/yyyy hh:mm:ss";
	
	/**
	 * Formato para la comparacion
	 */
	public final static String FORMAT_COMPARE = "MM/dd/yyyy";
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Catalgo de eventos publicos de la aplicacion
	 */
	private ArrayList <Evento> catalogoEventos;
	
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
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Crea una nueva instancia de la clase 
	 * @param c - Es el contexto de la aplicacion 
	 */
	public Spartan (Context c)
	{
		contexto = c;
		user = new Usuario(c);
		catalogoEventos = new ArrayList<Evento>();
		loadEvents(c);
	}
	

	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Retorna la instancia de la clase
	 * @param c - Es el contexto 
	 * @return la unica instancia de la clase 
	 */
	public static Spartan darInstancia(Context c)
	{
		if(instanciaSpartan == null)
		{
			instanciaSpartan = new Spartan(c);
		}
		return instanciaSpartan;
	}
	
	/**
	 * Retorna al usuario 
	 * @return
	 */
	public Usuario darUsuario()
	{
		return user;
	}
	
	/**
	 * Carga los eventos del archivo de eventos
	 */
	private void loadEvents(Context c)
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(c.getResources().getAssets().open("events_data.txt")));
			String linea = br.readLine();
			//ID=2;deporte=Baloncesto;titulo=Cursos de baloncesto libres en uniandes;lugar=Centro deportivo Universidad de los Andes;organizador=uniandes;localizacion=4.600196,-74.063390;Fecha=09/13/2014-15:30
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
		
			while(linea != null)
			{
				idEvento = linea.split(";")[0].split("=")[1];
				tipoEvento = linea.split(";")[1].split("=")[1];
				tituloEvento = linea.split(";")[2].split("=")[1];
				strLugarEvento = linea.split(";")[3].split("=")[1];
				organizador = linea.split(";")[4].split("=")[1];
				latitud = Double.parseDouble(linea.split(";")[5].split("=")[1].split(",")[0]);
				longitud = Double.parseDouble(linea.split(";")[5].split("=")[1].split(",")[1]);
				strFecha = linea.split(";")[6].split("=")[1];
				try
				{
					fechaEvento = dt.parse(strFecha);
					event = new Evento(idEvento,tipoEvento,tituloEvento,strLugarEvento,organizador,latitud,longitud,fechaEvento);
					catalogoEventos.add(event);	
					linea = br.readLine();
					
				}
				catch(Exception ex)
				{
					Log.d("HELLSPAWN", "OLA KE ASE");
				}
				
			}
			br.close();		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Busca en la lista de eventos sin tener en cuenta la localizacion
	 * @param key - Es el deporte
	 * @param D - Es la fecha 
	 * @return Lista con los resultados
	 */
	public ArrayList <Evento> searchEventNoLocation(String key, Date D)
	{
		ArrayList <Evento> ans = new ArrayList<Evento>();
		return ans;
	}
	
	/**
	 * Busca en la lista de eventos sin tener en cuenta la localizacion ni fecha
	 * @param key - Es el deporte
	 * @return lista con los resultados
	 */
	public ArrayList <Evento> searchByKey(String key)
	{
		ArrayList <Evento> ans = new ArrayList<Evento>();
		Evento e;
		for (int i = 0; i < catalogoEventos.size(); i++)
		{
			e = catalogoEventos.get(i);
			if (e.getTipoEvento().equals(key))
			{
				ans.add(e);
			}
		}
		return ans;
	}
	
	/**
	 * Busca los eventos por fecha y por key
	 * @param key - Es la key 
	 * @param D - Es la fecha
	 * @return - Lista de eventos
	 */
	public ArrayList <Evento> searchEventBySportAndDate(String key, String D)
	{
		ArrayList <Evento> ans = new ArrayList <Evento> ();
		Evento e = null;
		String dateEvento = "";
		for (int i = 0; i < catalogoEventos.size(); i++)
		{
			e = catalogoEventos.get(i);
			if (e.getTipoEvento().equals(key))
			{
				dateEvento = this.formatDateToString(e.getFechaEvento());
				if (D.equals(dateEvento))
				{
					ans.add(e);
				}
			}
		}
		return ans;
	}
	
	public ArrayList <Evento> searchByDate(String D)
	{
		ArrayList <Evento> ans = new ArrayList <Evento> ();
		Evento e = null;
		String dateEvento = "";
		for (int i = 0; i < catalogoEventos.size(); i++)
		{
			e = catalogoEventos.get(i);
			dateEvento = this.formatDateToString(e.getFechaEvento());
			if (D.equals(dateEvento))
			{
				ans.add(e);
			}
			
		}
		return ans;

	}
	
	/**
	 * Formatea una fecha con el formato dado
	 * @param d - Es la fecha
	 * @return - Un String con la fecha del evento 
	 */
	public String formatDateToString(Date d)
	{
		SimpleDateFormat dt = new SimpleDateFormat(FORMAT_COMPARE);
		String ans = "";
		ans = dt.format(d);
		return ans;
	}
	
	/**
	 * Retorna un evento dado su id 
	 * @param id - Id del evento 
	 * @return - El evento o null si no se encontro
	 */
	public Evento getEventById(String id)
	{
		Evento e = null;
		for (int i = 0; i < catalogoEventos.size(); i++)
		{
			e = catalogoEventos.get(i);
			if (e.getIdEvento().equals(id))
			{
				return e;
			}
		}
		return e;
	}
	
	/**
	 * Busca eventos sin tener en cuenta el deporte, la ubicacion y la hora
	 * @return
	 */
	public ArrayList <Evento> searchAnySportAnyWhereAnyTime()
	{
		return catalogoEventos;
	}
	
	/**
	 * Retorna todo el catalogo de eventos
	 * @return
	 */
	public ArrayList <Evento> getCatalogo()
	{
		return catalogoEventos;
	}
	
	/**
	 * Retorna la lista en strings de los eventos del resultado
	 * @param results - Es la lista con los resultados 
	 * @return - Lista de strings 
	 */
	public String [] getEventListData(ArrayList <Evento> results)
	{
		String [] ans = new String[results.size()];
		Evento ev;
		for (int i = 0; i < results.size(); i++)
		{
			ev = results.get(i);
			ans[i] = ev.getInformationStr();
		}
		return ans;
	}
	
	/**
	 * Retorna la lista en strings de los eventos del resultado
	 * @param results - Es la lista con los resultados 
	 * @return - Lista de strings 
	 */
	public String [] getAssistenceListData(ArrayList <Asistencia> asis)
	{
		String [] ans = new String[asis.size()];
		Asistencia As;
		for (int i = 0; i < asis.size(); i++)
		{
			As = asis.get(i);
			ans[i] = As.getStringRepresentation();
		}
		return ans;
	}
		
	/**
	 * Escribe los datos de una asistencia 
	 * @param a
	 */
	//TODO
	public void registrarAsistencias(Asistencia a)
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(contexto.getResources().getAssets().open("assistence_data.txt")));
			String linea = br.readLine();
			String toSave = "";
			while(linea != null)
			{
				//Concatena la informacion
				toSave = toSave + linea;
			}
			
			FileWriter out = new FileWriter(new File(contexto.getFilesDir(), "assistence_data.txt"));
			String data = "\n" + a.getStringToSave();
            out.write(data);
            out.close();
		}
		catch(Exception e)
		{
			
		}		
	}
	
	/**
	 * Retorna una asistencia dado su id 
	 * @param idAsistencia - Es el identificador
	 * @return La asistencia o null si no la encontro
	 */
	public Asistencia buscarAsistencia(int idAsistencia)
	{
		Asistencia A;
		for (int i = 0; i < user.getAsistencias().size(); i++)
		{
			A = user.getAsistencias().get(i);
			if (A.getIdAsistencia() == idAsistencia)
			{
				return A;
			}
		}
		return null;
	}
	
	/**
	 * Cambia el estado de la asistencia
	 * Modifica los demas aspectos del usuario 
	 * @param A - Es la asistencia
	 */
	public void CheckInAsistencia(Asistencia A)
	{
		if (!A.isEstado())
		{
			A.setEstado(true);
			String actividad = A.getEvento().getTipoEvento();
			updateScore(actividad);
		}
		//Deje asi	
	}
	
	/**
	 * Elimina una asistencia dada
	 * @param A
	 */
	public void EliminarAsistencia(Asistencia A)
	{
		user.getAsistencias().remove(A);
	}
	
	
	
	/**
	 * Actualiza el score 
	 * @param actividad
	 */
	public void updateScore(String actividad)
	{
		if (actividad.equals(com.spartan.recursos.Eventos.SOCCER))
		{
			user.setFutbol(user.getFutbol() + 1);
		}
		else if (actividad.equals(com.spartan.recursos.Eventos.BASKET))
		{
			user.setBasket(user.getBasket() + 1);
		}
		else if (actividad.equals(com.spartan.recursos.Eventos.VOLEY))
		{
			user.setVoley(user.getVoley() + 1);
		}
		else
		{
			user.setTennis(user.getTennis() + 1);
		}
		user.recalcularScore();
	}
	
	
}
