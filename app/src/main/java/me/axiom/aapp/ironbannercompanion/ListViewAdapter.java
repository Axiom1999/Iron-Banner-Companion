package me.axiom.aapp.ironbannercompanion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.axiom.aapp.ironbannercompanion.api.GuardianClass;
import me.axiom.aapp.ironbannercompanion.api.data.GuardianInfo;

public class ListViewAdapter extends ArrayAdapter<GuardianInfo> {

    String url="http://bungie.net";

    private Context context;
    private GuardianInfo[] guardianList;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, int resource, GuardianInfo[] object) {
        super(context, resource, object);
        this.context = context;
        this.guardianList = object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.listview_characters_content, parent, false);

        GuardianInfo guardian = guardianList[position];

        TextView textView_Class = (TextView) convertView.findViewById(R.id.listView_CLASS);
        TextView textView_Level = (TextView) convertView.findViewById(R.id.listView_LEVEL);
        TextView textView_Light = (TextView) convertView.findViewById(R.id.listView_LIGHT);

        textView_Class.setText(GuardianClass.name(guardian.getCharacterBase().getClassType()));
        textView_Level.setText(String.valueOf(guardian.getCharacterLevel()));
        textView_Light.setText(String.valueOf(guardian.getCharacterBase().getLightLevel()));

        ImageView imageView_IMG = (ImageView) convertView.findViewById(R.id.listView_IMG);
        ImageView imageView_BG = (ImageView) convertView.findViewById(R.id.listView_BG);

        Picasso.with(context).load(url + guardian.getEmblemPath()).into(imageView_IMG);
        Picasso.with(context).load(url + guardian.getBackgroundPath()).into(imageView_BG);

        return convertView;

    }

}
