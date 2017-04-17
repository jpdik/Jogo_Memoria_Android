package com.example.joao_paulo.memoria;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private static final Integer MAX_BOTOES = 6;
    private View btn[] = new View[MAX_BOTOES];

    private ConstraintLayout mainWindow;

    private ProgressBar barra;
    private TextView stringBarra;

    private TextView stringParabens1;
    private TextView stringParabens2;

    Context context;
    private Integer acertos;

    ArrayList<Integer> elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainWindow = (ConstraintLayout) findViewById(R.id.main);

        elementos = new ArrayList<>();
        context = getBaseContext();
        barra = (ProgressBar) findViewById(R.id.progressBar);
        stringBarra = (TextView) findViewById(R.id.progressBarView);

        stringParabens1 = (TextView) findViewById(R.id.textViewPara1);
        stringParabens2 = (TextView) findViewById(R.id.textViewPara2);

        for (Integer i = 0; i < MAX_BOTOES; i++){
            btn[i] = findViewById(getResources().getIdentifier("button" + String.valueOf(i+1), "id", context.getPackageName()));
        }

        reiniciar();
    }

    public void alterar(View view){
        if(view.getId() != btn[elementos.get(acertos)].getId())
            resetarBotoes();
        else {
            view.setVisibility(View.INVISIBLE);
            try {
                mainWindow.setBackgroundColor(view.getBackgroundTintList().getDefaultColor());
            }catch (NullPointerException e){
                mainWindow.setBackgroundColor(Color.WHITE);
            }
            acertos++;
            atualizarBarra(acertos);
        }
    }

    public void restart(View view){
        reiniciar();
    }

    public void resetarBotoes(){
        for(Integer i=0; i < MAX_BOTOES; i++)
            btn[i].setVisibility(View.VISIBLE);

        acertos = 0;
        atualizarBarra(acertos);
        mainWindow.setBackgroundColor(Color.WHITE);
    }

    public void atualizarBarra(Integer acertos){
        barra.setProgress(acertos);
        stringBarra.setText(acertos+"/"+MAX_BOTOES);

        if(acertos.equals(MAX_BOTOES))
            finalizou();
    }

    public void finalizou(){
        stringBarra.setVisibility(View.INVISIBLE);
        barra.setVisibility(View.INVISIBLE);
        stringParabens1.setVisibility(View.VISIBLE);
        stringParabens2.setVisibility(View.VISIBLE);
    }

    public void embaralhar(){
        elementos.clear();
        for(Integer i=0; i < MAX_BOTOES; i++)
            elementos.add(i);
        Collections.shuffle(elementos);

        for(Integer i=0; i < MAX_BOTOES; i++)
            System.out.println(elementos.get(i));
    }

    public void reiniciar(){

        stringBarra.setVisibility(View.VISIBLE);
        barra.setVisibility(View.VISIBLE);
        stringParabens1.setVisibility(View.INVISIBLE);
        stringParabens2.setVisibility(View.INVISIBLE);

        embaralhar();
        resetarBotoes();
    }
}
