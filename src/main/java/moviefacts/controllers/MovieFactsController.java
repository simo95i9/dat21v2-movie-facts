package moviefacts.controllers;

import moviefacts.services.MovieFactsServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieFactsController {

    @GetMapping("/")
    public String index() {
        return MovieFactsServices.index();
    }

    @GetMapping("/get-first")
    public String getFirst() {
        return MovieFactsServices.getFirst();
    }

    @GetMapping("/get-random")
    public String getRandom() {
        return MovieFactsServices.getRandom();
    }

    @GetMapping("/get-ten-sort-by-popularity")
    public String getTenSortByPopularity() {
        return MovieFactsServices.getTenSortByPopularity();
    }


    @GetMapping("/how-many-won-an-award")
    public String howManyWonAnAward() {
        return MovieFactsServices.howManyWonAnAward();
    }

    @GetMapping("/filter?char=’x’amount=’n’")
    public String filter() {
        return "Not implemented";
    }

    @GetMapping("/longest?g1=’x’g2=’y’")
    public String longest() {
        return "Not implemented";
    }

    @GetMapping("/SQL")
    public String sql() {
        return  "Not implemented";
    }


}
