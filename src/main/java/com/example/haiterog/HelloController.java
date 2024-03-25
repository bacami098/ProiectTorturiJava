package com.example.haiterog;

import com.example.haiterog.domain.Comanda;
import com.example.haiterog.UI.UI;
import com.example.haiterog.domain.Tort;
import com.example.haiterog.repository.RepositoryException;
import com.example.haiterog.service.ServiceComanda;
import com.example.haiterog.service.ServiceTort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Collection;

public class HelloController {
    @FXML
    public ListView lstMessage;
    @FXML
    public Button afiseazaComenzi;
    @FXML
    public Button addTort;
    @FXML
    public TextField id_tort;
    @FXML
    public ListView cmdMessage;
    @FXML
    public TextField tip_tort;
    @FXML
    public TextField id_comanda;
    @FXML
    public TextField lista_torturi;
    @FXML
    public Button removeTort;
    @FXML
    public TextField id_tort_mod;
    @FXML
    public Button addComanda;
    @FXML
    public Button removeComanda;
    @FXML
    public Button updateComanda;
    @FXML
    public TextField id_comanda_mod;

    @FXML
    public TextField data_comanda;
    @FXML
    public Button genereazaTorturi;
    @FXML
    public Button genereazaComenzi;
    public Button updateTort;
    @FXML
    ObservableList<String> list = FXCollections.observableList(new ArrayList<>());

    @FXML
    ObservableList<String> cmdlist = FXCollections.observableList(new ArrayList<>());
    @FXML
    public Button afiseazaTorturi;

    ServiceTort serviceTort= new ServiceTort();
    ServiceComanda serviceComanda = new ServiceComanda();

    @FXML
    public void getAllTorturi(ActionEvent actionEvent) throws RepositoryException {
        list.clear();
        Collection<Tort> lista =serviceTort.getAllT();
        for(Tort t:lista) {
            list.add(t.getId()+" "+t.getTip());
        }
        lstMessage.setItems(list);
    }

    public void getAllComenzi(ActionEvent actionEvent) throws RepositoryException{
        cmdlist.clear();
        Collection<Comanda> lista = serviceComanda.getAllC();
        for (Comanda c : lista){
            String s="";
            ArrayList<Tort> torturi = c.getTorturi();
            for(Tort t:torturi){
                s+=t.getId()+","+t.getTip()+";";
            }
            cmdlist.add("id comanda:"+c.getId()+"   lista torturi: "+s);
        }
        cmdMessage.setItems(cmdlist);
    }

    public void addTort(ActionEvent actionEvent) {
        int id;
        String t;
        try{
            id = Integer.parseInt(id_tort.getText());
            t=tip_tort.getText();
            serviceTort.addTort(id,t);
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
    }

    public void removeTort(ActionEvent actionEvent) {
        int id;
        try{
            id = Integer.parseInt(id_tort_mod.getText());
            serviceTort.removeTort(id);
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
    }

    public void updateTort(ActionEvent actionEvent) {
        int id,id_m;
        String s;
        try {
            id=Integer.parseInt(id_tort.getText());
            id_m=Integer.parseInt(id_tort_mod.getText());
            s=tip_tort.getText();
            Tort t = serviceTort.getTort(id_m);
            serviceTort.updateTort(id_m,id,s);
        }
        catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();}
    }

    public void addComanda(ActionEvent actionEvent) {
        int id;
        String s;
        String d;
        try {
            id=Integer.parseInt(id_comanda.getText());
            s=lista_torturi.getText();
            d=data_comanda.getText();
            ArrayList<Tort> torturi = new ArrayList<>();
            String[] tokens = s.split(",");
            for(String t:tokens){
                int nr = Integer.parseInt(t);
                torturi.add(serviceTort.getTort(nr));
            }
            serviceComanda.addComanda(id,torturi,d);
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
    }

    public void removeComanda(ActionEvent actionEvent) {
        int id;
        try {
            id=Integer.parseInt(id_comanda_mod.getText());
            serviceComanda.removeComanda(id);
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }
    }

    public void updateComanda(ActionEvent actionEvent) {
        int id,id_m;
        String s;
        String d;
        try {
            id=Integer.parseInt(id_comanda.getText());
            id_m = Integer.parseInt(id_comanda_mod.getText());
            s=lista_torturi.getText();
            d=data_comanda.getText();
            ArrayList<Tort> torturi = new ArrayList<>();
            String[] tokens = s.split(",");
            for(String t:tokens){
                int nr = Integer.parseInt(t);
                torturi.add(serviceTort.getTort(nr));
            }
            serviceComanda.updateComanda(id_m,id,torturi,d);
        }catch (NumberFormatException nfe){
            Alert hello = new Alert(Alert.AlertType.ERROR,"Introduceti un numar valid");
            hello.show();
        }catch (RepositoryException e){
            Alert hello = new Alert(Alert.AlertType.ERROR,e.getMessage());
            hello.show();
        }

    }

    public void gen100T(ActionEvent actionEvent) {
        UI ui = new UI();
        ui.generate100Tort();
    }

    public void gen100C(ActionEvent actionEvent) {
        UI ui = new UI();
        ui.generate100Comanda();
    }
}