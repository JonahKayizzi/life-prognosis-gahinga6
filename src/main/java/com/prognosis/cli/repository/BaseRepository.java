package com.prognosis.cli.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BaseRepository {
    private static String runCommand(String fileName, String argString) {
        String line = null;

       
        try{
            Process process = Runtime.getRuntime().exec(String.format("%s/scripts/%s %s",System.getProperty("user.dir"), fileName, argString));

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line =  reader.readLine();

        } catch  (Exception e) {
            e.printStackTrace();
        }

        return line;
    }


    protected static String runBashCommand (String fileName, String[] args) {
        String argString = Arrays.stream(args)
                              .map(String::valueOf) 
                              .collect(Collectors.joining(" "));

        return BaseRepository.runCommand(fileName, argString);
    }

    protected static String runBashCommand (String fileName) {
        return BaseRepository.runCommand(fileName, "");
    }
}
