package com.ufjf.br.trabalho02.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ufjf.br.trabalho02.R;
import com.ufjf.br.trabalho02.contract.TarefaContract;
import com.ufjf.br.trabalho02.dao.EtiquetaTarefaDAO;
import com.ufjf.br.trabalho02.dao.TarefaDAO;
import com.ufjf.br.trabalho02.model.Estado;
import com.ufjf.br.trabalho02.model.Etiqueta;
import com.ufjf.br.trabalho02.model.Tarefa;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    private Cursor cursor;
    private OnTarefaClickListener listener;
    private final Etiqueta etiqueta;

    public interface OnTarefaClickListener {
        void onTarefaClick(View tarefaView, int position);
    }

    public void setOnTarefaClickListener(OnTarefaClickListener listener) {
        this.listener = listener;
    }

    public TarefaAdapter(Cursor c, Etiqueta etiqueta) {
        cursor = c;
        this.etiqueta = etiqueta;
        notifyDataSetChanged();
    }

    private void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TarefaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.itemlista_layout, viewGroup, false);
        return new ViewHolder(tarefaView, context);
    }

    public Tarefa getTarefa(int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_DESCRICAO);
        int idxdataAtu = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_DATAHORAATU);
        int idxdataLimite = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_DATAHORALIMITE);
        int idxEstado = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_ESTADO);
        int idxGrau = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLUMN_NAME_GRAUDIFICULDADE);
        int idxId = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa._ID);
        cursor.moveToPosition(position);
        Tarefa tarefa = null;
        try {
            tarefa = new Tarefa(cursor.getLong(idxId), cursor.getString(idxTitulo),
                    cursor.getString(idxDescricao),
                    cursor.getString(idxdataAtu),
                    cursor.getString(idxdataLimite),
                    cursor.getInt(idxEstado),
                    cursor.getInt(idxGrau));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tarefa;
    }

    @Override
    public void onBindViewHolder(@NonNull final TarefaAdapter.ViewHolder viewHolder, final int i) {
        final Tarefa tarefa = getTarefa(i);
        viewHolder.txtTitulo.setText(tarefa.makeDescription());
        if (tarefa.getEstado().equals(Estado.CONCLUIDA)) {
            viewHolder.txtTitulo.setTextColor(Color.parseColor("#4CAF50"));
        }
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TarefaDAO.getInstance().delete(tarefa, viewHolder.context);
                if (etiqueta != null) {
                    setCursor(EtiquetaTarefaDAO.getInstance().getTarefasByEtiqueta(viewHolder.context, etiqueta));
                } else {
                    setCursor(TarefaDAO.getInstance().getTarefasByEstado(viewHolder.context));
                }
                notifyItemRemoved(i); // bug ao final da lista
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitulo = itemView.findViewById(R.id.layout_text_item);
        private final ImageButton delete = itemView.findViewById(R.id.delete_button);
        private final Context context;

        private ViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onTarefaClick(v, position);
                        }
                    }
                }
            });
        }
    }
}

