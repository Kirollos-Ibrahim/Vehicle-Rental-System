import Service.RentalManager;
import UI.JavaFXApllication5;

public class Main {
    public static void main(String[] args) {
        
        RentalManager manager = new RentalManager();
        JavaFXApllication5 ui = new JavaFXApllication5(manager);
        ui.start();
    }
}