package com.morleystuff.multithreadcapitalizer.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(59091)) {
            ExecutorService pool = Executors.newFixedThreadPool(20);
            System.out.println("Server is running...");
            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }

    private static class Capitalizer implements Runnable {
        private Socket socket;

        Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {
                    out.println(in.nextLine().toUpperCase());
                }
            } catch (IOException e) {
                System.out.println("Error: " + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {

                }
                System.out.println("Closed: " + socket);
            }
        }
    }

}
