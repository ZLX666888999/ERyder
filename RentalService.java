package ERyder;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class RentalService{
    private final LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    public void startRental(String bikeID, String email) {
        ActiveRental rental = new ActiveRental(bikeID, email, LocalDateTime.now());
        activeRentalsList.add(rental);
    }
    public void endRental(String bikeID) {
        Iterator<ActiveRental> it = activeRentalsList.iterator();
        while (it.hasNext()) {
            ActiveRental r = it.next();
            if (bikeID.equals(r.getBikeID())) {
                it.remove();
                break;
            }
        }
    }
    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }
        for (ActiveRental r : activeRentalsList) System.out.println(r);
    }
}