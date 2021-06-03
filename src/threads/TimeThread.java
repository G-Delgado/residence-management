package threads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class TimeThread extends Thread {

    
    private Label time;


    public TimeThread(Label time) {
        setDaemon(true);
        this.time=time;
    }

    @Override
    public void run() {
        boolean condition = true;
        while (condition) {

            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalDateTime locaDate = LocalDateTime.now();

            Platform.runLater(()->{
                time.setText(formatter.format(locaDate));
            }

            );
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
