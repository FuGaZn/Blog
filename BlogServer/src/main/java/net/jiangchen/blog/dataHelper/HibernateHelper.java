package net.jiangchen.blog.dataHelper;

import net.jiangchen.blog.util.ResultMessage;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 16/11/12.<br>
 * datahelper which use hibernate frame<br>
 * Pass test on 16/11/18<br>
 * Done on 16/11/18
 */
public class HibernateHelper<T> implements DataHelper<T> {

    private SessionFactory sessionFactory;
    private Session session;
    private Class<T> type;

    //使用默认构造类，则sessionfactory不会被初始化！！！！！报错NUllPointer!

    public HibernateHelper() {

    }

    public HibernateHelper(Class<T> type) {
        this.type = type;
        Configuration configuration = new Configuration();
        sessionFactory = configuration.configure().buildSessionFactory();
    }


    /**
     * 初始化Session
     */
    private void setUpSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    /**
     * 提交事务及关闭session
     */
    private void commitAndClose() {
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public ResultMessage save(Object o) {
        try {
            setUpSession();
            session.save(type.getName(), o);
            commitAndClose();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return ResultMessage.EXIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }


    @Override
    public ResultMessage update(Object o) {
        //如果传进来的o不是通过new初始化的，则不会成功修改
        try {
            setUpSession();

            session.update(type.getName(), o);

            commitAndClose();
        } catch (OptimisticLockException e) {
            e.printStackTrace();
            return ResultMessage.NOT_EXIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }


    @Override
    public ResultMessage delete(String key, Object ID) {
        try {
            Object o = exactlyQuery(key, ID);
            if (o == null) {
                return ResultMessage.NOT_EXIST;
            }
            setUpSession();
            session.delete(type.getName(), o);
            commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }


    @Override
    public T exactlyQuery(String field, Object value) {
        System.out.println("I  arraive");
        ArrayList<T> array = fullMatchQuery(field, value);
        return array.size() == 0 ? null : fullMatchQuery(field, value).get(0);
    }


    @Override
    public ArrayList<T> fullMatchQuery(String field, Object value) {
        try {
            Criteria criteria = SetUpCriteria();

            criteria.add(Restrictions.eq(field, value));
            ArrayList<T> result = (ArrayList<T>) criteria.list();
            session.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return new ArrayList<T>();
        }
    }


    @Override
    public ArrayList<T> prefixMatchQuery(String field, String value) {
        value = value + "%";
        return likePatternQuery(field, value);
    }


    @Override
    public ArrayList<T> suffixMatchQuery(String field, String value) {
        value = "%" + value;
        return likePatternQuery(field, value);
    }


    @Override
    public ArrayList<T> fuzzyMatchQuery(String field, String value) {
        value = "%" + value + "%";
        return likePatternQuery(field, value);
    }


    @Override
    public ArrayList<T> rangeQuery(String field, Object min, Object max) {
        try {
            Criteria criteria = SetUpCriteria();
            criteria.add(Restrictions.between(field, min, max));
            ArrayList<T> arrayList = (ArrayList<T>) criteria.list();
            session.close();
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return new ArrayList<T>();
        }
    }

    /**
     * 生成一个新的匹配标准
     *
     * @return 新的匹配标准
     * @throws ClassNotFoundException 如果没有指定type，则抛出该异常
     */
    private Criteria SetUpCriteria() throws ClassNotFoundException {
        setUpSession();
        return session.createCriteria(type);
    }

    /**
     * 利用模糊查找返回符合条件的PO列表
     *
     * @param field 查询域
     * @param value 域值
     * @return PO列表
     */
    private ArrayList<T> likePatternQuery(String field, String value) {
        try {
            Criteria criteria = SetUpCriteria();
            criteria.add(Restrictions.like(field, value));
            ArrayList<T> arrayList = (ArrayList<T>) criteria.list();
            session.close();
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return new ArrayList<T>();
        }
    }


    @Override
    public void clear(String table){
        setUpSession();
        String sql = "truncate table "+table;

        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        commitAndClose();
    }
}
