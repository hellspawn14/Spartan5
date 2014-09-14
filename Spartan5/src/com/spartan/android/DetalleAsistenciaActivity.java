package com.spartan.android;

import com.example.spartan5.R;
import com.spartan.entidades.Asistencia;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleAsistenciaActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Nombre del evento 
	 */
	private TextView nombreEvento;
	
	/**
	 * Lugar del evento 
	 */
	private TextView lugarEvento;
	
	/**
	 * Fecha del evento 
	 */
	private TextView fechaEvento;
	
	/**
	 * Estado 
	 */
	private TextView estado;
	
	/**
	 * Id del evento 
	 */
	private TextView idEvento;
	
	/**
	 * Imagen del evento 
	 */
	private ImageView imagenEvento;
	
	/**
	 * Lista de invitados
	 */
	private ListView listaInvitadosAsistencia;
	
	/**
	 * Items de los invitados
	 */
	private String [] items;
	
	/**
	 * Instancia principal de la app 
	 */
	private Spartan instanciaSpartan; 
	
	//-----------------------------------------------------------------
	//Constructor
	//-----------------------------------------------------------------

	/**
	 * Metodo para la creacion de la actividad
	 */
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_asistencia);
		
		//Inicializa los elementos de la vista 
		nombreEvento = (TextView) findViewById(R.id.lblAsistenciaTituloEvento);
		lugarEvento = (TextView) findViewById(R.id.lblAsistenciaLugarEvento);
		fechaEvento = (TextView) findViewById(R.id.lblAsistenciaHoraFechaEvento);
		idEvento = (TextView) findViewById(R.id.lblAsistenciaIdentificador);
		estado = (TextView) findViewById(R.id.lblAsistenciaEstado);
		imagenEvento = (ImageView) findViewById (R.id.imgAsistenciaEvento);
		
		Intent intent = getIntent();
		nombreEvento.setText(intent.getExtras().getString("NombreEvento"));
		lugarEvento.setText("Lugar: " + intent.getExtras().getString("LugarEvento"));
		fechaEvento.setText("Fecha: " + intent.getExtras().getString("FechaEvento"));
		idEvento.setText("Identificador: " + intent.getExtras().getString("ID"));
		estado.setText(intent.getExtras().getString("Estado"));
		imagenEvento.setImageResource(this.getImgResource());
		
		//Inicializa la instancia 
		instanciaSpartan = Spartan.darInstancia(getApplicationContext());
		
		//Inicializa la lista de invitados
		items = new String [1];
		items[0] = intent.getExtras().getString("Invitado");
		listaInvitadosAsistencia = (ListView) findViewById(R.id.listInvitadosAsistencia);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,items);
		listaInvitadosAsistencia.setAdapter(adapter);

	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
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
	
	public void hacerCheckIn(View w)
	{
		String idAs = (idEvento.getText() + "").split(":")[1].trim();
		int asis = Integer.parseInt(idAs);
		Asistencia A = instanciaSpartan.buscarAsistencia(asis);
		instanciaSpartan.CheckInAsistencia(A);
		estado.setText("Ya fuiste");
		
		Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
		startActivity(intent);
	}
	
	public void cancelarAsistencia(View w)
	{
		String idAs = (idEvento.getText() + "").split(":")[1].trim();
		int asis = Integer.parseInt(idAs);
		Asistencia A = instanciaSpartan.buscarAsistencia(asis);
		
		instanciaSpartan.EliminarAsistencia(A);
		
		Toast.makeText(getApplicationContext(), "Asistencia eliminada", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
		startActivity(intent);
		
	}

	
}
