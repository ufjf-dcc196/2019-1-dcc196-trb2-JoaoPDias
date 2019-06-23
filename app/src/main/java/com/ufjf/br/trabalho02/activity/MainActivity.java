package com.ufjf.br.trabalho02.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.ufjf.br.trabalho02.R;
import com.ufjf.br.trabalho02.dao.EtiquetaDAO;
import com.ufjf.br.trabalho02.dao.EtiquetaTarefaDAO;
import com.ufjf.br.trabalho02.dao.TarefaDAO;
import com.ufjf.br.trabalho02.model.Estado;
import com.ufjf.br.trabalho02.model.Etiqueta;
import com.ufjf.br.trabalho02.model.GrauDificuldade;
import com.ufjf.br.trabalho02.model.Tarefa;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        Tarefa tarefa = new Tarefa().setDescricao("Teste").setEstado(Estado.EXECUCAO).setDataLimite(new Date()).setGrau(GrauDificuldade.MUITODIFICIL).setTitulo("Teste").setGrau(GrauDificuldade.DIFICIL);
        Etiqueta etiqueta = new Etiqueta().setDescricao("Testando");
        tarefa = TarefaDAO.getInstance().save(tarefa,this);
        etiqueta = EtiquetaDAO.getInstance().save(etiqueta,this);
        EtiquetaTarefaDAO.getInstance().save(etiqueta,tarefa,this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_tarefa: {
                Intent intent = new Intent(MainActivity.this,TarefaListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_etiqueta: {
                Intent intent = new Intent(MainActivity.this, EtiquetaListActivity.class);
                startActivity(intent);
                break;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

