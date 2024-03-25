package com.example.haiterog.domain;

import java.util.ArrayList;

public class ComandaConverter implements EntityConverter<Comanda>
{

    @Override
    public String toString(Comanda o)
    {
        int nr = 0;
        String out = String.valueOf(o.getId());
        out = out.concat(";");
        for(Tort t : o.getTorturi())
        {
            nr++;
            out = out.concat(String.valueOf(t.getId()));
            out = out.concat(",");
            out = out.concat(t.getTip());
            if(nr < o.getTorturi().size())
            {
                out = out.concat(",");
            }
        }
        out = out.concat(";");
        out = out.concat(o.getData());
        return out;
    }

    @Override
    public Comanda fromString(String line)
    {
        String[] tokens = line.split(";");
        ArrayList<Tort> lista = new ArrayList<Tort>();
        String[] tokeni = tokens[1].split(",");
        int i = 0;
        while(i < tokeni.length)
        {
            Tort t = new Tort(Integer.parseInt(tokeni[i]),tokeni[i+1]);
            lista.add(t);
            i = i + 2;
        }
        return new Comanda(Integer.parseInt(tokens[0]), lista, tokens[2]);
    }
}
