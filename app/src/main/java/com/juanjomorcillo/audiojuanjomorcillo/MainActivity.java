package com.juanjomorcillo.audiojuanjomorcillo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    SoundPool spDo;
    SoundPool spRe;
    SoundPool spMi;
    SoundPool spFa;
    SoundPool spSol;
    SoundPool spLa;
    SoundPool spSi;
    int intDo, intRe, intMi, intFa, intSol, intLa, intSi, intDuracion, intCancion=0;
    float velocidad=1.0f, volumen=1.0f;
    TextView tvVelocidad, tvTituloCancion;
    MediaPlayer mp1=null;
    MediaPlayer mp2=null;
    EditText etEmpezarEn;
    MediaRecorder mrAudio;
    String almacenamiento;
    String almacenamientoMelodia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("App audio");
        spDo = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spRe = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spMi = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spFa = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spSol = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spLa = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spSi = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        intDo = spDo.load(this, R.raw.dododo, 1);
        intRe = spRe.load(this, R.raw.re, 1);
        intMi = spMi.load(this, R.raw.mi, 1);
        intFa = spFa.load(this, R.raw.fa, 1);
        intSol = spSol.load(this, R.raw.sol, 1);
        intLa = spLa.load(this, R.raw.la, 1);
        intSi = spSi.load(this, R.raw.si, 1);
        tvVelocidad = (TextView)findViewById(R.id.textViewVelocidad);
        tvTituloCancion = (TextView)findViewById(R.id.textViewTituloCancion);
        etEmpezarEn = (EditText)findViewById(R.id.editTextEmpezarEn);
        if(ContextCompat.checkSelfPermission(getApplicationContext(),	Manifest.permission.WRITE_EXTERNAL_STORAGE)	!=
                PackageManager.PERMISSION_GRANTED
                &&	ActivityCompat.checkSelfPermission(getApplicationContext(),	Manifest.permission.RECORD_AUDIO)	!=
                PackageManager.PERMISSION_GRANTED)	{
            ActivityCompat.requestPermissions(MainActivity.this,	new	String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,	Manifest.permission.RECORD_AUDIO},	1000);
        }
    }


    public void funcionDo(View view) {

        spDo.play(intDo, 1.0f, 1.0f, 0, 0, velocidad);
    }

    public void funcionRe(View view) {

        spRe.play(intRe, 1.0f, 1.0f, 0, 0, velocidad);
    }

    public void funcionMi(View view) {

        spMi.play(intMi, 1.0f, 1.0f, 0, 0, velocidad);
    }

    public void funcionFa(View view) {

        spFa.play(intFa, 1.0f, 1.0f, 0, 0, velocidad);
    }

    public void funcionSol(View view) {

        spSol.play(intSol, 1.0f, 1.0f, 0, 0, velocidad);
    }

    public void funcionLa(View view) {

        spLa.play(intLa, 1.0f, 1.0f, 0, 0, velocidad);
    }

    public void funcionSi(View view) {

        spSi.play(intSi, 1.0f, 1.0f, 0, 0, velocidad);
    }


    public void funcionMasVelocidad(View view) {

        if (velocidad<2){
            velocidad+=0.1f;

            tvVelocidad.setText(""+velocidad);
        }
        else {
            Toast.makeText(this, "Velocidad máxima", Toast.LENGTH_SHORT).show();
        }
    }

    public void funcionMenosVelocidad(View view) {

        if (velocidad>0.5f){
            velocidad-=0.1f;

            tvVelocidad.setText(""+velocidad);
        }
        else {
            Toast.makeText(this, "Velocidad mínima", Toast.LENGTH_SHORT).show();
        }
    }

    public void funcionCancion1(View view) {

        if(mp1==null && mp2==null){

            mp1 = MediaPlayer.create(this, R.raw.cancion1);
            intCancion=1;
            intDuracion = mp1.getDuration();
            tvTituloCancion.setText("Away In A Manger: "+intDuracion);
            etEmpezarEn.setText(""+intDuracion);
        }else{
            Toast.makeText(this, "Pulse antes el botón Detener", Toast.LENGTH_SHORT).show();
        }

    }

    public void funcionCancion2(View view) {

        if(mp1==null && mp2==null){

            mp2 = MediaPlayer.create(this, R.raw.cancion2);
            intCancion=2;
            intDuracion = mp2.getDuration();
            tvTituloCancion.setText("Checks For Free: "+intDuracion);
            etEmpezarEn.setText(""+intDuracion);
        }else{
            Toast.makeText(this, "Pulse antes el botón Detener", Toast.LENGTH_SHORT).show();
        }

    }

    public void funcionDetener(View view) {

            if(mp1!=null || mp2!=null){
                if(intCancion==1){
                    mp1.stop();
                    mp1.release();
                    mp1=null;
                    tvTituloCancion.setText("Ninguna canción cargada");
                }else if(intCancion==2){
                    mp2.stop();
                    mp2.release();
                    mp2=null;
                    tvTituloCancion.setText("Ninguna canción cargada");
                }
            }
    }

    public void funcionPlay(View view) {

        if(mp1!=null || mp2!=null){
            if(intCancion==1){
                mp1.start();
            }else if (intCancion==2){
                mp2.start();
            }
        }
        else {
            tvTituloCancion.setText("Ninguna canción cargada");
        }
    }

    public void funcionPausar(View view) {

        if (mp1!=null){
            if (mp1.isPlaying()==true) {
                mp1.pause();
            }
        }else if(mp2!=null){
            if (mp2.isPlaying()==true){
                mp2.pause();
            }
        }else{
                tvTituloCancion.setText("Ninguna canción cargada");
        }
    }

    public void funcionSubirVolumen(View view) {

        if(mp1!=null || mp2!=null){
            volumen +=0.1f;
            Toast.makeText(this, "Volumen: "+volumen, Toast.LENGTH_SHORT).show();
            if(intCancion==1){
                mp1.setVolume(volumen, volumen);
            }else if (intCancion==2){
                mp2.setVolume(volumen, volumen);
            }
        }
        else {
            tvTituloCancion.setText("Ninguna canción cargada");
        }
    }

    public void funcionBajarVolumen(View view) {
        if(mp1!=null || mp2!=null){
            volumen -=0.1f;
            Toast.makeText(this, "Volumen: "+volumen, Toast.LENGTH_SHORT).show();
            if(intCancion==1){
                mp1.setVolume(volumen, volumen);
            }else if (intCancion==2){
                mp2.setVolume(volumen, volumen);
            }
        }
        else {
            tvTituloCancion.setText("Ninguna canción cargada");
        }

    }

    public void funcionEmpezarEn(View view) {

        if (mp1!=null || mp2!=null){

            int intComienzo = Integer.parseInt(etEmpezarEn.getText().toString());

            if (intComienzo < 0) {

                Toast.makeText(this, "Valor negativo incorrecto", Toast.LENGTH_SHORT).show();
            }else{
                    if (intCancion == 1 && intComienzo <= mp1.getDuration()) {

                        mp1.seekTo(intComienzo);

                    } else if (intCancion == 2 && intComienzo <= mp2.getDuration()) {

                        mp2.seekTo(intComienzo);
                    }
            }

        }else {
            tvTituloCancion.setText("Ninguna canción cargada");
        }
    }

    public void funcionGrabarDetener(View view) {

        if (mrAudio==null) {

            almacenamiento = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            mrAudio = new MediaRecorder();
            mrAudio.setAudioSource(MediaRecorder.AudioSource.MIC);
            mrAudio.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mrAudio.setAudioEncoder(MediaRecorder.OutputFormat.AMR_WB);
            mrAudio.setOutputFile(almacenamiento);

            try {
                mrAudio.prepare();
                mrAudio.start();
                Toast.makeText(this, "Grabando", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Error en la grabacion", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            mrAudio.stop();
            Toast.makeText(this, "Detener", Toast.LENGTH_SHORT).show();
            mrAudio.release();
            mrAudio=null;
        }
    }


    public void funcionReproducir(View view) {

        MediaPlayer mPlayer;
        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(almacenamiento);
            mPlayer.prepare();
        }catch(IOException e){
            Toast.makeText(this, "Error de reproduccion", Toast.LENGTH_SHORT).show();
        }
        mPlayer.start();
        Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }


    public void funcionGrabarMelodia(View view) {

        if (mrAudio==null) {

            almacenamientoMelodia = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Melodia.mp3";
            mrAudio = new MediaRecorder();
            mrAudio.setAudioSource(MediaRecorder.AudioSource.MIC);
            mrAudio.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mrAudio.setAudioEncoder(MediaRecorder.OutputFormat.AMR_WB);
            mrAudio.setOutputFile(almacenamientoMelodia);

            try {
                mrAudio.prepare();
                mrAudio.start();
                Toast.makeText(this, "Grabando", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Error en la grabacion", Toast.LENGTH_SHORT).show();
            }
        }

        else{
            mrAudio.stop();
            Toast.makeText(this, "Detener", Toast.LENGTH_SHORT).show();
            mrAudio.release();
            mrAudio=null;
        }
    }

    public void funcionReproducirMelodia(View view) {

        MediaPlayer mPlayer;
        mPlayer = new MediaPlayer();
        try{
            mPlayer.setDataSource(almacenamientoMelodia);
            mPlayer.prepare();
        }catch(IOException e){
            Toast.makeText(this, "Error de reproduccion", Toast.LENGTH_SHORT).show();
        }
        mPlayer.start();
        Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }
}