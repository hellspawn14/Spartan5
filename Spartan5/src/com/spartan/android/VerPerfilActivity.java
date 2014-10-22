package com.spartan.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spartan5.R;
import com.spartan.entidades.Spartan;
import com.spartan.recursos.ConnChecker;

/**
 * Vista del perfil del usuario
 * 
 * @author hellspawn
 */
public class VerPerfilActivity extends Activity {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Juegos
	 */
	public final static String JUEGOS = "juegos";
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Barra con el status de juegador
	 */
	private RatingBar calificacionJugador;

	/**
	 * Texto con el status del jugador
	 */
	private TextView statsJugador;

	/**
	 * Texto con los partidos de futbol
	 */
	private TextView lblFutbol;

	/**
	 * Texto con los partidos de basket
	 */
	private TextView lblBasket;

	/**
	 * Texto con los partidos de voley
	 */
	private TextView lblVoley;

	/**
	 * Texto con los partidos de tenis
	 */
	private TextView lblTenis;

	/**
	 * Instancia de la aplicacion
	 */
	private Spartan instanciaSpartan;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil_usuario);
		//
		calificacionJugador = (RatingBar) findViewById(R.id.estadisticasPerfil);
		LayerDrawable stars = (LayerDrawable) calificacionJugador
				.getProgressDrawable();
		stars.getDrawable(0).setColorFilter(Color.GRAY,
				PorterDuff.Mode.SRC_ATOP);
		stars.getDrawable(1)
				.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
		stars.getDrawable(2).setColorFilter(Color.YELLOW,
				PorterDuff.Mode.SRC_ATOP);
		//
		// //Inicializa la instancia
		Intent intent = getIntent();
		instanciaSpartan = Spartan.darInstancia(getApplicationContext());
		// //
		// // //Inicializa el rating
		// float rating = intent.getExtras().getFloat("Calificacion");
		// calificacionJugador.setRating(rating);
		// //
		// // //Inicializa los textos
		// statsJugador = (TextView) findViewById(R.id.lblStatus);
		// statsJugador.setText(intent.getExtras().getString("NivelJugador"));
		// //
		// lblFutbol = (TextView) findViewById(R.id.lblEncuentrosFutbol);
		// lblFutbol
		// .setText(intent.getExtras().getString("Futbol") + " " + JUEGOS);
		// //
		// lblBasket = (TextView) findViewById(R.id.lblEncuentrosBasket);
		// lblBasket
		// .setText(intent.getExtras().getString("Basket") + " " + JUEGOS);
		// //
		// lblVoley = (TextView) findViewById(R.id.lblEncuentrosVoley);
		// lblVoley.setText(intent.getExtras().getString("Voley") + " " +
		// JUEGOS);
		// //
		// lblTenis = (TextView) findViewById(R.id.lblEncuentrosTennis);
		// lblTenis.setText(intent.getExtras().getString("Tenis") + " " +
		// JUEGOS);
		statsJugador = (TextView) findViewById(R.id.lblStatus);
		statsJugador.setText("Super pro");

	}

	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	public void verAsistencias(View w) {
	}

	public void cambiarPwd(View w) {
		Context c = getApplicationContext();
		if (ConnChecker.isOnline(getApplicationContext())) {
			Intent intent = new Intent(c, LogInActivity.class);
			startActivity(intent);

		} else {
			Toast.makeText(
					getApplicationContext(),
					"En este momento no dispones de conexión, intenta nuestra opción sin registro",
					Toast.LENGTH_LONG).show();
		}
	}

	public void logout(View w) {
		Context c = getApplicationContext();
		instanciaSpartan.deleteUserLoginName();
		instanciaSpartan.resetFile(c, Spartan.USERNAME_FILE);
		instanciaSpartan.resetFile(c, Spartan.EVENTS_FILE);
		instanciaSpartan.resetFile(c, Spartan.TEAMS_FILE);
		instanciaSpartan.resetFile(c, Spartan.ASSISTANCES_FILE);
		instanciaSpartan.resetFile(c, Spartan.USER_SCORE_FILE);

		Intent intent = new Intent(c, LogInActivity.class);
		startActivity(intent);

	}
}
