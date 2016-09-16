package com.humbertojpt.moteling;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Hermosa Programación.
 */
public class JsonMotelParser {


    public List<Motel> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayMoteles(reader);
        } finally {
            reader.close();
        }

    }



    public List<Motel> leerArrayMoteles(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList<Motel> motel = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            motel.add(leerMotel(reader));
        }
        reader.endArray();
        return motel;
    }

    public Motel leerMotel(JsonReader reader) throws IOException {
        // Variables locales
        String motel = null;
        String direccion = null;

        // Iniciar objeto
        reader.beginObject();

        /*
        Lectura de cada atributo
         */
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "name":
                    motel = reader.nextString();

                    break;
                case "addresses":
                    direccion = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Motel(motel,direccion);
    }

}

