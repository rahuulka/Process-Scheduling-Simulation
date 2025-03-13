// Sunwoo Yeom
// import packages needed
import java.io.*; // for input output
import java.util.*; //arraylist, scanner, and etc

// Input : PID, Arrival_Time, Burst_Time
// variable needed for WT & TAT : Completion_Time, Waiting_Time, Turnarount_Time

// define Process Data
class ProcessData{
    int PID, Arrival_Time, Burst_Time, Completion_Time, Waiting_Time, Turnarount_Time;

    public ProcessData(int PID, int Arrival_Time, int Burst_Time) { //defining or allowing input from text
        this.PID = PID;
        this.Arrival_Time = Arrival_Time;
        this.Burst_Time = Burst_Time;
    }
}

// main class that takes input from text and save, sort in arrayList + calling function that do scheduling
public class FIFO {
    public static void main(String[] args) {
        // define list that will save inputs taken from processes.txt
        List<ProcessData> processDatas = new ArrayList<>();

        // function to to take text as input
        try {
            File file = new File("processes.txt"); // processes.txt is what we going to scan
            Scanner scanner = new Scanner(file); // scan file a.k.a processes.txt

            while (scanner.hasNextLine()) { // keep scanning as long as there is next line
                // split the line into data whenever there is one ore more white spaces
                String[] data = scanner.nextLine().split("\\s+"); 
                int PID = Integer.parseInt(data[0]); // convert first data, which is PID, from string to int
                int Arrival_Time = Integer.parseInt(data[1]); // convert second data, which is Arrival_Time, from string to int
                int Burst_Time = Integer.parseInt(data[2]); // convert third data, which is Burst_Time, from string to int

                processDatas.add(new ProcessData(PID, Arrival_Time, Burst_Time)); // now add above data into list processDatas
            }
            scanner.close(); // don't forget to close scanner

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error reading file");
            return;
        }

        // Run scheduling function with processDatas as input
        System.out.println("This is just test");
    }
    
}