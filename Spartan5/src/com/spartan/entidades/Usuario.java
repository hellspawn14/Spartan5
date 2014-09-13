package com.spartan.entidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.Context;

/**
 * Representa al usuario
 * @author hellspawn
 */
public class Usuario 
{

	//-----------------------------------------------------------------
	//Constantes escritura de status
	//-----------------------------------------------------------------
	
	/**
	 * Futbol
	 */
	public final static String STR_FUTBOL = "futbol=";
	
	/**
	 * Basket
	 */
	public final static String STR_BASKET = "basket=";
	
	/**
	 * Voley
	 */
	public final static String STR_VOLEY = "voley=";
	
	/**
	 * Tennis
	 */
	public final static String STR_TENIS = "tenis=";
	
	/**
	 * Score
	 */
	public final static String STR_SCORE = "score=";
	
	/**
	 * Fin de los datos
	 */
	public final static String END_FILE = "** Fin datos **";
	
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
	 * Numero de encuentros de futbol
	 */
	private int nFutbol;
	
	/**
	 * Numero de encuentros de baloncesto
	 */
	private int nBasket;
	
	/**
	 * Numero de encuentros de voleybol
	 */
	private int nVoley;

	/**
	 * Numero de encuentros de tenis
	 */
	private int nTennis;
	
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
	
	/**
	 * Crea el usuario, lee los datos e inicializa los atributos
	 * @param c - Es el contexto 
	 */
	public Usuario(Context c)
	{
		readUserData(c);
		setUserStatus();
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Lee los datos del archivo de texto e inicializa los atributos del usuario
	 */
	private void readUserData(Context c)
	{
		try 
		{			  
			BufferedReader br = new BufferedReader(new InputStreamReader(c.getResources().getAssets().open("user_data.txt")));
			String linea = br.readLine();
			while( !linea.equals(END_FILE) )
			{
				userScore = Double.parseDouble(linea.split(";")[0].split("=")[1]);
				setFutbol(Integer.parseInt(linea.split(";")[1].split("=")[1]));
				nBasket = Integer.parseInt(linea.split(";")[2].split("=")[1]);
				nVoley = Integer.parseInt(linea.split(";")[3].split("=")[1]);
				nTennis = Integer.parseInt(linea.split(";")[4].split("=")[1]);
			}
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicializa el status del usuario segun el score
	 */
	private void setUserStatus()
	{
		userStatus = "";
		if (userScore <= 2)
		{
			userStatus = com.spartan.recursos.StatusJugador.TRONCO;
		}
		
		else if (userScore > 2 && userScore <= 3.5)
		{
			userStatus = com.spartan.recursos.StatusJugador.NOOB;
		}
		
		else if (userScore > 3.5 && userScore <= 4.5)
		{
			userStatus = com.spartan.recursos.StatusJugador.PIBE;
		}
		
		else
		{
			userStatus = com.spartan.recursos.StatusJugador.SPARTAN;
		}
	}
	
	private void writeUserData(double score, int nFut, int nBask, int nVol, int nTen)
	{
		String strCadena = STR_SCORE + score + ";" + STR_FUTBOL + nFut + ";" + STR_BASKET + nBask + ";" + STR_VOLEY + nVol + ";" + STR_TENIS + nTen;
	}

	//-----------------------------------------------------------------
	//Getters & Setters 
	//-----------------------------------------------------------------

	
	/**
	 * @return the nFutbol
	 */
	public int getFutbol() 
	{
		return nFutbol;
	}

	/**
	 * @return the nBasket
	 */
	public int getnBasket() 
	{
		return nBasket;
	}

	/**
	 * @param nBasket the nBasket to set
	 */
	public void setnBasket(int nBasket)
	{
		this.nBasket = nBasket;
	}

	/**
	 * @return the nVoley
	 */
	public int getnVoley() 
	{
		return nVoley;
	}

	/**
	 * @param nVoley the nVoley to set
	 */
	public void setnVoley(int nVoley) 
	{
		this.nVoley = nVoley;
	}

	/**
	 * @return the nTennis
	 */
	public int getnTennis() 
	{
		return nTennis;
	}

	/**
	 * @param nTennis the nTennis to set
	 */
	public void setnTennis(int nTennis) 
	{
		this.nTennis = nTennis;
	}

	/**
	 * @param nFutbol the nFutbol to set
	 */
	public void setFutbol(int nFutbol) 
	{
		this.nFutbol = nFutbol;
	}
	
	

}
