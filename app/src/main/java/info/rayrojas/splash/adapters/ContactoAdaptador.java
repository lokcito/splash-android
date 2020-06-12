package info.rayrojas.splash.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import info.rayrojas.splash.R;
import info.rayrojas.splash.models.Contacto;

public class ContactoAdaptador extends ArrayAdapter<Contacto> {
  Context context;
  private class ViewHolder {
    TextView phone;
    TextView nickname;

    private ViewHolder() {
    }
  }
  public ContactoAdaptador(Context context, List<Contacto> items) {
    super(context, 0, items);
    this.context = context;
  }
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    final Contacto rowItem = (Contacto) getItem(position);
    LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.item_contacto, null);
      holder = new ViewHolder();
      holder.phone = (TextView) convertView.findViewById(R.id.phone);
      holder.nickname = (TextView) convertView.findViewById(R.id.nickname);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    holder.phone.setText(rowItem.phone);
    holder.nickname.setText(rowItem.nickname);
    return convertView;
  }
}