package com.humbertojpt.moteling;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Hermosa Programación el 18/01/2015.
 */
public class Motel {
    private String name;
   // private List<Integer[]> addresses;

    public Motel(String name, String addresses) {
        this.name = name;
    //    this.addresses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public List<Integer[]> getAddresses(){
        return addresses;
    }

    public void setAddresses(final Integer[] values)
    {
        addresses.add(values);
    }
*/
}
