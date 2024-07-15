package com.movie.crud.controller;

import com.movie.crud.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.movie.crud.model.Movie;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/create")
    public String saveMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "create_movie";
    }

    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieRepository.save(movie);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm(@PathVariable Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not valid!:" + id));
        model.addAttribute("movie", movie);
        return "edit_movie";
    }

    @PostMapping("/update/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieRepository.save(movie);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not valid!:" + id));
        movieRepository.delete(movie);
        return "redirect:/";
    }

}
