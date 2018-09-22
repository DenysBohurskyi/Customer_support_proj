package test.com.entity.data;

import java.util.Objects;

public class Service {

    private String id;
    private Variant variation;

    public Service(String id, Variant variation) {
        this.id = id;
        this.variation = variation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Variant getVariation() {
        return variation;
    }

    public void setVariation(Variant variation) {
        this.variation = variation;
    }

    public static class Variant {

        private String id;

        public Variant(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Variant{" +
                    "id=" + id +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", variation=" + variation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Objects.equals(getId(), service.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
