package info.rayrojas.splash;
//Integrantes
//
import androidx.appcompat.app.AppCompatActivity;
import info.rayrojas.splash.adapters.ContactoAdaptador;
import info.rayrojas.splash.helpers.QueueUtils;
import info.rayrojas.splash.models.Contacto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  ListView contactosList;
  ContactoAdaptador contactoAdaptador;
  QueueUtils.QueueObject queue = null;
  ImageLoader queueImage = null;
  ArrayList<Contacto> items;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    queue = QueueUtils.getInstance(this.getApplicationContext());
    queueImage = queue.getImageLoader();
    items = new ArrayList<>();
    // Items fue llenado desde la nube
    Contacto.injectContactsFromCloud(queue, items, this);

    contactosList = findViewById(R.id.contactosList);
    contactoAdaptador = new ContactoAdaptador(this, items, queueImage);
    contactosList.setAdapter(contactoAdaptador);

    contactosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // En esta area puedo solicitar mas datos a la nube
        Contacto registro = items.get(position);
        showDetails(registro);
//        Toast.makeText(MainActivity.this, "Persona " + registro.phone,
//            Toast.LENGTH_SHORT).show();
      }
    });

  }

  public void showDetails(Contacto item) {
    Intent o = new Intent(this, ContactoDetalleActivity.class);
    o.putExtra("contactoId", item.id);
    startActivity(o);
  }

  public void refreshList(){
    if ( contactoAdaptador!= null ) {
      contactoAdaptador.notifyDataSetChanged();
    }
  }

}
