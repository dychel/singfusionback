package com.singfusion.singfusion.response;

import com.singfusion.singfusion.entity.Users;

import java.util.List;

public class UserResponse {

    public static ResponseMessage getMessageSaveUser(Users user) {

        if (user == null)
            return new ResponseMessage("chao", "User failed registered !", null);
        else
            return new ResponseMessage("ok", "User successfully registered !", user);
    }

    public static ResponseMessage getMessageUpdateUser(Users user) {

        if (user == null)
            return new ResponseMessage("chao", "User failed Updated !", null);
        else
            return new ResponseMessage("ok", "User successfully updated !", user);
    }

    public static ResponseMessage getMessageListUsers(List<Users> userList) {

        if (userList.isEmpty())
            return new ResponseMessage("chao", "User list empty.", null);
        else
            return new ResponseMessage("ok", "User List", userList);
    }

    public static ResponseMessage getMessageDeleteUserById() {
        return new ResponseMessage("ok", "User successfully deleted !", null);
    }
}
