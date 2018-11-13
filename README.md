# FreeTextSearch
A simple free text search engine service.

[![Build Status](https://travis-ci.com/royshahaf/FreeTextSearch.svg?branch=master)](https://travis-ci.com/royshahaf/FreeTextSearch)

## Operations
1. index - given a string, tokenize it and store it along with its tokens
2. search - given a string, tokenize it and use a similarity function to rank how similar it is to previously indexed strings

## Notes
1. This is a very basic implementation, where searching is likely not to scale well
2. Moreover, as the store becomes larger it may become impossible to store it in-memory. This basic implementation does not address this issue

## Implemented tokenizers
1. WhitespaceTokenizer
2. CharacterTokenizer

## Implemented similarity functions
1. SharedTokensSimilarity
2. LengthPercentSimilarity

## Built with
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spark Java](http://sparkjava.com/) - A lightweight http framework for java
* [slf4j](https://www.slf4j.org/) - A logging facade, allows easier transition between logging implementations
* [slf4j-log4j12](https://www.slf4j.org/) - A binding of slf4j to log4j
* [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back

## Getting started
1. Running this project requires maven and java (8+)
2. Clone (or download) the project
3. Run ``mvn dependency:copy-dependencies install`` in the projects folder
4. Run ``java -cp "target\FreeTextSearch-0.0.1-SNAPSHOT.jar;target\dependency\*" app.FreeTextSearch`` 
     1. This may be slightly different on different operating systems
5. By default, step 4 will run the server with a character tokenizer and length percent similarity function
     1. You may choose to use a different tokenizer / similarity function
     2. To do that follow this example:
``java -cp "target\FreeTextSearch-0.0.1-SNAPSHOT.jar;target\dependency\*" app.FreeTextSearch word sharedtokens``
    3. "word" (case doesn't matter) will start up the service with a word tokenizer (other values will start up the service with a character tokenizer)
    4. "sharedtokens" (case doesn't matter) will start up the service with a shared tokens similarity function (other values will start up the service with a length percent similarity function)
6. The service should be up and ready to receive http requests now

## Example of using the service
Assuming you've started the service with a word tokenizer and a shared tokens similarity function, the following series of http requests:

	curl -d "the cow says moo" -X POST http://127.0.0.1:5000/index
	curl -d "the cat and the hat" -X POST http://127.0.0.1:5000/index
	curl -d " the dish ran away with the spoon" -X POST http://127.0.0.1:5000/index
	curl -d " a cat ran away" -X POST http://127.0.0.1:5000/search

should yield the following output:
	
	[
	  {
	    "matches": [
	      {
	        "matchedAgainst": "the dish ran away with the spoon",
	        "similarity": 2
	      },
	      {
	        "matchedAgainst": "the cat and the hat",
	        "similarity": 1
	      },
	      {
	        "matchedAgainst": "the cow says moo",
	        "similarity": 0
	      }
	    ]
	  }
	]