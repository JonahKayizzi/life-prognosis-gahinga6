package com.prognosis.cli.service;

import com.prognosis.cli.utils.BashRunner;

public class AdminService {

    private final BashRunner bashRunner = new BashRunner();

    public Integer countUsers(){
      String output = this.bashRunner.execute(" .sh", null);
      Integer numOfUsers = Integer.parseInt(output.trim());
      return numOfUsers;
    }
    
}
