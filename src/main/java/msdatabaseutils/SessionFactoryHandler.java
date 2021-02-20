/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msdatabaseutils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * @author MSSM
 */
public abstract class SessionFactoryHandler {

    private String hibernateMappingFilePath = null;
    private SessionFactory sessionFactory = null;
    private Configuration configuration = null;
    private BackupsManager backupsManager = null;
    @SuppressWarnings("unused")
    private String applicationName;
    protected boolean isModernDriver;

    public SessionFactoryHandler(String applicationName, String hibernateMappingFilePath)
            throws HibernateException, MalformedURLException {
        this.hibernateMappingFilePath = hibernateMappingFilePath;
        this.applicationName = applicationName;

        this.configuration = (new Configuration()
                .configure(Paths.get(hibernateMappingFilePath).toAbsolutePath().toUri().toURL().toString()));
        this.sessionFactory = this.configuration.buildSessionFactory();
        this.isModernDriver = this.configuration.getProperty("hibernate.connection.driver_class")
                .equals("com.mysql.cj.jdbc.Driver");

        this.backupsManager = new BackupsManager(this, "Sauvegardes/");
        this.backupsManager.initiateBackup();
    }

    public SessionFactoryHandler(String applicationName) throws HibernateException, MalformedURLException {
        this(applicationName, "config/hibernate.cfg.xml");
    }

    public void constructDatabase() {
        if (this.isCheckIfFirstCreation() && this.isFirstCreaton()) {
            this.onFirstCreationCallback();
        }
    }

    public synchronized void insertArray(Object... entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.save(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public <T> T findById(Class<T> entityClass, int id) {
        try (Session session = getSessionFactory().openSession()) {
            T entity = session.find(entityClass, id);

            return entity;
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return null;
    }

    public synchronized void insert(Collection<? extends Object> entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.save(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized void insertOrUpdate(Collection<? extends Object> entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.saveOrUpdate(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized void insertOrUpdate(Object... entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.saveOrUpdate(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized void updateArray(Object... entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.update(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized void update(Collection<? extends Object> entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.update(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized void removeArray(Object... entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.delete(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized void remove(Collection<? extends Object> entities) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            for (Object mssmEntity : entities)
                if (mssmEntity != null)
                    session.delete(mssmEntity);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }
    }

    public synchronized List<Object> select(String stringQuery, Object... params) {
        List<Object> result = null;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createQuery(stringQuery);

            if (params != null)
                for (int i = 0; i < params.length; i++)
                    if (params[i] != null)
                        query.setParameter(i, params[i]);

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return result;
    }

    public synchronized List<Object> select(String stringQuery, List<Object> params) {
        List<Object> result = null;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createQuery(stringQuery);

            if (params != null)
                for (int i = 0; i < params.size(); i++) {
                    Object param = params.get(i);

                    if (params.get(i) != null)
                        query.setParameter(i, param);
                }

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return result;
    }

    public synchronized List<Object> select(String stringQuery, Map<String, Object> params) {
        List<Object> result = null;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createQuery(stringQuery);

            Set<Entry<String, Object>> entries = params.entrySet();

            if (params != null)
                for (Entry<String, Object> entry : entries)
                    if (entry.getValue() != null)
                        query.setParameter(entry.getKey(), entry.getValue());

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return result;
    }

    public synchronized List<Object> selectWithLimit(String stringQuery, int firstIndex, int offset, Object... params) {
        List<Object> result = null;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createQuery(stringQuery);

            if (params != null)
                for (int i = 0; i < params.length; i++)
                    if (params[i] != null)
                        query.setParameter(i, params[i]);

            query.setMaxResults(offset);
            query.setFirstResult(firstIndex);

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return result;
    }

    public synchronized List<Object> selectWithLimit(String stringQuery, int firstIndex, int offset,
                                                     List<Object> params) {
        List<Object> result = null;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createQuery(stringQuery);

            if (params != null)
                for (int i = 0; i < params.size(); i++)
                    if (params.get(i) != null)
                        query.setParameter(i, params.get(i));

            query.setMaxResults(offset);
            query.setFirstResult(firstIndex);

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return result;
    }

    public synchronized List<Object> selectUsingSQL(String stringQuery) {
        List<Object> result = null;

        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createNativeQuery(stringQuery);

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return result;
    }

    public synchronized int executeHql(String stringQuery, Object... params) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createQuery(stringQuery);

            if (params != null)
                for (int i = 0; i < params.length; i++)
                    if (params[i] != null)
                        query.setParameter(i, params[i]);

            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return -1;
    }

    public synchronized int executeSql(String stringQuery) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Query<Object> query = session.createNativeQuery(stringQuery);

            return query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return -1;
    }

    public synchronized Blob createBlob(byte[] inputStream) {
        try (Session session = getSessionFactory().openSession()) {
            return Hibernate.getLobCreator(session).createBlob(inputStream);
        } catch (Exception e) {
            e.printStackTrace();

            this.onUnableToConnect(e);
        }

        return null;
    }

    public synchronized Blob createBlob(File file) throws IOException {
        return this.createBlob(Files.readAllBytes(file.toPath()));
    }

    public String getHibernateMappingFilePath() {
        return hibernateMappingFilePath;
    }

    public void setHibernateMappingFilePath(String hibernateMappingFilePath) {
        this.hibernateMappingFilePath = hibernateMappingFilePath;
    }

    public synchronized SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public synchronized void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String getDatabaseName() {
        String connectionUrl = this.configuration.getProperty("connection.url");

        int interrogationMark = connectionUrl.indexOf('?');
        int firstSlashBeforeInterrogationMark = -1;

        for (int i = interrogationMark; i > 0; i--)
            if (connectionUrl.charAt(i) == '/') {
                firstSlashBeforeInterrogationMark = i;
                i = -1;
            }

        return connectionUrl.substring(firstSlashBeforeInterrogationMark + 1, interrogationMark);
    }

    public String getDatabaseUsername() {
        return this.configuration.getProperty("hibernate.connection.username");
    }

    public String getDatabasePassword() {
        return this.configuration.getProperty("hibernate.connection.password");
    }

    public boolean isFirstCreaton() {
        return (Long) this.select(this.getFirstCreationCountQuery()).get(0) == 0;
    }

    public BackupsManager getBackupsManager() {
        return backupsManager;
    }

    public void setBackupsManager(BackupsManager backupsManager) {
        this.backupsManager = backupsManager;
    }

    public void setSauvegardesPath(String path) {
        this.backupsManager.setFolderPath(path);
    }

    public void setMysqlFolder(String mysqlFolder) {
        this.backupsManager.setMysqlFolder(mysqlFolder);
    }

    public abstract String getFirstCreationCountQuery();

    public abstract void onFirstCreationCallback();

    public abstract boolean isCheckIfFirstCreation();

    private void onUnableToConnect(Exception e) {
        e.printStackTrace();
    }
}
