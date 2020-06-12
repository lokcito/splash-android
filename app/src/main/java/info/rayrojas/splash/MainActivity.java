package info.rayrojas.splash;

import androidx.appcompat.app.AppCompatActivity;
import info.rayrojas.splash.adapters.ContactoAdaptador;
import info.rayrojas.splash.helpers.QueueUtils;
import info.rayrojas.splash.models.Contacto;

import android.os.Bundle;
import android.widget.ListView;

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
    Contacto.injectContactsFromCloud(queue, items, this);

    contactosList = findViewById(R.id.contactosList);
    contactoAdaptador = new ContactoAdaptador(this, items, queueImage);
    contactosList.setAdapter(contactoAdaptador);
  }

  public void refreshList(){
    if ( contactoAdaptador!= null ) {
      contactoAdaptador.notifyDataSetChanged();
    }
  }

}
