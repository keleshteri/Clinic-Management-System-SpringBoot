package keleshteri.clinic.management.identity;

public enum IdentityType {
    NATIONAL("National Identity"),
    PASSPORT("Passport"),
    BIRTH("Birth Certificate");


    public final String label;

    IdentityType(String label) {
        this.label = label;
    }
}
