package dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import dao.HibernateUtil; // ajuste o nome do seu utilitário de sessão se for diferente

/**
 * DAO genérico para qualquer entidade do projeto.
 * 
 * Exemplo de uso:
 *   GenericDAO<Usuarios> dao = new GenericDAO<>(Usuarios.class);
 *   dao.insert(new Usuarios(...));
 *   List<Usuarios> lista = dao.listAll();
 */
public class GenericDAO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Class<T> classe;

    public GenericDAO(Class<T> classe) {
        this.classe = classe;
    }

    public void insert(T entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entidade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(T entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entidade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(T entidade) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entidade);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public T findById(int id, String nomeCampoId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        T resultado = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(classe);
            criteria.add(Restrictions.eq(nomeCampoId, id));
            resultado = (T) criteria.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultado;
    }

    public List<T> listAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<T> lista = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(classe);
            lista = criteria.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }
}
