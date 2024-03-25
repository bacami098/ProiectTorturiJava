package com.example.haiterog.service;

import com.example.haiterog.domain.*;
import com.example.haiterog.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ServiceTort
{
    private IRepository<Tort> repoTort = new MemoryRepository<Tort>();

    private EntityConverter<Tort> ecTort = new TortConverter();

    SettingsTort setari = SettingsTort.getInstance();

    public ServiceTort() {
        if (Objects.equals(this.setari.getRepoType(), "memory"))
        {
            this.repoTort = new MemoryRepository<Tort>();
        }
        if (Objects.equals(setari.getRepoType(), "text"))
        {
            this.repoTort = new TextFileRepository<>(this.setari.getRepoFile(), ecTort);
        }
        if(Objects.equals(this.setari.getRepoType(),"binary"))
        {
            this.repoTort = new BinaryFileRepository<Tort>(this.setari.getRepoFile());
        }
        if(Objects.equals(setari.getRepoType(),"db")){
            this.repoTort = new RepoTortDB(setari.getRepoFile());
        }
    }

    public void addTort(int id , String tip) throws RepositoryException
    {
        Tort t= new Tort(id,tip);
        repoTort.add(t);
    }

    public void removeTort(int id) throws RepositoryException
    {
        repoTort.remove(id);
    }

    public void updateTort(int id1,int id2 , String tip) throws RepositoryException
    {
        Tort t = new Tort(id2,tip);
        repoTort.update(id1,t);
    }

    public Collection<Tort> getAllT() throws RepositoryException
    {
        return repoTort.getAll();
    }

    public Tort getTort(int id) throws RepositoryException
    {
        return this.repoTort.getById(id);
    }
}
