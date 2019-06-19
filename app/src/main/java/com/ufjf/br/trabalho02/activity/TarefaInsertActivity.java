package com.ufjf.br.trabalho02.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ufjf.br.trabalho02.R;
import com.ufjf.br.trabalho02.contract.TarefaDBHelper;
import com.ufjf.br.trabalho02.dao.TarefaDAO;
import com.ufjf.br.trabalho02.model.Estado;
import com.ufjf.br.trabalho02.model.GrauDificuldade;
import com.ufjf.br.trabalho02.model.Tarefa;

import java.text.ParseException;
import java.util.Calendar;

public class TarefaInsertActivity extends AppCompatActivity implements
        View.OnFocusChangeListener {
    EditText txtDate, txtTime, txtTitulo, txtDescricao;
    Spinner spinnerEstado, spinnerGrau;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_insert);
        txtTitulo = (EditText) findViewById(R.id.edt_titulo);
        txtDescricao = (EditText) findViewById(R.id.edt_descricao);
        txtDate = (EditText) findViewById(R.id.edt_date);
        txtTime = (EditText) findViewById(R.id.edt_hora);
        txtDate.setOnFocusChangeListener(this);
        txtTime.setOnFocusChangeListener(this);
        spinnerEstado = (Spinner) findViewById(R.id.spinnerEstado);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_layout_item, Estado.values());
        adapter.setDropDownViewResource(R.layout.spinner_layout_item);
        spinnerEstado.setAdapter(adapter);
        spinnerGrau = (Spinner) findViewById(R.id.spinnerGrau);
        ArrayAdapter adapterGrau = new ArrayAdapter<>(this, R.layout.spinner_layout_item, GrauDificuldade.values());
        adapterGrau.setDropDownViewResource(R.layout.spinner_layout_item);
        spinnerGrau.setAdapter(adapterGrau);

        Button botaoSalvarTarefa = findViewById(R.id.buttonSalvarTarefa);
        botaoSalvarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testaTela()) {
                    Estado estado = (Estado) spinnerEstado.getSelectedItem();
                    GrauDificuldade grau = (GrauDificuldade) spinnerGrau.getSelectedItem();
                    String dataLimite = String.format("%s %s", txtDate.getText().toString(), txtTime.getText().toString());
                    String titulo = txtTitulo.getText().toString();
                    String descricao = txtTitulo.getText().toString();
                    try {
                        Tarefa tarefa = new Tarefa(titulo, descricao, grau, estado, dataLimite);
                        TarefaDAO.getInstance().save(tarefa, TarefaInsertActivity.this);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

            private boolean testaTela() {
                boolean apto = true;
                if (TextUtils.isEmpty(txtTitulo.getText())) {
                    txtTitulo.requestFocus();
                    Toast.makeText(TarefaInsertActivity.this,
                            "Informe um Título para a Tarefa",
                            Toast.LENGTH_SHORT).show();
                    apto = false;
                } else if (TextUtils.isEmpty(txtDescricao.getText())) {
                    txtDescricao.requestFocus();
                    Toast.makeText(TarefaInsertActivity.this,
                            "Informe uma Descrição para a Tarefa",
                            Toast.LENGTH_SHORT).show();
                    apto = false;
                } else if (TextUtils.isEmpty(txtDate.getText())) {
                    txtDate.requestFocus();
                    Toast.makeText(TarefaInsertActivity.this,
                            "Informe uma Data Limite para a Tarefa",
                            Toast.LENGTH_SHORT).show();
                    apto = false;
                } else if (TextUtils.isEmpty(txtTime.getText())) {
                    txtTime.requestFocus();
                    Toast.makeText(TarefaInsertActivity.this,
                            "Informe uma Hora Limite para a Tarefa",
                            Toast.LENGTH_SHORT).show();
                    apto = false;
                }
                return apto;
            }
        });
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v == txtDate && hasFocus) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(String.format("%d/%02d/%d", dayOfMonth, monthOfYear + 1, year));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == txtTime && hasFocus) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }
}
