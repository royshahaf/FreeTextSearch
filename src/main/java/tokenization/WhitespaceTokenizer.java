package tokenization;

import java.util.Arrays;
import java.util.List;

public class WhitespaceTokenizer implements Tokenizer {

	public List<String> tokenize(String string) {
		return Arrays.asList(string.split(" "));
	}

}
