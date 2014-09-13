package com.spartan.entidades;

import java.util.ArrayList;

/**
 * Representa al usuario
 * @author hellspawn
 */
public class Usuario 
{
	
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------
	
	
	
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Lista de eventos por asistir
	 */
	private ArrayList <Asistencia> asistencias;
	
	/**
	 * Lista de eventos creados por el usuario
	 */
	private ArrayList <EventoPrivado> eventosUsuario;
	
	/**
	 * Puntaje del usuario
	 */
	private double userScore;
	 
	/**
	 * Nivel del usuario
	 */
	private String userStatus;
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	
	public Usuario()
	{
		
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	private void readUserData()
	{
		
	}
	
	

}
