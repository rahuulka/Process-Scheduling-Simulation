import java.io.*;
import java.util.*;

class ProcessData {
    int PID, Arrival_Time, Burst_Time, Completion_Time, Waiting_Time, Turnaround_Time;

    public ProcessData(int PID, int Arrival_Time, int Burst_Time) {
        this.PID = PID;
        this.Arrival_Time = Arrival_Time;
        this.Burst_Time = Burst_Time;
    }
}

public class SJF {
    public static void main(String[] args) {
        List<ProcessData> processDatas = new ArrayList<>();

        try {
            File file = new File("processes.txt");
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split("\\s+");
                int PID = Integer.parseInt(data[0]);
                int Arrival_Time = Integer.parseInt(data[1]);
                int Burst_Time = Integer.parseInt(data[2]);

                processDatas.add(new ProcessData(PID, Arrival_Time, Burst_Time));
            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        sjfScheduling(processDatas);
    }

    public static void sjfScheduling(List<ProcessData> processDatas) {
        List<ProcessData> completed = new ArrayList<>();
        int currentTime = 0, totalWT = 0, totalTAT = 0;

        System.out.print("Gantt Chart:\n|");

        while (!processDatas.isEmpty()) {
            final int time = currentTime;
            List<ProcessData> arrived = new ArrayList<>();

            for (ProcessData pd : processDatas)
                if (pd.Arrival_Time <= time) arrived.add(pd);

            if (arrived.isEmpty()) {
                currentTime++;
                continue;
            }

            arrived.sort(Comparator.comparingInt(p -> p.Burst_Time));
            ProcessData pd = arrived.get(0);
            processDatas.remove(pd);

            pd.Completion_Time = currentTime + pd.Burst_Time;
            pd.Turnaround_Time = pd.Completion_Time - pd.Arrival_Time;
            pd.Waiting_Time = pd.Turnaround_Time - pd.Burst_Time;

            totalWT += pd.Waiting_Time;
            totalTAT += pd.Turnaround_Time;

            System.out.printf(" P%d |", pd.PID);
            currentTime = pd.Completion_Time;
            completed.add(pd);
        }

        currentTime = 0;
        System.out.println();
        System.out.print(currentTime);

        for (ProcessData pd : completed) {
            currentTime = pd.Completion_Time;
            System.out.printf("%5d", currentTime);
        }
        System.out.println("\nPID Arrival Burst Completion Waiting Turnaround");

        for (ProcessData pd : completed) {
            System.out.printf("%3d %6d %5d %10d %7d %10d\n",
                pd.PID, pd.Arrival_Time, pd.Burst_Time,
                pd.Completion_Time, pd.Waiting_Time, pd.Turnaround_Time);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", (double) totalWT / completed.size());
        System.out.printf("Average Turnaround Time: %.2f\n", (double) totalTAT / completed.size());
    }
}