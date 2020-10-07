package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.google.api.client.util.Maps;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;

public class DialogflowUtils {
	
	GoogleCredentials credentials;
	
	public DialogflowUtils() throws IOException {
		File file = new File("src/resources/key.json");
		InputStream is = new FileInputStream(file);
		credentials = GoogleCredentials.fromStream(is);
	}
	
	/**
	 * Funcion para obtener el IntentText de DialogFlow, mas informacion en {@link https://cloud.google.com/dialogflow/docs/quick/api?hl=es-419}
	 * @param projectId , Project id del agente de google, debe coincidir con el projectId de key.json
	 * @param texts , Array de strings con el texto a analizar
	 * @param sessionId , session id de la conversacion con el agente
	 * @param languageCode , codigo de lenguaje
	 * @return Map<String, QueryResult>, done Strign es el mensaje original y Query result la respuesta de DF
	 * @throws IOException
	 */
	public Map<String, QueryResult> GetIntentText (String projectId, List<String> texts, String sessionId, String languageCode) throws IOException {
		Map<String, QueryResult> queryResults = Maps.newHashMap();

		FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);

		SessionsSettings sessionSettings = SessionsSettings.newBuilder()
				.setCredentialsProvider(credentialsProvider)
				.build();

		SessionsClient sessionsClient = SessionsClient.create(sessionSettings);

		SessionName session = SessionName.of(projectId, sessionId);
		System.out.println("Session Path: " + session.toString());

		// Detectamos el intento por cada entrada de texo (array texts)
		for (String text : texts) {
			// Seteamos el texto y el codigo de lenguaje
			TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

			// Construimos la query con el textInput
			QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

			// Hacemos la peticion de detccion de Intent
			DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

			// Mostramos query result
			QueryResult queryResult = response.getQueryResult();

			System.out.println("====================");
			System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
			System.out.format("Detected Intent: %s (confidence: %f)\n", queryResult.getIntent().getDisplayName(),
					queryResult.getIntentDetectionConfidence());
			
			//Mostramos Fulfillment Text, que es la respuesta asociada al intent
			System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

			//Agregamos el resultado a la respuesta, donde la llave es el texto original
			queryResults.put(text, queryResult);
		}
		
		return queryResults;
	}

}
