package com.spartan.android;

import java.util.ArrayList;

import com.example.spartan5.R;
import com.spartan.entidades.Asistencia;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CheckInActivity extends Activity
{
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * ListView para mostrar los resultados
	 */
	private ListView listaAsistencias;
	
	/**
	 * Instancia de la clase principal 
	 */
	private Spartan instanciaSpartan;
	
	/**
	 * Colección de elementos para poner en la lista
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
		setContentView(R.layout.activity_check_in);
		
		//Inicializa la instancia 
		instanciaSpartan = Spartan.darInstancia(getApplicationContext());
		ArrayList <Asistencia> asistencias  = instanciaSpartan.darUsuario().getAsistencias();
		items = instanciaSpartan.getAssistenceListData(asistencias);
		listaAsistencias = (ListView) findViewById(R.id.listaAsistencias);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,items);
		listaAsistencias.setAdapter(adapter);	
		
		
		listaAsistencias.setOnItemClickListener(new OnItemClickListener() 
		{
			@SuppressWarnings("rawtypes")
			public void onItemClick(AdapterView parent, View view,int position, long id) 
			{
				Intent intent = new Intent(getApplicationContext(), DetalleAsistenciaActivity.class);
				
				String extra = ((TextView) view).getText().toString();
				
				String idEvento = ((TextView) view).getText().toString().split("\n")[8].split(":")[1].trim();
				intent.putExtra("ID", idEvento);
				String actividad = ((TextView) view).getText().toString().split("\n")[1].split(":")[1].trim();
				intent.putExtra("Actividad", actividad);
				String nombre = ((TextView) view).getText().toString().split("\n")[2].split(":")[1].trim();
				intent.putExtra("NombreEvento", nombre);
				String fechaEvento = ((TextView) view).getText().toString().split("\n")[4].split(":")[1].trim();
				intent.putExtra("FechaEvento", fechaEvento);
				String lugarEvento = ((TextView) view).getText().toString().split("\n")[5].split(":")[1].trim();
				intent.putExtra("LugarEvento", lugarEvento);
				String estado = ((TextView) view).getText().toString().split("\n")[6].trim();
				intent.putExtra("Estado", estado);
				String invitado = ((TextView) view).getText().toString().split("\n")[7].split(":")[1].trim();
				intent.putExtra("Invitado", invitado);
				startActivity(intent);
			}
		});		
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------

}
