package by.yandex.practicum.filmorate.services;

import by.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import by.yandex.practicum.filmorate.models.Film;
import by.yandex.practicum.filmorate.storages.UserStorage;
import by.yandex.practicum.filmorate.storages.dao.DbFilmStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DefaultRecommendationsService implements RecommendationsService {
    private final DbFilmStorage dbFilmStorage;
    private final UserStorage userStorage;

    @Autowired
    public DefaultRecommendationsService(DbFilmStorage dbFilmStorage, UserStorage userStorage) {
        this.dbFilmStorage = dbFilmStorage;
        this.userStorage = userStorage;
    }

    @Override
    public List<Film> getRecommendationsFilms(Long idUser) {
        if (userStorage.getById(idUser) == null) {
            throw new UserNotFoundException("User with id = '" + idUser + "' not found.");
        }
        return dbFilmStorage.getRecommendationsFilms(idUser);
    }
}
