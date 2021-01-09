package week3;

import java.util.Arrays;
import java.util.Random;

public class TestModule {

    private final SortModule sortModule;
    private final Random r = new Random();

    private final int[][] randomData = new int[3][];
    private final int[][] reverseData = new int[3][];

    private final double[][] testResult = new double[7][];

    public TestModule(SortModule sortModule) {
        this.sortModule = sortModule;

        initAllReverseData();
        for (int i = 0; i < testResult.length; i++) {
            testResult[i] = new double[6];
        }
        testResult[4][5] = -1;
    }

    private void initAllReverseData() {
        initReverseData(0, 1000);
        initReverseData(1, 10000);
        initReverseData(2, 100000);
    }

    private void initReverseData(int index, int size) {
        reverseData[index] = new int[size];

        for (int i = 0; i < size; i++) {
            reverseData[index][i] = size - i;
        }
    }

    private void initAllRandomData() {
        initRandomData(0, 1000);
        initRandomData(1, 10000);
        initRandomData(2, 100000);
    }

    private void initRandomData(int index, int size) {
        randomData[index] = new int[size];

        for (int i = 0; i < size; i++) {
            randomData[index][i] = r.nextInt(size) + 1;
        }
    }

    public void runIntegrationTest() {
        for (int i = 0; i < 1; i++) {
            initAllRandomData();

            runUnitTest(randomData[0], 0);
            runUnitTest(randomData[1], 1);
            runUnitTest(randomData[2], 2);
            runUnitTest(reverseData[0], 3);
            runUnitTest(reverseData[1], 4);
            runUnitTest(reverseData[2], 5);
        }
    }

    private void runUnitTest(int[] testData, int dataType) {
        for (int i = 0; i < testResult.length; i++) {
            int[] copyData = Arrays.copyOf(testData, testData.length);
            long start = System.currentTimeMillis();
            sortBy(copyData, i);
            testResult[i][dataType] += (System.currentTimeMillis() - start);
        }
    }

    private void sortBy(int[] testData, int algorithm) {
        switch (algorithm) {
            case 0:
                sortModule.bubbleSort(testData);
                break;
            case 1:
                sortModule.selectionSort(testData);
                break;
            case 2:
                sortModule.insertionSort(testData);
                break;
            case 3:
                sortModule.mergeSort(testData);
                break;
            case 4:
                if (testData.length <= 10000)
                sortModule.quickSort(testData, 0);
                break;
            case 5:
                sortModule.quickSort(testData, 1);
                break;
            case 6:
                sortModule.quickSort(testData, 2);
                break;
        }
    }

    public void showResult() {
        for (double[] measuredTime : testResult) {
            System.out.println(Arrays.toString(measuredTime));
        }
    }
}
