import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * 
 * @author Nicholas Lombardo
 * @description creates a quiz queue with LinkedList<Question> that constructor takes as argument
 * 
 */
public class Quiz {
	
	private List<Question> allQuestions;
	private Random generator;
	private Queue<Question> quiz;
	
	public Quiz(List<Question> QuestionsIn){
		generator = new Random();
		allQuestions = QuestionsIn;
		quiz = new LinkedList<Question>();
	}
	
	/**
	 * generates quiz
	 */
	public void generateQuiz(int quizSize) {
		for (int quest = 0; quest < quizSize; quest ++) {
			quiz.add(allQuestions.get(quest));
		}
	}

	/**
	 * 
	 * @returns the quiz queue
	 */
	public Queue<Question> getQuiz() {
		return quiz;
	}
}
