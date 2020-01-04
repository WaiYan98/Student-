import java.util.ArrayList;

public class ScoreManager {

    protected ArrayList<Student> studentList = new ArrayList<>();


    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void reset() {
        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setScore(0);
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

}
