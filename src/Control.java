import java.util.ArrayList;
import java.util.Scanner;

public class Control {

    private int highestScore;
    private int averageScore;
    private int lowestScore;

    private LoginControl loginControl = new LoginControl();
    private ScoreManager scoreManager = new ScoreManager();
    private QuizManager quizManager = new QuizManager();

    public void saveLogin() {
        loginControl.saveLogin();
    }

    public void readLogin() {
        loginControl.readLogin();
    }

    public void handleCheckLogin(User user) {
        loginControl.checkLogin(user);
    }

    public boolean checkLogin(User user) {
        return loginControl.checkLogin(user);
    }

    public void toFindHighest() {
        int hi = 0;
        for (int i = 0; i < scoreManager.studentList.size(); i++) {
            if (hi < scoreManager.studentList.get(i).getScore()) {
                hi = scoreManager.studentList.get(i).getScore();
            }
        }
        this.highestScore = hi;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void toFindAvg() {
        int sum = 0;
        for (int i = 0; i < scoreManager.studentList.size(); i++) {
            sum += scoreManager.studentList.get(i).getScore();
        }
        this.averageScore = sum / scoreManager.studentList.size();
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void toFindLo() {
        int lo = scoreManager.studentList.get(0).getScore();
        for (int i = 0; i < scoreManager.studentList.size(); i++) {
            if (lo > scoreManager.studentList.get(i).getScore()) {
                lo = scoreManager.studentList.get(i).getScore();
            }
        }
        this.lowestScore = lo;
    }

    public int getLowestScore() {
        return lowestScore;
    }

    public void showAllScore() {
        System.out.println(" " + "Name" + "\t" + "\t" + "\t" + "Score");
        for (int i = 0; i < scoreManager.studentList.size(); i++) {
            System.out.println(" " + loginControl.userList.get(i).getName() +
                    "\t" + "\t" + "\t" + " " +
                    scoreManager.studentList.get(i).getScore());
        }
    }

    public void showHiLoAvg() {
        toFindHighest();
        toFindAvg();
        toFindLo();
        System.out.println("Highest score :  " + getHighestScore());
        System.out.println("Average score :  " + getAverageScore());
        System.out.println("Lowest score  :  " + getLowestScore());

    }

    public void reset() {
        scoreManager.reset();
    }

    public void createQuiz() {
        quizManager.createQuiz();
    }

    public void read() {
        quizManager.read();
    }

    public void updateQ(int num, Quiz quiz) {
        quizManager.update(num, quiz);
    }

    public void deleteQ() {
        quizManager.delete();
    }

    public void deleteAllQ() {
        quizManager.deleteAll();
    }

    public void saveQuiz() {
        quizManager.saveQuiz();
    }

    public void readQuiz() {
        quizManager.readSaveQuiz();
    }

    public void register(String name, String password) {
        int id = 1;
        if (loginControl.userList.isEmpty()) {
            loginControl.userList.add(new User(name, password, id));
            scoreManager.addStudent(new Student(id, 0));
        } else {
            loginControl.userList.add(new User(name, password,
                    loginControl.userList.get(loginControl.userList.size() - 1).getId() + 1));
            scoreManager.addStudent(new Student(loginControl.userList.get(loginControl.userList.size() - 1).getId(),
                    0));
        }
    }

    public void studentToAnsQuiz() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < quizManager.quizList.size(); i++) {

            System.out.println(i + 1 + ".  " +
                    quizManager.quizList.get(i).getQuestion());

            String[] choiceArray = quizManager.quizList.get(i).getChoices().split(":");

            for (int k = 0; k < choiceArray.length; k++) {
                int count = k + 1;
                System.out.println("\t" + count + "." + choiceArray[k]);
            }
            System.out.println("Please answer the question :");
            String ans = scan.nextLine();
            quizManager.ansList.add(ans);

        }
    }

    public int getTheirScore(int index) {
        return scoreManager.studentList.get(index).getScore();
    }

    public void checkAns(int id) {
        for (int i = 0; i < quizManager.ansList.size(); i++) {
            if (Integer.parseInt(quizManager.ansList.get(i)) ==
                    quizManager.quizList.get(i).getAnswer()) {
                int index = idToIndex(id);
                scoreManager.studentList.get(index).setScore
                        (scoreManager.studentList.get(index).getScore() + 10);
            }
        }
    }

    public boolean checkFinishQuiz(int id) {
        boolean isFinish = false;

        for (int i = 0; i < scoreManager.studentList.size(); i++) {

            if (id == scoreManager.studentList.get(i).getId()) {
                if (scoreManager.studentList.get(i).getScore() == 0) {
                    isFinish = true;
                    break;
                }
            }
        }
        return isFinish;
    }

//    public int findId(int id) {
//        int idNum = 0;
//        for (int i = 0; i < scoreManager.studentList.size(); i++) {
//            if (scoreManager.studentList.get(i).getId() == id) {
//                idNum = scoreManager.studentList.get(i).getId();
//                break;
//            }
//        }
//        return id;
//    }

    public int idToIndex(int id) {
        int index = 0;
        for (int i = 0; i < scoreManager.studentList.size(); i++) {
            if (scoreManager.studentList.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int nameToId(String name) {
        int id = 0;
        for (int i = 0; i < loginControl.userList.size(); i++) {
            if (name.equals(loginControl.userList.get(i).getName())) {
                id = loginControl.userList.get(i).getId();
                break;
            }
        }
        return id;
    }

    public void saveScore() {
        scoreManager.saveQuizScore();
    }

    public void readScore() {
        scoreManager.readScore();
    }

}