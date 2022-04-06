package com.gdevinitiative.servicio1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class ContadorService extends Service {
    private static boolean runFlag = true;
    private MediaPlayer mp;
    public ContadorService() {
    }


    public void onCreate(){
        mp = MediaPlayer.create(this,R.raw.motorhead);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread trabajador = new Thread(new Cuenta());
        trabajador.start();
        mp.start();
        return START_STICKY;

    }
    @Override
    public void onDestroy(){
        runFlag = false;
        mp.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    private class Cuenta implements Runnable{
        public void run(){
            long contador = 0;
            while(runFlag==true){
                contador ++;
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                Log.d("Salida", contador+"");
            }
        }
    }
}