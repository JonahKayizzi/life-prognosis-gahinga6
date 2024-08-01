package com.prognosis.cli.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BashRunner {
    private String parseArgs(String[] args) {
        StringBuilder stringBuilder = new StringBuilder(100);

        for (String arg : args) {
            stringBuilder.append(arg);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    private String parseOutput(BufferedReader reader) {
        StringBuilder stringBuilder = new StringBuilder(1000);
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }


    public String execute(String fileName, String[] args) {
        String argString = args != null ? this.parseArgs(args): "";
        String line = null;

        try{
            Process process = Runtime.getRuntime().exec(String.format("%s/scripts/%s %s",System.getProperty("user.dir"), fileName, argString));

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line =  this.parseOutput(reader);

        } catch  (Exception e) {
            e.printStackTrace();
        }
        
        // try{       
        //     ProcessBuilder processBuilder = new ProcessBuilder();
        //     processBuilder.command("bash", "-c", String.format("%s/scripts/%s %s",System.getProperty("user.dir"), fileName, args[0],  args[1],  args[2] ));

        //     Process process = processBuilder.start();

        //     BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //     line = this.parseOutput(reader);
           
        //     process.waitFor();
        // } catch  (Exception e) {
        //     e.printStackTrace();
        // }

        return line;
    }  
}
