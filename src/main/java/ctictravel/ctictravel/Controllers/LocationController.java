package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.Interfaces.LocationsRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @RequestMapping(value = "/countries-cities", method = RequestMethod.GET)
    public String GetAllCountriesAndCities() {
        String url = "https://countriesnow.space/api/v0.1/countries";
        return new RestTemplate().getForObject(url, String.class);
    }

    @RequestMapping(value = "/contry-flag/{iso2}", method = RequestMethod.GET)
    public String GetCountryFlag(@PathVariable String iso2) {
        String url = "https://countriesnow.space/api/v0.1/countries/flag/images/q?iso2=" + iso2;
        return new RestTemplate().getForObject(url, String.class);
    }

    @RequestMapping(value = "/contry-states", method = RequestMethod.POST)
    public String GetCountryStates(@RequestBody LocationsRequest locationsRequest) {
        String url = "https://countriesnow.space/api/v0.1/countries/states/q?country=" + locationsRequest.getCountry();
        return new RestTemplate().getForObject(url, String.class);
    }
    @RequestMapping(value = "/states-cities", method = RequestMethod.POST)
    public String GetStatesCities(@RequestBody LocationsRequest locationsRequest) {
        String url = "https://countriesnow.space/api/v0.1/countries/state/cities/q?country=" + locationsRequest.getCountry() + "&state=" + locationsRequest.getState();
        return new RestTemplate().getForObject(url, String.class);
    }

}

