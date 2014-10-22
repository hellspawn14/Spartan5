package com.spartan.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spartan5.R;
import com.example.spartan5.R.id;
import com.example.spartan5.R.layout;
import com.spartan.recursos.ConnChecker;

public class CambiarContrasenaActivity extends Activity {

	private EditText anterior;
	private EditText nueva;
	private EditText nuevaConf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cambiar_contrasena);

		anterior = (EditText) findViewById(R.id.pwdAnterior);
		nueva = (EditText) findViewById(R.id.pwdNueva);
		nuevaConf = (EditText) findViewById(R.id.pwdNuevaConf);
	}

	public void cambiarContrasena(View w) {
		Context c = getApplicationContext();
		if (ConnChecker.isOnline(c)) {

			String sAnterior = anterior.getText() + "";
			String sNueva = nueva.getText() + "";
			String sNuevaConf = nuevaConf.getText() + "";
			if (sAnterior.equals("") || sNueva.equals("")
					|| !sNueva.equals(sNuevaConf))
				Toast.makeText(c, "Revisa los datos ingresados",
						Toast.LENGTH_LONG).show();
			else {
				// TODO llamar al servicio web

				// informar si fue correcto o si no
				Toast.makeText(c, "Contraseña cambiada exitosamente",
						Toast.LENGTH_LONG).show();
			}

		} else {
			Toast.makeText(
					c,
					"En este momento no dispones de conexión, intenta mas tarde",
					Toast.LENGTH_LONG).show();
		}
	}
}
