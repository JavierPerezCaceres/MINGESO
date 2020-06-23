package cl.tmingeso.back.controllers;

import cl.tmingeso.back.models.Career;
import cl.tmingeso.back.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://161.35.60.79:8080")
@RestController
@RequestMapping("/careers")
public class CareerController {

    // ***** START Repositories Declaration ******

    @Autowired
    CareerRepository careerRepository;

    // ***** END Repositories Declaration ******

    // ***** START services *****

    // Get all Careers.
    @GetMapping
    @ResponseBody
    public List<Career> getAllUsers() {
        return careerRepository.findAll();
    }

    // ***** END services *****
}
