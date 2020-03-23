package matchersExample;

//public class SquareArrayAssertJTest {
//
//    private SquareArray array;
//
//    @Before
//    public void setUp() throws Exception {
//        array = new SquareArray();
//    }
//
//    @Test
//    public void createInstance(){
//        assertThat(array.getClass()).isEqualTo(SquareArray.class);
//    }
//
//    @Test
//    public void getArray(){
//        array.setList(Arrays.asList(1,2,3));
//        List<Integer> tempList = array.getList();
//        assertThat(tempList).contains(1,2,3);
//    }
//
//    @Test
//    public void getOneNumberOfArray(){
//        array.setList(Arrays.asList(1,2,3));
//        List <Integer> tempList = array.getList();
//        assertThat(tempList.get(0)).isEqualTo(1);
//    }
//
//    @Test
//    public void getContainsSquareList(){
//        array.setList(Arrays.asList(1,2,3));
//        array.square_list();
//        List <Integer> tempList = array.getList();
//        assertThat(tempList).containsExactlyInAnyOrder(1,9,4);
//    }
//
//    @Test
//    public void getSizeOfList(){
//        array.setList(Arrays.asList(1,2,3));
//        assertThat(array.getList()).hasSize(3);
//    }
//
//    @Test
//    public void combineMatchers(){
//        array.setList(Arrays.asList(1,2,3));
//        List <Integer> tempList = array.getList();
//        assertThat(tempList.get(0)).isBetween(0,4);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        array = null;
//    }
//}
