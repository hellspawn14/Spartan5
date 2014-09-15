package com.spartan.android;



import com.example.spartan5.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class CrearEventoActivity extends Activity
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
	 * Boton de selección de futbol 
	 */
	private ImageButton btnSoccer;

	/**
	 * Boton de selección de basket 
	 */
	private ImageButton btnBasket;

	/**
	 * Boton de selección de voley 
	 */
	private ImageButton btnVoley;
	
	/**
	 * Boton de selección de tennis 
	 */
	private ImageButton btnTennis;
	
	/**
	 * Clave de busqueda
	 */
	private String sportKey; 
	
	/**
	 * Campo para ingresar en nombre del evento
	 */
	private EditText nombreEvento;
	
	/**
	 * Campo para ingresar el lugar 
	 */
	private EditText lugarEvento;
	
	/**
	 * Fecha del evento
	 */
	private DatePicker fechaEvento;
	
	/**
	 * Hora del evento 
	 */
	private TimePicker horaEvento;
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_evento);
		
		sportKey = "";
		
		btnSoccer = (ImageButton) findViewById(R.id.btnSoccerCrear);
		btnSoccer.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(com.spartan.recursos.Eventos.SOCCER);
			} 
		});
		
		btnBasket = (ImageButton) findViewById(R.id.btnBasketCrear);
		btnBasket.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(com.spartan.recursos.Eventos.BASKET);
			} 
		});

		btnVoley = (ImageButton) findViewById(R.id.btnVoleyCrear);
		btnVoley.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(com.spartan.recursos.Eventos.VOLEY);
			} 
		});
		
		btnTennis = (ImageButton) findViewById(R.id.btnTennisCrear);
		btnTennis.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(com.spartan.recursos.Eventos.TENNIS);
			} 
		});

		initTouchListeners();
		
		//Inicializa los demas elementos graficos
		nombreEvento = (EditText) findViewById(R.id.txtTituloDeporte);
		lugarEvento = (EditText) findViewById(R.id.txtLugarEvento);
		fechaEvento = (DatePicker) findViewById(R.id.pickerFechaCrear);
		horaEvento = (TimePicker) findViewById(R.id.pickerHoraCrear);
		
		

	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------

	/**
	 * Modifica la selección de los parametros de busqueda
	 */
	public void getKeySelection(String key)
	{
		sportKey = key;
		Toast.makeText(getApplicationContext(), sportKey, Toast.LENGTH_SHORT).show();
	}
	
	
	/**
	 * Inicia los TouchListeners
	 */
	public void initTouchListeners()
	{
		btnSoccer.setOnTouchListener(new OnTouchListener() 
		{
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
            	btnSoccer.setPressed(true);
            	btnBasket.setPressed(false);
            	btnVoley.setPressed(false);
            	btnTennis.setPressed(false);
            	getKeySelection(com.spartan.recursos.Eventos.SOCCER);
                return true;
            }
        });
		
		btnBasket.setOnTouchListener(new OnTouchListener() 
		{
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
            	btnBasket.setPressed(true);
            	btnSoccer.setPressed(false);
            	btnVoley.setPressed(false);
            	btnTennis.setPressed(false);
            	getKeySelection(com.spartan.recursos.Eventos.BASKET);
                return true;
            }
        });
		
		btnVoley.setOnTouchListener(new OnTouchListener() 
		{
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
            	btnVoley.setPressed(true);
            	btnBasket.setPressed(false);
            	btnSoccer.setPressed(false);
            	btnTennis.setPressed(false);
            	getKeySelection(com.spartan.recursos.Eventos.VOLEY);
                return true;
            }
        });
		
		btnTennis.setOnTouchListener(new OnTouchListener() 
		{
            @Override
            public boolean onTouch(View v, MotionEvent event) 
            {
            	btnTennis.setPressed(true);
            	btnVoley.setPressed(false);
            	btnBasket.setPressed(false);
            	btnSoccer.setPressed(false);
            	getKeySelection(com.spartan.recursos.Eventos.TENNIS);
                return true;
            }
        });
	}
	/**
	 * Procesa los datos para crear el evento (el evento no ha sido creado)
	 * @param w
	 */
	public void confirmarEvento(View w)
	{
		if (sportKey.equals(""))
		{
			Toast.makeText(getApplicationContext(), sportKey, Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			String tituloEvento = nombreEvento.getText() + "";
			String lugar = lugarEvento.getText() + "";
			
			int dia = fechaEvento.getDayOfMonth();
			int mes = fechaEvento.getMonth() + 1;
			int anio = fechaEvento.getYear();
			int hora = horaEvento.getCurrentHour();
			int minuto = horaEvento.getCurrentMinute();
			//"MM/dd/yyyy"
			String strMonth = "";
			if (mes < 10)
			{
				strMonth = "0" + mes;
			}
			else
			{
				strMonth = mes + "";
			}
			String strFecha = strMonth + "/" + dia + "/" + anio + " " + hora + ":" + minuto + ":" + "00";
			Intent intent = new Intent(getApplicationContext(), ConfirmarEventoActivity.class);
			
			intent.putExtra("Actividad", sportKey);
			intent.putExtra("Titulo", tituloEvento);
			intent.putExtra("Lugar", lugar);
			intent.putExtra("Fecha", strFecha);
			startActivity(intent);
		}
		
	}
}
