package dao;

import bean.TbProdutos;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class ProdutosDAO extends GenericDAO<TbProdutos> {

    public ProdutosDAO() {
        super(TbProdutos.class);
    }

    public List listNome(String nome) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List lista = null;

        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(TbProdutos.class);
            c.add(Restrictions.ilike("nmProduto", nome, MatchMode.ANYWHERE));
            lista = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    public List listValor(double valor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List lista = null;

        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(TbProdutos.class);
            c.add(Restrictions.gt("preco", valor));
            lista = c.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    public List listNomeValor(String nome, double valor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List lista = null;

        try {
            tx = session.beginTransaction();
            Criteria c = session.createCriteria(TbProdutos.class);
            c.add(Restrictions.ilike("nmProduto", nome, MatchMode.ANYWHERE));
            c.add(Restrictions.gt("preco", valor));
            lista = c.list();
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
