package app;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import json.JsonTransformer;
import search.NaiveSearchService;
import search.SearchService;
import similarities.LengthPercentSimilartiy;
import similarities.SharedTokensSimilartiy;
import similarities.SimilarityFunction;
import tokenization.CharacterTokenizer;
import tokenization.Tokenizer;
import tokenization.WhitespaceTokenizer;

public class FreeTextSearch {
	private static SearchService searchService;
	private static final Logger logger = LoggerFactory.getLogger(FreeTextSearch.class);

	public static void main(String[] args) {
		searchService = getServiceBasedOnArgs(args);
		port(5000);
		enableCors();
		post("/index", (request, response) -> {
			request.queryParams().stream().forEach(input -> searchService.index(input.trim()));
			return "";
		});
		post("/search", (request, response) -> request.queryParams().stream()
				.map(input -> searchService.search(input.trim())).collect(Collectors.toList()), new JsonTransformer());
	}

	private static SearchService getServiceBasedOnArgs(String[] args) {
		if (args.length < 2) {
			logger.info("Less than 2 arguments, using default settings (CharacterTokenizer, LengthPercentSimilartiy)");
			return new NaiveSearchService(new CharacterTokenizer(), new LengthPercentSimilartiy());
		} else {
			Tokenizer tokenizer;
			if (args[0].equalsIgnoreCase("word") ) {
				logger.info("Using word tokenizer");
				tokenizer = new WhitespaceTokenizer();
			} else {
				logger.info("Using character tokenizer");
				tokenizer = new CharacterTokenizer();
			}
			SimilarityFunction similarityFunction;
			if (args[1].equalsIgnoreCase("sharedtokens")) {
				logger.info("Using shared tokens similarity function");
				similarityFunction = new SharedTokensSimilartiy();
			} else {
				logger.info("Using length percent similarity function");
				similarityFunction = new LengthPercentSimilartiy();
			}
			return new NaiveSearchService(tokenizer, similarityFunction);
		}
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
