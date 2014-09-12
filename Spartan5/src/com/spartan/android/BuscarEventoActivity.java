package com.spartan.android;
import com.example.spartan5.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Pantalla con el menu para buscar evento 
 * @author hellspawn
 */
public class BuscarEventoActivity extends Activity 
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	/**
	 * Futbol
	 */
	public final static String SOCCER = "Fútbol";
	
	/**
	 * Basketball
	 */
	public final static String BASKET = "Baloncesto";
	
	/**
	 * Voleyball
	 */
	public final static String VOLEY = "Voleibol";
	
	/**
	 * Tennis
	 */
	public final static String TENNIS = "Tenis";
	
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
		setContentView(R.layout.activity_buscar_evento);
		
		sportKey = "ola ke ase";
		
		btnSoccer = (ImageButton) findViewById(R.id.btnSoccer);
		btnSoccer.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(SOCCER);
			} 
		});
		
		btnBasket = (ImageButton) findViewById(R.id.btnBasket);
		btnBasket.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(BASKET);
			} 
		});

		btnVoley = (ImageButton) findViewById(R.id.btnVoley);
		btnVoley.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(VOLEY);
			} 
		});
		
		btnTennis = (ImageButton) findViewById(R.id.btnTennis);
		btnTennis.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View w) 
			{
				getKeySelection(TENNIS);
			} 
		});

		initTouchListeners();
	}

	
	//-----------------------------------------------------------------
	// Metodos 
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
            	getKeySelection(SOCCER);
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
            	getKeySelection(BASKET);
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
            	getKeySelection(VOLEY);
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
            	getKeySelection(TENNIS);
                return true;
            }
        });
	}

}
