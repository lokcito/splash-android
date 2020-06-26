package info.rayrojas.splash;

import androidx.appcompat.app.AppCompatActivity;
import info.rayrojas.splash.helpers.QueueUtils;
import info.rayrojas.splash.models.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

  Button btnEntrar;
  EditText txtUsuario;
  EditText txtContrasena;
  QueueUtils.QueueObject queue;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    queue = QueueUtils.getInstance(this);

    btnEntrar = findViewById(R.id.btnEntrar);
    txtUsuario = findViewById(R.id.txtUsuario);
    txtContrasena = findViewById(R.id.txtContrasena);

    btnEntrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        btnEntrar.setText("Ingresando...");
        btnEntrar.setEnabled(false);
        Usuario o = new Usuario();
        o.usuario = txtUsuario.getText().toString();
        o.contrasena = txtContrasena.getText().toString();
        o.iniciarSesion(queue, LoginActivity.this);
      }
    });
  }
  public void refresh() {
    // Esta funcion se dispara cuando el inicio de sesion se llevo
    // a cabo.
    Intent o = new Intent(LoginActivity.this, MainActivity.class);
    startActivity(o);
  }
  public void error() {
    //Esta funciona se dispara cuando el inicio fallo
    Toast.makeText(this, "Usuario y contrasena errados.",
        Toast.LENGTH_SHORT).show();
    btnEntrar.setText("Entrar");
    btnEntrar.setEnabled(true);
  }
}
