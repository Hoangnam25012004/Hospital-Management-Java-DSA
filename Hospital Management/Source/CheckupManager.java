package sr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public List<Checkup> getCheckupsByDoctor(Doctor doctor) {
        PriorityQueue<Checkup> queue = new PriorityQueue<>(Comparator.comparingInt(Checkup::getPriority));
        
        for (Checkup checkup : checkups) {
            if (checkup.getDoctor().equals(doctor)) {
                queue.add(checkup); // Enqueue based on priority
            }
        }
        
        List<Checkup> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll()); // Dequeue in priority order
        }
        
        return result;
    }

    public void clearCheckupsForDoctor(Doctor doctor) {
        checkups.removeIf(checkup -> checkup.getDoctor().equals(doctor));
    }
}
