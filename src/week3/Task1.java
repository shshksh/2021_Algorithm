package week3;

public class Task1 {

    public static void main(String[] args) {
        TestModule testModule = new TestModule();
        testModule.initAllData();

        SortModule sortModule = new SortModule();

        testModule.test(sortModule);
        testModule.showResult();
    }

}
