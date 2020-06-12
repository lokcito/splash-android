package info.rayrojas.splash;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle saveInstanceState) {
    super.onCreate(saveInstanceState);
    Intent o = new Intent(this, MainActivity.class);
    // procesos que toman 5 minutos
    // timer
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    startActivity(o);
    finish();
  }
}
