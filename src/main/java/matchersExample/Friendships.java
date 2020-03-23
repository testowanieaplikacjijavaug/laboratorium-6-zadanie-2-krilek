package matchersExample;

import java.security.InvalidParameterException;
import java.util.*;

public class Friendships {
    Map<String, List<String>> friendships = new HashMap<>();

    //Dodanie przyjaciół - wykorzystuje funkcje addFriend!	
    public void makeFriends(String person1, String person2) {
        if(person1 == null || person1.isEmpty() || person2 == null || person2.isEmpty()){
            throw new InvalidParameterException();
        }
        addFriend(person1, person2);
	    addFriend(person2, person1);
    }
    
    //Pobranie listy przyjaciol
    public List<String> getFriendsList(String person) {
        if(person == null){
            throw new InvalidParameterException();
        }
        if(friendships.containsKey(person)){
            return friendships.get(person);
        }else{
            return new ArrayList<>();
        }
    }
    
    //Sprawdzenie czy przyjaciele
    public boolean areFriends(String person1, String person2) {
        if(person1 == null || person2 == null){
            return false;
        }
        List<String> friendsOfP1 = getFriendsList(person1);
        if(friendsOfP1 != null){
            return friendsOfP1.contains(person2);
        }

        return false;
    }
    //Dodanie do listy przyjaciol do danej osoby
    private void addFriend(String person, String friend) {
        if(!areFriends(person, friend)){
            if(friendships.containsKey(person)){
                if(!person.equals(friend)){
                    getFriendsList(person).add(friend);
                }
            }else{
                if(person.equals(friend)){
                    friendships.put(person, new ArrayList<>());
                }else{
                    ArrayList<String> l = new ArrayList<>();
                    l.add(friend);
                    friendships.put(person, l);
                }
            }
        }
    }
}