import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class SuperHeroRepository{
    private Session session;

    public SuperHeroRepository(Session session) {
        this.session = session;
    }

    public List<SuperHero> loadBy(SuperPower superpower) {
       return (List<SuperHero>) session.createQuery("from SuperHero where superPower = " + superpower.getType());
    }
}
