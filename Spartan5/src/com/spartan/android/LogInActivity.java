package com.spartan.android;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

public class LogInActivity extends Activity {

	private EditText name;

	private EditText pwd;

	private Spartan mundo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Context c = getApplicationContext();
		mundo = Spartan.darInstancia(c);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		FileInputStream fis;
		try {
			fis = openFileInput(Spartan.USERNAME_FILE);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bufferedReader = new BufferedReader(isr);
			String nameS = bufferedReader.readLine();
			if (nameS != null) {
				mundo.setUserLoginName(nameS);
				Toast.makeText(c, "Bienvenido\n" + nameS, Toast.LENGTH_LONG)
						.show();
				Intent intent = new Intent(c, MainOpciones.class);
				startActivity(intent);
			}
			fis.close();
			fis = null;
			isr.close();
			nameS = null;
		} catch (FileNotFoundException e) {
			// primer ingreso
		} catch (IOException e) {
			Log.v("", "IOEXception sacando el nombre de usuario ");
		}
		name = (EditText) findViewById(R.id.newUserName);
		pwd = (EditText) findViewById(R.id.newUserPassword);

	}

	public void ingresar(View w) {
		String nameS = name.getText() + "";
		String pwdS = pwd.getText() + "";
		Context c = getApplicationContext();
		String nombreM = mundo.getUserLoginName();
		if (nombreM == null) {
			if (!nameS.equals("") && !pwdS.equals("")) {
				if (ConnChecker.isOnline(c)) {
					// TODO llamar web service
					// si, respuesta afirmativa
					// de lo contrario informar contraseña o nombre de usuario
					// incorrectos
					mundo.setUserLoginName(nameS);

					try {
						FileOutputStream outputStream;
						outputStream = openFileOutput(Spartan.USERNAME_FILE,
								Context.MODE_PRIVATE);
						outputStream.write(nameS.getBytes());
						outputStream.close();
					} catch (Exception e) {
						Log.v("",
								"no se pudo gardar la informacion del usuario");
					}
					Intent intent = new Intent(getApplicationContext(),
							MainOpciones.class);
					startActivity(intent);
				} else {
					Toast.makeText(
							getApplicationContext(),
							"En este momento no dispones de conexión, intenta nuestra opción sin registro",
							Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getApplicationContext(),
						"Ingresa un nombre y contraseña primero :)",
						Toast.LENGTH_LONG).show();

			}
		} else {
			Toast.makeText(c, "Bienvenido\n" + nameS, Toast.LENGTH_LONG).show();
			Intent intent = new Intent(c, MainOpciones.class);
			startActivity(intent);
		}
	}

	public void registrarUsuario(View w) {
		Intent intent = new Intent(getApplicationContext(),
				RegistrarUsuarioActivity.class);
		startActivity(intent);
	}

	public void seguirSinRegistrar(View w) {
		Intent intent = new Intent(getApplicationContext(),
				MainOpcionesSinIngreso.class);
		startActivity(intent);
	}
}
