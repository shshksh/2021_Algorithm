package week3;

public class Task1 {

    public static void main(String[] args) {
        SortModule sortModule = new SortModule();

        TestModule testModule = new TestModule(sortModule);

        testModule.runIntegrationTest();
        testModule.showResult();
    }

}
