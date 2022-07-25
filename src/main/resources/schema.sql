//DROP TABLE IF EXISTS PUBLIC.FRIENDSHIPS;
//DROP TABLE IF EXISTS PUBLIC.LIKES;
//DROP TABLE IF EXISTS PUBLIC.FILM_GENRES;
//DROP TABLE IF EXISTS PUBLIC.GENRES;
//DROP TABLE IF EXISTS PUBLIC.FILMS;
//DROP TABLE IF EXISTS PUBLIC.MPA_RATINGS;
//DROP TABLE IF EXISTS PUBLIC.USERS;
//DROP TABLE IF EXISTS PUBLIC.DIRECTORS;

CREATE TABLE IF NOT EXISTS users (
	user_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	email VARCHAR(200),
	login VARCHAR(50) NOT NULL,
	name VARCHAR(50),
	birthday DATE
);

CREATE TABLE IF NOT EXISTS friendships (
	friendship_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	user_id INT,
	friend_id INT,
	status VARCHAR(50),
	CONSTRAINT fk_user_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
	CONSTRAINT fk_friend_user FOREIGN KEY (friend_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mpa_ratings(
	mpa_rating_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	code VARCHAR(30) NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS films(
	film_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	release_date DATE,
	duration INT,
	rate NUMERIC,
	mpa_rating_id VARCHAR(15),
	CONSTRAINT fk_mpa_rating FOREIGN KEY (mpa_rating_id) REFERENCES mpa_ratings(mpa_rating_id)
);

CREATE TABLE IF NOT EXISTS likes(
	like_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	user_id INT,
	film_id INT, 
	CONSTRAINT fk_like_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
	CONSTRAINT fk_like_film_id FOREIGN KEY (film_id) REFERENCES films(film_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS genres(
	genre_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS film_genres(
	film_genre_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	film_id INT,
	genre_id INT,
	CONSTRAINT fk_genre_film_id FOREIGN KEY (film_id) REFERENCES films(film_id) ON DELETE CASCADE,
	CONSTRAINT fk_genre_id FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);

CREATE TABLE IF NOT EXISTS directors(
    director_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS film_directors(
    film_id int references FILMS(film_id),
    director_id int references DIRECTORS(director_id) ON DELETE CASCADE,
    PRIMARY KEY (film_id, director_id)
)