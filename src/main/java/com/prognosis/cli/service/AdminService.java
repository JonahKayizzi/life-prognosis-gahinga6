package com.prognosis.cli.service;

import com.prognosis.cli.utils.*;;

public class AdminService {

    private BashRunner bashRunner = new BashRunner();

    public Integer countUsers(){
      String output = this.bashRunner.execute("count_users.sh", null);
      Integer numOfUsers = Integer.parseInt(output.trim());
      return numOfUsers;
    }

    // public static String createUser(String email) {

    //     Integer usersCount = AdminRepository.countUsers();
    //     String nextUserId = (++usersCount).toString();
    //     String code = UUID.randomUUID().toString();

    //     String[] args = { nextUserId, email, code };
        


    //     // String[] command = {"bash", "-c", String.format("",nextUserId, email, code)};

    //     try{
    //        AdminRepository.runBashCommand("create_users.sh", args);
    //     }catch  (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return code;
    // }
    
}
