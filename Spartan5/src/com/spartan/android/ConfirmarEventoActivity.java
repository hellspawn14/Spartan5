package com.spartan.android;

import com.example.spartan5.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConfirmarEventoActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Constructor
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmar_evento);
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Muestra la actividad de seleccion de contactos invocando las funciones del dispositivo
	 */
	public void seleccionarContacto(View v)
	{
		
	}
	
	public void terminarConfirmacion(View w)
	{
		Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "Evento confirmado", Toast.LENGTH_SHORT).show();
	}

}
