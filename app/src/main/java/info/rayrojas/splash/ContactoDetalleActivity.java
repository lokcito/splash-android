package info.rayrojas.splash;

import androidx.appcompat.app.AppCompatActivity;
import info.rayrojas.splash.helpers.QueueUtils;
import info.rayrojas.splash.models.Contacto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ContactoDetalleActivity extends AppCompatActivity {
  QueueUtils.QueueObject queue = null;
  int contactoId;
  Contacto contactoObject = new Contacto(0, "","", "");
  ImageLoader imageLoader;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contacto_detalle);
    Intent o = getIntent();
    contactoId = o.getIntExtra("contactoId", -1);
    if ( contactoId <= -1 ) {
      Toast.makeText(this, "No se selecciono un contacto.",
          Toast.LENGTH_SHORT).show();
    }
    /// Consumimos informacion detalla de la nube
    contactoObject.id = contactoId;

    queue = QueueUtils.getInstance(this.getApplicationContext());
    imageLoader = queue.getImageLoader();
    Contacto.injectContactFromCloud(queue, contactoObject, this);

    Button btnComprar = findViewById(R.id.btnComprar);
    btnComprar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent o = new Intent(ContactoDetalleActivity.this, CompraActivity.class);
        startActivity(o);
      }
    });

  }
  public void refresh() {
    TextView txtNombre = findViewById(R.id.txtNombre);
    txtNombre.setText(contactoObject.nickname);

    NetworkImageView imgFoto = findViewById(R.id.imgFoto);
    imgFoto.setImageUrl(contactoObject.urlImage, imageLoader);
  }
}
