
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class SessionFactoryRule implements MethodRule {
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private Session session;


    @Override
    public Statement apply(Statement statement, FrameworkMethod frameworkMethod, Object test) {
        return new Statement(){
            @Override
            public void evaluate() throws Throwable {
                sessionFactory = createSessionFactory();
                createSession();
                beginTransaction();
                try {
                    statement.evaluate();
                } finally {
                    shutdown();
                }
            }
        };
    }

    public void commit() {
        transaction.commit();
    }
    public void beginTransaction() {
        transaction = session.beginTransaction();
    }
    public Session getSession() {
        return session;
    }

    private Session createSession() {
        session = sessionFactory.openSession();
        return session;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(SuperHero.class)
                .addAnnotatedClass(SuperPower.class)
                .addAnnotatedClass(SuperPowerType.class);
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    private void shutdown() {
        try{
            transaction.rollback();
            session.close();
            sessionFactory.close();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}
