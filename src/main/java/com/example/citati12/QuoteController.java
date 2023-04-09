package com.example.citati12;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuoteController {
    private final List<Quote> quotes = new ArrayList<>() {{
        add(new Quote(1, "Життя — це те, що з тобою трапляється, коли ти плануєш інше."));
        add(new Quote(2, "Життя надто коротке, щоб не робити те, що тебе щасливим робить."));
        add(new Quote(3, "Коли життя дає тобі лимони, зроби з них лимонад."));
        add(new Quote(4, "Краще гіркеє правдиве слово, ніж солодке брехливе."));
        add(new Quote(5, "Кожен час - це дарунок, тому відчувайте його смак та цінуйте."));
    }};

    @GetMapping("/quotes")
    public List<Quote> getAllQuotes() {
        return quotes;
    }

    @GetMapping("/quotes/{id}")
    public Quote getQuoteById(@PathVariable int id) {
        return quotes.stream()
                .filter(quote -> quote.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/quotes")
    public void addQuote(@RequestBody @Valid Quote quote) {
        int nextId = quotes.stream().mapToInt(Quote::getId).max().orElse(0) + 1;
        quote.setId(nextId);
        quotes.add(quote);
    }

    @PostMapping("/quotes/batch")
    public void addQuotes(@RequestBody @Validated List<Quote> quotesToAdd) {
        quotes.addAll(quotesToAdd);
    }

    @PutMapping("/quotes/{id}")
    public void updateQuote(@PathVariable int id, @RequestBody @Valid Quote quote) {
        quotes.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .ifPresent(q -> {
                    q.setText(quote.getText());
                });
    }

    @DeleteMapping("/quotes/{id}")
    public void deleteQuote(@PathVariable int id) {
        quotes.removeIf(q -> q.getId() == id);
    }

    @Controller
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "index"; // повернення назви HTML-файлу без розширення
        }
    }
}




