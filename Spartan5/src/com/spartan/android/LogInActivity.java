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

public class LogInActivity extends Activity {

	private EditText name;

	private EditText pwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);

		FileInputStream fis;
		try {
			
			//TODO guardar el usuario en un atributo activo para validar al regresar que el usuario ya haya ingresado
			fis = openFileInput("user.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bufferedReader = new BufferedReader(isr);
			String nameS = bufferedReader.readLine();
			if (nameS != null) {
				Log.v("", nameS);
				Toast.makeText(getApplicationContext(), "Bienvenido\n" + nameS,
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(),
						MainOpciones.class);
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
		
		//TODO primero preguntar si ya se ha ingresado
		if (!nameS.equals("") && !pwdS.equals("")) {
			
			try {
				FileOutputStream outputStream;
				outputStream = openFileOutput("user.txt", Context.MODE_PRIVATE);
				outputStream.write(nameS.getBytes());
				outputStream.close();
			} catch (Exception e) {
				Log.v("", "no se pudo gardar la informacion del usuario");
			}
			Intent intent = new Intent(getApplicationContext(), MainOpciones.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(getApplicationContext(), "Ingresa un nombre y contraseña primero :)",
					Toast.LENGTH_LONG).show();
			
		}

		Log.v("nombre", nameS);
		Log.v("mostrando pwd", pwdS);
		// TODO conectar con el servicio web

		// si, respuesta afirmativa

		// de lo contrario informar contraseña o nombre de usuario incorrectos
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
