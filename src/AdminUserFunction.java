import java.util.ArrayList;
import java.util.Scanner;

public class AdminUserFunction {


    protected ArrayList<Quiz> quizList = new ArrayList<>();
    protected ArrayList<String> ansList = new ArrayList<>();
    private int highestScore;
    private int averageScore;
    private int lowestScore;

    public int getHighestScore() {
        return highestScore;
    }


    public void toFindHighest(ArrayList<Student> studentList) {
        int hi = 0;
        for (int i = 0; i < studentList.size(); i++) {
            if (hi < studentList.get(i).getScore()) {
                hi = studentList.get(i).getScore();
            }
        }
        this.highestScore = hi;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void toFindAvg(ArrayList<Student> studentList) {
        int sum = 0;
        for (int i = 0; i < studentList.size(); i++) {
            sum += studentList.get(i).getScore();
        }
        this.averageScore = sum / studentList.size();
    }

    public int getLowestScore() {
        return lowestScore;
    }

    public void toFindLo(ArrayList<Student> studentList) {
        int lo = studentList.get(0).getScore();
        for (int i = 0; i < studentList.size(); i++) {
            if (lo > studentList.get(i).getScore()) {
                lo = studentList.get(i).getScore();
            }
        }
        this.lowestScore = lo;
    }

    //insert studentList******
    public void showAllScore(ArrayList<Student> studentList, ArrayList<User> userList) {

        System.out.println(" " + "Name" + "\t" + "\t" + "\t" + "Score");
        for (int i = 0; i < studentList.size(); i++) {
            if (userList.get(i).getId() == studentList.get(i).getId()) {
                System.out.println(" " + userList.get(i).getName() + "\t" + "\t" + "\t" + " " +
                        studentList.get(i).getScore());
            }
        }
    }

    public void showHiLoAvg(ArrayList<Student> studentList) {
        toFindHighest(studentList);
        toFindAvg(studentList);
        toFindLo(studentList);
        System.out.println("Highest score :  " + getHighestScore());
        System.out.println("Average score :  " + getAverageScore());
        System.out.println("Lowest score  :  " + getLowestScore());

    }

    public void reset(ArrayList<Student> studentList) {
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setScore(0);
        }
    }

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


    public void register(ArrayList<Student>studentList,ArrayList<User> userList,
                         String name, String password) {
        int id = 1;
        if (userList.isEmpty()) {
            userList.add(new User(name, password, id));
            addStudent(studentList,new Student(id, 0));
        } else {
            userList.add(new User(name, password,
                    userList.get(userList.size() - 1).getId() + 1));
            addStudent(studentList,new Student(userList.get(userList.size() - 1).getId(),
                    0));
        }


    }

    public void addStudent(ArrayList<Student> studentList, Student student) {
        studentList.add(student);
    }


    public void checkAns(ArrayList<Student> studentList, int id) {
        for (int i = 0; i < ansList.size(); i++) {
            if (Integer.parseInt(ansList.get(i)) ==
                    quizList.get(i).getAnswer()) {
                int index = StudentUserFunction.idToIndex
                        (StudentUserFunction.findId(id));
                studentList.get(index).setScore(studentList.get(index).getScore() + 10);
            }
        }
    }


    public boolean checkFinishQuiz(ArrayList<Student> studentList, int id) {
        boolean isFinish = false;

        for (int i = 0; i < studentList.size(); i++) {

            if (id == studentList.get(i).getId()) {
                if (studentList.get(i).getScore() == 0) {
                    isFinish = true;
                    break;
                }
            }
        }
        return isFinish;
    }
}

