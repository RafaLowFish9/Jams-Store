package com.example.jamsstore;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jamsstore.modelos_de_datos.Usuario;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class crud_admin extends AppCompatActivity {

    private static final String TAG = "crud_admin";
    EditText UsuarioPersona, nombrePersona, ApellidoPersona, CorreoPersona, ContrasenaP;
    ListView ListPersona;
    Button agregar, guardar, eliminar;
    UsuarioAdapter usuarioAdapter;
    List<Usuario> usuarioList;
    Usuario usuarioSeleccionado;

    public crud_admin(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crud_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.crud_admin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        // Capturar elementos
        UsuarioPersona = findViewById(R.id.txt_UsuarioPersona);
        nombrePersona = findViewById(R.id.txt_nombrePersona);
        ApellidoPersona = findViewById(R.id.txt_ApellidoPersona);
        CorreoPersona = findViewById(R.id.txt_CorreoPersona);
        ContrasenaP = findViewById(R.id.txt_ContrasenaPersona);
        ListPersona = findViewById(R.id.lv_DatosPersonas);
        agregar = findViewById(R.id.btn_añadir);
        guardar = findViewById(R.id.btn_guardar);
        eliminar = findViewById(R.id.btn_eliminar);

        // Inicializar la lista y el adaptador
        usuarioList = new ArrayList<>();
        usuarioAdapter = new UsuarioAdapter(this, usuarioList);
        ListPersona.setAdapter(usuarioAdapter);

        // Cargar los datos desde Firestore
        cargarUsuariosDesdeFirestore();

        agregar.setOnClickListener(view -> {
            String usuario = UsuarioPersona.getText().toString();
            String nombre = nombrePersona.getText().toString();
            String apellido = ApellidoPersona.getText().toString();
            String correo = CorreoPersona.getText().toString();
            String contrasena = ContrasenaP.getText().toString();

            if (!usuario.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {
                if (isUsuarioUnico(usuario, correo, null)) {
                    Usuario nuevoUsuario = new Usuario(null, "colombia", nombre, apellido, correo, contrasena, usuario, "12/06/2001", null, null, null);

                    FirebaseFirestore baseDeDatos = FirebaseFirestore.getInstance();
                    baseDeDatos.collection("Usuarios")
                            .add(nuevoUsuario)
                            .addOnSuccessListener(documentReference -> {
                                Log.d(TAG, "Documento de usuario añadido con ID: " + documentReference.getId());
                                cargarUsuariosDesdeFirestore(); // Recargar la lista después de añadir un nuevo usuario
                            })
                            .addOnFailureListener(e -> {
                                Log.w(TAG, "Error al añadir el documento: " + e);
                            });
                } else {
                    Toast.makeText(crud_admin.this, "El nombre de usuario o el correo ya están en uso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar.setOnClickListener(view -> {
            if (usuarioSeleccionado != null) {
                FirebaseFirestore baseDeDatos = FirebaseFirestore.getInstance();
                baseDeDatos.collection("Usuarios")
                        .document(usuarioSeleccionado.getId_usuario())
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            Log.d(TAG, "Documento de usuario eliminado con ID: " + usuarioSeleccionado.getId_usuario());
                            Toast.makeText(crud_admin.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                            cargarUsuariosDesdeFirestore(); // Recargar la lista después de eliminar un usuario
                        })
                        .addOnFailureListener(e -> {
                            Log.w(TAG, "Error al eliminar el documento: " + e);
                        });
            } else {
                Toast.makeText(crud_admin.this, "Seleccione un usuario para eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        guardar.setOnClickListener(view -> {
            if (usuarioSeleccionado != null) {
                String usuario = UsuarioPersona.getText().toString();
                String nombre = nombrePersona.getText().toString();
                String apellido = ApellidoPersona.getText().toString();
                String correo = CorreoPersona.getText().toString();
                String contrasena = ContrasenaP.getText().toString();

                if (!usuario.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {
                    if (isUsuarioUnico(usuario, correo, usuarioSeleccionado.getId_usuario())) {
                        usuarioSeleccionado.setNombre_usuario(usuario);
                        usuarioSeleccionado.setNombre(nombre);
                        usuarioSeleccionado.setApellido(apellido);
                        usuarioSeleccionado.setCorreo_electronico(correo);
                        usuarioSeleccionado.setContrase_a(contrasena);

                        FirebaseFirestore baseDeDatos = FirebaseFirestore.getInstance();
                        baseDeDatos.collection("Usuarios")
                                .document(usuarioSeleccionado.getId_usuario())
                                .set(usuarioSeleccionado)
                                .addOnSuccessListener(aVoid -> {
                                    Log.d(TAG, "Documento de usuario actualizado con ID: " + usuarioSeleccionado.getId_usuario());
                                    Toast.makeText(crud_admin.this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                                    cargarUsuariosDesdeFirestore(); // Recargar la lista después de actualizar un usuario
                                })
                                .addOnFailureListener(e -> {
                                    Log.w(TAG, "Error al actualizar el documento: " + e);
                                });
                    } else {
                        Toast.makeText(crud_admin.this, "El nombre de usuario o el correo ya están en uso", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(crud_admin.this, "Seleccione un usuario para guardar", Toast.LENGTH_SHORT).show();
            }
        });

        ListPersona.setOnItemClickListener((adapterView, view, position, id) -> {
            usuarioSeleccionado = usuarioAdapter.getItem(position);
            if (usuarioSeleccionado != null) {
                UsuarioPersona.setText(usuarioSeleccionado.getNombre_usuario());
                nombrePersona.setText(usuarioSeleccionado.getNombre());
                ApellidoPersona.setText(usuarioSeleccionado.getApellido());
                CorreoPersona.setText(usuarioSeleccionado.getCorreo_electronico());
                ContrasenaP.setText(usuarioSeleccionado.getContrase_a());
                Toast.makeText(crud_admin.this, "Usuario seleccionado: " + usuarioSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isUsuarioUnico(String nombreUsuario, String correo, String idUsuarioActual) {
        for (Usuario usuario : usuarioList) {
            if (!usuario.getId_usuario().equals(idUsuarioActual) &&
                    (usuario.getNombre_usuario().equals(nombreUsuario) || usuario.getCorreo_electronico().equals(correo))) {
                return false;
            }
        }
        return true;
    }

    private void cargarUsuariosDesdeFirestore() {
        FirebaseFirestore baseDeDatos = FirebaseFirestore.getInstance();
        baseDeDatos.collection("Usuarios")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        usuarioList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Usuario usuario = document.toObject(Usuario.class);
                            usuario.setId_usuario(document.getId());
                            usuarioList.add(usuario);
                        }
                        usuarioAdapter.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "Error obteniendo documentos.", task.getException());
                    }
                });
    }
}
