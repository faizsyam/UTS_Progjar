/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bacafile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baskoro
 */
public class BacaFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            byte[] buf = new byte[30];
            
            Socket socket = new Socket("10.151.34.155", 6666);
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
                        
            os.write("username:5113100111\n".getBytes());
            os.flush();
            int len,n1=0,n2=0, flag=0;
            String message, question = null;
            while(true) {
                buf = new byte[20];
                len = is.read(buf);
                if(len == -1) {
                    System.out.print("KELUAR!"); 
                    break;
                }
               
                message=new String(buf);
                System.out.print(message); 
                int id=1;
                while(true){
                    
                    //System.out.print("["+message.substring(id,id+1)+"]\n");
                    if(message.substring(id,id+1).equals(" ")){
                        n1 = Integer.parseInt(message.substring(1,id));
                        System.out.print("KETEMU!!: ");
                        System.out.println(n1);
                        if(n1 > 0){
                            int id2=id+1;
                            while(true){
                                if(message.substring(id2,id2+1).equals(" ")){
                                    String op = message.substring(id+1,id2);
                                    System.out.println("OPERATOR: "+op);
                                    int id3=id2+1;
                                    while(true){
                                        if(message.substring(id3,id3+1).equals(" ")){
                                            n2 = Integer.parseInt(message.substring(id2+1,id3));
                                            System.out.print("KETEMU LAGI!!: ");
                                            System.out.println(n2);
                                            
                                            int n3=0;
                                            System.out.println(n1);
                                            System.out.println(n2);
                                            switch (op) {
                                                case "+":
                                                    n3=n1+n2;
                                                    break;
                                                case "-":
                                                    n3=n1-n2;
                                                    break;
                                                case "x":
                                                    n3=n1*n2;
                                                    break;
                                                case "mod":
                                                    n3=n1%n2;
                                                    break;
                                            }
                                            System.out.print("HASIL: ");
                                            System.out.println(n3);
                                            os.write("RESULT:".getBytes());
                                            os.write(n3);
                                            os.write("\n".getBytes());
                                            flag=1;
                                            break;
                                        }
                                        id3=id3+1;
                                    }
                                    if(flag==1)break;
                                }
                                id2=id2+1;
                            }
                        }
                        if(flag==1)break;
                    }
                    //System.out.println("TEST1");
                    if(!message.substring(id,id+1).equals("0") && !message.substring(id,id+1).equals("2") && !message.substring(id,id+1).equals("3") && !message.substring(id,id+1).equals("4") && !message.substring(id,id+1).equals("5") && !message.substring(id,id+1).equals("6") && !message.substring(id,id+1).equals("7") && !message.substring(id,id+1).equals("8") && !message.substring(id,id+1).equals("9")){
                        //System.out.println("TEST2");
                        id=-1;
                        break;
                        
                    }
                    System.out.println("END");
                    id=id+1;
                }
                //System.out.print("@"+len+"@"); 
            }
            System.out.println("TEST");
            
            
            
            while(true) {
                buf = new byte[10];
                len = is.read(buf);
                if(len == -1) {
                    break;
                }
                message=new String(buf);
                System.out.print(message); 
            }
            os.close();
            is.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(BacaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String String(byte[] buf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
