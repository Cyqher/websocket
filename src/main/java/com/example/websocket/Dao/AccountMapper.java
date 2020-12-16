package com.example.websocket.Dao;

import com.example.websocket.PO.Admin;
import com.example.websocket.PO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface AccountMapper {

    public int createNewUser(User user);

    public int createNewAdmin(Admin admin);

    public List<User> selectAllUsers();

    User getUserByName(@Param("name") String name);

    List<User> getUsersByTeamId(@Param("teamId")int teamId);

    User selectUserById(@Param("id") int id);

    List<Admin> selectAllAdmins();

    Admin getAdminByName(@Param("name") String name);

    Admin selectAdminById(@Param("id") int id);

}
