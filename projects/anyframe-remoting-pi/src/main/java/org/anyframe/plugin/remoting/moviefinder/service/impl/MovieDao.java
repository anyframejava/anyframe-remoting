/*
 * Copyright 2008-2012 the original author or authors.
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
package org.anyframe.plugin.remoting.moviefinder.service.impl;

import javax.inject.Inject;

import org.anyframe.pagination.Page;
import org.anyframe.plugin.remoting.domain.Movie;
import org.anyframe.query.QueryService;
import org.anyframe.query.dao.QueryServiceDaoSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * This MovieDao class is a DAO class to provide movie crud functionality.
 * 
 * @author Sujeong Lee
 */
@Repository("remotingMovieDao")
public class MovieDao extends QueryServiceDaoSupport {

	//Velocity-Support-contextProperties-START
	@Value("#{contextProperties['pageSize'] ?: 10}")
	int pageSize;

	@Value("#{contextProperties['pageUnit'] ?: 10}")
	int pageUnit;
	//Velocity-Support-contextProperties-END

	@Inject
	public void setQueryService(QueryService queryService) {
		super.setQueryService(queryService);
	}

	public String create(Movie movie) {
		movie.setMovieId("MV-" + System.currentTimeMillis());
		super.create("createRemotingMovie", movie);
		return movie.getMovieId();
	}

	public void remove(String movieId) {
		Movie movie = new Movie();
		movie.setMovieId(movieId);
		super.remove("removeRemotingMovie", movie);
	}

	public void update(Movie movie) {
		super.update("updateRemotingMovie", movie);
	}

	public Movie get(String movieId) {
		Movie movie = new Movie();
		movie.setMovieId(movieId);
		return super.findByPk("findRemotingMovieByPk", movie);
	}

	public Page getPagingList(Movie movie, int pageIndex) {
		return super.findListWithPaging("findRemotingMovieList", movie,
				pageIndex, pageSize, pageUnit);
	}

}
