package com.example.haiterog.domain;

import java.io.Serializable;

public class Tort extends Entity implements Serializable
{
    private String tip;

    public Tort(int id,String tip)
    {
        super(id);
        this.tip = tip;
    }

    public String getTip()
    {
        return this.tip;
    }

    public String toString()
    {
        return "Tort:{id = "+ getId() + ", tip = "+ tip +"}";
    }
}
