package com.example.haiterog.domain;

public class TortConverter implements EntityConverter<Tort>
{
    @Override
    public String toString(Tort o)
    {
        return o.getId() + "," + o.getTip();
    }

    @Override
    public Tort fromString(String line)
    {
        String[] tokens = line.split(",");
        return new Tort(Integer.parseInt(tokens[0]), tokens[1]);
    }
}
