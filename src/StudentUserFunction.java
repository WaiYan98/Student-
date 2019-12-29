import java.util.ArrayList;
import java.util.Scanner;

public class StudentUserFunction {

    protected static ArrayList<Student> studentList = new ArrayList<>();

    public void studentToAnsQuiz(ArrayList<Quiz>quizList,ArrayList<String>ansList) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < quizList.size(); i++) {

            System.out.println(i + 1 + ".  " +
                    quizList.get(i).getQuestion());

            String[] choiceArray = quizList.get(i).getChoices().split(":");

            for (int k = 0; k < choiceArray.length; k++) {
                int count = k + 1;
                System.out.println("\t" + count + "." + choiceArray[k]);
            }
            System.out.println("Please answer the question :");
            String ans = scan.nextLine();
            ansList.add(ans);

        }
    }

    public void saveQuizScore() {
        Out out = new Out("score.txt");

        for (int i = 0; i < studentList.size(); i++) {
            out.println(studentList.get(i).getId() + ":" + studentList.get(i).getScore());
        }
    }

    public void readScore() {
        In in = new In("score.txt");
        String[] array = new String[2];

        while (in.hasNextLine()) {
            array = in.readLine().split(":");
            studentList.add(new Student(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
        }
    }

    public static int findId(int id) {
        int idNum = 0;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                idNum = studentList.get(i).getId();
                break;
            }
        }
        return id;
    }


    public static int idToIndex(int id) {
        int index = 0;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int getTheirScore(int index) {
        return studentList.get(index).getScore();
    }

}
