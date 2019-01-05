package semav.organisationsservice.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    private License[] licenses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public License[] getLicenses() {
        return licenses;
    }

    public void setLicenses(License[] licenses) {
        this.licenses = licenses;
    }
}
