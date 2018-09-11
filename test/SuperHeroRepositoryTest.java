import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SuperHeroRepositoryTest {
    @Rule
    public final SessionFactoryRule sf = new SessionFactoryRule();
    @Test
    public void returnsHerosWithMatchingType() {
        Session session = sf.getSession();
        SuperPowerType powerType = createSuperPowerType();
        session.save(powerType);
        SuperPower superpower = createSuperPower(powerType);
        session.save(superpower);
        SuperHero hero = createSuperHero(superpower);
        session.save(hero);
        sf.commit();
        SuperHeroRepository heroRepository = new SuperHeroRepository(session);
        List<SuperHero> heroes = heroRepository.loadBy(superpower);
        assertNotNull(heroes);
        assertEquals(1, heroes.size());
    }

    private SuperHero createSuperHero(SuperPower superpower) {
        SuperHero hero = new SuperHero();
        hero.name = "Name";
        hero.power = superpower;
        hero.weakness = "None";
        hero.secretIdentity = "Mr. Jones";
        return hero;
    }
    private SuperPower createSuperPower(SuperPowerType powerType) {
        SuperPower superpower = new SuperPower();
        superpower.name = "SuperPower";
        superpower.description = "Description";
        superpower.type = powerType;
        return superpower;
    }
    private SuperPowerType createSuperPowerType() {
        SuperPowerType powerType = new SuperPowerType();
        powerType.name = "TheType";
        powerType.description = "12345678901234567890aDescription";
        return powerType;
    }

}
