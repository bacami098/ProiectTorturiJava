package com.example.haiterog.repository;

import com.example.haiterog.domain.Entity;

import java.io.*;
import java.util.Collection;
import java.util.List;

public class BinaryFileRepository<T extends Entity> extends MemoryRepository<T>
{
    private String fileName;

    public BinaryFileRepository(String fileName)
    {
        this.fileName = fileName;
        try
        {
            loadFile();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void loadFile() throws IOException,ClassNotFoundException
    {
        ObjectInputStream ois = null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream((this.fileName)));
            this.data = (List<T>) ois.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Repo starting a new file");
        }
        finally
        {
            if(ois !=null)
            {
                ois.close();
            }
        }
    }

    @Override
    public void add(T o) throws RepositoryException {
        super.add(o);
        try
        {
            saveFile();
        } catch (IOException e)
        {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        super.remove(id);
        try
        {
            saveFile();
        } catch (IOException e)
        {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public void update(int id, T o) throws RepositoryException {
        super.update(id, o);
        try
        {
            saveFile();
        } catch (IOException e)
        {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public Collection<T> getAll() {
        return super.getAll();
    }

    @Override
    public T find(int id) {
        return super.find(id);
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    @Override
    public T getById(int id) throws RepositoryException {
        return super.getById(id);
    }

    private void saveFile() throws IOException
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.fileName)))
        {
            oos.writeObject(data);
        }
    }
}
