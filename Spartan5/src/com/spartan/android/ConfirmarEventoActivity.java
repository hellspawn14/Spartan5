package com.spartan.android;

import com.example.spartan5.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarEventoActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	/**
	 * Formato de fecha
	 */
	public final static String FORMAT = "MM/dd/yyyy hh:mm:ss";

	
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Titulo del evento
	 */
	private TextView tituloEvento;
	
	/**
	 * Lugar del evento 
	 */
	private TextView lugarEvento; 
	
	/**
	 * Fecha del evento
	 */
	private TextView fechaEvento; 
	
	/**
	 * Imagen del evento
	 */
	private ImageView imagenEvento;
	
	/***
	 * Lista de invitados
	 */
	private ListView listaInvitado;
	
	/**
	 * Lista de invitados
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
		setContentView(R.layout.activity_confirmar_evento);
		
		Intent intent = getIntent();
		tituloEvento = (TextView) findViewById(R.id.lblTituloEvento);
		lugarEvento = (TextView) findViewById(R.id.lblLugarEvento);
		fechaEvento = (TextView) findViewById(R.id.lblHoraFechaEvento);
		imagenEvento = (ImageView) findViewById(R.id.imgDeporte);
		
		imagenEvento.setImageResource(this.getImgResource());
		tituloEvento.setText(intent.getExtras().getString("Titulo"));
		lugarEvento.setText("Lugar: " + intent.getExtras().getString("Lugar"));
		fechaEvento.setText("Fecha: " + intent.getExtras().getString("Fecha"));
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
	
	/**
	 * Retorna el identificador del recurso de imagen de la actividad
	 * @param activity - Es el nombre de la actividad
	 * @return - Identificador del recurso 
	 */
	public int getImgResource()
	{
		Intent intent = getIntent();
		String deporte = intent.getExtras().getString("Actividad");
		if(deporte.equals(com.spartan.recursos.Eventos.SOCCER))
		{
			return R.drawable.ball_soccer;
		}
		
		else if (deporte.equals(com.spartan.recursos.Eventos.BASKET))
		{
			return R.drawable.ball_basket;
		}
		
		else if (deporte.equals(com.spartan.recursos.Eventos.VOLEY))
		{
			return R.drawable.ball_volei;
		}
		else
		{
			return R.drawable.ball_tennis;
		}
	}


}
