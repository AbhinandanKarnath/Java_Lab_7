import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Flight {
    private String flightNumber;
    private String destination;

    public Flight(String flightNumber, String destination) {
        this.flightNumber = flightNumber;
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightNumber='" + flightNumber + '\'' + ", destination='" + destination + '\'' + '}';
    }
}

class Passenger {
    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Passenger{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}

class Booking {
    private Flight flight;
    private Passenger passenger;

    public Booking(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public String toString() {
        return "Booking{" + "flight=" + flight + ", passenger=" + passenger + '}';
    }
}

public class AirlineBookingSystem {
    public static void main(String[] args) {
     
            List<Booking> bookings = new ArrayList<>();
    
            Consumer<Booking> printBookingDetails = booking -> System.out.println("Booking: " + booking);
            Function<Booking, String> getFlightDetails = booking -> booking.getFlight().toString();
            Predicate<Passenger> isAdult = passenger -> passenger.getAge() >= 18;
            Supplier<Flight> defaultFlightSupplier = () -> new Flight("DF123", "Default Destination");
    
            Scanner scanner = new Scanner(System.in);
            int choice;
    
            do {
                System.out.println("Menu:");
                System.out.println("1. Print Booking Details");
                System.out.println("2. Print Flight Details");
                System.out.println("3. Print Adult Passengers");
                System.out.println("4. Get Default Flight");
                System.out.println("5. Create New Booking");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
    
                switch (choice) {
                    case 1:
                        bookings.forEach(printBookingDetails);
                        break;
                    case 2:
                        bookings.stream().map(getFlightDetails).forEach(System.out::println);
                        break;
                    case 3:
                        bookings.stream()
                                .map(Booking::getPassenger)
                                .filter(isAdult)
                                .forEach(passenger -> System.out.println("Adult Passenger: " + passenger));
                        break;
                    case 4:
                        Flight defaultFlight = defaultFlightSupplier.get();
                        System.out.println("Default Flight: " + defaultFlight);
                        break;
                    case 5:
                        System.out.print("Enter flight number: ");
                        String flightNumber = scanner.nextLine();
                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();
                        Flight flight = new Flight(flightNumber, destination);
    
                        System.out.print("Enter passenger name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter passenger age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Passenger passenger = new Passenger(name, age);
    
                        Booking newBooking = new Booking(flight, passenger);
                        bookings.add(newBooking);
                        System.out.println("New booking created: " + newBooking);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            } while (choice != 6);
    
            scanner.close();
        
    }
}