package info.rayrojas.splash.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import info.rayrojas.splash.LoginActivity;
import info.rayrojas.splash.helpers.QueueUtils;

public class Usuario {
  public String usuario;
  public String contrasena;

  public void iniciarSesion(QueueUtils.QueueObject qeue,
                            final LoginActivity _interface) {
    ///
    // Codigo que inicia sesion
    ///
    String url = "https://protected-fjord-91518.herokuapp.com/api/auth/login";
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            //Here your code when the request is successful
            //All code status 200, 201, 204
            _interface.refresh();
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            //Here your code when the request was going fatal
            //All code status 400, 401, 404, 500, 502, 503
            _interface.error();
          }
        }){
      @Override
      protected Map<String,String> getParams(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("email", Usuario.this.usuario);
        params.put("password", Usuario.this.contrasena);
        return params;
      }
    };
    qeue.addToRequestQueue(stringRequest);
  }
}
