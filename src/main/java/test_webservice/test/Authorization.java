package test_webservice.test;

import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.RsaKeyUtil;
import org.jose4j.lang.JoseException;
import org.json.simple.parser.ParseException;

public class Authorization {
	/**
	 * @param token
	 *            token
	 * @param args
	 *            -
	 * @return boolean
	 * @throws ParseException
	 */
	public static String oAuthTokenValidator(String token) {
		String userName = null;
		if (token != null) {

			RsaKeyUtil rsaKeyUtil = new RsaKeyUtil();
			PublicKey publicKey = null;
			try {
				publicKey = rsaKeyUtil.fromPemEncoded("-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxjEEHNLBo2UnCLGiTf/p\nrIAHmwhw5oC+SWF+bh2xGjCq4vj2MrRKvHIzPX9pXZzmn+htahIW28RoNazHitgr\nwrWr+6rfbgNDgQ1b1N47qBiZzrRxzgcZFRx2OfP8MBXtvBT7tXlAJhdEWlJWUfGt\nCluPG49Rz4Sd4naoGEldpAKQ0I2EijVCGQh1vtEOeA5pfPCSfhO9A/DlT7kl4dso\ntdTocG9zDhGmx0+kaSiV9q+so9RnmSJWgLlM+5KalKyFFoK3PRgcLsjMf+DELD7F\n3XTNngFTjXwy+CFXbWCsAh7L8OeDdi9K452bb8JDKh/Z9pBtP80PBlSFP0NArAUx\nTQIDAQAB\n-----END PUBLIC KEY-----");
			} catch (InvalidKeySpecException | JoseException e1) {
				e1.printStackTrace();
			}
			JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime().setSkipDefaultAudienceValidation().setVerificationKey(publicKey).build();
			JwtClaims jwtDecoded = null;
			try {
				jwtDecoded = jwtConsumer.processToClaims(token);
				userName = jwtDecoded.getStringClaimValue("user_name");
			} catch (Exception e) {

				return null;
			}
			if (userName != null && !userName.equals("")) {
				return userName;
			}
		}

		return null;
	}
}
