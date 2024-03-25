package com.example.haiterog.repository;

import com.example.haiterog.domain.Comanda;
import com.example.haiterog.domain.Tort;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepoComandaDB extends MemoryRepository<Comanda>  {

    private Connection conn = null;
    private final String JDBC_URL;

    public RepoComandaDB(String dbLocation){
        this.JDBC_URL= "jdbc:sqlite:"+dbLocation;
        openConnection();
        createSchema();
        loadData();

    }

    private void loadData() {
        ArrayList<Comanda> comenzi = this.getAll();
        for(Comanda c : comenzi) {
            try {
                super.add(c);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Comenzi(id int,torturi varchar(100),data_c varchar(35));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    @Override
    public void add(Comanda o) throws RepositoryException {
        try {
            super.add(o);
        } catch (RepositoryException e) {
            throw new DuplicateRepository("Exista deja un element cu acest id!");
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Comenzi VALUES (?, ?,?)")) {
                statement.setInt(1, o.getId());
                String s="";
                ArrayList<Tort> torturi = o.getTorturi();
                for(Tort t:torturi){
                    s+=t.getId()+","+t.getTip()+";";
                }
                statement.setString(2, s);
                statement.setString(3, o.getData());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        try {
            super.remove(id);
        } catch (RepositoryException e) {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Comenzi WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Comanda o) throws RepositoryException {
        try {
            super.update(id, o);
        } catch (RepositoryException e) {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updateComanda = conn.prepareStatement("UPDATE Comenzi SET torturi = ?,data = ? WHERE id = ?")){
                String s="";
                ArrayList<Tort> torturi = o.getTorturi();
                for(Tort t:torturi){
                    s+=t.getId()+","+t.getTip()+";";
                }
                updateComanda.setString(2, s);
                updateComanda.setString(3, o.getData());
                updateComanda.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Comanda> getAll() {
        ArrayList<Comanda> comenzi = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Comenzi "); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    String s = rs.getString("torturi");
                    ArrayList<Tort> torturi = new ArrayList<>();
                    String[] tokens = s.split(";");
                    for(String t:tokens){
                        String[] tort = t.split(",");
                        Tort tort1 = new Tort(Integer.parseInt(tort[0]),tort[1]);
                        torturi.add(tort1);
                    }
                    Comanda c = new Comanda(rs.getInt("id"), torturi,rs.getString("data_c"));
                    comenzi.add(c);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comenzi;
    }
}