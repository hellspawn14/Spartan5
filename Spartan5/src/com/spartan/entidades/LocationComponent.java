package com.spartan.entidades;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;


/**
 * Representa al componente de localizacion
 * @author whogirl
 */
public class LocationComponent extends IntentService
{
  //-----------------------------------------------------------------
  //Constantes
  //-----------------------------------------------------------------

  private static final String TAG = LocationComponent.class.getSimpleName();

    public static final String PENDING_RESULT_EXTRA = "pending_result";

  //-----------------------------------------------------------------
  //Atributos
  //-----------------------------------------------------------------

  /**
   * Singleton de ubicación para la app
   */
  private static LocationComponent instance;
  //-----------------------------------------------------------------
  //Constructores
  //-----------------------------------------------------------------


  private LocationComponent()
  {
    //empty constructor
     super(TAG);
  }
  public static LocationComponent getInstance()
  {
    if(instance==null)
      instance=new LocationComponent();
    return instance;
  }
  //-----------------------------------------------------------------
  //Metodos
  //-----------------------------------------------------------------


  public double[] getCidAndLac(Context context)
  {
    TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
    double[] res = new double[6];
    int cid = cellLocation.getCid(); //cid
    int lac = cellLocation.getLac(); //lac
    String mynetmccmnc = telephonyManager.getNetworkOperator();
    int mcc = Integer.parseInt(mynetmccmnc.substring(0,3));
    int mnc = Integer.parseInt(mynetmccmnc.substring(3));
    double[] r =gmapsdata(cid,lac, mcc, mnc);
    res[0]=cid;
    res[1]=lac;
    res[2]=mcc;
    res[3]=mnc;
    res[4]=-1;
    res[5]=-1;
    if(r!=null){
      res[4]=r[0];
      res[5]=r[1];		}
    return res;
  }

  private double[] gmapsdata(int cellID, int lac, int mcc, int mnc) {
    double[] res=null;
          try {
                  URL providerAddress = new URL("http://www.google.com/glm/mmap");
                  HttpURLConnection connection = (HttpURLConnection) providerAddress.openConnection();
                  connection.setRequestMethod("POST");
                  connection.setDoOutput(true);
                  Log.d("whogirl","do output" );
                  connection.connect();
                  Log.d("whogirl","connected" );
                  OutputStream outputStream = connection.getOutputStream();
                  writeData(outputStream, cellID, lac, mcc, mnc);
                  Log.d("whogirl","wrote data to conn's output stream" );
                  InputStream inputStream = connection.getInputStream();
                  DataInputStream dataInputStream = new DataInputStream(inputStream);

                  dataInputStream.readShort();
                  dataInputStream.readByte();
                  int code = dataInputStream.readInt();
                  Log.d("whogirl","code read" );
                  if (code == 0) {
                      Log.d("whogirl","code not 0" );
                          double lat = (double) dataInputStream.readInt() / 1000000D;
                          double lon = (double) dataInputStream.readInt() / 1000000D;
                          res = new double[2];
                          res[0]=lat;
                          res[1]=lon;

                  }

          } catch (Exception e) {
            Log.d("whogirl","getting nothing from google's db" );
            e.printStackTrace();
          }
          return res;
  }

  private void writeData(OutputStream out, int cellID, int lac, int mcc, int mnc) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeShort(0x0E); // Fct code

        dos.writeInt(0);//la sesion
        dos.writeInt(0);
        dos.writeShort(57); // condigo de pais originalmente 0,
        dos.writeShort(0); // me
        dos.writeShort(0); // tag

        dos.writeByte(0x1B); //

        dos.writeInt(0);
        dos.writeInt(0);
        dos.writeInt(3);

        dos.writeShort(0); // length of provider name

        // provider name string
        dos.writeInt(cellID); // CID
        dos.writeInt(lac); // LAC
        dos.writeInt(mnc); // MNC
        dos.writeInt(mcc); // MCC
        dos.writeInt(-1); // always -1
        dos.writeInt(0); // rx level

        dos.flush();
}
  @Override
  protected void onHandleIntent(Intent intent) {
//		PendingIntent reply = intent.getParcelableExtra(PENDING_RESULT_EXTRA);
//        InputStream in = null;
//        try {
//
//                double[] res = getCidAndLac(getApplicationContext());
//
//
//            //does not matter, never worked
//        } catch (PendingIntent.CanceledException exc) {
//            Log.i(TAG, "reply cancelled", exc);
//        }
  }

//	Options!:
//	http://www.survivingwithandroid.com/2014/03/consume-webservice-in-android-intentservice.html
//	http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
//	tocaba poner permisos :v http://stackoverflow.com/questions/23710045/android-location-using-cell-tower
//	otro intento https://raw.githubusercontent.com/andr3jx/Mobilog/master/src/at/ftw/mobilitylogger/MainActivity.java
//	original http://android-coding.blogspot.com/2011/06/get-location-of-cell-id-from.html
}
