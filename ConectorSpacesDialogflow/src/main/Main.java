package main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.cloud.dialogflow.v2.QueryResult;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import utils.Constantes;
import utils.DialogflowUtils;

public class Main {

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, JSONException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inicializando conector...");
		
		Socket mSocket;
		IO.Options opts = new IO.Options();
		
		opts.forceNew = true;
		opts.reconnection = true;
		opts.transports = new String[] { "websocket" };
		opts.query = "tokenType=jwt&token=" + Constantes.SPACES_TOKEN;
		
		mSocket = IO.socket("https://spacesapis-socket.avayacloud.com/chat", opts);
		
		mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("socketmanagement conected");
					//Despues de establecer la conexion del socket, nos subscribimos al espacio para poder leer y mandar mensajes en el chat.
					SubscribeToChannel(Constantes.SPACE_ID, mSocket);
			}

		}).on("CHANNEL_SUBSCRIBED", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Suscrito al canal.");
			}

		}).on("MESSAGE_SENT", new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				JSONObject payload = (JSONObject) args[0];
				System.out.println("Message Event: " + payload.toString());
				try {
					if (Constantes.SPACE_ID.equals(payload.getString("topicId")) //Si el mensaje es del espacio subscrito
							&& payload.getString("category").equals("chat") // y si el evento es un chat
							&& payload.getJSONObject("content").getString("bodyText").toUpperCase().contains("@GRUPO2")) { //y si el mensaje contiene @bot 
						
						//Obtenemos el mensaje de spaces
						String msgFromSpaces = payload.getJSONObject("content").getString("bodyText").toUpperCase().replace("@GRUPO2", "");
						//Generamos un sessionId para DF concatenando idEspacio con idUsuarioSender, asi aseguramos una session unica por usuario hablando con el bot
						String sessionId = payload.getString("topicId")+payload.getJSONObject("sender").getString("_id");
						//Obtenemos el nombre del sender
						String sender = payload.getJSONObject("sender").getString("displayname");
						
						ArrayList<String> texto = new ArrayList<String>();
						texto.add(msgFromSpaces);
						
						//Obtenemos el fulfillmentetext a travez de las funciones de DF
						Map<String, QueryResult> respuesta = new DialogflowUtils().GetIntentText(Constantes.PROJETC_ID, texto, sessionId, "es-US");
						String fulfillmenttext = respuesta.get(msgFromSpaces).getFulfillmentText();
						
						//Obtenemos el payload para enviar un mensaje por el socket
						JSONObject payloadMsg = GetPayloadSendMessageSPaces("&#129302; <b>@"+sender+":</b> "+fulfillmenttext, payload.getString("topicId"));
						mSocket.emit("SEND_MESSAGE", payloadMsg);
					}					
				} catch(Exception e) {
					// TODO Auto-generated catch block
					System.out.println("sent message exception: " + e.getMessage());
				}
			}

		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Socket desconectado.");
			}

		}).on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {

			@Override
			public void call(Object... args) {
				System.out.println("Socket connect failed.");
				mSocket.disconnect();
			}

		});
		mSocket.connect();
	}
	
	/**
	 * Funcion para subscripcion a un espacio
	 * @param topicid , Id del espacio a subscribir
	 * @param socket , instancia del socket conectado a Spaces
	 */
	private static void SubscribeToChannel(String topicid, Socket socket) {
		JSONObject json = new JSONObject();
		JSONObject channel = new JSONObject();
		try {
			channel.put("_id", topicid).put("type", "topic");
			json.put("channel", channel);
			socket.emit("SUBSCRIBE_CHANNEL", json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Funcion para obtener el payload (JSONObject) necesario para mandar un mensaje a SPaces
	 * @param text , texto a mandar en el mensaje
	 * @param topicid , id del espacio al que se mandara el mensaje
	 * @param userId , id del usuario que manda el mensaje
	 * @return JSONObject con el payload de mensaje
	 */
	private static JSONObject GetPayloadSendMessageSPaces(String text, String topicid) {
		System.out.println("json without senderid for topic: " + topicid);
		String msg = "{\r\n" + 
				"    \"content\": {\r\n" + 
				"       \"bodyText\": \""+text.replace("\"", "\\\"")+"\"\r\n" +
				"    },\r\n" + 
				"    \"category\": \"chat\",\r\n" + 
				"    \"topicId\": \""+topicid+"\"\r\n" + 
				"}";
		try {
			JSONObject json = new JSONObject(msg);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
