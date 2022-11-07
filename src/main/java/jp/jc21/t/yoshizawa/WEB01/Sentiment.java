package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Sentiment1 message = getSentiment1("Stepover Toehold With Facelock");
		if (message != null) {
			System.out.println("positive:" + message.documents[0].confidenceScores.positive);
			System.out.println("neutral:" + message.documents[0].confidenceScores.neutral);
			System.out.println("negative:" + message.documents[0].confidenceScores.negative);
		}
	}

	static Sentiment1 getSentiment1(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3b08-text.cognitiveservices.azure.com/" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "ef93e007e3404ffd8eec8e453d9ffac8");

		/*Documents2 doc = new Documents2();
		doc.id = "1";
		doc.sentences[0].text = s;

		Sentiment1 src = new Sentiment1();
		src.documents2 = new Documents2[1];
		src.documents2[0] = doc;
*/
		Docs2 doc = new Docs2();
		doc.id = "1";
		doc.text = s;

		Source2 src = new Source2();
		src.documents = new Docs2[1];
		src.documents[0] = doc;
		String jsonData = new Gson().toJson(src);

		//InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, map, jsonData);
		Sentiment1 message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Sentiment1.class);
			reader.close();
		}
		return message;
	}

}

class Sentiment1 {
	Documents2[] documents;
	String[] errors;
	String modelVersion;
}

class Documents2 {
	String id;
	//String sentiment;
	ConfidenceScores confidenceScores;
	//Sentences[] sentences;
	//String[] warning;
}

class ConfidenceScores {
	String positive;
	String neutral;
	String negative;
}

/*class Sentences {
	Targets[] targets;
	ConfidenceScores[] confidenceScores;
	int length;
	int offset;
	Assessments[] assessments;
	String sentiment;
	String text;
}

class Targets{
	ConfidenceScores confidenceScores;
	int length;
	int offset;
	Relations[] relations;
	String sentiment;
	String text;	
}

class Assessments{
	ConfidenceScores confidenceScores;
	String isNegative;
	int length;
	int offset;
	String sentiment;
	String text;
}

class Relations{
	String ref;
	String relationType;
}

/*class ConfidenceScores2{
	String positive;
	String negative;
}*/

/*class ConfidenceScores2{
	String positive;
	String negative;
}*/

class Source2 {
	Docs2[] documents;
}

class Docs2 {
	String id;
	String text;
}

