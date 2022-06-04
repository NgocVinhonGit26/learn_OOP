public class Main {
    public static void main(String[] args) {
        ManageAccount anAccount = new ManageAccount();

        Account acc = new Account();
        acc.setUserName("Phung Ngoc Vinh");
        acc.setBalance(20194719);

        anAccount.addAccount(acc);

        anAccount.showBalance();

        // System.out.printf("Money after withdrawal is: %d\n ",
        // anAccount.withdrawMoney(1000));
        System.out.printf("Money after withdrawal is: ");
        System.out.println(anAccount.withdrawMoney(1000));
        System.out.printf("Money after receiving is: ");
        System.out.println(anAccount.ReceiveMoney(2000));

    }
}
