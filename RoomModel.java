
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Enums and interfaces remain the same as in the original code
enum RoomAvailability {
    Available, 
    NotAvailable;
}
// Advanced enum for the different types of rooms also including the base price of each room.
enum RoomType {
    PEASANT(100),
    DELUXE(200),
    SUITE(300);

    private final int basePrice;

    RoomType(int basePrice){
        this.basePrice = basePrice;
    }
    public int getBasePrice(){
        return basePrice;
    }
}
//interface for the cost per night
interface Cost {
    int costPerNight();
}
// interface for room features which will be implemented in the child classes
// Some Rooms will not have certain features so boolean is used
// String is used for the details.
interface RoomFeatures {
    boolean hasTV();
    SimpleStringProperty getTVDetails();
    boolean hasBathtub();
    SimpleStringProperty getBathtubDetails();
}
// abstract class which contains the room ID
abstract class Record {
    // Change from string to SimpleStringProperty as it needs to be displayed on the table
    protected SimpleStringProperty id;

    public Record(String id) {
        this.id = new SimpleStringProperty(id);
    }
}
// parent-child abstract class that has the toString method for child classes to inherit
abstract class Room extends Record implements Cost {
    //roomAvailability and roomType change to ObjectProperty
    protected SimpleObjectProperty<RoomAvailability> roomAvailability;
    protected SimpleObjectProperty<RoomType> roomType;
    protected String features;

    public Room(String id, RoomType roomType, RoomAvailability roomAvailability) {
        super(id);
        this.roomType = new SimpleObjectProperty<>(roomType);
        this.roomAvailability = new SimpleObjectProperty<>(roomAvailability);
    }

    public SimpleObjectProperty<RoomType> roomTypeProperty(){
        return roomType;
    }

    public RoomType getRoomType() {
        return roomType.get();
    }

    public abstract int costPerNight();

    public SimpleObjectProperty<RoomAvailability> roomAvailabilityProperty(){
        return roomAvailability;
    } 

    public RoomAvailability getRoomAvailability() {
        return roomAvailability.get();
    }

    public String getFeatures() {
        return features;
    }

    public SimpleStringProperty iDProperty() {
        return id;
    }

    public String getID() {
        return id.get();
    }



    @Override
    public String toString() {
        return "Room ID: " + id + ", Type: " + roomType + ", Price per Night: " + costPerNight() + ", Availability: " + roomAvailability;
    }
}
// Concrete class and inherits from Room
class OceanRoom extends Room implements RoomFeatures {
    private final int ADDITIONAL_COST = 75;
    private final boolean hasTV = true;
    private final SimpleStringProperty tvDetails = new SimpleStringProperty("Samsung LED");
    private final boolean hasBathtub = true;
    private final SimpleStringProperty bathtubDetails = new SimpleStringProperty("Aquarium themed tub");

    OceanRoom(String id, RoomType roomType, RoomAvailability roomAvailability) {
        super(id, roomType, roomAvailability);
        this.features = "TV: " + tvDetails + ", Bathtub: " + bathtubDetails;
    }

    // Overriding methods is used to provide implementation from the interfaces
    @Override
    public boolean hasTV() {
        return hasTV;
    }

    @Override
    public SimpleStringProperty getTVDetails() {
        return tvDetails;
    }

    @Override
    public boolean hasBathtub() {
        return hasBathtub;
    }

    @Override
    public SimpleStringProperty getBathtubDetails() {
        return bathtubDetails;
    }

    @Override
    public int costPerNight() {
        return  roomType.get().getBasePrice() + ADDITIONAL_COST;
    }
    // toString from the child class and features are added
    @Override
    public String toString() {
        return super.toString() + ", Features: " + tvDetails + ", "+ bathtubDetails;
    }
}


class MedievalRoom extends Room implements RoomFeatures {
    private final int ADDITIONAL_COST = 50;
    private final boolean hasTV = false;
    private final SimpleStringProperty tvDetails = new SimpleStringProperty( "No TV provided");
    private final boolean hasBathtub = true;
    private final SimpleStringProperty bathtubDetails = new SimpleStringProperty("Classic wooden bathtub");

    MedievalRoom(String id, RoomType roomType, RoomAvailability roomAvailability) {
        super(id, roomType, roomAvailability);
    }

    @Override
    public boolean hasTV() {
        return hasTV;
    }

    @Override
    public SimpleStringProperty getTVDetails() {
        return tvDetails;
    }

    @Override
    public boolean hasBathtub() {
        return hasBathtub;
    }

    @Override
    public SimpleStringProperty getBathtubDetails() {
        return bathtubDetails;
    }

    @Override
    public int costPerNight() {
        return roomType.get().getBasePrice() + ADDITIONAL_COST;
    }

    @Override
    public String toString() {
        return super.toString() + ", Features: " + bathtubDetails;
    }
}

class ForestRoom extends Room implements RoomFeatures {
    private final int ADDITIONAL_COST = 100;
    private final boolean hasTV = true;
    private final SimpleStringProperty tvDetails = new SimpleStringProperty("LG OLED evo TV 55 inches");
    private final boolean hasBathtub = false;
    private final SimpleStringProperty bathtubDetails = new SimpleStringProperty("No bathtub available");

    ForestRoom(String id, RoomType roomType, RoomAvailability roomAvailability) {
        super(id, roomType, roomAvailability);
    }


    // Other methods remain the same
    @Override
    public boolean hasTV() {
        return hasTV;
    }

    @Override
    public SimpleStringProperty getTVDetails() {
        return tvDetails;
    }

    @Override
    public boolean hasBathtub() {
        return hasBathtub;
    }

    @Override
    public SimpleStringProperty getBathtubDetails() {
        return bathtubDetails;
    }

    @Override
    public int costPerNight() {
        return roomType.get().getBasePrice() + ADDITIONAL_COST;
    }

    @Override
    public String toString() {
        return super.toString() + ", Features: TV - " + tvDetails;
    }
}

// Main class to store the rooms 
public class RoomModel {
// Change from arraylist to ObservableList to be displayed on the table
    private ObservableList<Room> allRoom;
    private ObservableList<OceanRoom> oceanRooms;
    private ObservableList<MedievalRoom> medievalRooms;
    private ObservableList<ForestRoom> forestRooms;

    public RoomModel() {
        allRoom = FXCollections.observableArrayList();
        oceanRooms = FXCollections.observableArrayList();
        medievalRooms = FXCollections.observableArrayList();
        forestRooms = FXCollections.observableArrayList();
        initializeRooms();
    }

    public ObservableList<OceanRoom> oceanRoomsProperty(){
        return oceanRooms;
    } 

    public ObservableList<MedievalRoom> medievalRoomsProperty(){
        return medievalRooms;
    }

    public ObservableList<ForestRoom> forestRoomsProperty(){
        return forestRooms;
    }

    public void addRoom(OceanRoom room) {
        oceanRooms.add(room);
    }

    public void addRoom(MedievalRoom room) {
        medievalRooms.add(room);
    }

    public void addRoom(ForestRoom room) {
        forestRooms.add(room);
    }
    public ObservableList<Room> getAllRooms() {
        return allRoom;
    }

    // Method to see if it can be booked
    public boolean bookRoom(String id) {
        //Uses a for each loop to scan through the available rooms
        for (Room room : allRoom) {
            if (room.getID().equals(id)) { // Check to see if the room id matches to any of the rooms' id
                if (room.getRoomAvailability() == RoomAvailability.Available) { 
                    room.roomAvailability.set(RoomAvailability.NotAvailable);
                    return true;
                    // if available room is booked and availability will become NotAvailable
                }
            } else {
                return false;
            }
        }
        return false;
    }

    // similar method to bookRoom just vice versa
    public boolean removeBooking(String id) {
        for (Room room : allRoom) {
            if (room.getID().equals(id)) {
                if (room.getRoomAvailability() == RoomAvailability.NotAvailable) {
                    room.roomAvailability.set(RoomAvailability.Available);
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    // Store only ocean rooms from all rooms in the oceanRooms list
    public ObservableList<OceanRoom> getOceanRooms() {
        ObservableList<OceanRoom> oceanRooms = FXCollections.observableArrayList();
        for (Room room : allRoom) {
            // If the room OceanRoom, room will be added to the observable list of oceanRooms
            if (room instanceof OceanRoom) {
                oceanRooms.add((OceanRoom) room);
            }
        }
        return oceanRooms;
    }
    // Same like previous method just for medieval rooms
    public ObservableList<MedievalRoom> getMedievalRooms() {
        ObservableList<MedievalRoom> medievalRooms = FXCollections.observableArrayList();
        for (Room room : allRoom) {
            if (room instanceof MedievalRoom) {
                medievalRooms.add((MedievalRoom) room);
            }
        }
        return medievalRooms;
    }

    // Same but for ForestRooms
    public ObservableList<ForestRoom> getForestRooms() {
        ObservableList<ForestRoom> forestRooms = FXCollections.observableArrayList();
        for (Room room : allRoom) {
            if (room instanceof ForestRoom) {
                forestRooms.add((ForestRoom) room);
            }
        }
        return forestRooms;
    }
    
    // initialize all the rooms
    public void initializeRooms() {
        allRoom.add(new OceanRoom("123", RoomType.PEASANT, RoomAvailability.Available));
        allRoom.add(new OceanRoom("456", RoomType.DELUXE, RoomAvailability.Available));
        allRoom.add(new OceanRoom("789", RoomType.SUITE, RoomAvailability.Available));
        allRoom.add(new MedievalRoom("147", RoomType.PEASANT, RoomAvailability.Available));
        allRoom.add(new MedievalRoom("258", RoomType.DELUXE, RoomAvailability.Available));
        allRoom.add(new MedievalRoom("369", RoomType.SUITE, RoomAvailability.Available));
        allRoom.add(new ForestRoom("987", RoomType.PEASANT, RoomAvailability.Available));
        allRoom.add(new ForestRoom("654", RoomType.DELUXE, RoomAvailability.Available));
        allRoom.add(new ForestRoom("321", RoomType.SUITE, RoomAvailability.Available));
    }
}