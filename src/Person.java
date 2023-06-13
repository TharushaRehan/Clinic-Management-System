public class Person {

    // attributes
    private String name;
    private String surname;
    private String dob;
    private String mobileNumber;

    // constructor
    public Person(String name1, String surname1, String dob1, String mobNumber) {
        this.name = name1;
        this.surname = surname1;
        this.dob = dob1;
        this.mobileNumber = mobNumber;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
