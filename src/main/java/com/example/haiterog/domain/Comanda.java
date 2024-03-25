package com.example.haiterog.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Comanda extends Entity implements Serializable
{
    private ArrayList<Tort> torturi;
    private String data;

    public Comanda(int id,ArrayList<Tort> torturi,String data)
    {
        super(id);
        this.data = data;
        this.torturi = torturi;
    }

    public ArrayList<Tort> getTorturi()
    {
        return this.torturi;
    }

    public String getData()
    {
        return data;
    }

    public String toString()
    {
        return "Comanda:{id = "+this.getId() + ", torturi = "+this.torturi + ", data = "+ this.data + "}";
    }
}
