// Sunwoo Yeom

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