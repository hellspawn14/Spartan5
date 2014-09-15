package com.spartan.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.spartan5.R;
import com.spartan.entidades.Asistencia;
import com.spartan.entidades.Evento;
import com.spartan.entidades.Spartan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarEventoActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------

	/**
	 * Formato de fecha
	 */
	public final static String FORMAT = "MM/dd/yyyy hh:mm:ss";

	public final static int PICK_CONTACT = 1;
	
	
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Titulo del evento
	 */
	private TextView tituloEvento;
	
	/**
	 * Lugar del evento 
	 */
	private TextView lugarEvento; 
	
	/**
	 * Fecha del evento
	 */
	private TextView fechaEvento; 
	
	/**
	 * Imagen del evento
	 */
	private ImageView imagenEvento;
	
	/***
	 * Lista de invitados
	 */
	private ListView listaInvitado;
	
	/**
	 * Lista de invitados
	 */
	private String [] items;
	
	/**
	 * Invitado al evento
	 */
	private String invitado;
	
	/**
	 * Numero telefonico del contacto 
	 */
	private String numeroTelefonicoContacto;
	
	/**
	 * Instancia principal de la aplicacion
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
		setContentView(R.layout.activity_confirmar_evento);
		
		instanciaSpartan = Spartan.darInstancia(getApplicationContext());
		
		Intent intent = getIntent();
		tituloEvento = (TextView) findViewById(R.id.lblTituloEvento);
		lugarEvento = (TextView) findViewById(R.id.lblLugarEvento);
		fechaEvento = (TextView) findViewById(R.id.lblHoraFechaEvento);
		imagenEvento = (ImageView) findViewById(R.id.imgDeporte);
		
		imagenEvento.setImageResource(this.getImgResource());
		tituloEvento.setText(intent.getExtras().getString("Titulo"));
		lugarEvento.setText("Lugar: " + intent.getExtras().getString("Lugar"));
		fechaEvento.setText("Fecha: " + intent.getExtras().getString("Fecha"));
		
		//Inicializa la lista de invitados
		items = new String [1];
		items[0] = "No se ha compartido este evento";
		listaInvitado = (ListView) findViewById(R.id.listaConfirmarInvitado);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,items);
		listaInvitado.setAdapter(adapter);	
		
		invitado = "No hay invitado";
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Muestra la actividad de seleccion de contactos invocando las funciones del dispositivo
	 */
	public void seleccionarContacto(View v)
	{
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent,PICK_CONTACT);
	}
	
	/**
	 * Inicializa la lista de contactos
	 */
	public void onActivityResult(int reqCode, int resultCode, Intent data) 
	{
		invitado = "";
		if(resultCode == RESULT_OK){
			if(reqCode == PICK_CONTACT)
			{
				Uri uriContacto = data.getData();
				if(uriContacto != null)
				{						
					try 
					{
						String[] cols = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
						Cursor cursor =  managedQuery(uriContacto, cols, null, null, null);
						cursor.moveToFirst();
						invitado = cursor.getString(0);

						Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
						String[] columnas = {ContactsContract.CommonDataKinds.Phone.NUMBER};
						String seleccion = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "='" + invitado + "'";
						Cursor c = managedQuery(phoneUri,columnas,seleccion,null, null );
						if(c.moveToFirst())
						{
							numeroTelefonicoContacto = c.getString(0);
						}

					} catch (Exception e) 
					{
						numeroTelefonicoContacto = e.getMessage();
					}
					if (numeroTelefonicoContacto == null)
					{
						numeroTelefonicoContacto = "Número no disponible";
					}
					String strContacto = invitado + ": " + numeroTelefonicoContacto;
					items[0] = strContacto;
					ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,items);
					listaInvitado.setAdapter(adapter);	
					//compartidaCon.setText(nombreContacto);
				}
			}
		}
	}

	
	/**
	 * Envia mensajes SMS
	 * @param w
	 */
	public void enviarMensajeCrear(View w)
	{
		this.sendSMS();
	}
	
	/**
	 * Envia mensaje SMS
	 */
	private void sendSMS()
	{
		//Mensaje
		String mensaje = "Hola, quiero invitarte a " + tituloEvento.getText() + "\n" + lugarEvento.getText() + "\n" + fechaEvento.getText();   
		if (numeroTelefonicoContacto == null || numeroTelefonicoContacto.equals("Número no disponible"))
		{
			Toast.makeText(getApplicationContext(), "Debe introducir un numero valido", Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			 try 
			 {
			     SmsManager smsManager = SmsManager.getDefault();
			     smsManager.sendTextMessage(numeroTelefonicoContacto, null, mensaje, null, null);
			     Toast.makeText(getApplicationContext(), "Mensaje enviado",
			     Toast.LENGTH_LONG).show();
			  } 
			 catch (Exception e) 
			 {
			     Toast.makeText(getApplicationContext(),"Mensaje no enviado",Toast.LENGTH_LONG).show();
			     e.printStackTrace();    
			 }
			//Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();
		}
	}

	
	public void terminarConfirmacion(View w)
	{
		Intent intent = getIntent();
		String idEvento = (instanciaSpartan.getCatalogo().size() + 1)+ "";
		String tipoEvento = intent.getExtras().getString("Actividad"); 
		String titulo = intent.getExtras().getString("Titulo");
		String lugar = intent.getExtras().getString("Lugar");
		String organizador = "Usuario";
		double latLong = 0;
		Date fecha = this.stringToDate(intent.getExtras().getString("Fecha"));
		
		//Agrega el evento
		Evento e = new Evento (idEvento, tipoEvento ,titulo, lugar, organizador, latLong, latLong, fecha);
		instanciaSpartan.getCatalogo().add(e);
		
		//Agrega la asistencia
		int idAsistencia = instanciaSpartan.darUsuario().getAsistencias().size() + 1;
		Asistencia As = new Asistencia(e,invitado, idAsistencia);
		instanciaSpartan.darUsuario().getAsistencias().add(As);
	
		Intent intentPaso = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
		startActivity(intentPaso);
		Toast.makeText(getApplicationContext(), "Evento confirmado", Toast.LENGTH_SHORT).show();
	}
	
	public Date stringToDate(String fechaStr)
	{
		SimpleDateFormat dt = new SimpleDateFormat(FORMAT);
		Date D;
		try 
		{
			D = dt.parse(fechaStr);
			return D;
			
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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


}
