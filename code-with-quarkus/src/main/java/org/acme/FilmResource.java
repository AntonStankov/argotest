package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/api")
public class FilmResource {


    @Inject
    private FilmRepository filmRepository;

    @Inject
    private FilmRepo filmRepo;

    @GET
    @Path("/get/{page}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(@PathParam("page") short page) throws Exception{
        Stream<Film> film = filmRepository.getFilm(page, 10);
        return film.map(film1 -> String.format("%d| %s: %s", film1.getFilmId(), film1.getTitle(), film1.getDescription()))
                .collect(Collectors.joining("\n"));
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean addFilm(Film film){
        filmRepo.persist(film);
        filmRepo.flush();
        return true;
    }
}
