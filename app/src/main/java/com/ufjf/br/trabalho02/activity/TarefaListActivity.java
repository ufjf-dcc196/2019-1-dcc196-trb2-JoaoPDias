package com.ufjf.br.trabalho02.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ufjf.br.trabalho02.R;
import com.ufjf.br.trabalho02.adapter.TarefaAdapter;
import com.ufjf.br.trabalho02.dao.TarefaDAO;

public class TarefaListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TarefaAdapter adapter;
    public static final int REQUEST_TAREFA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        final RecyclerView rv = findViewById(R.id.recyclerTarefa);
        this.adapter = new TarefaAdapter(TarefaDAO.getInstance().getTarefasByEstado(this));
        adapter.setOnTarefaClickListener(new TarefaAdapter.OnTarefaClickListener() {
            @Override
            public void onTarefaClick(View tarefaView, int position) {
                Toast.makeText(TarefaListActivity.this,"Teste",Toast.LENGTH_LONG).show();
            }
        });
        rv.setAdapter(adapter);
        rv.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setBackgroundColor(Color.parseColor("#FFFFFF"));
        rv.setLayoutManager(new LinearLayoutManager(this));
        Button botaoTarefa = findViewById(R.id.buttonCadastrarTarefa);
        botaoTarefa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TarefaListActivity.this, TarefaInsertActivity.class);
                        startActivityForResult(intent, TarefaListActivity.REQUEST_TAREFA);
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_tarefa: {
                Intent intent = new Intent(TarefaListActivity.this,TarefaListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_etiqueta: {
                Toast.makeText(this, "Etiqueta", Toast.LENGTH_SHORT).show();
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
