package cl.tmingeso.back.controllers;

import cl.tmingeso.back.models.Career;
import cl.tmingeso.back.repositories.CareerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CareerController.class)
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
class CareerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CareerRepository careerRepository;

    private static final String NAME = "Ingeniería Civil en Informática";
    private static final String URL = "/careers";

    @Test
    void getAllCareers() {

        // Declaration
        Career career = new Career(NAME);
        List<Career> careerList = new ArrayList<>();

        // Set to new Career.
        career.setName(NAME);

        // New Career is added.
        careerList.add(career);

        // Test
        given(careerRepository.findAll()).willReturn(careerList);
        try{
            mvc.perform(get(URL)
                    .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].name", is(NAME)));
        }
        catch (Exception ignored){
        }
    }

}