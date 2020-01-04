import java.util.ArrayList;
import java.util.Scanner;

public class QuizManager {

    protected ArrayList<Quiz> quizList = new ArrayList<>();
    protected ArrayList<String> ansList = new ArrayList<>();

    public void createQuiz() {
        String choice = "";
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Enter question :");
        String question = scan.nextLine();

        while (true) {
            System.out.println("Please enter choices :");
            String ch = scan.nextLine();
            choice = choice + ch + ":";
            System.out.println("If do you want to exit enter 0 or enter 1 you can add another" +
                    "choice :");
            int stop = scan1.nextInt();
            if (stop == 0) {
                break;
            }
        }

        System.out.println("Please enter Ans :");
        int ans = scan1.nextInt();
        quizList.add(new Quiz(question, choice, ans));
    }

    public void read() {
        for (int i = 0; i < quizList.size(); i++) {
            System.out.println(i + 1 + ".  " + quizList.get(i).getQuestion());

            String[] choiceArray = quizList.get(i).getChoices().split(":");

            for (int k = 0; k < choiceArray.length; k++) {
                int count = k + 1;
                System.out.println("\t" + count + "." + choiceArray[k]);
            }
            System.out.println(quizList.get(i).getAnswer());
            System.out.println("");
        }

    }

    public void update(int num, Quiz quiz) {
        quizList.remove(num - 1);
        quizList.add(num - 1, quiz);
    }

    public void delete() {
        System.out.println("Which number do you want to delete :");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        quizList.remove(num - 1);
    }

    public void deleteAll() {
        quizList.clear();
    }

    public void saveQuiz() {
        Out out = new Out("quiz.txt");

        for (int i = 0; i < quizList.size(); i++) {
            out.println(quizList.get(i).getQuestion() + "0" +
                    quizList.get(i).getChoices() + "0" +
                    quizList.get(i).getAnswer());
        }
    }

    public void readSaveQuiz() {
        In in = new In("quiz.txt");
        String[] quiz = new String[3];

        while (in.hasNextLine()) {
            quiz = in.readLine().split("0");
            quizList.add(new Quiz(quiz[0], quiz[1], Integer.parseInt(quiz[2])));
        }
    }



}
