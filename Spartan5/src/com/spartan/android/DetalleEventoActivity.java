package com.spartan.android;

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

import com.example.spartan5.R;
import com.spartan.entidades.Asistencia;
import com.spartan.entidades.Evento;
import com.spartan.entidades.Spartan;

public class DetalleEventoActivity extends Activity
{
	//-----------------------------------------------------------------
	//Constantes
	//-----------------------------------------------------------------
	
	public final static int PICK_CONTACT = 1;
	//-----------------------------------------------------------------
	//Atributos
	//-----------------------------------------------------------------

	/**
	 * Lbl con el titulo del evento
	 */
	private TextView tituloEvento;
	
	/**
	 * Lbl con el lugar del evento 
	 */
	private TextView lugarEvento;
	
	/**
	 * Fecha del evento
	 */
	private TextView fechaEvento;
	
	/**
	 * Identificador del evento 
	 */
	private TextView identificadorEvento;
	
	/**
	 * Imagen del evento
	 */
	private ImageView imgEvento;
	
	/**
	 * Instancia de la clase principal
	 */
	private Spartan instanciaSpartan;
	
	/**
	 * Nombre y numero del invitado
	 */
	private String invitado;
	
	/**
	 * Numero de telefono del contacto seleccionado
	 */
	private String numeroTelefonicoContacto;
	
	/**
	 * Lista de invitados en la interfaz 
	 */
	private ListView listaInvitados;
	
	
	/**
	 * Items
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
		setContentView(R.layout.activity_detalle_evento_resultados);
		
		//Inicializa los elementos de la vista 
		tituloEvento = (TextView) findViewById(R.id.lblResultadoTituloEvento);
		lugarEvento = (TextView) findViewById(R.id.lblResultadoLugarEvento);
		fechaEvento = (TextView) findViewById(R.id.lblResultadoHoraFechaEvento);
		identificadorEvento = (TextView) findViewById(R.id.lblResultadoIdentificador);
		imgEvento = (ImageView) findViewById (R.id.imgResultadoEvento);
		
		Intent intent = getIntent();
		tituloEvento.setText(intent.getExtras().getString("NombreEvento"));
		lugarEvento.setText("Lugar: " + intent.getExtras().getString("LugarEvento"));
		fechaEvento.setText("Fecha: " + intent.getExtras().getString("FechaEvento"));
		identificadorEvento.setText("Identificador: " + intent.getExtras().getString("ID"));
		imgEvento.setImageResource(this.getImgResource());
		
		//Inicializa la instancia 
		instanciaSpartan = Spartan.darInstancia(getApplicationContext());
		
		//Inicializa la lista de invitados
		items = new String [1];
		items[0] = "No se ha compartido este evento";
		listaInvitados = (ListView) findViewById(R.id.listaInvitadosDetalle);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,items);
		listaInvitados.setAdapter(adapter);	
		
		invitado = "No hay invitado";
	}
	
	//-----------------------------------------------------------------
	//Metodos
	//-----------------------------------------------------------------
	
	/**
	 * Muestra la actividad de seleccion de contactos invocando las funciones del dispositivo
	 */
	public void seleccionarContactoDetalle(View v)
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
					listaInvitados.setAdapter(adapter);	
					//compartidaCon.setText(nombreContacto);
				}
			}
		}
	}
	
	/**
	 * Envia mensajes SMS
	 * @param w
	 */
	public void enviarMensaje(View w)
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

	
	public void confirmarAsistencia(View w)
	{
		String idEventoRegistrar = identificadorEvento.getText().toString().split(":")[1].trim();
		Evento e = instanciaSpartan.getEventById(idEventoRegistrar);
		if (e != null)
		{
			int idAsistencia = instanciaSpartan.darUsuario().getAsistencias().size();
			Asistencia As = new Asistencia(e,invitado, idAsistencia);
			//Registra la asistencia en el objeto
			instanciaSpartan.darUsuario().getAsistencias().add(As);
			
			//TODO CORREGIR 
			//instanciaSpartan.registrarAsistencias(As);
			Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
			startActivity(intent);
			Toast.makeText(getApplicationContext(), "Evento confirmado", Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			Toast.makeText(getApplicationContext(), "Evento no confirmado", Toast.LENGTH_SHORT).show();
		}
		
		
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
			return R.drawable.ball_soccer_button_focused;
		}
		
		else if (deporte.equals(com.spartan.recursos.Eventos.BASKET))
		{
			return R.drawable.ball_basket_button_focused;
		}
		
		else if (deporte.equals(com.spartan.recursos.Eventos.VOLEY))
		{
			return R.drawable.ball_voley_button_focused;
		}
		else
		{
			return R.drawable.ball_tennis_button_focused;
		}
	}
	


}
