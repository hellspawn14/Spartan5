package com.spartan.android;

import java.util.ArrayList;

import com.example.spartan5.R;
import com.spartan.entidades.Evento;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Pantalla con los resultados de busqueda 
 * @author hellspawn
 */
public class ResultadosBusquedaActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * ListView para mostrar los resultados
	 */
	private ListView resultadosBusqueda;
	
	/**
	 * Instancia de la clase principal 
	 */
	private Spartan instanciaSpartan;
	
	/**
	 * Colección de elementos para poner en la lista
	 */
	private String [] items;
	
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultados_busqueda);
		
		instanciaSpartan = Spartan.darInstancia(getApplicationContext());
		//Obtiene la informacion de la vista anterior
		Intent intent = getIntent();
		ArrayList <String> resultadosStr = intent.getStringArrayListExtra("Resultados");
		ArrayList <Evento> resultadosEve = this.retrieveEventosById(resultadosStr);
		items = instanciaSpartan.getEventListData(resultadosEve);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,items);
		resultadosBusqueda = (ListView) findViewById(R.id.listaResultados);
		resultadosBusqueda.setAdapter(adapter);	
		
		
		/**
		 * listaItems.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("rawtypes")
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				String nombreProducto = ((TextView) view).getText().toString().split("Prese") [0].trim();
				Log.d("Hellspawn", nombreProducto);
				Intent intent = new Intent(getApplicationContext(), DetalleProductoActivity.class);
				intent.putExtra("nombreProducto", nombreProducto);
				startActivity(intent);
			}

		});

		 */
		
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------

	/**
	 * Reconstruye los resultados de la busqueda
	 * @param resultsStr - Es la lista con los ids de los eventos resultado
	 * @return Lista de eventos del resultado
	 */
	private ArrayList <Evento> retrieveEventosById(ArrayList <String> resultsStr)
	{
		ArrayList <Evento> ans = new ArrayList <Evento>();
		ArrayList <Evento> catalogo = instanciaSpartan.getCatalogo();
		Evento inCat;
		String inResults;
		for (int i = 0; i < resultsStr.size(); i++)
		{
			for (int k = 0; k < catalogo.size(); k++)
			{
				inCat = catalogo.get(k);
				inResults = resultsStr.get(i);
				if(inCat.getIdEvento().equals(inResults))
				{
					ans.add(inCat);
				}
			}
		}
		return ans;
	}
}
