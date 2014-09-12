package com.spartan.android;

import com.example.spartan5.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class CrearEventoActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

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
	
	public void confirmarEvento(View w)
	{
		
	}
}
