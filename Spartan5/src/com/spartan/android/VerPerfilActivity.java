package com.spartan.android;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.RatingBar;

import com.example.spartan5.R;

/**
 * Vista del perfil del usuario 
 * @author hellspawn
 */
public class VerPerfilActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	/**
	 * Barra con el status de juegador
	 */
	RatingBar calificacionJugador;

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_usuario);
		
		calificacionJugador = (RatingBar) findViewById(R.id.estadisticasPerfil);
		LayerDrawable stars = (LayerDrawable) calificacionJugador.getProgressDrawable();
		stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
		//stars.getDrawable(1).setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
		stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

	}


	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------

}
