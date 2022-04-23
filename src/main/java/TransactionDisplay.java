
import java.util.Scanner;

public class TransactionDisplay {

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj rodzaj transakcji (wydatek lub wpłata)");
        Type type = Type.valueOf(scanner.nextLine());

        TransactionDao transactionDao = new TransactionDao();
        Transaction transaction = transactionDao.findByType(type);
        System.out.println(transaction);

    }
}

