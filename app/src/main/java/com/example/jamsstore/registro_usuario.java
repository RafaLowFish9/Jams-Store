package com.example.jamsstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.util.Patterns;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jamsstore.modelos_de_datos.Usuario;

public class registro_usuario extends AppCompatActivity {

    // private FirebaseAuth mAuth;


    //EditText

    //Campos formulario de la actividad de registro
    private Spinner paisSpinner;
    private EditText correoEditText;
    private EditText nombreEditText;
    private EditText apellidoEditText;
    private EditText nombreUsuarioEditText;
    private EditText contraseaEditText;

    //Botones
    private Button btn_registro_editText; //Botón de continuar en el formulario de registro


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registro_usuario), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // ...
            // Initialize Firebase Auth
            //    mAuth = FirebaseAuth.getInstance();

            //Capturar los elementos editText del formulario por su id
            paisSpinner = findViewById(R.id.campo_pais);
            ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
                    this,
                    R.array.paises,
                    android.R.layout.simple_spinner_item
            );
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            paisSpinner.setAdapter(adaptador);

            //Capturar campos por el id
            correoEditText = findViewById(R.id.campo_correo);
            nombreEditText = findViewById(R.id.campo_nombre);
            apellidoEditText = findViewById(R.id.campo_apellido);
            nombreUsuarioEditText = findViewById(R.id.campo_nombre_usuario);
            contraseaEditText = findViewById(R.id.campo_contrase_a);

            //Capturar botón registro
            btn_registro_editText = findViewById(R.id.button_confirm);

            //Añadir evento listener para el botón de registro
            btn_registro_editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validarFormulario(paisSpinner, correoEditText, nombreEditText, apellidoEditText, nombreUsuarioEditText, contraseaEditText)) {
                        String pais = paisSpinner.getSelectedItem().toString();
                        String nombre = nombreEditText.getText().toString();
                        String apellido = apellidoEditText.getText().toString();
                        String correo = correoEditText.getText().toString();
                        String contrase_a = contraseaEditText.getText().toString();
                        String nombre_usuario = nombreEditText.getText().toString();

                        Usuario usuario = new Usuario(
                                null,
                                pais,
                                nombre,
                                apellido,
                                correo,
                                contrase_a,
                                nombre_usuario,
                                null,
                                null,
                                null,
                                null
                        );

                        Intent intent = new Intent(registro_usuario.this, day_of_birth_activity.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                    }

                }
            });


            return insets;
        });



        Button btn_continuar = findViewById(R.id.button_confirm);
        btn_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registro_usuario.this, login.class);
                startActivity(intent);
            }
        });

    }

    //Función para validar todos los cambios del formulario
    public boolean validarFormulario(Spinner spinnerPaises, EditText editTextCorreo, EditText editTextNombre, EditText editTextApellido, EditText editTextNombreUsuario, EditText editTextContrasena ) {
        boolean validar = true;

        //País
        String pais = spinnerPaises.getSelectedItem().toString();
        if(pais.equals("Seleccione su país")) {
            TextView textoError = (TextView) spinnerPaises.getSelectedView();
            textoError.setError("Seleccione un país");
            validar = false;
        }

        //Correo
        String correo = editTextCorreo.getText().toString();
        if(correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            editTextCorreo.setError("Ingrese una dirección de correo electrónico válida");
            validar = false;
        }
        //Validar nombre y apellido
        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        if(nombre.isEmpty() || !nombre.matches("^[a-zA-Z ]+$") || nombre.length() > 20) {
            editTextNombre.setError("Nombre inválido, introduzca solo letras (máximo 20 caracteres)");
            validar = false;
        }
        if(apellido.isEmpty() || !apellido.matches(("^[a-zA-Z ]+$")) || apellido.length() > 20) {
            editTextApellido.setError("Apellido inválido, introduzca solo letras (Máximo 20 caracteres)");
            validar = false;
        }

        //Validar nombre de usuario
        String nombreUsuario = editTextNombreUsuario.getText().toString();
        if(nombreUsuario.isEmpty() || !nombreUsuario.matches("^[a-zA-Z0-9_]{4,20}")) {
            editTextNombreUsuario.setError("El nombre de usuario debe contener al menos 4-20 caracteres y no utilizar espacios.");
            validar = false;
        }
        //c0Ntras3$#a
        //Validar contraseña
        String contrasena = editTextContrasena.getText().toString();
        if(contrasena.isEmpty() || !contrasena.matches("^[a-zA-Z0-9_]{4,20}")) {
            editTextContrasena.setError("Su contraseña debe tener al menos 8 caracteres, " +
                    "utilizando al menos una mayúscula, una minúscula, un número " +
                    "y un caracter especial");
                    validar = false;
        }

        return validar;
    }
}




    //Al iniciar la actividad, comienza checando si el usuario se encuentra loggeado en la aplicación
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser usuario_actual = mAuth.getCurrentUser();
//
//        //Revisar si el usuario se encuentra loggeado (no es null)
//        if(usuario_actual != null) {
//
//        }
//    }

    //Si el usuario
