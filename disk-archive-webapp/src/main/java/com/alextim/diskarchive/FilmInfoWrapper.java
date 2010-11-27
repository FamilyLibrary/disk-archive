package com.alextim.diskarchive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.alextim.diskarchive.entity.Actor;
import com.alextim.diskarchive.entity.Film;


public class FilmInfoWrapper {
	private static final String FILM_PARAM = "filmId";

	private static String baseImageURL = "renderGeneralImage.html";
	
	private transient Film film;

	public FilmInfoWrapper(Film film) {
		this.film = film;
	}
	
	public Long getFilmId() {
		return film.getId();
	}
	
	public String getAuthor() {
		String author = "";
		if (film.getAuthor() != null) {
			author = film.getAuthor().getName();
		}
		return author;
	}
	
	public String getActors() {
		String result = "";
		Set<Actor> actors = film.getActors();
		if (actors != null && actors.size() > 0) {
			int index = 0;
			
			String formatWithoutComma = "%s";
			String formatWithComma = ", %s";
			
			List<Actor> actorsList = new ArrayList<Actor>(actors); 
			Collections.sort(actorsList, new Comparator<Actor>() {
				@Override
				public int compare(Actor actor1, Actor actor2) {
					int result = 0;
					String name1 = null;
					String name2 = null;
					if (actor1 != null) {
						name1 = actor1.getName();
					} 
					if (actor2 != null) {
						name2 = actor2.getName();
					}
					if (name1 != null) {
						result = name1.compareTo(name2);
					} else if (name2 != null) {
						result = name2.compareTo(name1);
					}
					return result;
				}
			});
			
			for (Actor actor : actorsList) {
				String name = actor.getName();
				result+=String.format((index == 0 ? formatWithoutComma : formatWithComma), name.trim());
				index++;
			}
		}
		return result;
	}

	public String getDescription() {
		return film.getDescription();
	}
	
	public int getNumberOfSeries() {
		return 0;
	}
	
	public String getImageUrl() {
		return baseImageURL + "?" + FILM_PARAM + "=" + film.getId();
	}
}
