package matchersExample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FriendshipsHamcrestTest {

    private Friendships friendships;

    @BeforeEach
    public void setUp() throws Exception {
        friendships = new Friendships();
    }

    @Test
    public void createInstance(){
        assertThat(friendships.getClass(), is(Friendships.class));
    }

    @Test
    public void GetFriendListOfNull(){

        assertThatThrownBy(() -> friendships.getFriendsList(null))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void GetFriendListOfEmpty(){
        assertThat(friendships.getFriendsList(""), is(empty()));
    }

    @Test
    public void GetFriendListOfAThatHaveFriendB(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.getFriendsList("A"), contains("B"));
        assertThat(friendships.getFriendsList("B"), contains("A"));
    }
    @Test
    public void GetFriendListOfAThatHaveFriendBAndC(){
        friendships.makeFriends("A", "B");
        friendships.makeFriends("A", "C");
        assertThat(friendships.getFriendsList("A"), containsInAnyOrder("B", "C"));
    }

    @Test
    public void AreFriendsOfEmptyFriendList(){
        assertThat(friendships.areFriends("B", "A"), is(false));
    }

    @Test
    public void AreFriendsOfEmptyStrings(){
        assertThat(friendships.areFriends("", ""), is(false));
    }
    @Test
    public void AreFriendsOfNullStrings(){
        assertThat(friendships.areFriends(null, null), is(false));
    }
    @Test
    public void AreFriendsOfSingleNullString(){
        assertThat(friendships.areFriends(null, "A"), is(false));
        assertThat(friendships.areFriends("A", null), is(false));
    }
    @Test
    public void AreFriendsOfSingleEmptyString(){
        assertThat(friendships.areFriends("A", ""), is(false));
        assertThat(friendships.areFriends("", "A"), is(false));
    }

    @Test
    public void AreFriendsOfNonExistingPersonPerson1(){
        friendships.makeFriends("B", "C");
        assertThat(friendships.areFriends("A", "B"), is(false));
        assertThat(friendships.areFriends("D", "C"), is(false));
    }

    @Test
    public void AreFriendsOfReversedShouldntChangeOutput(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.areFriends("A", "B"), is(true));
        assertThat(friendships.areFriends("B", "A"), is(true));
    }

    @Test
    public void AreFriendsOfNotExisting(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.areFriends("C", "D"), is(false));
        assertThat(friendships.areFriends("E", "F"), is(false));
    }

    @Test
    public void AreFriendsOfSingleNotExisting(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.areFriends("C", "B"), is(false));
        assertThat(friendships.areFriends("A", "C"), is(false));
    }

    @Test
    public void MakeFriendsOfNull(){
        assertThatThrownBy(() -> friendships.makeFriends(null, null))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void MakeFriendsOfSingleNull(){
        assertThatThrownBy(() -> friendships.makeFriends("A", null))
                .isInstanceOf(InvalidParameterException.class);
        assertThatThrownBy(() -> friendships.makeFriends(null, "A"))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void MakeFriendsOfEmpty(){
        assertThatThrownBy(() -> friendships.makeFriends("", ""))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void MakeFriendsOfSingleEmpty(){
        assertThatThrownBy(() -> friendships.makeFriends("", "A"))
                .isInstanceOf(InvalidParameterException.class);
        assertThatThrownBy(() -> friendships.makeFriends("A", ""))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void AddingMoreFriendsToAShouldIncreaseItsSize(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.getFriendsList("A"), is(contains("B")));
        friendships.makeFriends("A", "C");
        assertThat(friendships.getFriendsList("A"), is(containsInAnyOrder("B", "C")));
    }

    @Test
    public void AddingItselfAsFriendShouldResultInEmptyFriendList(){
        friendships.makeFriends("A", "A");
        assertThat(friendships.getFriendsList("A"), is(hasSize(0)));
    }

    @Test
    public void AddingItselfAsFriendAndAddingFriendShouldBeTheSameAsAddingFriend(){
        friendships.makeFriends("A", "A");
        friendships.makeFriends("A", "B");
        List<String> friendsListA = friendships.getFriendsList("A");
        Friendships newFriendships = new Friendships();
        newFriendships.makeFriends("A", "B");
        List<String> friendsListAOfNew = friendships.getFriendsList("A");
        assertThat(friendsListA, is(containsInAnyOrder(friendsListAOfNew.toArray(new String[0]))));
    }

    @AfterEach
    public void tearDown() throws Exception {
        friendships = null;
    }

}