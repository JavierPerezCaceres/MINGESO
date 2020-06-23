package cl.tmingeso.back.information;

import cl.tmingeso.back.models.Career;
import cl.tmingeso.back.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@Component
public class CSVReader {

    // ***** START Repositories Declaration *****

    @Autowired
    private CareerRepository careerRepository;

    // ***** END Repositories Declaration *****

    // ***** START Methods *****

    // Reads the information inside the CSV file.
    // Input: Void.
    // Output: List<String> whit all the engineering careers at USACH.
    List<String> readCSV(){

        Logger logger = Logger.getLogger("com.project.callcontrol.PURGE");

        List<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/java/cl/tmingeso/back/information/engineeringCareers.csv"))) {
            String line;
            while ((line = br.readLine()) != null){
                content.add(line);
            }
        }
        catch (IOException e) {
            logger.log(Level.INFO, "Error with input .CSV",e);
        }

        return content;
    }

    // Allows to execute code when the app is ready.
    // Input: Event that signals when build is over.
    // Output: Void.
    @EventListener
    public void saveCareers(ApplicationReadyEvent event){

        // Insert the news careers.
        List<String> content;
        content = readCSV();
        for(int i = 0; i < content.size(); i++){
            careerRepository.save(new Career(content.get(i)));
        }
    }

    // ***** END Methods *****
}

