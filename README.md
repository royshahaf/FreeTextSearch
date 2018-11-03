# FreeTextSearch
A simple free text search engine service.

[![Build Status](https://travis-ci.com/royshahaf/FreeTextSearch.svg?branch=master)](https://travis-ci.com/royshahaf/FreeTextSearch)

## Operations
1. index - given a string, tokenize it and store it along with its tokens
2. search - given a string, tokenize it and use a similarity function to rank how similar it is to previously indexed strings

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
* [slf4j-log4j12] - A binding of slf4j to log4j
* [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back
