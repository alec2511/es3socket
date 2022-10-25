package com.example;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server {
    private static String nomeS = "serverGjinaj";

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(3000);
            System.out.println("Server in ascolto sulla porta 3000");
            for (;;) {
                Socket sock = ss.accept();
                System.out.println("Client connesso");
                ClientHendler Client = new ClientHendler(sock, nomeS);
                Client.start();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}