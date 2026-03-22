#  Movie Ticket Booking System Using Java

## Description
A console-based Movie Ticket Booking System written in Java that allows users to manage shows, check seat availability, book tickets, and view bookings.  
The system uses a `HashMap` to store shows and an inner `Show` class to handle seat management. Tickets are booked on a first‑come, first‑served basis, and each customer’s seat numbers are recorded.

## Features
- **Add a Show** – Create a new show with a name and total number of seats.
- **View All Shows** – Display all available shows with seat availability.
- **Check Seat Availability** – See how many seats are left for a specific show.
- **Book Tickets** – Reserve a specified number of seats for a customer.
- **View Bookings** – Show all bookings for a particular show, including customer names and seat numbers.
- **Input Validation** – Ensures positive seat numbers and handles missing shows gracefully.

## Algorithm

### 1. Class Structure

#### `Project8` (Main class)
- `HashMap<String, Show> shows` – Stores shows by name.

#### `Show` (inner static class)
- **Fields**:
  - `String name` – Show name.
  - `int totalSeats` – Total capacity.
  - `boolean[] seats` – Array indicating booked seats (`true` = booked).
  - `HashMap<String, ArrayList<Integer>> customerBookings` – Maps customer names to lists of booked seat numbers.
- **Methods**:
  - `availableSeats()` – Counts and returns the number of free seats.
  - `bookSeats(String customer, int numberOfSeats)` – Books seats sequentially from the first available; returns `true` if successful, `false` otherwise.
  - `displayBookings()` – Prints all customer bookings.

#### Methods in `Project8`
- `addShow(String name, int totalSeats)` – Adds a new show; checks for duplicates.
- `viewShows()` – Displays all shows with available/total seats.
- `checkAvailability(String showName)` – Shows available seats for a specific show.
- `bookTickets(String showName, String customerName, int numberOfSeats)` – Books tickets for a show; validates input.
- `viewBookings(String showName)` – Displays bookings for a show.

### 2. Main Program (`main`)
- Creates `Scanner` for user input.
- Instantiates `Project8` system.
- Loop displaying menu options:
  1. Add Show – prompts for show name and total seats; calls `addShow`.
  2. View All Shows – calls `viewShows`.
  3. Check Seat Availability – prompts for show name; calls `checkAvailability`.
  4. Book Tickets – prompts for show name, customer name, and number of tickets; calls `bookTickets`.
  5. View Bookings – prompts for show name; calls `viewBookings`.
  6. Exit – prints goodbye message and breaks.
- Handles invalid choices.
