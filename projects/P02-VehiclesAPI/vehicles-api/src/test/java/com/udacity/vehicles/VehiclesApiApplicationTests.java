package com.udacity.vehicles;

//import com.google.gson.Gson;
import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.test.web.servlet.ResultMatcher;

import java.net.URI;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class VehiclesApiApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    CarRepository carRepository;

    @Autowired
    private JacksonTester<Car> json;


    @Before
    //@Test
    public void setup() {
        Car savedCar = this.carRepository.save(getCar());
    }

    @Test
    public void contextLoads() {
    }

    @MockBean
    CarService carService;
   /* public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
*/
    //private Gson json = new Gson();
    /**
     * Creates an example Car object for use in testing.
    * @return an example Car object

    */

    private Car getCar() {

        Car car = new Car();

        car.setLocation(new Location(40.730610, -73.935242));

        Details details = new Details();

        Manufacturer manufacturer = new Manufacturer(100, "Audi");

        details.setManufacturer(manufacturer);

        details.setModel("Q5");

        details.setMileage(32280);

        details.setExternalColor("white");

        details.setBody("sedan");

        details.setEngine("3.6L V6");

        details.setFuelType("Gasoline");

        details.setModelYear(2018);

        details.setProductionYear(2018);

        details.setNumberOfDoors(4);

        car.setDetails(details);

        car.setCondition(Condition.USED);

        return car;
    }

    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        car.setId(1L);

        ResponseEntity<String> response = testRestTemplate.exchange("http://localhost:" + port + "/cars",
                HttpMethod.POST, new HttpEntity<>(car), String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        /*mockMvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());*/
    }

   /* @Test
    public void updateCar() throws Exception {
        Car car = getCar();
        car.setCondition(Condition.NEW);
        when(carService.save(any(Car.class))).thenReturn(car);

        mockMvc.perform(
                put("/cars/1")
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("condition", is(Condition.NEW.toString())));
        verify(carService, times(1)).save(any(Car.class));
    }*/

    @Test
    public void updateCar1() throws Exception {
        Car car = getCar();
        car.setLocation(new Location(38.375172, 26.875061));
        car.setCondition(Condition.NEW);
        mockMvc.perform(put(new URI("/cars/1"))
                .content(json.write(car).getJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200));
    }

    /*@Test
    public void updateCar() throws Exception {
        Car car = getCar();
        car.setCondition(Condition.NEW);
        when(carService.save(any(Car.class))).thenReturn(car);

        mockMvc.perform(
                put("/cars/1")
                        .content(json.toJson(car)) //.getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("condition", is(Condition.NEW.toString())));
        verify(carService, times(1)).save(any(Car.class));
    }*/

}
