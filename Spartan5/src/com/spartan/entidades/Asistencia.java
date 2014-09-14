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
	
	//TODO
	/**
	 * Retorna una representacion en String para poner en la lista de asistencias 
	 * @return
	 */
	public String getStringRepresentation()
	{
		return "";
	}
	
	/**
	 * Retorna una representacion de la asistencia para guardarla 
	 * @return idEvento;estado;Invitados:X1,X2,X3....
	 */
	public String getStringToSave()
	{
		int est = 0;
		if (estado == true)
		{
			est = 1;
		}
		String inv = "";
		for (int i = 0; i < invitados.length - 1; i++)
		{
			inv = inv + invitados[i] + ",";
		}
		inv = inv + invitados[invitados.length];
		return evento.getIdEvento() + ";" + est + ";" + "Invitados: " + inv; 
	}

}
