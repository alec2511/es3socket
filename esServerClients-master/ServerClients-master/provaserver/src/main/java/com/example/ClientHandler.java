package com.example;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientHendler extends Thread {
    
    private static int numeroUtente = 0;
    private Socket s;
    private String nomeS;
    private ArrayList<Socket> arrC = new ArrayList<Socket>();

    public ClientHendler(Socket s, String nomeServer) {
        this.s = s;
        nomeS= nomeServer;
        arrC.add(s);
    }

    public void run() {
        try {
            // parlare
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

            // ascoltare
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            pr.println("Ciao scrivi il tuo nome?");
            String nome = br.readLine();
            nome.toUpperCase();
            numeroUtente++;
            int identificativo = numeroUtente;
            pr.println("Benvenuto: " + nomeS + " sei l'utente numero " + identificativo);
            Boolean giro = true;
            String menu = "--Menu selezione--
             @Digita 1 per vedere la data e ora 
             @Digita 2 per vedere in nome del server 
             @Digita 3 per vedere il tuo id 
             @Digita 0 per chiudere la connessione con il server";
            pr.println(menu);
            while (giro) {
                String ricevuto = br.readLine();
                int scelta = Integer.parseInt(ricevuto);
                switch (scelta) {
                    case 1:
                        LocalDateTime t = LocalDateTime.now();
                        pr.println("Data : " + t.getDayOfMonth() + "/" + t.getMonth() + "/" + t.getYear()
                                + " Ora: " + t.getHour() + ":" + t.getMinute() + ":" + t.getSecond());
                        break;
                    case 2:
                        pr.println(nomeS);
                        break;
                    case 3:
                        pr.println(identificativo);
                        break;
                    case 0:
                        pr.println("Arrivederci " + nomeS);
                        giro = false;
                        break;
                }
            }
            s.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}