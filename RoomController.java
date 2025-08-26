public class RoomController {
    private RoomModel model;
    private RoomView view;

    public RoomController(RoomModel model, RoomView view) {
        this.model = model;
        this.view = view;
    }
    public void setView(RoomView view) {
        this.view = view;
    }

    public void updateView(){
        view.displayRooms();
    }
    // Use the bookRoom method from RoomModel 
    public boolean bookRoomID(String id) {
        return model.bookRoom(id);
    }
    // RemoveBooking method from RoomModel
    public boolean removeBookingID(String id){
        return model.removeBooking(id);
    }

}