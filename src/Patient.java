public class Patient extends Person{

    private String patientID;

    // constructor
    public Patient(String name1, String surname1, String dob1, String mobNumber,String patientId){
        super(name1,surname1,dob1,mobNumber);
        this.patientID=patientId;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }


}
