public class Consultation {

    // attributes
    private String cost;
    private String notes;
    private String date;
    private String patientID;
    private String docName;

    // constructor
    public Consultation(String cost, String notes, String date, String patientID, String dName) {
        this.cost = cost;
        this.notes = notes;
        this.date = date;
        this.patientID = patientID;
        this.docName = dName;
    }

    // getters and setters
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
