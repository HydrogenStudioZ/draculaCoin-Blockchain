package blockChain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
public class Wallet {

	public PrivateKey privatekey;
	public PublicKey publickey;
	
Wallet(){
	
	generateKeyPair();
}
public void generateKeyPair() {
	try {
	KeyPairGenerator k =KeyPairGenerator.getInstance("ECDSA","BC");
	SecureRandom random =SecureRandom.getInstance("SHA1PRNG");
	
	ECGenParameterSpec ecSpec =new ECGenParameterSpec("prime192v1");
	
	k.initialize(ecSpec,random);
	//this specification is now passed along 
	KeyPair keypair = k.generateKeyPair();
	privatekey=keypair.getPrivate();
	publickey=keypair.getPublic();
	
	
	}
	catch(Exception e) {
		throw new RuntimeException(e);
	}
	
	
}

}
