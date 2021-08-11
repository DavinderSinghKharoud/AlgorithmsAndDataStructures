package Others.AbsoluteDifference;

public class Difference {

    private int[] elements;
    public int maximumDifference;

    public Difference(int[] a) {
        elements = a;
    }

    public void computeDifference() {

        if (elements.length < 2) {
            maximumDifference = elements[0];
            return;
        }

        int temp = Math.abs(elements[0] - elements[1]);


        for (int index = 0; index < elements.length; index++) {

            for (int repeatIndex = index + 1; repeatIndex < elements.length; repeatIndex++) {

                int difference = Math.abs(elements[index] - elements[repeatIndex]);

                if (temp < difference) {
                    temp = difference;
                }
            }
        }

        maximumDifference = temp;
    }
}
