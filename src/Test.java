public class Test {

    public static void main(String[] args) {
        int id = 0;

        Control control = new Control();

        control.readLogin();
        control.readScore();
        control.readQuiz();

        System.out.println(control.checkFinishQuiz(1));
        control.studentToAnsQuiz();

        System.out.println("Test");

    }
}
