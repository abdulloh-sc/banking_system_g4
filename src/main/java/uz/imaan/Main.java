package uz.imaan;

import java.util.Scanner;

public class Main {

    static final int MAX = 10;
    static int[] accountNumbers = new int[MAX];
    static String[] accountHolders = new String[MAX];
    static double[] balance = new double[MAX];
    static int[] pins = new int[MAX];

    static int accountCount = 0;

    static void main() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running){
            showMenu();

            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    checkBalanc(scanner);
                    break;
                case 5:
                    transfer(scanner);
                    break;
                case 6:
                    printAllAcc();
                    break;
                case 7:
                    running = false;
                    System.out.println("dastur yakunlandi, Xayrr");
                    break;
                default:
                    System.out.println("notogri tanlov");
            }

        }

    }


    static void showMenu(){
        System.out.println("""
                ===== BANK TIZIMI =====
                1.New account
                2.Pul kiritish
                3.Pul yechish
                4.Balansni tekshirish
                5.Pul o'tkazish
                6.Barcha hisoblar
                7. EXIT
                """);
        System.out.print("Tanlovingiz : ");
    }

    static void createAccount(Scanner sc){
        if (accountCount >= MAX){
            System.out.println("hisoblar soni tolgan");
            return;
        }
        System.out.print("ismingizni kiriting : ");
        sc.nextLine();

        String name = sc.nextLine();

        System.out.print("pin code oylab toping");
        int pin = sc.nextInt();

        int accNumber = 10000 + (int) (Math.random() * 90000);

        accountNumbers[accountCount] = accNumber;
        accountHolders[accountCount] = name;
        balance[accountCount] = 0;
        pins[accountCount] = pin;

        accountCount++;

        System.out.println("hisob muvaffiqiyatli ochildi");
        System.out.println("siznig hisobingiz : " + accNumber);
    }

    static int findAccountIndex(int accNumber){
        for (int i = 0; i < accountCount; i++) {
            if (accountNumbers[i] == accNumber){
                return i;
            }
        }
        return -1;
    }

    static boolean verifyPin(int index, Scanner sc){
        System.out.print("pin codeni kiriting : ");
        int enterPin = sc.nextInt();

        if (enterPin != pins[index]){
            System.out.println("pin notogri");
            return false;
        }
        return true;
    }

    static void deposit(Scanner sc){
        System.out.print("hisob raqamni kiriting : ");
        int accNumber = sc.nextInt();

        int index = findAccountIndex(accNumber);

        if (index == -1){
            System.out.println("bunaqa account yoq");
            return;
        }
        System.out.print("summani kiriting : ");
        double amount = sc.nextDouble();

        if (amount <= 1000){
            System.out.println("summa 1000 somdan dan kotta bosin");
            return;
        }

        balance[index] += amount;
        System.out.println("succusful, yangi balans : " + balance[index]);
    }

    static void withdraw(Scanner sc){
        System.out.print("hisob raqamni kiriting : ");
        int accNumber = sc.nextInt();

        int index = findAccountIndex(accNumber);

        if (index == -1){
            System.out.println("hisob mavjud emas !!!🫪");
            return;
        }

        if (!verifyPin(index, sc)){
            return;
        }

        System.out.print("yechmoqchi bolgan summani kiritng : ");
        double amount = sc.nextDouble();

        if (amount <= 0){
            System.out.println("0 dan kotta bosin😡");
        } else if (amount > balance[index]) {
            System.out.println("balansda pul yetarli emas ");
        } else {
            balance[index] -= amount;
            System.out.println("yangi balance : " + balance[index]);
        }
    }

    static void checkBalanc(Scanner sc){
        System.out.print("hisob raqamni kiriting : ");
        int accNumber = sc.nextInt();

        int index = findAccountIndex(accNumber);

        if (index == -1){
            System.out.println("hisob mavjud emas !!!🫪");
            return;
        }

        if (!verifyPin(index, sc)){
            return;
        }

        System.out.println(accountHolders[index] + " ning balansi : " + balance[index]);
    }

    static void transfer(Scanner sc){
        System.out.print("qaysi hisobdan yubormoqchisiz : ");
        int accNumber = sc.nextInt();

        int index = findAccountIndex(accNumber);

        if (index == -1){
            System.out.println("hisob mavjud emas !!!🫪");
            return;
        }

        if (!verifyPin(index, sc)){
            return;
        }

        System.out.print("qaysi hisobga yuvormoxchisiz : ");
        int toAcc = sc.nextInt();
        int toIndex = findAccountIndex(toAcc);

        if (toIndex == -1){
            System.out.println("hisob toplmadi");
            return;
        }

        if (accNumber == toIndex){
            System.out.println("ozingizga pul otqizolmisiz");
            return;
        }

        System.out.print("summani kiriting : ");
        double amount = sc.nextDouble();

        if (amount <= 0){
            System.out.println("summa 0 dan kotta bosin");
        } else if (amount > balance[index]) {
            System.out.println("mablag yetarli emas");
        }else {
            balance[index] -= amount;
            balance[toIndex] += amount;
            System.out.println("otkazma cho'tki yakunlandi ");
        }
    }

    static void printAllAcc(){
        if (accountCount == 0){
            System.out.println("hali mavjud emas");
            return;
        }
        System.out.println("hamma hisoblar : ");

        for (int i = 0; i < accountCount; i++) {
            System.out.println("raqam : " + accountNumbers[i] +
                    " | ism: " + accountHolders[i] +
                    " | balance: " + balance[i]);
        }
    }













}
