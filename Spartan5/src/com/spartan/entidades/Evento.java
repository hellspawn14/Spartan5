package com.spartan.entidades;

import java.util.Date;

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
	 * Evento publico
	 */
	public final static String EVENTO_PRIV = "Privado";
	
	/**
	 * Evento privado
	 */
	public final static String EVENTO_PUBLICO = "Publico";

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------
	
	/**
	 * Identificador del evento
	 */
	private String idEvento;
	
	/**
	 * Tipo de evento
	 */
	private String tipoEvento;

	/**
	 * Titulo del evento
	 */
	private String tituloEvento;
	
	/**
	 * Fecha del evento
	 */
	private Date fechaEvento;
	
	/**
	 * Lugar del evento (direccion)
	 */
	private String strLugarEvento;
	
	/**
	 * Organizador evento
	 */
	private String organizador;

	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	

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
	
	
	
}
