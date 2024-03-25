package com.example.haiterog.Teste;

import com.example.haiterog.domain.Tort;
import org.junit.Test;
import com.example.haiterog.repository.RepositoryException;
import com.example.haiterog.service.ServiceTort;
import com.example.haiterog.service.ServiceComanda;

import java.util.ArrayList;

public class TesteService {
    @Test
    public void serviceTort()
    {
        ServiceTort serviceTort = new ServiceTort();
        boolean thrown = false;
        try
        {
            serviceTort.addTort(120, "Ciocolata");
            assert serviceTort.getAllT().size() == 107;
        }
        catch (RepositoryException e)
        {
            thrown= true;
        }
        assert thrown == false;
        try
        {
            serviceTort.removeTort(120);
            assert serviceTort.getAllT().size() == 106;
        }
        catch (RepositoryException e)
        {
            thrown = true;
        }
        assert thrown == false;
        try
        {
            serviceTort.removeTort(120);
        }
        catch (RepositoryException e)
        {
            thrown = true;
        }
        assert thrown ==true;
    }

    @Test
    public void serviceComanda()
    {
        ServiceComanda serviceComanda = new ServiceComanda();
        try
        {
            assert serviceComanda.getAllC().size()==100;
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        ArrayList<Tort> lista_tort = new ArrayList<>();
        Tort t1 = new Tort(4, "Vegan");
        Tort t = new Tort(5, "Capsuni");
        lista_tort.add(t1);
        lista_tort.add(t);
        try
        {
            serviceComanda.addComanda(101,lista_tort,"10.10.2023");
            assert serviceComanda.getAllC().size()==101;
        }
        catch (RepositoryException e)
        {
            assert false;
        }
        try
        {
            serviceComanda.removeComanda(101);
            assert serviceComanda.getAllC().size()== 100;
        }
        catch (RepositoryException e)
        {
            assert false;
        }
    }
}

