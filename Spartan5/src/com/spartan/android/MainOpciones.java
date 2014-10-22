package com.spartan.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.spartan5.R;

public class MainOpciones extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_opciones);
	}
	
	public void verEventosUsuario(View w)
	{
		Log.v("todo","ver Eventos Usuario");
	}
	
	public void verEquiposUsuario(View w)
	{
		Log.v("todo","ver Equipos Usuario");
	}
	
	public void verPerfilUsuario(View w)
	{
		Intent intent = new Intent(getApplicationContext(), VerPerfilActivity.class);
		startActivity(intent);
	}
	
	public void verJugadoresRecomendados(View w){
		Log.v("todo","ver Jugadores Recomendados");
	}
	
	public void verTorneosUsuario(View w){
		Log.v("todo","ver Torneos Usuario");
	}
	
	
	//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main_opciones, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
