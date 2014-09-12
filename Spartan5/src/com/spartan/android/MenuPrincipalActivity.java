package com.spartan.android;

import com.example.spartan5.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.graphics.PorterDuff;

/**
 * Pantalla con el menu principal de la aplicación
 * @author hellspawn
 */
public class MenuPrincipalActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Barra con el status de juegador
	 */
	RatingBar calificacionJugador;
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		calificacionJugador = (RatingBar) findViewById(R.id.estadisticasJuegador);
		LayerDrawable stars = (LayerDrawable) calificacionJugador.getProgressDrawable();
		stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
		//stars.getDrawable(1).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
		stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Metodo para el llamado de la vista para buscar un evento 
	 * @param w - Es la vista actual 
	 */
	public void buscarEvento(View w)
	{
		Intent intent = new Intent(getApplicationContext(), BuscarEventoActivity.class);
		startActivity(intent);
	}
	
	public void verPerfil(View w)
	{
		Intent intent = new Intent(getApplicationContext(), BuscarEventoActivity.class);
		startActivity(intent);
	}
	
}
