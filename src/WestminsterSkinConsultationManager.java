import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // create necessary arraylists
        ArrayList<Person> personsList = new ArrayList<>();
        ArrayList<Doctor> doctorsList = new ArrayList<>();
        ArrayList<Doctor> printDoctor = new ArrayList<>();

        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        String output = wantToLoadData(); // load wantToLoadData method
        // if returned letter is 'y' load previous data
        if (output.equalsIgnoreCase("Y")){
            loadFromFile(doctorsList,personsList);
            while (true) {
                System.out.println("Enter 'Y' to continue or 'Q' to exit"); // asks user wants to continue or exit
                String userInput = input.nextLine();
                if (userInput.equalsIgnoreCase("y")) {  // display menu options, and based on the input load particular method
                    System.out.println("----------------------------------------");
                    System.out.println("Add a new doctor - 'A' ");
                    System.out.println("Delete a doctor - 'D' ");
                    System.out.println("Print the list of the doctors - 'P' ");
                    System.out.println("Save in a file - 'S' ");
                    System.out.println("Open the GUI - 'G' ");
                    System.out.println("Quit - 'Q' ");
                    System.out.println("----------------------------------------");
                    String methodInput = input.nextLine();

                    if (methodInput.equalsIgnoreCase("A")) {
                        manager.addNewDoctor(doctorsList, personsList);
                    } else if (methodInput.equalsIgnoreCase("d")) {
                        manager.deleteDoctor(doctorsList);
                    } else if (methodInput.equalsIgnoreCase("p")) {
                        manager.printDoctors(doctorsList,printDoctor);
                    } else if (methodInput.equalsIgnoreCase("s")) {
                        manager.saveToFile(doctorsList);
                    } else if (methodInput.equalsIgnoreCase("g")) {
                        newGUI(doctorsList);
                    }else if (methodInput.equalsIgnoreCase("q")){
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                else if (userInput.equalsIgnoreCase("Q")){
                    break;
                }
                else {
                    System.out.println("Invalid Input");
                }
            }
        }
        else if (output.equalsIgnoreCase("N")){ // if letter is 'n' continue without previous data
            while (true) {
                System.out.println("\nEnter 'Y' to continue or 'Q' to exit");  // asks user wants to continue or exit
                String userInput = input.nextLine();
                if (userInput.equalsIgnoreCase("y")) {     // display menu options, and based on the input load particular method
                    System.out.println("----------------------------------------");
                    System.out.println("Add a new doctor - 'A' ");
                    System.out.println("Delete a doctor - 'D' ");
                    System.out.println("Print the list of the doctors - 'P' ");
                    System.out.println("Save in a file - 'S' ");
                    System.out.println("Open the GUI - 'G' ");
                    System.out.println("Quit - 'Q' ");
                    System.out.println("----------------------------------------");
                    String methodInput = input.nextLine();

                    if (methodInput.equalsIgnoreCase("A")) {
                        manager.addNewDoctor(doctorsList, personsList);
                    } else if (methodInput.equalsIgnoreCase("d")) {
                        manager.deleteDoctor(doctorsList);
                    } else if (methodInput.equalsIgnoreCase("p")) {
                        manager.printDoctors(doctorsList,printDoctor);
                    } else if (methodInput.equalsIgnoreCase("s")) {
                        manager.saveToFile(doctorsList);
                    } else if (methodInput.equalsIgnoreCase("g")) {
                        newGUI(doctorsList);
                    }else if (methodInput.equalsIgnoreCase("q")){
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                }
                else if (userInput.equalsIgnoreCase("Q")){
                    break;
                }
                else {
                    System.out.println("Invalid Input");
                }
            }
        }
        else {
            wantToLoadData();
        }
    }

    // add a doctor to the system
    @Override
    public void addNewDoctor(ArrayList<Doctor> doctor, ArrayList<Person> persons) {
        Scanner input = new Scanner(System.in);
        System.out.println("===============Add a Doctor===============");
        boolean canAdd = checkDoctorCount(doctor);
        System.out.println(doctor.size()+" doctors in the system.\n");
        if (canAdd){
            try {
                System.out.println("Enter First Name - ");
                String fName = input.next();
                System.out.println("Enter Surname - ");
                String surname = input.next();
                System.out.println("Enter Date of Birth (YYYY/MM/DD) - ");
                String dob = input.next();
                Date date = new SimpleDateFormat("yyyy/MM/dd").parse(dob);  // parse date of birth input to date format
                System.out.println("Enter Mobile Number - ");
                String mobNumber = input.next();
                while (mobNumber.length()!=10){                            // if mobile number length not equal to 10, do this
                    System.out.println("Enter Valid Mobile Number.\n");
                    System.out.println("Enter Mobile Number - ");
                    mobNumber = input.next();
                }
                Person p1 = new Person(fName, surname, dob, mobNumber);
                persons.add(p1);
                System.out.println("Enter Doctor's Medical License Number - ");   // get medical license number and specialisation as an input
                String medNumber = input.next();
                System.out.println("Enter Doctor's Specialisation - ");
                String specialisation = input.next();
                Doctor doctor1 = new Doctor(fName,surname,dob,mobNumber,medNumber,specialisation); // create a doctor object
                doctor.add(doctor1);
                System.out.println("Successfully added the doctor.\n");
            }
            catch (ParseException | InputMismatchException e){   // catch exceptions and display this message
                System.out.println("Invalid Input.");
            }
        }
        else {
            System.out.println("Already added 10 doctors.");    // if number of doctors greater than 10, display this message
        }
    }

    // delete a doctor from the system
    @Override
    public void deleteDoctor(ArrayList<Doctor> doctor) {
        Scanner input = new Scanner(System.in);
        System.out.println(doctor.size());
        System.out.println("===============Delete a Doctor===============\n");
        if (doctor.size() > 0) {                              // if there is at least one doctor, this code will run
            int idx=-1;
            System.out.println("Enter Doctor's Medical License Number - ");   // get medical license number as an input
            String medNumber = input.next();
            for (int i = 0; i < doctor.size(); i++) {
                if (doctor.get(i).getMedLicenceNumber().equalsIgnoreCase(medNumber)) {      // get the index of the doctor
                    idx=i;
                    break;
                }
            }
            if (idx >=0) {
                System.out.println(doctor.get(idx).getName() + " " + doctor.get(idx).getSurname() + " Removed Successfully.");
                doctor.remove(idx);                                                          // remove doctor from the list
                System.out.println(doctor.size() + " doctors in the centre.");   // display remaining number of doctors
                System.out.println("==============================================\n");
            } if (idx==-1){
                System.out.println("Invalid Medical License Number.\n");
            }
        }
        else{
            System.out.println("No Doctors in the System.");
        }
    }


    // display doctors' details
    @Override
    public void printDoctors(ArrayList<Doctor> doctor,ArrayList<Doctor> printDoctor) {
        System.out.println("===============Print Doctors' Details===============");
        if (doctor.isEmpty()){                                       // check whether doctor arraylist is empty
            System.out.println("No doctors in the system.\n");
        }
        else {
            //printDoctor.addAll(doctor);              // add to another arraylist
            Collections.sort(doctor);           // sort the arraylist according to the surname

            for (int i = 0; i < doctor.size(); i++) {           // display the details
                System.out.println("First Name - " + doctor.get(i).getName());
                System.out.println("Surname - " + doctor.get(i).getSurname());
                System.out.println("Date of Birth - " + doctor.get(i).getDob());
                System.out.println("Mobile Number - " + doctor.get(i).getMobileNumber());
                System.out.println("Medical License Number - " + doctor.get(i).getMedLicenceNumber());
                System.out.println("Specialisation - " + doctor.get(i).getSpecialisation());
                System.out.println("==============================================");
            }
        }
    }

    // save data to a text file
    @Override
    public void saveToFile(ArrayList<Doctor> doctor) {
        System.out.println("===============Save to a File===============\n");
        try {
            Writer myWriter = new FileWriter("Doctors.txt");         // get values from arraylist and write them on a text file
            for (int i=0;i<doctor.size();i++){
                String fName = doctor.get(i).getName();
                String surname = doctor.get(i).getSurname();
                String dob = doctor.get(i).getDob();
                String mobNumber = doctor.get(i).getMobileNumber();
                String medNumber = doctor.get(i).getMedLicenceNumber();
                String specialisation = doctor.get(i).getSpecialisation();
                myWriter.write(fName+"\n"+surname+"\n"+dob+"\n"+mobNumber+"\n" + medNumber+"\n"+specialisation+"\n");
            }
            myWriter.close();
            System.out.println("Successfully saved to a file.\n");
        }
        catch (IOException e){
            System.out.println("An error occurred.\n");
        }
    }

    // load previous data from doctors file
    public static void loadFromFile(ArrayList<Doctor> doctor,ArrayList<Person> persons) {
        int lineCount = 0;
        ArrayList<String> docDetails = new ArrayList<>();
        String fName,surname,dob,mobNumber,licNumber,specialisation;
        int nameIndex=0;
        int surnameIndex=1;
        int dobIndex=2;
        int mobNumberIndex=3;
        int licNumberIndex=4;
        int specIndex=5;
        try {                                                                        // load the text file and get the data
            File inputFile = new File("Doctors.txt");
            Scanner rf = new Scanner(inputFile);
            String fileLine;
            while (rf.hasNext()) {
                fileLine = rf.nextLine();
                docDetails.add(fileLine);                     // add fileLine to docDetails array list
                lineCount = lineCount + 1;
            }
            System.out.print("\n");
            rf.close();
        }
        catch (IOException e){
            System.out.println("File is not exists.");                                  // if there is no file print this
            System.out.println("==============================================\n");
        }
        for (int i=0;i<docDetails.size()/6;i++){                // get data from docDetails arraylist
            fName=docDetails.get(nameIndex);
            surname=docDetails.get(surnameIndex);
            dob=docDetails.get(dobIndex);
            mobNumber=docDetails.get(mobNumberIndex);
            licNumber=docDetails.get(licNumberIndex);
            specialisation=docDetails.get(specIndex);
            createDoctor(fName,surname,  dob, mobNumber, licNumber, specialisation,doctor,persons);  // load create doctor method
            nameIndex=nameIndex+6;                    // update index numbers
            surnameIndex=surnameIndex+6;
            dobIndex=dobIndex+6;
            mobNumberIndex=mobNumberIndex+6;
            licNumberIndex=licNumberIndex+6;
            specIndex=specIndex+6;
        }
            System.out.println("Successfully Loaded Data from the File.");
            System.out.println("==============================================\n");
        }

    // ask user whether he/she wants to load previous data
    // return 'Y' or 'N'
    public static String wantToLoadData(){
        Scanner input = new Scanner(System.in);
        String load;
        while (true) {                // run until user enters 'y' or 'n'
            System.out.println("=========================================================");
            System.out.println("Enter 'Y' to load data from the file.");
            System.out.println("Enter 'N' to run the system without previous data.");
            System.out.println("=========================================================");
            load = input.nextLine();
            if (load.equalsIgnoreCase("Y")) {
                break;
            } else if (load.equalsIgnoreCase("N")) {
                break;
            }
        }
        return load;
    }

    // create person, doctor objects and add them to arraylist
    public static void createDoctor(String fName,String surname, String dob,String mobNumber,String licNumber,String specialisation,ArrayList<Doctor> doctor,ArrayList<Person> persons){
        Person p1 = new Person(fName, surname, dob, mobNumber);
        persons.add(p1);
        Doctor d1 = new Doctor(fName, surname, dob, mobNumber,licNumber,specialisation);
        doctor.add(d1);
    }

    // check number of doctors in the system
    // Only 10 doctors can add to the system
    public boolean checkDoctorCount(ArrayList<Doctor> doctor){
        if (doctor.size()!=10){
            return true;
        }
        else return false;
    }

    // create GUI
    public static void newGUI(ArrayList<Doctor> doctor){
        String[][] newArray = convert(doctor);
        CourseworkGUI courseworkGUI = new CourseworkGUI(newArray,doctor);
        courseworkGUI.setTitle("Skin Consultation Center");
        courseworkGUI.setVisible(true);
        courseworkGUI.setSize(800,600);
        courseworkGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        courseworkGUI.getContentPane().setBackground(new Color(212, 230, 241));

    }

    // convert doctor arraylist to a 2d array and return
    public static String[][] convert(ArrayList<Doctor> doctor){
        String[][] myDoctors = new String[doctor.size()][6];
        int a=0;
        int b=1;
        int c=2;
        int d=3;
        int e=4;
        int f=5;

        for (int i=0;i<doctor.size();i++){
            myDoctors[i][a]=doctor.get(i).getName();
            myDoctors[i][b]=doctor.get(i).getSurname();
            myDoctors[i][c]=doctor.get(i).getDob();
            myDoctors[i][d]=doctor.get(i).getMobileNumber();
            myDoctors[i][e]=doctor.get(i).getMedLicenceNumber();
            myDoctors[i][f]=doctor.get(i).getSpecialisation();
        }
        return myDoctors;
    }
}
