package matchersExample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

//
//    @Test
//    public void getArray(){
//        array.setList(Arrays.asList(1,2,3));
//        List <Integer> tempList = array.getList();
//        assertThat(tempList,contains(1,2,3));
//    }
//
//    @Test
//    public void getOneNumberOfArray(){
//        array.setList(Arrays.asList(1,2,3));
//        List <Integer> tempList = array.getList();
//        assertThat(tempList.get(0),is(1));
//    }
//
//    @Test
//    public void getContainsSquareList(){
//        array.setList(Arrays.asList(1,2,3));
//        array.square_list();
//        List <Integer> tempList = array.getList();
//        assertThat(tempList,containsInAnyOrder(1,9,4));
//    }
//
//    @Test
//    public void getSizeOfList(){
//        array.setList(Arrays.asList(1,2,3));
//        assertThat(array.getList(),hasSize(3));
//    }
//
//    @Test
//    public void combineMatchers(){
//        array.setList(Arrays.asList(1,2,3));
//        List <Integer> tempList = array.getList();
//        assertThat(tempList.get(0),allOf(lessThan(4), greaterThan(0)));
//    }

    @AfterEach
    public void tearDown() throws Exception {
        friendships = null;
    }

}