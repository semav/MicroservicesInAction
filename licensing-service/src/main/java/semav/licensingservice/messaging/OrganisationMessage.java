package semav.licensingservice.messaging;

public class OrganisationMessage{
    public enum Type{
        GetAll,
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

    @Override
    public String toString() {
        return "OrganisationMessage{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
