/*
 * Copyright 2002-2012 the original author or authors.
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
package org.anyframe.sample.remoting.rmi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.anyframe.sample.remoting.ServerRunner;
import org.anyframe.sample.remoting.domain.Movie;
import org.anyframe.sample.remoting.moviefinder.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TestCase Name : RMISpringSupportTest <br>
 * <br>
 * [Description] : This TestCase defines scenarios to test service function by
 * implementing Http Invoker based Movie service provided by Spring. <br>
 * [Characteristic] <li>To use by exposing ordinary service as RMI remote
 * service via setup</li> <li>Constraints-given that RMI uses a specific port
 * for communication, making it hard to pass through a firewall, all services
 * provided at client and server should be written in Java.</li> <li>Server
 * Implementation: to expose ordinary service written in Spring Bean as RMI
 * Service with org.springframework.remoting.rmi.RmiServiceExporter class</li>
 * <li>Client Implementation: the use of
 * org.springframework.remoting.rmi.RmiProxyFactoryBean classs provided by
 * Spring</li> <br>
 * [Assumption] <li>-Server : JettyServer Use, Spring Configuration file use</li>
 * <li>-Client : RemotingSpringTestCase USe, Spring Configuration file use</li> <br>
 * [Main Flow] <li>#-1 Positive Case : To search the whole list in the form of
 * List</li> <li>#-2 Positive Case : To search the whole list in the form of Map
 * </li> <li>#-3 Positive Case : To search Movie</li> <li>#-4 Positive Case : To
 * create a new Movie</li> <li>#-5 Positive Case : To change Movie information</li>
 * <li>#-6 Positive Case : To remove Movie</li>
 * 
 * @author SooYeon Park
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./src/test/resources/rmi/server/WEB-INF/context-remoting-rmi.xml",
		"file:./src/test/resources/rmi/client/context-remoting-rmi-client.xml" })
public class RMISpringSupportTest extends ServerRunner {

	// ==============================================================
	// ====== Pre-job Definition necessary for TestCase Execution====
	// ==============================================================

	@Inject
	@Named("movieServiceClient")
	private MovieService movieServiceClient;

	// ==============================================================
	// ====== TestCase methods ======================================
	// ==============================================================

	/**
	 * [Flow #-1] Positive Case : To search the whole list in the form of List 
	 * 
	 * @throws Exception
	 *             throws exception which is from service
	 */
	@Test
	public void testFindMovieListAll() throws Exception {
		// 1. find movie list all
		List<Movie> movieList = movieServiceClient.findMovieListAll();

		// 2. check the movie list count
		assertEquals(2, movieList.size());
	}

	/**
	 * [Flow #-2] Positive Case : To search the whole list in the form of Map 
	 * 
	 * @throws Exception
	 *             throws exception which is from service
	 */
	@Test
	public void testFindMovieMapAll() throws Exception {
		// 1. find movie map all
		Map<String, Movie> movieMap = movieServiceClient.findMovieMapAll();

		// 2. check the movie map count
		assertEquals(2, movieMap.size());
	}

	/**
	 * [Flow #-3] Positive Case : To search Movie whose I.D. is :001” 
	 * 
	 * @throws Exception
	 *             throws exception which is from service
	 */
	@Test
	public void testFindMovie() throws Exception {
		// 1. find movie
		Movie movie = movieServiceClient.findMovie("001");

		// 2. check the movie information
		assertEquals("The Sound Of Music", movie.getTitle());
		assertEquals("Robert Wise", movie.getDirector());
	}

	/**
	 * [Flow #-4] Positive Case : To create a new Movie whose I.D. is “003” 
	 * 
	 * @throws Exception
	 *             throws exception which is from service
	 */
	@Test
	public void testCreateMovie() throws Exception {
		// 1. check the existing movie list count
		assertEquals(2, movieServiceClient.findMovieListAll().size());

		// 2. create new movie
		movieServiceClient.createMovie(new Movie("003", "Life Is Beautiful",
				"Roberto Benigni"));

		// 3. check the new movie list count
		assertEquals(3, movieServiceClient.findMovieListAll().size());

		// 4. check the new movie information
		Movie movie = movieServiceClient.findMovie("003");
		assertEquals("Life Is Beautiful", movie.getTitle());
		assertEquals("Roberto Benigni", movie.getDirector());
	}

	/**
	 * [Flow #-5] Positive Case : To change the existing Movie information whose I.D. is “002” 
	 * 
	 * @throws Exception
	 *             throws exception which is from service
	 */
	@Test
	public void testUpdateMovie() throws Exception {
		// 1. update movie
		movieServiceClient.updateMovie(new Movie("002", "Life Is Wonderful",
				"Roberto"));

		// 2. find updated movie
		Movie movie = movieServiceClient.findMovie("002");

		// 3. check the movie information
		assertEquals("Life Is Wonderful", movie.getTitle());
		assertEquals("Roberto", movie.getDirector());
	}

	/**
	 * [Flow #-6] Positive Case : To remove the existing Movie whose I.D. is “002”
	 * 
	 * @throws Exception
	 *             throws exception which is from service
	 */
	@Test
	public void testRemoveMovie() throws Exception {
		// 1. set movie id to remove
		Movie movie = new Movie();
		movie.setMovieId("002");

		// 2. remove the movie
		movieServiceClient.removeMovie(movie);

		// 3. check the removed movie info
		assertNull(movieServiceClient.findMovie("002"));
	}
}
