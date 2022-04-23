import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TransactionUpdate {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id transakcji, którą chcesz zaktualizować");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj rodzaj transakcji (wydatek lub wpłata)");
        Type type = Type.valueOf(scanner.nextLine());

        System.out.println("Podaj opis");
        String description = scanner.nextLine();

        System.out.println("Podaj kwotę");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Podaj datę (yyyy-MM-dd)");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = scanner.next();

        Transaction transaction = new Transaction(id, type, description, amount, date);

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.update(transaction);

    }
}