package com.slimechan.raceway_system.console;

import com.slimechan.raceway_system.manages.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Scanner;

@Service
public class ReaderThread extends Thread{

    @Autowired
    UserManager manager;

    private boolean run;
    private Scanner scan;

    public ReaderThread(){
        scan = new Scanner(System.in);
    }

    @Override
    public void run(){
        run = true;
        while(run){
            if(scan.hasNextLine()){
                String[] s = scan.nextLine().split(" ");
                process(s[0], Arrays.copyOfRange(s, 1, s.length));
            }
        }
    }

    public void end(){
        run = false;
    }

    public void process(String cmd, String[] args){
        switch (cmd){
            case "useradd":
                if(args.length>1){
                    // useradd login pass f i o
                    manager.addUser(args[0], args[1], args[2]+" "+args[3]+" "+args[4]);
                    System.out.println("User "+args[0]+" created");
                }
                break;
            case "stop":
                System.out.println("Stopping app...");
                end();
                System.exit(-1);
                break;
        }
    }
}
