import java.io.*;
import java.util.*;

public class VotingSystem{

    static Scanner scannerInput = new Scanner(System.in);
    static ArrayList<Integer> votes = new ArrayList<>();
    static ArrayList<String> candidates = new ArrayList<>();
    
    static final String file_voter = "voters.txt";


    static final String file_candidates = "candidates.txt";

    static ArrayList<String> passVoter = new ArrayList<>();
    static ArrayList<String> namesVoter = new ArrayList<>();
    static ArrayList<Boolean> statVoter = new ArrayList<>();
    static ArrayList<Integer> ageVoter = new ArrayList<>();

    public static void main(String[] args) {
        loadData();  
        votersLoad();
        startProgram();
    }

    public static void startProgram() {
        while (true) {
            System.out.println("\n\t\tWelcome to Voting System");
            System.out.println("1. Admin Menu\n2. Voter Menu\n3. Exit");
            System.out.println("Select from Menu:");

            int choice = scannerInput.nextInt();

            switch (choice) {
                case 1:
                    logAdmin();
                    break;
                case 2:
                    menuVoter();
                    break;
                case 3:
                    exit();
                    return;
                default:
                    System.out.println("Wrong Choice. Try Again!");
            }
        }
    }


    static void logAdmin() {
        scannerInput.nextLine(); 

        String username = "votingAdmin";
        String pass = "admin123";

        System.out.println("Enter Username (Admin):");
        String user = scannerInput.nextLine();

        System.out.println("Enter Password (Password):");
        String password = scannerInput.nextLine();

        if (user.equals(username) && password.equals(pass)) {
            System.out.println("Logged in Successfully");
            adminMenu();
        } else {
            System.out.println("Wrong information. Try Again!");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("1. Add Candidate\n"
                    + "2. Remove Candidate\n"
                    + "3. View Candidates\n"
                    + "4. View Result\n"
                    + "5. Logout");
            System.out.println("Select from Main Menu:");
            int choice = scannerInput.nextInt();

            switch (choice) {
                case 1:
                    candidateAdd();
                    break;
                case 2:
                    candidateRemove();
                    break;
                case 3:
                    candidateView();
                    break;
                case 4:
                    resultView();
                    break;
                case 5:
                    System.out.println("Logged out from Admin Menu.");
                    return;
                default:
                    System.out.println("Invalid Option. Try again!");
            }
        }
    }

    static void candidateAdd() {
        scannerInput.nextLine();
        System.out.println("Enter Candidate name:");
        String name = scannerInput.nextLine();

        if (candidates.contains(name)) {
            System.out.println("Candidate already exists!");
        } else {
            candidates.add(name);
            votes.add(0);
            saveData();
            System.out.println("Candidate added.");
        }
    }

    static void candidateRemove() {
        scannerInput.nextLine();
        boolean exists = false;
        System.out.println("Enter a candidate name to remove:");
        String name = scannerInput.nextLine();


        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).equalsIgnoreCase(name)) {
                candidates.remove(i);
                votes.remove(i);
                exists = true;
                break;
            }
        }

        if (exists) {
            saveData();
            System.out.println("Candidate removed.");
        } else {
            System.out.println("Candidate not found.");
        }
    }

    static void candidateView() {

        System.out.println("\nList of Candidates:");
        if (candidates.isEmpty()) {
            System.out.println("No candidates available.");
            return;
        }

        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " + candidates.get(i));
        }
        System.out.println();
    }

    static void resultView() {
        System.out.println("\n\t\tVoting Result");
        if (candidates.isEmpty()) {
            System.out.println("No candidates exist.");
            return;
        }

        for (int i = 0; i < candidates.size(); i++) {
            System.out.println(candidates.get(i) + " : " + votes.get(i) + " votes");
        }
    }

    static void menuVoter() {
        while (true) {
            System.out.println("1. Register\n2. Login\n3. Back");
            System.out.println("Select from Menu:");
            int choice = scannerInput.nextInt();

            switch (choice) {
                case 1:
                    regVoter();
                    break;
                case 2:
                    logVoter();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Option. Try again!");
            }
        }
    }

    static void regVoter() {
        scannerInput.nextLine();
        System.out.println("Enter new username:");
        String username = scannerInput.nextLine();

        if (namesVoter.contains(username)) {
            System.out.println("Username already taken, choose another one.");
            return;
        }

        System.out.println("Enter password:");
        String password = scannerInput.nextLine();

        System.out.println("Enter your age:");
        int age = scannerInput.nextInt();

        if (age < 18) {
            System.out.println("You must be 18 or older to register for voting.");
            return;
        }

        namesVoter.add(username);
        passVoter.add(password);
        ageVoter.add(age);
        statVoter.add(false);

        saveVoters();
        System.out.println("Registration successful! You can now log in to vote.");
    }

    static void logVoter() {
        scannerInput.nextLine();
        System.out.println("Enter username:");
        String username = scannerInput.nextLine();
        System.out.println("Enter password:");
        String password = scannerInput.nextLine();

        for (int i = 0; i < namesVoter.size(); i++) {
            if (namesVoter.get(i).equals(username) && passVoter.get(i).equals(password)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                if (statVoter.get(i)) {
                    System.out.println("You have already voted.");
                    return;
                }
                voterOptions(i);
                return;
            }
        }
        System.out.println("Invalid username or password!");
    }

    static void voterOptions(int voterIndex) {
        while (true) {
            System.out.println("\n1. View Candidates\n2. Cast Vote\n3. Logout");
            System.out.println("Select from Menu:");
            int choice = scannerInput.nextInt();

            switch (choice) {
                case 1:
                    candidateView();
                    break;
                case 2:
                    if (statVoter.get(voterIndex)) {
                        System.out.println("You have already voted!");
                    } else {
                        boolean voteCast = castVote();
                        if (voteCast) {
                            statVoter.set(voterIndex, true);
                            saveVoters();
                        }
                        
                    }
                    break;
                case 3:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid Option. Try again!");
            }
        }
    }

    static boolean castVote() {
        candidateView();

        if (candidates.isEmpty()) {
            System.out.println("No candidates to vote for.");
            return false;
        }

        System.out.println("Enter candidate number to vote:");
        int choice = scannerInput.nextInt();

        if (choice < 1 || choice > candidates.size()) {
            System.out.println("Invalid choice!");
            return false;
        }

        votes.set(choice - 1, votes.get(choice - 1) + 1); //Array starts with 0 so we do choice - 1 to get the correct index
        saveData();
        System.out.println("Vote cast successfully!");
        return true;
        
    }

    static void saveData() {
        try {
        FileWriter dataWriter = new FileWriter(file_candidates);
        for (int i = 0; i < candidates.size(); i++) {
            dataWriter.write((i + 1) + "\n");   //First data is ID
            dataWriter.write(candidates.get(i) + "\n"); // Second is Name
            dataWriter.write(votes.get(i) + "\n"); // Third is Votes
        }
        dataWriter.close();
        } catch (IOException e) {
        System.out.println("You have an error in your input data...");
}
    }

    static void loadData() {
        try {
        File file = new File(file_candidates);
        if (!file.exists()){
            return;
        }

        Scanner fileReader = new Scanner(file);
        candidates.clear();
        votes.clear();

        while (fileReader.hasNextLine()) {
            int voterID = Integer.parseInt(fileReader.nextLine().trim()); //not needed
            String name = fileReader.nextLine().trim();
            int countVote = Integer.parseInt(fileReader.nextLine().trim());

            candidates.add(name);
            votes.add(countVote);
        }

        fileReader.close();
        } catch (IOException e) {
            System.out.println("Invalid input type...");
        }   
    }


    static void saveVoters() {
        try {
            FileWriter dataWriter = new FileWriter(file_voter);
            for (int i = 0; i < namesVoter.size(); i++) {
                dataWriter.write(namesVoter.get(i) + "\n");
                dataWriter.write(passVoter.get(i) + "\n");
                dataWriter.write(ageVoter.get(i) + "\n");
                dataWriter.write(statVoter.get(i) + "\n");
            }
            dataWriter.close();
        } catch (IOException e) {
            System.out.println("There was an input issue while saving voter data...");
        }
    }

    static void votersLoad() {
        try {
            File file = new File(file_voter);
            if (!file.exists()) return;
            ageVoter.clear();
            passVoter.clear();
            namesVoter.clear();
            statVoter.clear();
            
            Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    String name = fileReader.nextLine().trim();
                    String pass = fileReader.nextLine().trim();
                    int age = Integer.parseInt(fileReader.nextLine().trim());
                    boolean voted = Boolean.parseBoolean(fileReader.nextLine().trim());
                    
                    namesVoter.add(name);
                    passVoter.add(pass);
                    ageVoter.add(age);
                    statVoter.add(voted);
                }
                  fileReader.close();
        } catch (IOException e) {
            System.out.println("There was an error loading voter data...");
        }
    }
    static void exit() {
        saveData();
        saveVoters();
        System.out.println("Exiting voting system... \nThank you for using our Voting System!");
    }
}