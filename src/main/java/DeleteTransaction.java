import java.util.Scanner;

public class DeleteTransaction {

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji którą chesz usunąć)");
        int id = scanner.nextInt();
        scanner.nextLine();

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.deleteById(id);
        System.out.println("Transakcja została usnięta");

    }
}
