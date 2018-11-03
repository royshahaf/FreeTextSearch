package tokenization;

import java.util.Arrays;
import java.util.List;

public class CharacterTokenizer implements Tokenizer {

	@Override
	public List<String> tokenize(String string) {
		return Arrays.asList(string.split("(?!^)"));
	}

}
