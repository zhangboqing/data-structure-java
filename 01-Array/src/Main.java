public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] scores = new int[3];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = i;
        }

        int[] scores2 = new int[]{100, 90, 80};
        for (int i = 0; i < scores2.length; i++) {
            System.out.println(scores2[i]);
        }

        for(Integer score : scores2) {
            System.out.println(score);
        }

        Object[] arr = new Object[2];
        arr[0] = Integer.valueOf(1);
        arr[0] = Long.valueOf(1);
        System.out.println(arr);
    }
}
