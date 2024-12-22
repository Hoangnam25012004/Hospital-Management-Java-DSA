package sr;

public class Doctor {
    private String Id, Name, Contact, Specialty;
    private int fees;

    public Doctor() {

    }
    public Doctor(String Id, String Name, String Contact, String Specialty, int fees) {
        this.Id = Id;
        this.Name = Name;
        this.Contact = Contact;
        this.Specialty = Specialty;
        this.fees = fees;
    }

    // Getter methods
    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getContact() {
        return Contact;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public int getFees() {
        return fees;
    }

    // Setter methods
    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public void setSpecialty(String Specialty) {
        this.Specialty = Specialty;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    // toString method
    @Override
    public String toString() {
        return "Doctor:" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Specialty='" + Specialty + '\'' +
                ", fees=" + fees;
    }
}
