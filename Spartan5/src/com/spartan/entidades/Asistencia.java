package com.spartan.entidades;

/**
 * Representa una asistencia del usuario a un evento 
 * @author hellspawn
 */
public class Asistencia 
{

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Indica si el usuario ya asistio o no al evento
	 */
	private boolean estado;
	
	/**
	 * Referencia al evento 
	 */
	private Evento evento;
	
	/**
	 * Lista de invitados
	 */
	private String invitados[];
	
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Crea una nueva asistencia 
	 * @param e - Es el evento 
	 * @param inv - Es la lista de invitados
	 */
	public Asistencia(Evento e, String inv[])
	{
		estado = false;
		evento = e;
		invitados = inv;
	}

	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * @return the estado
	 */
	public boolean isEstado() 
	{
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) 
	{
		this.estado = estado;
	}

	/**
	 * @return the evento
	 */
	public Evento getEvento() 
	{
		return evento;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setEvento(Evento evento) 
	{
		this.evento = evento;
	}

	/**
	 * @return the invitados
	 */
	public String[] getInvitados() 
	{
		return invitados;
	}

	/**
	 * @param invitados the invitados to set
	 */
	public void setInvitados(String[] invitados)
	{
		this.invitados = invitados;
	}

}
