package com.example.haiterog.domain;

public class LunaTorturi
{
    private int luna;
    private int nr_tort;

    public LunaTorturi(int luna, int nr_tort)
    {
        this.luna = luna;
        this.nr_tort = nr_tort;
    }

    public int getLuna() {
        return luna;
    }

    public int getNr_tort() {
        return nr_tort;
    }

    public void setLuna(int luna) {
        this.luna = luna;
    }

    public void setNr_tort(int nr_tort) {
        this.nr_tort = nr_tort;
    }

    public String toString()
    {
        return "LunaTort:{luna = "+this.luna + ", nr_torturi_comandate = "+this.nr_tort + "}";
    }
}
