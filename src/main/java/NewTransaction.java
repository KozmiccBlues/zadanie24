import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class NewTransaction {
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dodaj transakcję");

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

        Transaction transaction = new Transaction(type, description, amount, date);

        TransactionDao transactionDao = new TransactionDao();
        transactionDao.add(transaction);

    }
}
