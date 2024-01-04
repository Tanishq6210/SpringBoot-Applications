package demo.jpahibernateindepth.entity;

import jakarta.persistence.Embeddable;

@Embeddable //Check the package
public class Address {
    private String line1;
    private String line2;

    private String city;

    public Address() {
        super();
    }
    public Address(String line1, String line2, String city) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
    }
}
