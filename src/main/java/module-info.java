module com.example.lab2iter {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.lab2iter to javafx.fxml;
    exports com.example.lab2iter;
}