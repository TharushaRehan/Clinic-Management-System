public class Doctor extends Person implements Comparable{

    // attributes
    private String medLicenceNumber;
    private String specialisation;

    // constructor
    public Doctor(String name2, String surname1, String dob1, String mobNumber,String licenceNumber, String specialisation1){
        super(name2,surname1,dob1,mobNumber);
        this.medLicenceNumber=licenceNumber;
        this.specialisation=specialisation1;
    }

    // getters and setters
    public String getMedLicenceNumber() {
        return medLicenceNumber;
    }

    public void setMedLicenceNumber(String medLicenceNumber) {
        this.medLicenceNumber = medLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }


    // Method for sorting doctors by their surname
    @Override
    public int compareTo(Object obj) {
        Doctor doctor = (Doctor) obj;
        return (super.getSurname().compareTo(doctor.getSurname()));
    }
}
