public class CoinChange {

    public static void main(String[] args) {

        int Amount = 1988;

        int[] currency = {5000, 1000, 500, 100, 50, 20, 10, 5, 2, 1};

        System.out.println("Amount = Rs. " + Amount);

        for (int i = 0; i < currency.length; i++) {

            int count = Amount / currency[i];

            if (count > 0) {
                System.out.println(currency[i] + " * " + count);
                Amount = Amount % currency[i];
            }
        }
    }
}