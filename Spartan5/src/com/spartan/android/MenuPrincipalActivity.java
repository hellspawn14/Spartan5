package com.spartan.android;

import com.example.spartan5.R;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
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
	
	/**
	 * Instancia de la aplicacion 
	 */
	private Spartan instanciaSpartan; 
	
	private TextView txtFutbol;
	
	private TextView txtBasket;
	
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
		
		//Inicializa el rating bar 
		calificacionJugador = (RatingBar) findViewById(R.id.estadisticasJuegador);
		LayerDrawable stars = (LayerDrawable) calificacionJugador.getProgressDrawable();
		stars.getDrawable(0).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
		stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
		
		//Carga los datos del recurso 
		instanciaSpartan = Spartan.darInstancia(getApplicationContext()); 
		
		
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
	
	/**
	 * Metodo para el llamado de la vista para crear un evento 
	 * @param w - Es la vista actual 
	 */
	public void crearEvento(View w)
	{
		Intent intent = new Intent(getApplicationContext(), CrearEventoActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Metodo para el llamado de la vista con los datos detallados del perfil del usuario
	 * @param w - Es la vista actual 
	 */
	public void checkIn(View w)
	{
		Intent intent = new Intent(getApplicationContext(), CheckInActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Metodo para el llamado de la vista con los datos detallados del perfil del usuario
	 * @param w - Es la vista actual 
	 */
	public void verPerfil(View w)
	{
		Intent intent = new Intent(getApplicationContext(), VerPerfilActivity.class);
		startActivity(intent);
	}
	
}
