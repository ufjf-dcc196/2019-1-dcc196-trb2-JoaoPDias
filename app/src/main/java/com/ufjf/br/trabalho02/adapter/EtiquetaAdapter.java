package com.ufjf.br.trabalho02.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ufjf.br.trabalho02.R;
import com.ufjf.br.trabalho02.contract.TarefaContract;
import com.ufjf.br.trabalho02.dao.EtiquetaDAO;
import com.ufjf.br.trabalho02.model.Etiqueta;

import org.jetbrains.annotations.NotNull;

public class EtiquetaAdapter extends RecyclerView.Adapter<EtiquetaAdapter.ViewHolder> {
    private Cursor cursor;
    private OnEtiquetaClickListener listener;

    public interface OnEtiquetaClickListener {
        void onEtiquetaClick(View tarefaView, int position);
    }

    public void setOnEtiquetaClickListener(OnEtiquetaClickListener listener) {
        this.listener = listener;
    }

    public EtiquetaAdapter(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EtiquetaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.itemlista_layout, viewGroup, false);
        return new ViewHolder(tarefaView, context);
    }

    public Etiqueta getEtiqueta(int position) {
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefaContract.Etiqueta.COLUMN_NAME_TITULO);
        int idxId = cursor.getColumnIndexOrThrow(TarefaContract.Etiqueta._ID);
        cursor.moveToPosition(position);
        Etiqueta etiqueta;
        etiqueta = new Etiqueta(cursor.getLong(idxId), cursor.getString(idxTitulo));
        return etiqueta;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Etiqueta etiqueta = getEtiqueta(i);
        viewHolder.txtTitulo.setText(etiqueta.makeDescription());
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EtiquetaDAO.getInstance().delete(etiqueta, viewHolder.context);
                setCursor(EtiquetaDAO.getInstance().getEtiquetas(viewHolder.context));
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
                            listener.onEtiquetaClick(v, position);
                        }
                    }
                }
            });
        }
    }
}
