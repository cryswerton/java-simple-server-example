import java.net.*;
import java.io.*;

public class server {
    public static void main(String[] args) throws IOException {
        int port = 8081;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("server is running on port: " + port);

        while(true){
            Socket clientSocket = serverSocket.accept();
            System.err.println("client connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String str;
            while((str = in.readLine()) != null){
                System.out.println(str);
                if(str.isEmpty()){
                    break;
                }
            }
            OutputStream clientOutput = clientSocket.getOutputStream();
            clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            clientOutput.write("\r\n".getBytes());
            clientOutput.write("<h1>Hello from Java server</h1>".getBytes());
            clientOutput.write("\r\n\r\n".getBytes());
            clientOutput.flush();
            System.out.println("client connection closed.");
            in.close();
            clientOutput.close();
        }
    }

}
