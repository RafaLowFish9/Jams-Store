package com.example.jamsstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jamsstore.modelos_de_datos.Usuario;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {


    public UsuarioAdapter(Context context, List<Usuario> usuarios) {
        super(context, 0, usuarios);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario usuario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_usuario_adapter, parent, false);
        }

        TextView nombreTextView = convertView.findViewById(R.id.usuario_nombre);
        TextView apellidoTextView = convertView.findViewById(R.id.usuario_apellido);
        TextView correoTextView = convertView.findViewById(R.id.usuario_correo);

        nombreTextView.setText(usuario.getNombre());
        apellidoTextView.setText(usuario.getApellido());
        correoTextView.setText(usuario.getCorreo_electronico());

        return convertView;
    }
}