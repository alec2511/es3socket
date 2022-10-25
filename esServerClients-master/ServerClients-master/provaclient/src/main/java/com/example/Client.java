package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class Client {
    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 3000);

        // parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

        // ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(br.readLine());
        pr.println(tastiera.readLine());
        System.out.println(br.readLine());

        Boolean giro = true;
        String controllo = br.readLine();
        controllo = controllo.replace('@','\n');
        System.out.println(controllo);

        while (giro) {
            String manda = tastiera.readLine();
            int scelta = Integer.parseInt(manda);
            pr.println(scelta);
            System.out.println(br.readLine() + " ecco la risposta");
            if (scelta == 3) {
                giro = false;
            }
        }
        s.close();
    }
}