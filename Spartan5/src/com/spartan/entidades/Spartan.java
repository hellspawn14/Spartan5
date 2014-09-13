package com.spartan.entidades;

import java.util.ArrayList;

import android.content.Context;

/**
 * Representa la clase principal de la aplicacion 
 * @author hellspawn
 */
public class Spartan 
{
	
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Catalgo de eventos de la aplicacion
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
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Crea una nueva instancia de la clase 
	 * @param c - Es el contexto de la aplicacion 
	 */
	public Spartan (Context c)
	{
		user = new Usuario(c);
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
	
}
