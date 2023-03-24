package com.facens.geopositioning;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener{
    Context context;
    //
    public GPStracker(Context c){
        context = c;
    }
    //
    public Location getLocation(){
    //pede acesso ao sistema de localizaçao
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            //Caso nao tenha sido permitido mostra Nao foi permitido na tela
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //se o gps estiver habilitado retorna a localizaçao e concatena com a variavel l
        if(isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }

        else
        {
            //caso o nao seja detectada a permissao de uso do GPS mostra a mensagem na tela
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();
        }
        //retorno nulo do metodo
        return null;
    }
    //
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}