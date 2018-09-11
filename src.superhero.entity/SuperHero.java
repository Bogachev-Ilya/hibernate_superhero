import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class SuperHero extends AbstractEntity implements Serializable {

    @NotEmpty
    @Column(unique = true)
    public String name;
    @ManyToOne
    @NotNull
    public SuperPower power;
    @NotEmpty
    public String weakness;
    @NotEmpty
    public String secretIdentity;

    public SuperHero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SuperPower getPower() {
        return power;
    }

    public void setPower(SuperPower power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHero superHero = (SuperHero) o;
        return Objects.equals(name, superHero.name) &&
                Objects.equals(power, superHero.power) &&
                Objects.equals(weakness, superHero.weakness) &&
                Objects.equals(secretIdentity, superHero.secretIdentity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, power, weakness, secretIdentity);
    }
}
