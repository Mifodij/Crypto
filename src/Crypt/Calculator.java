package Crypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Calculator {
    static String[] args;
    private JPanel Calculator;
    private JComboBox comboBox1;
    private JTextField TextField;
    private JButton button1;
    private JTextField FileOut;
    private JTextField FileIn;
    private JTextField PasswordToCrypt;
    private JComboBox EncrDecrSwitch;
    private JButton Encrypt_Decrypt;


    public static void main(String[] args) {
        //Calculator.args = args;
        JFrame jf = new JFrame("Hashcalc");
        jf.setContentPane(new Calculator().Calculator);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setTitle("Calculator");
        jf.setLocationRelativeTo(null);
        jf.pack();
        jf.setVisible(true);
    }

    /**
     * Конструктор класса и работы самого калькулятора
     */






    public Calculator() {
        /**
         * Действие на нажатие кнопки расчет хэша
         * @param actionEvent
         */
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String s;
                int selected;
                s = TextField.getText();
                String out = "Another algoritms";
                selected = comboBox1.getSelectedIndex();
                //System.out.println("selected: = "+selected);
                if(selected == 0){
                    out = Hash_data.Hash_data(s,"MD5");
                }
                else if(selected == 1){
                    out = Hash_data.Hash_data(s,"SHA-1");
                }
                else if(selected == 2){
                    out = Hash_data.Hash_data(s,"SHA-256");
                }
                else if(selected == 3){
                    out = Hash_data.Hash_data(s,"SHA-512");
                }
                else if(selected == 4){
                    out = Hash_data.Hash_data(s,"SHA-224");
                }
                else if(selected == 5){
                    out = Hash_data.Hash_data(s,"SHA-384");
                }else{
                    JOptionPane.showMessageDialog(null,"Not supported algorithm");
                }

                JOptionPane.showMessageDialog(null,out);
                //System.out.println(out);



            }
        });
        Encrypt_Decrypt.addActionListener(new ActionListener() {
            /**
             * Действие на нажатие кнопки шифрование расшифрования
             * @param actionEvent
             */

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String in = FileIn.getText();
                if(in.equals("")){
                    JOptionPane.showMessageDialog(null,"File_in field is empty");
                }
                String out= FileOut.getText();
                if(out.equals("")){
                    JOptionPane.showMessageDialog(null,"File_out field is empty");
                }
                AES aes = new AES();
                String Pass = PasswordToCrypt.getText();

                try(InputStream io = new FileInputStream(in);
                    BufferedInputStream bis = new BufferedInputStream(io, 1_000_000);
                    OutputStream out_st = new FileOutputStream(out);
                    BufferedOutputStream bos = new BufferedOutputStream(out_st, 1_000_000)){

                    byte [] array = new byte[bis.available()];
                    bis.read(array);
                    //System.out.println(new String(array));

                    int select = EncrDecrSwitch.getSelectedIndex();
                    String out_Massage="Some troubles";
                    byte[] rez =null;
                    if(select == 0){
                        rez = aes.Encrypt(array, Pass);
                        out_Massage = "Encryption is done";
                    }else if(select == 1){
                        rez = aes.Decrypt(array, Pass);
                        out_Massage = "Decryption is done";
                    }

                    bos.write(rez);
                    bos.flush();

                    JOptionPane.showMessageDialog(null, out_Massage);


                }catch(FileNotFoundException ext){
                    System.out.println("File in that put:"+in+" not found");
                }
                catch (IOException ex){
                    System.out.println("Error reading/writing File");
                    ex.printStackTrace();
                }

            }





        });
    }




}


