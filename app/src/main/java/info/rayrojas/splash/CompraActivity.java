package info.rayrojas.splash;

import androidx.appcompat.app.AppCompatActivity;
import info.rayrojas.splash.helpers.QueueUtils;
import info.rayrojas.splash.models.Contacto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CompraActivity extends AppCompatActivity {
  QueueUtils.QueueObject queue = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_compra);
    queue = QueueUtils.getInstance(this.getApplicationContext());

    final EditText txtDisciplinaNombre = findViewById(R.id.txtDisciplinaNombre);


    Button btn = findViewById(R.id.button);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Contacto contactoObject = new Contacto(0,
            txtDisciplinaNombre.getText().toString(),
            "", "");

        Contacto.postContactFromLocal(queue, contactoObject, CompraActivity.this);
      }
    });

  }
}
