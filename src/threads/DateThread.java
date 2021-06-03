package threads;


import java.time.LocalDateTime;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class DateThread extends Thread {

    
    private Label date;


    public DateThread(Label date) {
        setDaemon(true);
        this.date=date;
    }

    @Override
    public void run() {
        boolean condition = true;
        while (condition) {

            LocalDateTime locaDate = LocalDateTime.now();


            Platform.runLater(()->{
                date.setText(locaDate.getYear()+"/"+locaDate.getMonth()+"/"+locaDate.getDayOfMonth());
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
