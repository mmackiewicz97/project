package org.book.dao;

import org.book.entity.Autor;
import org.book.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDAOImpl implements CategoryDAO{

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Category> getCategories() {
        Session currentSession = sessionFactory.getCurrentSession();
        //zapytanie
        Query<Category> query = currentSession.createQuery("FROM Category", Category.class);
        List<Category> categories = query.getResultList();
        return categories ;
    }

    @Override
    public void saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public Category getCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, categoryId);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getCategory(categoryId));
    }
}
