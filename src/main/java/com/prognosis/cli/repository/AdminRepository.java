package com.prognosis.cli.repository;

import java.util.UUID;

public class AdminRepository extends BaseRepository {
    public  static Integer countUsers (){
      String output = AdminRepository.runBashCommand("count_users.sh");
      Integer numOfUsers = Integer.parseInt(output.trim());
      return numOfUsers;
    }

    public static String createUser(String email) {
        Integer usersCount = AdminRepository.countUsers();
        String nextUserId = (++usersCount).toString();
        String code = UUID.randomUUID().toString();

        String[] args = { nextUserId, email, code };
        


        // String[] command = {"bash", "-c", String.format("",nextUserId, email, code)};

        try{
           AdminRepository.runBashCommand("create_users.sh", args);
        }catch  (Exception e) {
            e.printStackTrace();
        }

        return code;
    }
    
}
