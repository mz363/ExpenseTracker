package com.mz363.FinanceManager.dao;

import com.mz363.FinanceManager.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("localDao")
public class LocalUserDataService implements UserDao {

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(new User(id, user.getName()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> user = selectUserById(id);
        if(user.isPresent()) {
            DB.remove(user.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUserById(UUID id, User userUpdate) {
        return selectUserById(id)
                .map(user -> {
                    int indexOfUser = DB.indexOf(user);
                    if(indexOfUser >= 0) {
                        DB.set(indexOfUser, new User(id, userUpdate.getName()));
                        return 1;
                    }
                    return 0;
                })
        .orElse(0);
    }

}
