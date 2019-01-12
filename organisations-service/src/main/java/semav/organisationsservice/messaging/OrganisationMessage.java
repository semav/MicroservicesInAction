package semav.organisationsservice.messaging;

public class OrganisationMessage{
    public enum Type{
        Delete
    }

    private Integer id;
    private Type type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
