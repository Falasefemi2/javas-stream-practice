package com.stream.banktransaction;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toList;

public class BankTransaction {

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("1", "Falase femi", 4000, "Deposit", false),
                new Transaction("2", "David Ayo", 6000, "Withdraw", true),
                new Transaction("3", "Ibukun Emma", 9000, "Transfer", false),
                new Transaction("4", "Kemi Esther", 10000, "Deposit", true),
                new Transaction("5", "Kelvin", 900000, "Withdraw", false),
                new Transaction("6", "Pep", 70000, "Transfer", false),
                new Transaction("7", "Silva", 293444, "Deposit", false)
        );

        // 1 Filter successful and failed transactions
        Map<Boolean, List<Transaction>> transactionStatus = transactions.stream()
                .collect(partitioningBy(t -> t.isSuccessful() == true));
        System.out.println("Successful Transaction");
        transactionStatus.get(true).forEach(System.out::println);
        System.out.println("Failed Transaction");
        transactionStatus.get(false).forEach(System.out::println);
        System.out.println();

        // 2 Calculate total money processed
        double totalAmount = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total Amount spent: " + totalAmount);
        System.out.println();

        // 3 Group transactions by type
        Map<String, List<Transaction>> groupedTransaction = transactions.stream().collect(groupingBy(Transaction::getType));
        System.out.println("Group by category");
        groupedTransaction.forEach((c, i) -> {
            System.out.println(c + ": ");
            i.forEach(System.out::println);
        });
        System.out.println();

        // 4 Top 5 highest transactions
        List<Transaction> topFiveHighestTransactions = transactions.stream()
                .sorted((a, b) -> Double.compare(b.getAmount(), a.getAmount()))
                .limit(5)
                .collect(toList());
        System.out.println("Top 5 Transactions");
        topFiveHighestTransactions.forEach(System.out::println);
        System.out.println();

        // 5 Average transaction per account holder
        Map<String, Double> averageTransactions = transactions.stream()
                .collect(groupingBy(Transaction::getAccountHolder, averagingDouble(Transaction::getAmount)));
        averageTransactions.forEach((acct, amount) -> System.out.println(acct + ": " + amount));
        System.out.println();

        // 6 List of all unique account holders
        System.out.println("Account Holders");
        List<String> accountHolders = transactions.stream()
                .map(acct -> acct.getAccountHolder())
                .distinct()
                .collect(toList());
        accountHolders.forEach(System.out::println);
        System.out.println();

        // 7 Count how many failed withdrawals
        long failedTransactions = transactions.stream()
                .filter(t -> !t.isSuccessful() && t.getType().equalsIgnoreCase("Withdraw"))
                .count();
        System.out.println("Failed withdraws: " + failedTransactions);
        System.out.println();

        // 8 Get summary statistics for transaction amounts
        Map<String, Double> transactionStat = transactions.stream().collect(groupingBy(Transaction::getAccountHolder, summingDouble(Transaction::getAmount)));
        System.out.println(transactionStat);

    }
}
