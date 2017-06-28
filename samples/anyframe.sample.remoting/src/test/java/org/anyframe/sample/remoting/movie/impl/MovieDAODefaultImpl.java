/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.anyframe.sample.remoting.movie.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.anyframe.sample.remoting.movie.Category;
import org.anyframe.sample.remoting.movie.Country;
import org.anyframe.sample.remoting.movie.Movie;


public class MovieDAODefaultImpl implements MovieDAO {

    private Map<String, Movie> MOVIES = new HashMap<String, Movie>();

    public MovieDAODefaultImpl() {
        Movie movie1 = new Movie("001", "The Sound Of Music", "Robert Wise");
        Movie movie2 =
            new Movie("002", "No Country For Old Man", "Ethan Coen,Joel Coen");

        MOVIES.put(movie1.getMovieId(), movie1);
        MOVIES.put(movie2.getMovieId(), movie2);
    }

    public List<Movie> findMovieListAll() throws Exception {
        List<Movie> movieList = new ArrayList<Movie>();
        Iterator<Movie> itr = this.MOVIES.values().iterator();
        while (itr.hasNext())
            movieList.add((Movie) itr.next());
        return movieList;
    }

    public Map<String, Movie> findMovieMapAll() throws Exception {
        return this.MOVIES;
    }

    public List<Movie> findMovieList(Country country, Category category)
            throws Exception {
        return null;
    }

    public Movie fineMovie(String movieId) throws Exception {
        return (Movie) this.MOVIES.get(movieId);
    }

    public void createMovie(Movie movie) throws Exception {
        this.MOVIES.put(movie.getMovieId(), movie);
    }

    public void updateMovie(Movie movie) throws Exception {
        this.MOVIES.put(movie.getMovieId(), movie);
    }

    public void removeMovie(Movie movie) throws Exception {
        this.MOVIES.remove(movie.getMovieId());
    }
}
