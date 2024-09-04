package Movies;

public class Movie {

    private final  String name;
    private final String adult;
    private final int year;
    private final double popularity;

    public Movie(TmdbMovie tmdbMovie) {
        this.name = tmdbMovie.title();
        this.adult = tmdbMovie.adult();
        if (tmdbMovie.release_date() != null && !tmdbMovie.release_date().isEmpty()){
            this.year = Integer.parseInt(tmdbMovie.release_date().substring(0, 4));
        } else {
            this.year = 0;
        }
        this.popularity = tmdbMovie.popularity();
    }

    @Override
    public String toString() {
        return "Movie {" +
                "name='" + name + '\'' +
                ", adult=" + adult +
                ", year='" + year + '\'' +
                ", populatiry=" + popularity +
                '}';
    }
}
