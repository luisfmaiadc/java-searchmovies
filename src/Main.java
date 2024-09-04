import Movies.Movie;
import Movies.TmdbMovie;
import Movies.TmdbResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        int exit = 0;
        while (exit != 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o nome do filme para realizar a busca.");
            var movieName = scanner.nextLine();
            String key = "33407ab90d9f81bbd58ff2787dd40a88";
            String url = "https://api.themoviedb.org/3/search/movie?api_key=" + key + "&query=" + movieName.replace(" ", "+");

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                //Conversão de JSON para Objeto através da Biblioteca GSON
                Gson gson = new Gson();
                TmdbResponse tmdbResponse = gson.fromJson(json, TmdbResponse.class);
                //Pegando lista de resultados
                List<TmdbMovie> tmdbMovies = tmdbResponse.getResults();

                // Exibindo os filmes encontrados
                for (TmdbMovie tmdbMovie : tmdbMovies) {
                    Movie movie = new Movie(tmdbMovie);
                    System.out.println(movie);
                }
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                System.out.println("Erro ao realizar busca de filme.");
                System.out.println(e.getMessage());
            }
            System.out.println("Caso queira fazer outra busca digite 0, caso queira sair digite 1.");
            exit = scanner.nextInt();
        }

    }
}
