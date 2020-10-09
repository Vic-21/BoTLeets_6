package utils;

public class Constantes {
	
	//Spaces
	
	/**
	 * Token spaces
	 */
	public static String SPACES_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkX3NpZyI6InRlb0w2ei02ckRHeF96ekJFUnY3VmVDeUpCMzRDTGtEc21LZXNFZzc1clUiLCJwcm9kdWN0X3R5cGUiOiJhY2NvdW50cyIsImxhc3R1cGRhdGV0aW1lIjoiMjAyMC0xMC0wNVQyMDowMjo1Ni45MzQiLCJpc3MiOiJhdmF5YWNsb3VkLmNvbSIsInB1YmxpY2tleWlkIjoiYWd4emZtOXVaWE51WVRJd01UUnlHZ3NTRFVkS2QzUlFkV0pzYVdOTFpYa1lnSUN3N1BpY2lna00iLCJleHAiOjE2MDQ4NTQ2MjMsInVzZXJfaWQiOiJhZ3h6Zm05dVpYTnVZVEl3TVRSeUVRc1NCRlZ6WlhJWWdJQ3dqT3kxNGdrTSIsInZlciI6IjIuMCJ9.SpWkBYelyflycD14fzytPKSgOPROd9JonzzFohMgSUvW4iN6JWBLK4ZmUaBSON3M2X2qq11WeyOVs006f9-RnU4T9-wfbfqZsR1u8ziM-mVDxP1hJmPTqQwkj-tbaGeLvcyXCR0hFEDsT_I6UvWFAFjDE4kZlcTbHdeC-ovLospBjabojskL0Kfwa4K8UvnFIW6-wHR82k9lB-HbVzccJPIrSIY_KmG1Xnd03W8DSd5UpjkGwDbX652Pxo8HS9mCUeMkECNjTzFWtjoRsdcWMlxkM6XZnj2_V_O2MgDHKWVS9Q67aBs29ugT_G5UUKRzJrCTIPLPJhCNofhjV271Yw";
	
	/**
	 * Id del spacio donde se publicaran los chat/tasks
	 */
	public static String SPACE_ID = "5f725fac3c208b159773bc8d";
	
	
	//Dialogflow
	
	/**
	 * Project id del agente de Dialogflow, debe coincidir con el projectId que se encuentra en el json.key de resources
	 */
	public static String PROJETC_ID = "primer-ejemplo-yqfm";

}
/*"type": "service_account",
   *"project_id": "primer-ejemplo-yqfm",
    "private_key_id": "08a398dc3af390492dec209747a7d429310ec19f",
    "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuK5Acvm0N0cvQ\nu85P29zGCOcA2Wbf96W1kqPw7SVCB0MuwTNQAlGbfoPZhItnabjLhhMZmSfWbn4U\ntKLCsxOaIq4iHjhqXrz6qT+9j/hege8O9L67UGxzENQf4tqwAt+JaAVqTBotyrUI\nlZ/ePAAmtJYbvoDEXM3qO/60FbN+442ERh+WguCbcR4KZpdsdengYVzi7DOdIoFY\n34h0bfnhN4ULAd0opw+G3k6BKTJXrJAMMoAlLrSWRexWx57lbRFqqcNuG3pY4jcH\njreglvBcxicEcv6Rytt5i3TY5MYMxUBzj+cHXLAtD++mQtEQDdrHYuKnlu2Pod6a\n0a8HHWE3AgMBAAECggEAB3VEFtxXTSKGXhTSBny9XRmKfXB8cEC+2XdDCpENFGES\nkRD4LTKRjggChgZKoI4CuhcEAFzyO24V0LXUy7jhW6tP1j7XCZmu8UjdZYpMXLem\n+0v4UuBkacpMWw/axMi1YRaRjMtfdPCGL1eWNez50K57nbUwTGLw9QPGqgLNNzSz\nA6yJz75EQ12AFObhFsIk7ddCsjW+PLe5pJKTCjTgu5FhxaY6aUIfDTLdIXFmh4NA\nZaeR/OnSGv2BfV1ja+bRxMGEQ+LxEXearlMPj6yCAj9S29DfNnyspDOBvEitwy2t\ndA9As2dlukBqPa48ycUrP3yceMMLbyrCvDNHFHUyGQKBgQDzgdz4yWpaNs0OLHZe\nYSh5dCNjtgXJWKnAMF1Psqf/rpty23hc8BCAMeyfg3aA+UA02BKzgqIv5SxNaUU/\nTxJG/Djq2bkwtSYwWD+QmZLQ4gDpj/SlnkEP/+4hxnQs/1mJjErj3Ss6fwN+8hZl\nujla1ZukT6vC8pPoWIKltTnGbQKBgQC3Gw0YLg7TDaq1+fJ5TDN4Jc0c5D9BU9S0\nPQOMo/tJfW6M8AQZGHWxDgqtxvJWjMD1Ochw6Zka87pPg/Ij3wGiydmRNQHmeWBd\nWjjvYCoR6Of8bmf+0khoPJLHZkQEMNXuRNRf8FGmLmpqP6tjZfMt4Dc1GJAzMxEi\nHC06a+xPswKBgQDh+EQoNzlTiIFXwtkBkmXt92GT+Wl7RKUabYdaGy+drNoihxo2\nHK0jWbD9AhEpdKQppc4M+ZAqpCAtoiAZtPYAG7Vx8yPQ2Hoc6Pfr5ygAYYp5NBbS\nzn4QK+8/Npu+2iOr6h3hQPAJs6550owIUP1ClvF9VFXPbi7lsaVjwz5fLQKBgQCV\nIxhYQHNcnb4UeB9xgopNauSUiqemyuPtZeC1NX7fzaU0b+22jwCVFG3IOsl5iy1C\nzyyeIvXMSQXP7BVV+DZ549QsfDMvCF9fybOj7Px3F++T4E0d9SJ6nBOlC3uMukYU\nysT72151b0rHfVJYJFqcXAo7WeVhfk+PK8n+0pZoFwKBgEpqmiX3XPeNOBYRJmwP\n6H/CfpdEXBKaRbi/O24s6qlLWPyqCQpqMIYBjArCY5WHCCVCuzP/mnxhPKGL+8Pf\nSluMgRJQvVSDTeazqwJut+ulRsk47pXsafvFW0to0e8jTxKqSdtRDgO18vvHJrpr\nnwPlaWRg4vEiDpNNjLdaxq9C\n-----END PRIVATE KEY-----\n",
    "client_email": "dialogflow-tnrokn@primer-ejemplo-yqfm.iam.gserviceaccount.com",
    "client_id": "101052481022553404882",
    "auth_uri": "https://accounts.google.com/o/oauth2/auth",
    "token_uri": "https://oauth2.googleapis.com/token",
    "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
    "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/dialogflow-tnrokn%40primer-ejemplo-yqfm.iam.gserviceaccount.com"
  }*/