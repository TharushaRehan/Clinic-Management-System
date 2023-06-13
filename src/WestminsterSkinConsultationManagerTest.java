import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WestminsterSkinConsultationManagerTest {

    ArrayList<Doctor> doctorArrayList = new ArrayList<>();

    @org.junit.jupiter.api.Test
    @DisplayName("Add a doctor")
    void addNewDoctor() {
        // add  doctor objects to the arraylist
        Person  person1 = new Person("Kamal","Perera","1995/01/15","2122545632");
        Doctor doctor1 = new Doctor(person1.getName(), person1.getSurname(), person1.getDob(), person1.getMobileNumber(),"D001","Paediatric");
        doctorArrayList.add(doctor1);
        assertEquals(1, doctorArrayList.size());

        Person  person2 = new Person("Amal","Perera","1998/05/15","5655412546");
        Doctor doctor2 = new Doctor(person2.getName(), person2.getSurname(), person2.getDob(), person2.getMobileNumber(),"D002","Paediatric");
        doctorArrayList.add(doctor2);
        assertEquals(2, doctorArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void deleteDoctor() {
        Person  person2 = new Person("Amal","Perera","1998/05/15","5655412546");
        Doctor doctor2 = new Doctor(person2.getName(), person2.getSurname(), person2.getDob(), person2.getMobileNumber(),"D002","Paediatric");
        // add doctor to the doctor arraylist
        doctorArrayList.add(doctor2);
        // remove a doctor
        doctorArrayList.remove(0);
        // after removing the doctor list size should be 0
        assertEquals(0,doctorArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void printDoctors() {
        Person  person2 = new Person("Amal","Perera","1998/05/15","5655412546");
        Doctor doctor2 = new Doctor(person2.getName(), person2.getSurname(), person2.getDob(), person2.getMobileNumber(),"D002","Paediatric");
        // add doctor to the doctor arraylist
        doctorArrayList.add(doctor2);
        // there has to be at least one doctor to print the details
        assertEquals(1,doctorArrayList.size());
    }

    @org.junit.jupiter.api.Test
    void saveToFile() {
        Person  person2 = new Person("Amal","Perera","1998/05/15","5655412546");
        Doctor doctor2 = new Doctor(person2.getName(), person2.getSurname(), person2.getDob(), person2.getMobileNumber(),"D002","Paediatric");
        // add doctor to the doctor arraylist
        doctorArrayList.add(doctor2);
        // there has to be at least one doctor to save the details to a text file
        assertEquals(1,doctorArrayList.size());
    }
}