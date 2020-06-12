package info.rayrojas.splash.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import info.rayrojas.splash.MainActivity;
import info.rayrojas.splash.helpers.QueueUtils;

public class Contacto {
  public String phone;
  public String nickname;
  public Contacto(String _phone, String _nickname) {
    this.phone = _phone;
    this.nickname = _nickname;
  }
  public static ArrayList getCollection() {
    ArrayList<Contacto> collection = new ArrayList<>();
    collection.add(new Contacto("981999923", "Bichito"));
    collection.add(new Contacto("9859913923", "Plaga"));
    collection.add(new Contacto("981914213", "Libelula"));
    collection.add(new Contacto("981914213", "Alcachofa"));
    collection.add(new Contacto("981999923", "Bichito"));
    collection.add(new Contacto("9859913923", "Plaga"));
    collection.add(new Contacto("981914213", "Libelula"));
    collection.add(new Contacto("981914213", "Alcachofa"));
    collection.add(new Contacto("981999923", "Bichito"));
    collection.add(new Contacto("9859913923", "Plaga"));
    collection.add(new Contacto("981914213", "Libelula"));
    collection.add(new Contacto("981914213", "Alcachofa"));
    return collection;
  }


  public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                             final ArrayList<Contacto> contactos,
                                             final MainActivity _interface) {
    String url = "http://fipo.equisd.com/api/users.json";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
        (Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject response) {
            if (response.has("objects")) {

              try {
                JSONArray list = response.getJSONArray("objects");
                for (int i=0; i < list.length(); i++) {
                  JSONObject o = list.getJSONObject(i);
                  contactos.add(new Contacto(o.getString("first_name"),
                      o.getString("last_name")));
                }

              } catch (JSONException e) {
                e.printStackTrace();
              }
              _interface.refreshList(); // Esta función debemos implementarla
              // en nuestro activity
            }
          }
        }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError error) {

          }
        });
    o.addToRequestQueue(jsonObjectRequest);
  }


}
