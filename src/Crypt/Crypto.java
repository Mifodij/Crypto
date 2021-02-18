package Crypt;




public interface Crypto {

    public void setKey(String myKey);

    abstract String Encrypt(String data, String key);


    abstract String Decrypt(String data, String key);




}
