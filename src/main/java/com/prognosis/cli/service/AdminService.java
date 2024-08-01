package com.prognosis.cli.service;

import java.util.UUID;

import com.prognosis.cli.model.User.Role;
import com.prognosis.cli.utils.BashRunner;

public class AdminService {

    private final BashRunner bashRunner = new BashRunner();

    public Integer countUsers(){
      String output = this.bashRunner.execute("count_users.sh", null);
      Integer numOfUsers = Integer.parseInt(output.trim());
      return numOfUsers;
    }

    public String createUser(String email) {
        Integer usersCount = this.countUsers();
        String nextUserId = (++usersCount).toString();
        String code = UUID.randomUUID().toString();

        String[] args = { nextUserId, Role.PATIENT.toString(), email, code };
        this.bashRunner.execute("create_user.sh", args);

        return code;
    }
    
}
