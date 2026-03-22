import java.util.*;

public class Project8 {
    private HashMap<String, Show> shows;

    public Project8() {
        shows = new HashMap<>();
    }

    static class Show {
        String name;
        int totalSeats;
        boolean[] seats;
        HashMap<String, ArrayList<Integer>> customerBookings;

        Show(String name, int totalSeats) {
            this.name = name;
            this.totalSeats = totalSeats;
            seats = new boolean[totalSeats];
            customerBookings = new HashMap<>();
        }

        int availableSeats() {
            int count = 0;
            for (boolean b : seats) {
                if (!b) count++;
            }
            return count;
        }

        boolean bookSeats(String customer, int numberOfSeats) {
            if (numberOfSeats > availableSeats()) {
                return false;
            }

            ArrayList<Integer> bookedSeats = new ArrayList<>();
            for (int i = 0; i < totalSeats && bookedSeats.size() < numberOfSeats; i++) {
                if (!seats[i]) {
                    seats[i] = true;
                    bookedSeats.add(i + 1);
                }
            }

            customerBookings.put(customer, bookedSeats);
            return true;
        }

        void displayBookings() {
            if (customerBookings.isEmpty()) {
                System.out.println("No bookings yet.");
            } else {
                for (String cust : customerBookings.keySet()) {
                    System.out.println("Customer: " + cust + " | Seats: " + customerBookings.get(cust));
                }
            }
        }
    }

    public void addShow(String name, int totalSeats) {
        if (shows.containsKey(name)) {
            System.out.println("Show already exists.");
        } else {
            shows.put(name, new Show(name, totalSeats));
            System.out.println("Show '" + name + "' added with " + totalSeats + " seats.");
        }
    }

    public void viewShows() {
        if (shows.isEmpty()) {
            System.out.println("No shows available.");
        } else {
            System.out.println("Available Shows:");
            for (String showName : shows.keySet()) {
                Show show = shows.get(showName);
                System.out.println("- " + showName + " (" + show.availableSeats() + "/" + show.totalSeats + " seats available)");
            }
        }
    }

    public void checkAvailability(String showName) {
        Show show = shows.get(showName);
        if (show == null) {
            System.out.println("Show not found.");
        } else {
            System.out.println("Show: " + showName);
            System.out.println("Available seats: " + show.availableSeats() + "/" + show.totalSeats);
        }
    }

    public void bookTickets(String showName, String customerName, int numberOfSeats) {
        Show show = shows.get(showName);
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }
        if (numberOfSeats <= 0) {
            System.out.println("Number of seats must be positive.");
            return;
        }
        if (show.bookSeats(customerName, numberOfSeats)) {
            System.out.println("Booking successful for " + customerName + " (" + numberOfSeats + " seats).");
        } else {
            System.out.println("Booking failed. Not enough available seats.");
        }
    }

    public void viewBookings(String showName) {
        Show show = shows.get(showName);
        if (show == null) {
            System.out.println("Show not found.");
        } else {
            System.out.println("Bookings for show '" + showName + "':");
            show.displayBookings();
        }
    }

    public static void main(String[] args) {
        Project8 system = new Project8();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Movie Ticket Booking =====");
            System.out.println("1. Add a Show");
            System.out.println("2. View All Shows");
            System.out.println("3. Check Seat Availability");
            System.out.println("4. Book Tickets");
            System.out.println("5. View Bookings for a Show");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter show name: ");
                    String showName = sc.nextLine();
                    System.out.print("Enter total number of seats: ");
                    int seats = sc.nextInt();
                    system.addShow(showName, seats);
                }

                case 2 -> system.viewShows();

                case 3 -> {
                    System.out.print("Enter show name: ");
                    String showName = sc.nextLine();
                    system.checkAvailability(showName);
                }

                case 4 -> {
                    System.out.print("Enter show name: ");
                    String showName = sc.nextLine();
                    System.out.print("Enter your name: ");
                    String customer = sc.nextLine();
                    System.out.print("Number of tickets: ");
                    int num = sc.nextInt();
                    system.bookTickets(showName, customer, num);
                }

                case 5 -> {
                    System.out.print("Enter show name: ");
                    String showName = sc.nextLine();
                    system.viewBookings(showName);
                }

                case 6 -> System.out.println("Thank you for using the system. Goodbye!");

                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}