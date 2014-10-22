package com.spartan.android;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.spartan5.R;

public class BuscarEquiposActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscar_equipos);
		Log.v("hi", "oncreate BuscarEquiposActivity");
	}
}
