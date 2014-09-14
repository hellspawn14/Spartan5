package com.spartan.android;

import com.example.spartan5.R;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
	
	/**
	 * Numero de encuentros de futbol
	 */
	private TextView txtFutbol;

	/**
	 * Numero de encuentros de basket
	 */
	private TextView txtBasket;
	
	/**
	 * Numero de encuentros de voleybol
	 */
	private TextView txtVoley;
	
	/**
	 * Numero de encuentros de tenis
	 */
	private TextView txtTennis;
	
	/**
	 * Boton con el nivel del jugador
	 */
	private ImageButton btnBadge;
	
	/**
	 * Calificacion del jugador
	 */
	private float calificacion;
	
	/**
	 * Id del recurso
	 */
	private int recursoImagen;
	
	/**
	 * Nivel del jugador
	 */
	private String nvlJugador;
	
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
		
		//Conexion con los elementos graficos 
		txtFutbol = (TextView) findViewById(R.id.numFutbol);
		txtBasket = (TextView) findViewById(R.id.numBasket);
		txtVoley = (TextView) findViewById(R.id.numVoley);
		txtTennis = (TextView) findViewById(R.id.numTennis);
		btnBadge = (ImageButton) findViewById(R.id.imagenBadge);
		
		//Carga los datos del recurso 
		instanciaSpartan = Spartan.darInstancia(getApplicationContext()); 
		
		//Inicializa los componentes 
		txtFutbol.setText(instanciaSpartan.darUsuario().getFutbol() + "");
		txtBasket.setText(instanciaSpartan.darUsuario().getBasket() + "");
		txtVoley.setText(instanciaSpartan.darUsuario().getVoley() + "");
		txtTennis.setText(instanciaSpartan.darUsuario().getTennis() + "");
		
		calificacion = Float.parseFloat(instanciaSpartan.darUsuario().getScore() + "");
		calificacionJugador.setRating(calificacion);
		
		nvlJugador = instanciaSpartan.darUsuario().getStatus();
		recursoImagen = getStatusImg(nvlJugador);
		btnBadge.setImageResource(recursoImagen);
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
		//Envia la informacion a la vista
		intent.putExtra("Calificacion", calificacion);
		intent.putExtra("RecursoImg", recursoImagen);
		intent.putExtra("NivelJugador", nvlJugador);
		intent.putExtra("Futbol", txtFutbol.getText());
		intent.putExtra("Basket", txtBasket.getText());
		intent.putExtra("Voley", txtVoley.getText());
		intent.putExtra("Tenis", txtTennis.getText());
		startActivity(intent);
	}
	
	/**
	 * Retorna el identificador de la imagen de la insignia segun el status
	 * @param status - Es el status del jugador
	 * @return - ID del recurso
	 */
	public int getStatusImg(String status)
	{
		if (status == com.spartan.recursos.StatusJugador.NOOB)
		{
			return R.drawable.badge_noob;
		}
		
		else if(status == com.spartan.recursos.StatusJugador.PIBE)
		{
			return R.drawable.badge_pibe;
		}
		else if(status == com.spartan.recursos.StatusJugador.TRONCO)
		{
			return R.drawable.badge_tronco;
		}
		else
		{
			return R.drawable.badge_spartan;
		}
	}
	
}
