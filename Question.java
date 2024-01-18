import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Nicholas Lombardo
 * @description The question class has three different constructors, which handle different question styles
 * 
 */
public class Question {
	
	private String question;
	private String correctAnswer;
	private List<String> answers;
	private HashMap<String, List<String>> questionDict;
	private double attempts;
	private double rightAttempts;
	private List<Double> analytics;
	private int questionType;
	private String imgPath;
	
	
	/**
	 * 
	 * @param QuestionIn takes an array containing structure [question, right answer, wrong answers: 1, 2 , 3]
	 */
	public Question(String QuestionIn, String correctAnswerIn, 
			String wrongAnswer1In, String wrongAnswer2In, String wrongAnswer3In){
		
		questionType = 0;
		question = QuestionIn;
		correctAnswer = correctAnswerIn;
		questionDict = new HashMap<String, List<String>>();
		
		// puts question and answers into dictionary 
		answers = new ArrayList<String>();
		answers.add(correctAnswerIn);
		answers.add(wrongAnswer1In);
		answers.add(wrongAnswer2In);
		answers.add(wrongAnswer3In);
		questionDict.put(question, answers);
		analytics = new LinkedList<Double>();
	}
	
	/**
	 * 
	 * @param QuestionIn takes an array containing structure [question, right answer, wrong answers: 1, 2 , 3]
	 */
	public Question(String QuestionIn, String correctAnswerIn, String imgPathIn){
		
		questionType = 1;
		question = QuestionIn;
		correctAnswer = correctAnswerIn;
		questionDict = new HashMap<String, List<String>>();
		imgPath = imgPathIn;
		
		// puts question and answers into dictionary 
		answers = new ArrayList<String>();
		answers.add(correctAnswerIn);
		questionDict.put(question, answers);
		analytics = new LinkedList<Double>();
	}
	
	/**
	 * 
	 * @param QuestionIn takes an array containing structure [question, right answer, wrong answers: 1, 2 , 3]
	 */
	public Question(String QuestionIn, String correctAnswerIn){
		
		questionType = 2;
		question = QuestionIn;
		correctAnswer = correctAnswerIn;
		questionDict = new HashMap<String, List<String>>();
		
		// puts question and answers into dictionary 
		answers = new ArrayList<String>();
		answers.add(correctAnswerIn);
		questionDict.put(question, answers);
		analytics = new LinkedList<Double>();
	}
	
	/**
	 * checks to see if answer is correct
	 * @param ansIn
	 * @return
	 */
	public boolean checkAnswer(String ansIn) {
		
		if (ansIn.equals(correctAnswer)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @returns the question string
	 */
	public String getQuestion() {
		return question;
	}
	
	
	/**
	 * 
	 * @return answers list
	 */
	public List<String> getAnswers(){
		
		return answers;
	}
	
	/**
	 * toString method that returns the question String
	 *  @return question string
	 */
	public String toString() {
		return question;
	}
	
	
	/**
	 * 
	 * @return int with question type
	 */
	public int getQuestionType() {
		return questionType;
	}
	
	/**
	 * 
	 * @return the path of the question image
	 */
	public String getImgPath() {
		return imgPath;
	}

}
