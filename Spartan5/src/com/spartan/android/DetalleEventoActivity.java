package com.spartan.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spartan5.R;

public class DetalleEventoActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------
	
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Lbl con el titulo del evento
	 */
	private TextView tituloEvento;
	
	/**
	 * Lbl con el lugar del evento 
	 */
	private TextView lugarEvento;
	
	/**
	 * Fecha del evento
	 */
	private TextView fechaEvento;
	
	/**
	 * Identificador del evento 
	 */
	private TextView identificadorEvento;
	
	/**
	 * Imagen del evento
	 */
	private ImageView imgEvento;
	
	//-----------------------------------------------------------------
	//Constructor
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_evento_resultados);
		
		//Inicializa los elementos de la vista 
		tituloEvento = (TextView) findViewById(R.id.lblResultadoTituloEvento);
		lugarEvento = (TextView) findViewById(R.id.lblResultadoLugarEvento);
		fechaEvento = (TextView) findViewById(R.id.lblResultadoHoraFechaEvento);
		identificadorEvento = (TextView) findViewById(R.id.lblResultadoIdentificador);
		imgEvento = (ImageView) findViewById (R.id.imgResultadoEvento);
		
		Intent intent = getIntent();
		tituloEvento.setText(intent.getExtras().getString("NombreEvento"));
		lugarEvento.setText("Lugar: " + intent.getExtras().getString("LugarEvento"));
		fechaEvento.setText("Fecha: " + intent.getExtras().getString("FechaEvento"));
		identificadorEvento.setText("Identificador: " + intent.getExtras().getString("ID"));
		imgEvento.setImageResource(this.getImgResource());
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
	
	public void confirmarAsistencia(View w)
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
			return R.drawable.ball_soccer_button_focused;
		}
		
		else if (deporte.equals(com.spartan.recursos.Eventos.BASKET))
		{
			return R.drawable.ball_basket_button_focused;
		}
		
		else if (deporte.equals(com.spartan.recursos.Eventos.VOLEY))
		{
			return R.drawable.ball_voley_button_focused;
		}
		else
		{
			return R.drawable.ball_tennis_button_focused;
		}
	}


}
