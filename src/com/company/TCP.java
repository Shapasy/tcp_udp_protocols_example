package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCP{
    TCP(String server_msg){
        System.out.println("TCP  \uD83C\uDF69 :-");
        TCP_Server server = new TCP_Server(server_msg);
        TCP_Client client = new TCP_Client();
        server.start();
        client.start();
    }
}

class TCP_Server extends Thread{
    String msg;
    TCP_Server(String msg){
        System.out.println("TCP Server is created \uD83C\uDFAF");
        this.msg = msg;
    }

    @Override
    public void run(){call();}

    private void call(){
        try {
            ServerSocket server_socket = new ServerSocket(1234);
            Socket client_socket = server_socket.accept();
            System.out.println("Session between server and client is created \uD83D\uDD0C");
            DataOutputStream data_out = new DataOutputStream(client_socket.getOutputStream());
            data_out.writeUTF(this.msg);
            client_socket.close();
        }catch (Exception e){ System.out.println("Error From Server Side ❌");}
    }
}

class TCP_Client extends Thread{
    TCP_Client(){
        System.out.println("TCP Client is created \uD83C\uDFAF");
    }

    @Override
    public void run() { call();}

    private void call(){
        try{
            Socket client_socket = new Socket("localhost",1234);
            DataInputStream data_in = new DataInputStream(client_socket.getInputStream());
            String msg_from_server = new String(data_in.readUTF());
            System.out.println("Client recived : "+msg_from_server);
            client_socket.close();
        }catch (Exception e){System.out.println("Error From Client Side ❌");}
    }
}


