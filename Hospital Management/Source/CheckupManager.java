package sr;

import java.util.PriorityQueue;
import java.util.Queue;

public class CheckupManager {
    private Queue<Checkup> checkups;

    public CheckupManager() {
        checkups = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.getPriority(), c2.getPriority()));
    }

    public void addCheckup(Checkup checkup) {
        checkups.offer(checkup);
    }

    public Queue<Checkup> getCheckupsByDoctor(Doctor doctor) {
        Queue<Checkup> result = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.getPriority(), c2.getPriority()));
        for (Checkup checkup : checkups) {
            if (checkup.getDoctor().equals(doctor)) {
                result.offer(checkup);
            }
        }
        return result;
    }
}