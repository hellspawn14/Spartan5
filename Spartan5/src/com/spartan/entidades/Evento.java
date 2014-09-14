package com.spartan.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

/**
 * Representa uno de los eventos del catalogo 
 * @author hellspawn
 */
public class Evento 
{
	
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------
	
	/**
	 * Nombre
	 */
	public final static String NOMBRE = "Nombre: ";
	
	/**
	 * Organizador
	 */
	public final static String ORG = "Organizador: ";
	
	/**
	 * Deporte
	 */
	public final static String DEPORTE = "Actividad: ";
	
	/**
	 * Fecha
	 */
	public final static String FECHA = "Fecha: ";
	
	/**
	 * Lugar
	 */
	public final static String LUGAR = "Lugar: ";
	
	/**
	 * Formato de fecha
	 */
	public final static String FORMAT = "MM/dd/yyyy hh-mm a";
	
	/**
	 * Identificador del evento 
	 */
	public final static String ID = "Identificador: ";
			
	
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------
	
	/**
	 * Identificador del evento
	 */
	private String idEvento;
	
	/**
	 * Tipo de evento -> Deporte
	 */
	private String tipoEvento;

	/**
	 * Titulo del evento
	 */
	private String tituloEvento;
	
	/**
	 * Lugar del evento (direccion)
	 */
	private String strLugarEvento;
	
	/**
	 * Organizador evento
	 */
	private String organizador;
	
	/**
	 * Latitud 
	 */
	private Double latitud;
	
	/**
	 * Longitud
	 */
	private Double longitud;
	
	/**
	 * Fecha del evento
	 */
	private Date fechaEvento;
	
	private String strFecha;
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------

	/**
	 * @param idEvento
	 * @param tipoEvento
	 * @param tituloEvento
	 * @param strLugarEvento
	 * @param organizador
	 * @param latitud
	 * @param longitud
	 * @param fechaEvento
	 */
	public Evento(String idEvento, String tipoEvento, String tituloEvento,String strLugarEvento, String organizador, Double latitud,Double longitud, Date fechaEvento)
	{
		this.idEvento = idEvento;
		this.tipoEvento = tipoEvento;
		this.tituloEvento = tituloEvento;
		this.strLugarEvento = strLugarEvento;
		this.organizador = organizador;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fechaEvento = fechaEvento;
		strFecha = this.formatDateToString(fechaEvento);
	}

	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------	
	
	/**
	 * @return the idEvento
	 */
	public String getIdEvento() 
	{
		return idEvento;
	}

	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(String idEvento) 
	{
		this.idEvento = idEvento;
	}

	/**
	 * @return the tipoEvento
	 */
	public String getTipoEvento() 
	{
		return tipoEvento;
	}

	/**
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(String tipoEvento) 
	{
		this.tipoEvento = tipoEvento;
	}

	/**
	 * @return the tituloEvento
	 */
	public String getTituloEvento() 
	{
		return tituloEvento;
	}

	/**
	 * @param tituloEvento the tituloEvento to set
	 */
	public void setTituloEvento(String tituloEvento) 
	{
		this.tituloEvento = tituloEvento;
	}

	/**
	 * @return the fechaEvento
	 */
	public Date getFechaEvento() 
	{
		return fechaEvento;
	}

	/**
	 * @param fechaEvento the fechaEvento to set
	 */
	public void setFechaEvento(Date fechaEvento) 
	{
		this.fechaEvento = fechaEvento;
	}

	/**
	 * @return the strLugarEvento
	 */
	public String getStrLugarEvento() 
	{
		return strLugarEvento;
	}

	/**
	 * @param strLugarEvento the strLugarEvento to set
	 */
	public void setStrLugarEvento(String strLugarEvento) 
	{
		this.strLugarEvento = strLugarEvento;
	}

	/**
	 * @return the organizador
	 */
	public String getOrganizador() 
	{
		return organizador;
	}

	/**
	 * @param organizador the organizador to set
	 */
	public void setOrganizador(String organizador) 
	{
		this.organizador = organizador;
	}
	
	public String formatDateToString(Date d)
	{
		SimpleDateFormat dt = new SimpleDateFormat(FORMAT);
		String ans = "";
		ans = dt.format(d);
		return ans;
	}
	
	/**
	 * Retorna una representacion de strings del evento con los datos basicos 
	 * @return representacion de strings del evento con los datos basicos 
	 */
	public String getInformationStr()
	{
		String ans = ID + idEvento + "\n" + DEPORTE + tipoEvento + "\n" + NOMBRE + tituloEvento + "\n" + ORG + organizador + "\n" + FECHA + strFecha + "\n" + LUGAR + strLugarEvento;
		//Identificador: NN
		//Nombre: Cursos de... 
		//Organizador: Uniandes
		//Fecha: 09/13/2014 15:30:00
		return ans;
	}
	
	
}
