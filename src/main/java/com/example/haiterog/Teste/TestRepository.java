package com.example.haiterog.Teste;
import com.example.haiterog.domain.Comanda;
import com.example.haiterog.domain.EntityConverter;
import com.example.haiterog.domain.Tort;
import com.example.haiterog.domain.TortConverter;
import org.junit.Test;
import com.example.haiterog.repository.*;

import java.util.ArrayList;

public class TestRepository {
    @Test
    public void testMemoryRepo() {
        IRepository<Comanda> repoComanda = new MemoryRepository<Comanda>();
        ArrayList<Tort> lista_tort = new ArrayList<>();
        Tort t1 = new Tort(1, "Ciocolata");
        Tort t = new Tort(2, "Vanilie");
        lista_tort.add(t1);
        lista_tort.add(t);
        Comanda c = new Comanda(10, lista_tort,"10.10.2023");
        ArrayList<Tort> lista_tortn = new ArrayList<>();
        Tort t2 = new Tort(3, "Zmeura");
        lista_tortn.add(t2);
        try
        {
            repoComanda.add(c);
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        try
        {
            repoComanda.add(c);
        }
        catch (RepositoryException e)
        {
            assert true;
        }
        try
        {
            Comanda comanda = repoComanda.getById(10);
            assert comanda.getTorturi().size()==2;
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        try
        {
            Comanda c1 = new Comanda(11,lista_tortn,"11.12.2023");
            repoComanda.update(10,c1);
            assert repoComanda.getSize()==1;
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        try
        {
            repoComanda.remove(11);
            assert repoComanda.getSize() == 0;
        }
        catch (RepositoryException e)
        {
            assert false;
        }

    }

    @Test
    public void testFileRepo()
    {
        EntityConverter<Tort> ecTort = new TortConverter();
        IRepository<Tort> repoTort = new TextFileRepository<>("D:\\MAP\\haiterog\\src\\main\\java\\com\\example\\haiterog\\Teste\\testTort.txt",ecTort);
        assert repoTort.getSize()==2;
        try
        {
            Tort t1 = repoTort.getById(1);
            assert "Ciocolata".equals(t1.getTip());
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        try
        {
            repoTort.add(new Tort(3,"Zmeura"));
            assert repoTort.getSize()==3;
        }catch (RepositoryException e){
            assert false;
        }
        try
        {
            repoTort.update(3,new Tort(3,"Banane"));
            Tort t2 = repoTort.getById(3);
            assert "Banane".equals(t2.getTip());
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        try
        {
            repoTort.remove(3);
            assert repoTort.getSize()==2;
        }
        catch (RepositoryException e)
        {
            assert false;
        }
    }
}
//