package ru.yandex.practicum.filmorate.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.yandex.practicum.filmorate.model.User;

public class UserService {

    private Map<Integer, ArrayList<User>> friends= new HashMap<>();
    private List<User> currentFriends;

    //берём список текущих друзей
    private List currentUserFriendsList(User userMain){
        currentFriends.clear();
        currentFriends =friends.get(userMain);
        return currentFriends;
    }
    //проверяем на наличие друга
    private boolean checkListForFriend(User friend){
        if (currentFriends.contains(friend)) {

            return true;
        }
        return false;
    }
//добавление в друзья
    public User addFriend(User userMain, User userAdd){
        currentUserFriendsList(userMain);
        if (checkListForFriend(userAdd)){
            return null;
        }
        else{
            currentFriends.add(userAdd);
        return userAdd;}
    }
    //удаление из друзей
    public User deleteFriend(User userMain, User userAdd){
        currentUserFriendsList(userMain);
        if (checkListForFriend(userAdd)){
            return null;
        }
        else{
            currentFriends.remove(userAdd);
            return userAdd;}
    }
    //вывод общих друзей
    public List commonFriends(User user1, User user2){
       List<User>  listUser1=currentUserFriendsList(user1);
        List<User>  listUser2=currentUserFriendsList(user2);
        List<User> commonList= new ArrayList<>();
        for (User user:listUser1){
            if (listUser2.contains(user)){commonList.add(user);
        }
           }
        return commonList;
    }
}
