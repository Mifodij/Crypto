package Crypt;

/**
 * Класс для расчета Hash из полученной строки
 */


public class Hash_data {

    /**
     * Метод для получения хеша из строки данных
     * @param txt       - переменная которая содержит в себе хэшируемую строку
     * @param hashType  - переменная определяющая тип хэша
     * @return возвращаем строку в которой содержится хэш значение
     */

    public static String Hash_data (String txt, String hashType){


        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            //error action
        }
        return null;

    }


}
