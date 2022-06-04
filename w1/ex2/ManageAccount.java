public class ManageAccount {
    public static final int MAX_NUMBERS_ACCOUNT = 1;
    private Account listAccount[] = new Account[MAX_NUMBERS_ACCOUNT];

    public void addAccount(Account Acc) {
        listAccount[0] = Acc;
        System.out.print("Success\n");
    }

    public void showBalance() {
        System.out.printf("Name: %s\n", listAccount[0].getUserName());
        System.out.printf("Balance is: %d\n", listAccount[0].getBalance());
        // System.out.println(listAccount[0].getBalance());
    }

    public int ReceiveMoney(int money) {
        listAccount[0].setBalance(listAccount[0].getBalance() + money);
        return listAccount[0].getBalance();
    }

    public int withdrawMoney(int money) {
        if (listAccount[0].getBalance() < money) {
            System.out.print("Accounts do not have enough money to withdraw\n");
        } else {
            listAccount[0].setBalance(listAccount[0].getBalance() - money);
        }
        return listAccount[0].getBalance();
    }
}
