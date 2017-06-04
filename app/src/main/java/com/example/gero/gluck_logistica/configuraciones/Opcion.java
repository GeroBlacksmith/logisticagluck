package com.example.gero.gluck_logistica.configuraciones;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Osimielci on 30/4/2017.
 */

public class Opcion {
    public static class Grafica{
        public static void pantallaCompleta(Activity activity){
            // remove title
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        public static void ocultarActionBar(ActionBar ab){
            ab.hide();
        }
    }
}
