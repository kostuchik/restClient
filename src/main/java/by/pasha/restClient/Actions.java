package by.pasha.restClient;

import by.pasha.restClient.models.Measurement;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Actions {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String basicURL = "http://localhost:8080";

    public static void main(String[] args) {
        String newSensor = new Scanner(System.in).nextLine();






    }
    public static void addSensor(String newSensor) {
        String url = basicURL + "/sensors/registration";
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", newSensor);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        restTemplate.postForObject(url, request, String.class);
    }
    public static void addMeasurement(Double value, Boolean raining, String sensorName) {
        String url = basicURL + "/measurements/add";
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("value", String.valueOf(value));
        jsonToSend.put("raining", String.valueOf(raining));
        jsonToSend.put("sensor", sensorName);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);
        restTemplate.postForObject(url, request, String.class);
    }
    public static List<Measurement> getMeasurements() {
        String url = basicURL + "/measurements";
        ResponseEntity<List<Measurement>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Measurement>>(){});
        return response.getBody();
    }
    public static Integer getRainyDaysCount(){
        String url = basicURL + "/measurements/rainyDaysCount";
        return restTemplate.getForObject(url, Integer.class);
    }
}

