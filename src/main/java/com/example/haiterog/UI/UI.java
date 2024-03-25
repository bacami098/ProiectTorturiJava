package com.example.haiterog.UI;

import com.example.haiterog.domain.Comanda;
import com.example.haiterog.domain.LunaTorturi;
import com.example.haiterog.domain.Tort;
import com.example.haiterog.repository.DuplicateRepository;
import com.example.haiterog.repository.RepositoryException;
import com.example.haiterog.service.ServiceComanda;
import com.example.haiterog.service.ServiceTort;

import java.util.ArrayList;
import java.util.Scanner;

public class UI
{
    private ServiceTort serviceT = new ServiceTort();
    private ServiceComanda serviceC = new ServiceComanda();

    public void addTort()
    {
        try
        {
            int id;
            String tip;
            System.out.println("Id tort:");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            System.out.println("Tip tort:");
            tip = cin.next();
            this.serviceT.addTort(id,tip);
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void afisareTort()
    {
        try
        {
            for (Tort t : this.serviceT.getAllT())
            {
                System.out.println(t);
            }
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void afisareComanda()
    {
        try
        {
            for (Comanda c: this.serviceC.getAllC())
            {
                System.out.println(c);
            }
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void addComanda()
    {
        try
        {
            int idC,idT,nr;
            ArrayList<Tort> lista = new ArrayList<Tort>();
            String data;
            Scanner cin = new Scanner(System.in);
            System.out.println("ID comanda:");
            idC = cin.nextInt();
            System.out.println("Data comenzii:");
            data = cin.next();
            System.out.println("Nr de torturi pt aceasta comanda:");
            nr = cin.nextInt();
            for(int i=0;i<nr;i++)
            {
                System.out.println("Torturi disponibile:");
                afisareTort();
                System.out.println("ID tort ales:");
                idT = cin.nextInt();
                Tort t = this.serviceT.getTort(idT);
                lista.add(t);
            }
            this.serviceC.addComanda(idC,lista,data);
            System.out.println("Comanda a fost adaugata");
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeComanda()
    {
        try
        {
            int id;
            System.out.println("Id comanda:");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            this.serviceC.removeComanda(id);
            System.out.println("Comanda a fost stearsa");
        }
        catch (RepositoryException e)
        {
            System.out.println();
        }
    }

    public void removeTort()
    {
        try
        {
            int id;
            System.out.println("Id tort:");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            this.serviceT.removeTort(id);
            System.out.println("Tortul a fost sters");
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateTort()
    {
        try
        {
            int id1,id2;
            String tip;
            System.out.println("Id tort de modificat:");
            Scanner cin = new Scanner(System.in);
            id1 = cin.nextInt();
            System.out.println("Id tort nou:");
            id2 = cin.nextInt();
            System.out.println("Tip tort nou:");
            tip = cin.next();
            this.serviceT.updateTort(id1,id2,tip);
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateComanda()
    {
        try
        {
            int id1,id2,nr,idT;
            ArrayList<Tort> lista = new ArrayList<Tort>();
            String data;
            System.out.println("Id comanda de modificat:");
            Scanner cin = new Scanner(System.in);
            id1 = cin.nextInt();
            System.out.println("ID comanda noua");
            id2 = cin.nextInt();
            System.out.println("Data comenzii:");
            data = cin.next();
            System.out.println("Nr de torturi pt aceasta comanda");
            nr = cin.nextInt();
            for(int i=0;i<nr;i++)
            {
                System.out.println("Torturi disponibile:");
                afisareTort();
                System.out.println("ID tort de comandat");
                idT = cin.nextInt();
                Tort t = this.serviceT.getTort(idT);
                lista.add(t);
            }
            serviceC.updateComanda(id1,id2,lista,data);
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void addStartT()
    {
        try
        {
            this.serviceT.addTort(1,"Ciocolata");
            this.serviceT.addTort(2,"Banane");
            this.serviceT.addTort(3,"Fistic");
            this.serviceT.addTort(4,"Vegan");
            this.serviceT.addTort(5,"Capsuni");
            System.out.println("Cele 5 torturi au fost adaugate");
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void generate100Tort() {
        int nr;
        String[] tipuri = new String[]{"Tort cu portocale si migdale", "Tort cu praline si caramel sarat", "Red Velvet", "Mousse de ciocolata", "Tort cu fructe proaspete"
                , "Tort cu caramel și nuca" , "Tort opera" , "Tort cu lamaie si merisoare" , "Tort tiramisu" , "Tort cu cafea si nuci" ,
                "Tort cu capsuni si crema de vanilie" , "Tort cu iaurt si fructe de padure" , "Tort cu ciocolata alba si zmeura", "Tort cu menta si ciocolata neagra"};
        try {
            for (int i = 6; i <= 106; i++) {
                nr = ((int) (Math.random() * 10000)) % 14;
                String tipul = tipuri[nr];
                serviceT.addTort(i, tipul);
            }

        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
    }

    public void generate100Comanda() {
        try {
            int id = serviceC.getAllC().size() + 1;
            int idTort = serviceT.getAllT().size();
            String[] zi = new String[]{"1", "2","3", "4","5", "6","7", "8","9", "10","11", "12","13", "14","15", "16",
                    "17", "18","19", "20","21", "22","23", "24","25", "26","27", "28","29", "30"};
            String[] luna = new String[]{"1", "2","3", "4","5", "6","7", "8","9", "10","11", "12"};
            for (int i = 1; i <= 100; i++)
            {
                int nrMax = ((int) (Math.random() * 10000)) % 3 + 1;
                ArrayList<Tort> lista = new ArrayList<>();
                for (int j = 0; j < nrMax; j++) {
                    int idT = ((int) (Math.random() * 10000)) % idTort + 1;
                    lista.add(serviceT.getTort(idT));
                }
                int i1 = ((int) (Math.random() * 10000)) % 30;
                int i2 = ((int) (Math.random() * 10000)) % 12;
                String data = zi[i1] + "." + luna[i2] + "." + "2023";
                serviceC.addComanda(id, lista,data);
                id++;
            }
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortLunaTort()
    {
        for(LunaTorturi lt : this.serviceC.lunitort() )
        {
            System.out.println(lt);
        }
    }

    public void optiuni()
    {
        System.out.println("1.Adauga tort");
        System.out.println("2.Sterge tort");
        System.out.println("3.Modifica tort");
        System.out.println("4.Adauga 5 torturi");
        System.out.println("5.Afiseaza torturi");
        System.out.println("6.Adauga comanda");
        System.out.println("7.Sterge comanda");
        System.out.println("8.Modifica comanda");
        System.out.println("9.Afiseaza comenzi");
        System.out.println("10.Genereaza 100 torturi");
        System.out.println("11.Genereaza 100 comenzi");
        System.out.println("12.Sortare");
        System.out.println("0.Inchide program");
    }

    public void menu()
    {
        boolean ok = true;
        while (ok)
        {
            optiuni();
            int opt;
            Scanner cin = new Scanner(System.in);
            System.out.println("Optiunea ta:");
            opt = cin.nextInt();
            switch (opt)
            {
                case 1:
                    addTort();
                    break;
                case 2:
                    removeTort();
                    break;
                case 3:
                    updateTort();
                    break;
                case 4:
                    addStartT();
                    break;
                case 5:
                    afisareTort();
                    break;
                case 6:
                    addComanda();
                    break;
                case 7:
                    removeComanda();
                    break;
                case 8:
                    updateComanda();
                    break;
                case 9:
                    afisareComanda();
                    break;
                case 10:
                    generate100Tort();
                    break;
                case 11:
                    generate100Comanda();
                    break;
                case 12:
                    sortLunaTort();
                    break;
                case 0:
                    ok = false;
                    break;
            }
        }
    }
}
