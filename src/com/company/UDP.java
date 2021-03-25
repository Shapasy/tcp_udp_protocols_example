package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class UDP{
    UDP(String msg){
        System.out.println("UDP \uD83C\uDF69 :-");
        com.company.UDP_Server server = new com.company.UDP_Server(msg);
        com.company.UDP_Client client = new com.company.UDP_Client();
        server.start();
        client.start();
    }

}
class UDP_Server extends Thread{
    String msg;
    UDP_Server(String msg){
        System.out.println("UDP Server is created \uD83C\uDFAF");
        this.msg = msg;
    }

    @Override
    public void run() { call();}

    private  void call(){
        try {
            DatagramSocket socket = new DatagramSocket(1234);
            DatagramPacket datagramPacket = new DatagramPacket(
                    this.msg.getBytes(),
                    this.msg.length(),
                    InetAddress.getLocalHost(),
                    1235
            );
            socket.send(datagramPacket);
        }catch(Exception e){System.out.println("Error From Server Side ❌");}
    }

}

class UDP_Client extends Thread{

    UDP_Client(){
        System.out.println("UDP Client is created \uD83C\uDFAF");
    }

    @Override
    public void run() { call();}

    private  void call(){
        try (DatagramSocket socket = new DatagramSocket(1235)) {
            byte[] buffer = new byte[65507];
            socket.setSoTimeout(1500);
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                socket.receive(packet);
                String receivedMessage = new String(packet.getData());
                System.out.println("Client Recived : "+receivedMessage);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout, Client closed ⚔");
        }

    }
}