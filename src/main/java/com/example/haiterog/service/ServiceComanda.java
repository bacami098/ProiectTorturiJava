package com.example.haiterog.service;

import com.example.haiterog.domain.*;
import com.example.haiterog.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class ServiceComanda
{
    private IRepository<Comanda> repoComanda = new MemoryRepository<Comanda>();

    private EntityConverter<Comanda> ecComanda = new ComandaConverter();

    SettingsComanda setari = SettingsComanda.getInstance();

    public ServiceComanda()
    {
        if (Objects.equals(setari.getRepoType(), "memory"))
        {
            this.repoComanda = new MemoryRepository<Comanda>();
        }
        if (Objects.equals(setari.getRepoType(), "text"))
        {
            this.repoComanda = new TextFileRepository<>(setari.getRepoFile(), ecComanda);
        }
        if(Objects.equals(setari.getRepoType(),"binary"))
        {
            this.repoComanda = new BinaryFileRepository<Comanda>(setari.getRepoFile());
        }
        if(Objects.equals(setari.getRepoType(),"db")){
            this.repoComanda = new RepoComandaDB(setari.getRepoFile());
        }
    }

    public void addComanda(int id , ArrayList<Tort> torturi, String data) throws RepositoryException
    {
        Comanda c= new Comanda(id,torturi,data);
        repoComanda.add(c);
    }

    public void removeComanda(int id) throws RepositoryException
    {
        repoComanda.remove(id);
    }

    public void updateComanda(int id1,int id2 , ArrayList<Tort> torturi, String data) throws RepositoryException
    {
        Comanda c = new Comanda(id2,torturi,data);
        repoComanda.update(id1,c);
    }

    public Collection<Comanda> getAllC() throws RepositoryException
    {
        return repoComanda.getAll();
    }

    public ArrayList<LunaTorturi> lunitort(){
        int[] luna = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        ArrayList<LunaTorturi> list = new ArrayList<>();
        for(int l : luna)
        {
            int nr = 0;
            for(Comanda c : this.repoComanda.getAll())
            {
                String s = c.getData();
                String[] tokens = s.split("\\.");
                if(Objects.equals(l,Integer.valueOf(tokens[1])))
                {
                    for(Tort t : c.getTorturi())
                    {
                        nr++;
                    }
                }
            }
            list.add(new LunaTorturi(l,nr));
        }
        list.sort(Comparator.comparingInt(LunaTorturi::getNr_tort).reversed());
        return list;
    }
}
