package com.example.haiterog.repository;

import com.example.haiterog.domain.Tort;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepoTortDB extends MemoryRepository<Tort>  {

    private Connection conn = null;
    private final String JDBC_URL;

    public RepoTortDB(String dbLocation){
        this.JDBC_URL = "jdbc:sqlite:" + dbLocation;
        openConnection();
        createSchema();
        loadData();

    }

    private void loadData() {
        try
        {
            try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM Torturi");ResultSet rs =statement.executeQuery();)
            {
                while (rs.next())
                {
                    Tort t = new Tort(rs.getInt("id"),rs.getString("tip"));
                    this.data.add(t);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Torturi(id int, tip varchar(35));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    @Override
    public void add(Tort o) throws RepositoryException {
        try {
            super.add(o);
        } catch (RepositoryException e) {
            throw new DuplicateRepository("Exista deja un element cu acest id!");
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Torturi VALUES (?, ?)")) {
                statement.setInt(1, o.getId());
                statement.setString(2, o.getTip());
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
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Torturi WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Tort o) throws RepositoryException {
        try {
            super.update(id, o);
        } catch (RepositoryException e) {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updateTort = conn.prepareStatement("UPDATE Torturi SET tip = ? WHERE id = ?")){
                updateTort.setString(2, o.getTip());
                updateTort.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Tort> getAll() {
        ArrayList<Tort> torturi = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Torturi"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Tort t = new Tort(rs.getInt("id"),rs.getString("tip"));
                    torturi.add(t);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return torturi;
    }
}