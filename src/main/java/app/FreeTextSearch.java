package app;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.stream.Collectors;

import json.JsonTransformer;
import search.NaiveSearchService;
import search.SearchService;
import similarities.LengthPercentSimilartiy;
import similarities.SharedTokensSimilartiy;
import tokenization.CharacterTokenizer;
import tokenization.WhitespaceTokenizer;

public class FreeTextSearch {
	static SearchService searchService = new NaiveSearchService(new CharacterTokenizer(),
			new LengthPercentSimilartiy());

	public static void main(String[] args) {

		port(5000);
		enableCors();
		post("/index", (request, response) -> {
			request.queryParams().stream().forEach(input -> searchService.index(input.trim()));
			return "";
		});
		post("/search", (request, response) -> request.queryParams().stream()
		.map(input -> searchService.search(input.trim())).collect(Collectors.toList()), new JsonTransformer());
	}

	private static void enableCors() {
		options("/*", (request, response) -> {

			String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

			return "OK";
		});

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
	}
}
