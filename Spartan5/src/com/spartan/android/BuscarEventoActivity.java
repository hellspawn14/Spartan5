package com.spartan.android;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.spartan5.R;
import com.spartan.entidades.Evento;
import com.spartan.entidades.Spartan;

/**
 * Pantalla con el menu para buscar evento 
 * @author hellspawn
 */
public class BuscarEventoActivity extends Activity 
{
	
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
	 * Checkbox para parametro cualquier deporte
	 */
	private CheckBox checkDeporte;
		
	/**
	 * Checkbox para parametro cualquier fecha
	 */
	private CheckBox checkFecha;
	
	/**
	 * Fecha del evento
	 */
	private DatePicker fechaEvento;
	
	/**
	 * Instancia de la clase principal
	 */
	private Spartan instanciaSpartan;
	
	
	
	//-----------------------------------------------------------------
	//Constructores
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		Log.v("hi", "oncreate buscarEvento");
		
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_buscar_evento);
//		
//		//Carga los datos del recurso 
//		instanciaSpartan = Spartan.darInstancia(getApplicationContext()); 
//				
//		sportKey = "";
//		
//		btnSoccer = (ImageButton) findViewById(R.id.btnSoccer);
//		btnSoccer.setOnClickListener(new OnClickListener() 
//		{
//			@Override
//			public void onClick(View w) 
//			{
//				getKeySelection(com.spartan.recursos.Eventos.SOCCER);
//			} 
//		});
//		
//		btnBasket = (ImageButton) findViewById(R.id.btnBasket);
//		btnBasket.setOnClickListener(new OnClickListener() 
//		{
//			@Override
//			public void onClick(View w) 
//			{
//				getKeySelection(com.spartan.recursos.Eventos.BASKET);
//			} 
//		});
//
//		btnVoley = (ImageButton) findViewById(R.id.btnVoley);
//		btnVoley.setOnClickListener(new OnClickListener() 
//		{
//			@Override
//			public void onClick(View w) 
//			{
//				getKeySelection(com.spartan.recursos.Eventos.VOLEY);
//			} 
//		});
//		
//		btnTennis = (ImageButton) findViewById(R.id.btnTennis);
//		btnTennis.setOnClickListener(new OnClickListener() 
//		{
//			@Override
//			public void onClick(View w) 
//			{
//				getKeySelection(com.spartan.recursos.Eventos.TENNIS);
//			} 
//		});
//
//		//Inicializacion otros elementos
//		checkDeporte = (CheckBox) findViewById(R.id.checkDeporte);
//		checkFecha = (CheckBox) findViewById(R.id.checkFecha);
//		fechaEvento = (DatePicker) findViewById(R.id.FechaEventoPicker);
//		initTouchListeners();
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
	 * Muestra los resultados de la busqueda segun los parametros seleccionados en la vista
	 * @param w
	 */
	public void goToResults(View w)
	{
		//Caso I -> Solo con la key 
		if(!checkDeporte.isChecked() && checkFecha.isChecked())
		{
			
			if (sportKey.equals(""))
			{
				this.showDialog("Oops", "Debe seleccionar un deporte antes de continuar");
			}
			
			else
			{
				ArrayList <Evento> results = instanciaSpartan.searchByKey(sportKey);
				packToIntent(results);
			}
		}
		
		//Caso II -> Busca cualquier deporte, sin ubicacion sin hora
		if (checkDeporte.isChecked() && checkFecha.isChecked())
		{
			ArrayList <Evento> results = instanciaSpartan.searchAnySportAnyWhereAnyTime();
			packToIntent(results);
		}
		
		//Caso III -> Busca un deporte teniendo en cuenta la fecha
		if(!checkDeporte.isChecked() && !checkFecha.isChecked())
		{
			int day  = fechaEvento.getDayOfMonth();
			int month= fechaEvento.getMonth() + 1;
			int year = fechaEvento.getYear();
			
			//"MM/dd/yyyy"
			String strMonth = "";
			if (month < 10)
			{
				strMonth = "0" + month;
			}
			else
			{
				strMonth = month + "";
			}
			
			String dateUsuario = strMonth + "/" + day + "/" + year;  
			ArrayList <Evento> results = instanciaSpartan.searchEventBySportAndDate(sportKey, dateUsuario);
			packToIntent(results);	
		}
		
		//Caso IV -> Solo tiene en cuenta la fecha 
		if (checkDeporte.isChecked() && !checkFecha.isSelected())
		{
			int day  = fechaEvento.getDayOfMonth();
			int month= fechaEvento.getMonth() + 1;
			int year = fechaEvento.getYear();
			
			//"MM/dd/yyyy"
			String strMonth = "";
			if (month < 10)
			{
				strMonth = "0" + month;
			}
			else
			{
				strMonth = month + "";
			}
			
			String dateUsuario = strMonth + "/" + day + "/" + year;  
			ArrayList <Evento> results = instanciaSpartan.searchByDate(dateUsuario);
			packToIntent(results);
		}

			
	}
	
	/**
	 * Empaca una lista para enviarla por el intent
	 * @param results - Es la lista con los eventos resultado
	 */
	private void packToIntent(ArrayList <Evento> results)
	{
		Intent intent = new Intent(getApplicationContext(), ResultadosBusquedaActivity.class);
		ArrayList <String> resultsStr = new ArrayList<String>();
		Evento ev;
		for (int i = 0; i < results.size(); i++)
		{
			ev = results.get(i);
			resultsStr.add(ev.getIdEvento());
		}
		intent.putStringArrayListExtra("Resultados", resultsStr);
		startActivity(intent);
	}
	
	/**
	 * Muestra un dialogo
	 * @param title
	 * @param message
	 */
	private void showDialog(String title, String message)
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle(title);
		alertDialog.setCancelable(false);
		alertDialog.setMessage(message);
		alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog,int id) 
			{
				
			}
		});
		AlertDialog dialog= alertDialog.create();
		dialog.show();
	}


}
