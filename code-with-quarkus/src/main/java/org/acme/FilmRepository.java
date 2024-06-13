package org.acme;

import com.speedment.jpastreamer.application.JPAStreamer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository implements PanacheRepository<Film> {

    @Inject
    private JPAStreamer jpaStreamer;

    public Stream<Film> getFilm(int page, int size){
        return jpaStreamer.stream(Film.class)
                .skip(page * 10L)
                .limit(10);
    }



}
