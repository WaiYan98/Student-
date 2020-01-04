import java.util.Scanner;

public class StudentQuizApp {

    private static Control control = new Control();

    private static Scanner intScan = new Scanner(System.in);
    private static Scanner scan = new Scanner(System.in);

    private static String name = "";

    public static void main(String[] args) {


        System.out.println("STUDENT QUIZ MANAGEMENT SYSTEM : " + "\n");

        int userNum = 0;
        int function = 0;

        control.readLogin();
        control.readScore();
        control.readQuiz();

        while (true) {
            System.out.println("LOGIN");
            System.out.println("Please enter your name :");
            name = scan.nextLine();

            System.out.println("Please enter your password");
            String password = scan.nextLine();

            if (Login.logIn(new User(name, password, 0))) {
                userNum = 1;
                break;
            } else if (control.checkLogin(new User(name, password, 0))) {
                userNum = 2;
                break;
            }

            System.err.println("User name or password was wrong");
            System.out.println("");

        }

        switch (userNum) {

            case 1:
                info();
                break;

            case 2:
                System.out.println("This is Student user :");

        }


        if (userNum == 1) {

            loop:
            while (true) {

                showAdminFunction();

                System.out.println("Please select number :");
                function = intScan.nextInt();

                switch (function) {

                    case 1:
                        info();
                        break;
                    case 2:
                        handleReset();
                        break;
                    case 3:
                        handleCreateQuiz();
                        break;
                    case 4:
                        handleReadQuiz();
                        break;
                    case 5:
                        handleUpdateQuiz();
                        break;
                    case 6:
                        handleDeleteQuiz();
                        break;
                    case 7:
                        handleDeleteAll();
                        break;
                    case 8:
                        handleRegister();
                        break;
                    case 9:
                        break loop;
                }

            }
        } else {

            loop:
            while (true) {
                showStudentFunction();

                System.out.println("Please select number :");
                function = intScan.nextInt();

                switch (function) {

                    case 1:
                        handleToAnswerQuiz();
                        break;
                    case 2:
                        showTheirScore();
                        break;
                    case 3:
                        break loop;
                }

            }

        }

        control.saveLogin();
        control.saveQuiz();
        control.saveScore();
    }

    public static void info() {
        System.out.println("");
        control.showAllScore();
        System.out.println("");
        control.showHiLoAvg();
    }

    public static void handleReset() {
        control.reset();
    }

    public static void handleCreateQuiz() {
        while (true) {
            control.createQuiz();

            System.out.println("If do you want to create enter 1 or exit enter 0 :");
            int exit = intScan.nextInt();

            if (exit == 0) {
                break;
            }
        }
    }

    public static void handleReadQuiz() {
        control.read();
    }

    public static void handleUpdateQuiz() {
        String choice = "";

        while (true) {
            System.out.println("Which number do you want to update :");
            int upNum = intScan.nextInt();

            System.out.println("Please enter the question :");
            String question = scan.nextLine();

            while (true) {
                System.out.println("Please enter the choices :");
                String ch = scan.nextLine();
                choice = choice + ch + ":";
                System.out.println("If do you want to exit enter 0 or enter 1 you can add another" +
                        "choice :");
                int stop = intScan.nextInt();
                if (stop == 0) {
                    break;
                }
            }
            System.out.println("Please enter the answer :");
            int ans = intScan.nextInt();

            control.updateQ(upNum, new Quiz(question, choice, ans));

            System.out.println("If do you want to update enter 1 or exit enter 0 :");
            int exit = intScan.nextInt();
            if (exit == 0) {
                break;
            }
        }
    }

    public static void handleDeleteQuiz() {
        while (true) {
            control.deleteQ();

            System.out.println("If do you want to delete enter 1 or exit enter 0 :");
            int exit = intScan.nextInt();

            if (exit == 0) {
                break;
            }
        }
    }

    public static void handleDeleteAll() {
        System.out.println("Do you want to really delete :" + "\n" + "Enter yes or no :");
        String button = scan.nextLine();

        if (button.equals("yes")) {
            control.deleteAllQ();
        }
    }

    public static void handleRegister() {
        System.out.println("Please enter name :");
        String name = scan.nextLine();

        System.out.println("Please enter password :");
        String password = scan.nextLine();
        control.register(name, password);
    }

    public static void showStudentFunction() {
        System.out.println("");
        System.out.println("1. Answer quiz" + "\n" + "2. Score" + "\n" + "3. Logout");
    }

    public static void handleToAnswerQuiz() {
        if (control.checkFinishQuiz(control.nameToId(name))) {
            control.studentToAnsQuiz();
            control.checkAns(control.nameToId(name));
        } else {
            System.err.println("You have already answer the quiz");
        }
    }

    public static void showTheirScore() {
        System.out.println("Name" + "\t" + "\t" + "\t" + "Score");
        System.out.println(" " + name + "\t" + "\t" + "\t" + " " +
                control.getTheirScore(control.idToIndex(control.nameToId(name))));
    }


    public static void showAdminFunction() {
        System.out.println("");
        System.out.println("1. Info" + "\n" + "2. Reset" + "\n" + "3. Create quiz" +
                "\n" + "4. Read quiz" + "\n" + "5. Update quiz" + "\n" + "6. Delete quiz" +
                "\n" + "7. Delete all quiz" + "\n" + "8. Register" +
                "\n" + "9. Logout");
    }
}
