package com.spartan.android;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spartan5.R;
import com.spartan.entidades.Spartan;
import com.spartan.recursos.ConnChecker;

public class RegistrarUsuarioActivity extends Activity {

	private EditText name;
	private EditText phone;
	private EditText pwd;
	private EditText pwd2;
	private Spartan instancia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);

		instancia = Spartan.darInstancia(getApplicationContext());
		name = (EditText) findViewById(R.id.newUserName);
		phone = (EditText) findViewById(R.id.newUserPhone);
		pwd = (EditText) findViewById(R.id.newUserPassword);
		pwd2 = (EditText) findViewById(R.id.pwdNuevaConf);
	}

	public void registrar(View w) {
		Context c = getApplicationContext();

		String nombre = name.getText() + "";
		String cel = phone.getText() + "";
		int celu = 0;
		String pwd1 = pwd.getText() + "";
		String spwd2 = pwd2.getText() + "";
		if (nombre.equals("") || phone.equals("") || !pwd1.equals(spwd2)) {
			Toast.makeText(c, "Verifica los datos ingresados",
					Toast.LENGTH_LONG).show();
		} else {
			celu = Integer.parseInt(cel);
			if (ConnChecker.isOnline(c)) {
				// TODO llamar al servicio web
				// correcto => para a nueva vista
				// incorrecto => informar
				instancia.setUserLoginName(nombre);

				try {
					FileOutputStream outputStream;
					outputStream = openFileOutput(Spartan.USERNAME_FILE,
							Context.MODE_PRIVATE);
					outputStream.write(nombre.getBytes());
					outputStream.close();
				} catch (Exception e) {
					Log.v("", "no se pudo gardar la informacion del usuario");
				}

				Toast.makeText(c, "Bienvenido\n" + nombre, Toast.LENGTH_LONG)
						.show();
				Intent intent = new Intent(c, MainOpciones.class);
				startActivity(intent);
			} else {
				Toast.makeText(
						c,
						"En este momento no dispones de conexión para registrarte, intenta nuestra opción sin registro",
						Toast.LENGTH_LONG).show();
			}
		}

	}

}
