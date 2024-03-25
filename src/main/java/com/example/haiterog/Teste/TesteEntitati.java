package com.example.haiterog.Teste;

import com.example.haiterog.domain.Tort;
import org.junit.Test;

public class TesteEntitati
{
    @Test
    public void testTort(){
        Tort t = new Tort(1,"Ciocolata");
        assert t.getId() == 1;
        assert "Ciocolata".equals(t.getTip());
    }
}