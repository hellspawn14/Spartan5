package com.spartan.android;

import com.example.spartan5.R;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class CheckInActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * ListView para mostrar los resultados
	 */
	private ListView asistencias;
	
	/**
	 * Instancia de la clase principal 
	 */
	private Spartan instanciaSpartan;
	
	/**
	 * Colección de elementos para poner en la lista
	 */
	private String [] items;
	//-----------------------------------------------------------------
	//Constructor
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_in);
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------

}
