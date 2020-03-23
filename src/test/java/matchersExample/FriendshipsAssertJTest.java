package matchersExample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FriendshipsAssertJTest {

    private Friendships friendships;

    @BeforeEach
    public void setUp() throws Exception {
        friendships = new Friendships();
    }

    @Test
    public void createInstance(){
        assertThat(friendships.getClass()).isEqualTo(Friendships.class);
    }


    @Test
    public void GetFriendListOfNull(){
        assertThatThrownBy(() -> friendships.getFriendsList(null))
                .isInstanceOf(InvalidParameterException.class);
    }

    @Test
    public void GetFriendListOfEmpty(){
        assertThat(friendships.getFriendsList("")).isEmpty();
    }

    @Test
    public void GetFriendListOfAThatHaveFriendB(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.getFriendsList("A")).containsExactly("B");
        assertThat(friendships.getFriendsList("B")).containsExactly("A");
    }
    @Test
    public void GetFriendListOfAThatHaveFriendBAndC(){
        friendships.makeFriends("A", "B");
        friendships.makeFriends("A", "C");
        assertThat(friendships.getFriendsList("A")).containsExactlyInAnyOrder("B", "C");
    }

    @Test
    public void AreFriendsOfEmptyFriendList(){
        assertThat(friendships.areFriends("B", "A")).isFalse();
    }

    @Test
    public void AreFriendsOfEmptyStrings(){
        assertThat(friendships.areFriends("", "")).isFalse();
    }
    @Test
    public void AreFriendsOfNullStrings(){
        assertThat(friendships.areFriends(null, null)).isFalse();
    }
    @Test
    public void AreFriendsOfSingleNullString(){
        assertThat(friendships.areFriends(null, "A")).isFalse();
        assertThat(friendships.areFriends("A", null)).isFalse();
    }
    @Test
    public void AreFriendsOfSingleEmptyString(){
        assertThat(friendships.areFriends("A", "")).isFalse();
        assertThat(friendships.areFriends("", "A")).isFalse();
    }

    @Test
    public void AreFriendsOfReversedShouldntChangeOutput(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.areFriends("A", "B")).isTrue();
        assertThat(friendships.areFriends("B", "A")).isTrue();
    }

    @Test
    public void AreFriendsOfNotExisting(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.areFriends("C", "D")).isFalse();
        assertThat(friendships.areFriends("E", "F")).isFalse();
    }

    @Test
    public void AreFriendsOfSingleNotExisting(){
        friendships.makeFriends("A", "B");
        assertThat(friendships.areFriends("C", "B")).isFalse();
        assertThat(friendships.areFriends("A", "C")).isFalse();
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
        assertThat(friendships.getFriendsList("A")).containsExactly("B");
        friendships.makeFriends("A", "C");
        assertThat(friendships.getFriendsList("A"))
                .hasSize(2)
                .containsExactly("B", "C");
    }

    @Test
    public void AddingItselfAsFriendShouldResultInEmptyFriendList(){
        friendships.makeFriends("A", "A");
        assertThat(friendships.getFriendsList("A")).hasSize(0);
    }

    @Test
    public void AddingItselfAsFriendAndAddingFriendShouldBeTheSameAsAddingFriend(){
        friendships.makeFriends("A", "A");
        friendships.makeFriends("A", "B");
        List<String> friendsListA = friendships.getFriendsList("A");
        Friendships newFriendships = new Friendships();
        newFriendships.makeFriends("A", "B");
        List<String> friendsListAOfNew = friendships.getFriendsList("A");
        assertThat(friendsListA)
                .containsExactlyInAnyOrder(friendsListAOfNew.toArray(new String[0]));
    }

    @AfterEach
    public void tearDown() throws Exception {
        friendships = null;
    }
}
