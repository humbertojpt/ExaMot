package com.humbertojpt.moteling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Creado por Hermosa Programación.
 */
public class AdaptadorDeMoteles extends ArrayAdapter<Motel> {

    public AdaptadorDeMoteles(Context context, List<Motel> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View v = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            v = inflater.inflate(
                    R.layout.item_lista,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView nombreMotel = (TextView)v.findViewById(R.id.nombreMotel);
        TextView direccMotel = (TextView)v.findViewById(R.id.direccionMotel);


        //Obteniendo instancia de la Tarea en la posición actual
        Motel item = getItem(position);

        nombreMotel.setText(item.getName());
        //direccMotel.setText((CharSequence) item.getAddresses());

        //Devolver al ListView la fila creada
        return v;

    }

    /**
     * Este método nos permite obtener el Id de un drawable a través
     * de su nombre
     * @param nombre  Nombre del drawable sin la extensión de la imagen
     *
     * @return Retorna un tipo int que representa el Id del recurso
     */
    private int convertirRutaEnId(String nombre){
        Context context = getContext();
        return context.getResources()
                .getIdentifier(nombre, "drawable", context.getPackageName());
    }
}