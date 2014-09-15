package com.spartan.android;

import com.example.spartan5.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Pantalla de inicio de la aplicacion (menu splash)
 * @author hellspawn
 */
public class SplashScreenActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------
	
	/**
	 * Indica el tiempo que la actividad sera mostrada al usuario
	 */
	private static int SPLASH_TIME_OUT = 2000;
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------
	
	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run() 
			{
				Intent i = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
				startActivity(i);
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
