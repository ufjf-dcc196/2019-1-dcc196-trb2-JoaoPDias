package com.ufjf.br.trabalho02.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ufjf.br.trabalho02.R;
import com.ufjf.br.trabalho02.dao.EtiquetaDAO;
import com.ufjf.br.trabalho02.model.Etiqueta;

public class EtiquetaInsertActivity extends AppCompatActivity {
    private EditText txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etiqueta_insert);
        txtTitulo = findViewById(R.id.edt_tituloEtiqueta);
        Button botaoSalvarEtiquta = findViewById(R.id.buttonSalvarEtiqueta);
        botaoSalvarEtiquta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testaTela()) {
                    String titulo = txtTitulo.getText().toString();
                    Etiqueta etiqueta = new Etiqueta(titulo);
                    EtiquetaDAO.getInstance().save(etiqueta,EtiquetaInsertActivity.this);
                    Intent intent = new Intent();
                    intent.putExtra("etiqueta", etiqueta);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }
            }

            private boolean testaTela() {
                boolean apto = true;
                if (TextUtils.isEmpty(txtTitulo.getText())) {
                    txtTitulo.requestFocus();
                    Toast.makeText(EtiquetaInsertActivity.this,
                            "Informe um Título para a Etiqueta",
                            Toast.LENGTH_SHORT).show();
                    apto = false;
                }
                return apto;
            }
        });
    }
}
