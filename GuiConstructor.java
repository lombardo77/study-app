import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Nicholas Lombardo
 * @description collection of constructor methods that assemble the quiz GUI
 *
 */
public class GuiConstructor {
	/**
	 * interactive texts
	 */
	private Text startQuizText;
	private Text correct;
	private Text incorrect;
	private Text next;
	private Text addQuestionText;
	private Text ans1;
	private Text ans2;
	private Text ans3;
	private Text ans4;
	private Text skip;
	private Text submit;
	private Text questionText;
	private Text multipleChoiceText;
	private Text inputQuestionText;
	private Text exitAddQ;
	private Text submitQ;
	private Text back;
	
	/**
	 * boxes
	 */
	private VBox vbox;
	private HBox questionBox;
	private HBox skipSubmit;
	private StackPane skipSubStack;
	private StackPane imageStack;
	private GridPane ansGrid;
	private TextField input;
	private boolean checker;
	private Image questionImg;
	private ImageView imgView;
	private Scene scene;
	private TextField addInputQTextF;
	private TextField addInputAnsTextF;
	private TextField addMCQTextF;
	private TextField addMCAnsTextFcorrect;
	private TextField addMCAnsTextFincorrect1;
	private TextField addMCAnsTextFincorrect2;
	private TextField addMCAnsTextFincorrect3;
	private HBox inputFields;
	private HBox MCFields;
	
	
	/**
	 * quiz objects
	 */
	private List<Question> questions;
	private FileHandler handler;
	private Quiz test1;
	private Question question;
	private int rightAns;
	private int wrongAns;
	
	
	

	
	/**
	 * no arg constructor
	 */
	public GuiConstructor() {
		startNewQuiz();
		
		// first screen text
		startQuizText = new Text("Start Quiz");
		addQuestionText = new Text("Add Question");
		startQuizTextSettings();
		
		// vbox settings
		vbox = new VBox(startQuizText, addQuestionText);
		vbox.setAlignment(Pos.CENTER);
		
		// add question screen text
		multipleChoiceText = new Text("Multiple Choice Question");
		inputQuestionText = new Text("Input Type Question");
		exitAddQ = new Text("Start Quiz");
		
		// main scene
		scene = new Scene(vbox, 1400, 1000);
		
		// answer grid
		ansGrid = new GridPane();
		
		// input text field settings
		input = new TextField();
		input.setPrefWidth(70);
		input.setMaxWidth(200);
		addInputQTextF = new TextField("Question");
		addInputQTextF.setPrefWidth(200);
		addInputQTextF.setMaxWidth(200);
		addInputAnsTextF = new TextField("Answer");
		addInputAnsTextF.setPrefWidth(200);
		addInputAnsTextF.setMaxWidth(200);
		inputFields = new HBox(addInputQTextF, addInputAnsTextF);
		inputFields.setAlignment(Pos.BASELINE_CENTER);
		addInputAnsTextF.setPadding(new Insets(10,10,10,10));
		addInputQTextF.setPadding(new Insets(10,10,10,10));
		
		// multiple choice tex fild settings
		addMCQTextF = new TextField("Question");
		addMCAnsTextFcorrect = new TextField("Correct Ans");
		addMCAnsTextFincorrect1 = new TextField("Incorrect 1");
		addMCAnsTextFincorrect2 = new TextField("Incorrect 2");
		addMCAnsTextFincorrect3 = new TextField("Incorrect 3");
		
		addMCQTextF.setPrefWidth(200);
		addMCQTextF.setMaxWidth(200);
		
		addMCAnsTextFcorrect.setPrefWidth(200);
		addMCAnsTextFcorrect.setMaxWidth(200);
		
		addMCAnsTextFincorrect1.setPrefWidth(200);
		addMCAnsTextFincorrect1.setMaxWidth(200);
		
		addMCAnsTextFincorrect2.setPrefWidth(200);
		addMCAnsTextFincorrect2.setMaxWidth(200);
		
		addMCAnsTextFincorrect3.setPrefWidth(200);
		addMCAnsTextFincorrect3.setMaxWidth(200);
		
		
		MCFields = new HBox(addMCQTextF, addMCAnsTextFcorrect, 
				addMCAnsTextFincorrect1, addMCAnsTextFincorrect2, addMCAnsTextFincorrect3);
		
		addMCQTextF.setPadding(new Insets(10,10,10,10));
		addMCAnsTextFcorrect.setPadding(new Insets(10,10,10,10));
		addMCAnsTextFincorrect1.setPadding(new Insets(10,10,10,10));
		addMCAnsTextFincorrect2.setPadding(new Insets(10,10,10,10));
		addMCAnsTextFincorrect3.setPadding(new Insets(10,10,10,10));
		
		MCFields.setAlignment(Pos.BASELINE_CENTER);
		
		// submit/ go back text
		submitQ = new Text("Submit");
		back = new Text("Go back");
		
		// skip/submit settings
		skip = new Text("Skip");
		submit = new Text("Submit");
		skipSubmit = new HBox(skip, submit);
		skipSubStack = new StackPane(skipSubmit);
		
		questionBox = new HBox();	
		questionBox.setAlignment(Pos.BASELINE_CENTER);
		
		// correct/ incorrect settings
		correct = new Text("Correct");
		incorrect = new Text("Incorrect");
		next = new Text("Next");
		textConstructor();
	}

	/**
	 * constructs main quiz screen text
	 */
	private void textConstructor() {
		skip.setFont(Font.font(40));
		submit.setFont(Font.font(40));
		correct.setFont(Font.font(300));
		incorrect.setFont(Font.font(300));
		correct.setFill(Color.GREEN);
		incorrect.setFill(Color.RED);
		next.setFont(Font.font(40));
		
		// events
		next.setOnMouseEntered(e->{
			next.setFill(Color.ORANGE);
		});
		next.setOnMouseExited(e->{
			next.setFill(Color.BLACK);
		});
		skipSubmit.setAlignment(Pos.BASELINE_CENTER);
	}

	/**
	 * method starts new quiz object
	 */
	private void startNewQuiz() {
		questions = new LinkedList<Question>();
		handler = new FileHandler("C:\\Users\\lomba\\eclipse-workspace\\QuizApp\\questions.csv");
		questions = handler.getList();	
		test1 = new Quiz(questions);
		test1.generateQuiz(questions.size());
		System.out.println(questions);
	}

	
	/**
	 * sets the start new quiz text settings
	 */
	private void startQuizTextSettings() {
		// font settings
		startQuizText.setFill(Color.BLACK);	
		startQuizText.setFont(Font.font(100));
		addQuestionText.setFont(Font.font(70));
		
		// events
		startQuizText.setOnMouseEntered(e->{
			startQuizText.setFill(Color.ORANGE);
		});
		startQuizText.setOnMouseExited(e->{
			startQuizText.setFill(Color.BLACK);
		});
		startQuizText.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(startQuizText, addQuestionText);
			nextQuestion();
		});
		
				
		addQuestionText.setOnMouseEntered(e->{
			addQuestionText.setFill(Color.ORANGE);
		});
		addQuestionText.setOnMouseExited(e->{
			addQuestionText.setFill(Color.BLACK);
		});
		addQuestionText.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(startQuizText, addQuestionText);
			addQuestionScreen();
		});
		
		
		
	}

	/**
	 * the add question screen constructor. handles the events and sets fonts
	 */
	private void addQuestionScreen() {
		
		// fonts 
		multipleChoiceText.setFont(Font.font(50));
		inputQuestionText.setFont(Font.font(50));
		exitAddQ.setFont(Font.font(20));
		submitQ.setFont(Font.font(40));
		back.setFont(Font.font(20));
		
		vbox.getChildren().addAll(multipleChoiceText, inputQuestionText, exitAddQ);
		
		// event settings
		multipleChoiceText.setOnMouseEntered(e->{
			multipleChoiceText.setFill(Color.ORANGE);
		});
		multipleChoiceText.setOnMouseExited(e->{
			multipleChoiceText.setFill(Color.BLACK);
		});
		
		inputQuestionText.setOnMouseEntered(e->{
			inputQuestionText.setFill(Color.ORANGE);
		});
		inputQuestionText.setOnMouseExited(e->{
			inputQuestionText.setFill(Color.BLACK);
		});
		
		exitAddQ.setOnMouseEntered(e->{
			exitAddQ.setFill(Color.ORANGE);
		});
		exitAddQ.setOnMouseExited(e->{
			exitAddQ.setFill(Color.BLACK);
		});
		
		exitAddQ.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(exitAddQ, submitQ, inputFields, multipleChoiceText, inputQuestionText);
			nextQuestion();
		});
			
		inputQuestionText.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(multipleChoiceText, inputQuestionText, exitAddQ);
			vbox.getChildren().addAll(inputFields, submitQ, back);
			addInputQuestion();
		});
		
		multipleChoiceText.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(multipleChoiceText, inputQuestionText, exitAddQ);
			vbox.getChildren().addAll(MCFields, submitQ, back);
			addMCQuestion();
		});
	
	}

	/**
	 * add multiple choice question to CSV file and handles events
	 */
	private void addMCQuestion() {
		vbox.getChildren().addAll(exitAddQ);
		
		// event settings
		submitQ.setOnMouseClicked(e->{
			handler.addQuestion(new Question(addMCQTextF.getText(), addMCAnsTextFcorrect.getText(), 
					addMCAnsTextFincorrect1.getText(), addMCAnsTextFincorrect2.getText(), addMCAnsTextFincorrect3.getText()));
			addInputQTextF.clear();
			addInputAnsTextF.clear();
		});
		
		submitQ.setOnMouseEntered(e->{
			submitQ.setFill(Color.ORANGE);
		});
		submitQ.setOnMouseExited(e->{
			submitQ.setFill(Color.BLACK);
		});
		
		back.setOnMouseEntered(e->{
			back.setFill(Color.ORANGE);
		});
		back.setOnMouseExited(e->{
			back.setFill(Color.BLACK);
		});
		
		back.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(exitAddQ, submitQ, MCFields, back);
			addQuestionScreen();
		});
		
		exitAddQ.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(exitAddQ, submitQ, inputFields, multipleChoiceText, inputQuestionText);
			nextQuestion();
		});
		
	}

	/**
	 * add question to csv file and handles events
	 */
	private void addInputQuestion() {
		
		vbox.getChildren().addAll(exitAddQ);

		submitQ.setOnMouseClicked(e->{
			handler.addQuestion(new Question(addInputQTextF.getText(), addInputAnsTextF.getText()));
			addInputQTextF.clear();
			addInputAnsTextF.clear();
		});
		
		submitQ.setOnMouseEntered(e->{
			submitQ.setFill(Color.ORANGE);
		});
		submitQ.setOnMouseExited(e->{
			submitQ.setFill(Color.BLACK);
		});
		
		back.setOnMouseEntered(e->{
			back.setFill(Color.ORANGE);
		});
		back.setOnMouseExited(e->{
			back.setFill(Color.BLACK);
		});
		
		back.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(exitAddQ, submitQ, inputFields, back);
			addQuestionScreen();
		});
		
		exitAddQ.setOnMouseClicked(e->{
			vbox.getChildren().removeAll(exitAddQ, submitQ, inputFields, multipleChoiceText, inputQuestionText, back );
			nextQuestion();
		});
	}

	/**
	 * gets new question from main quiz queue
	 */
	private void nextQuestion() {
		if (test1.getQuiz().size() == 0) {
			finishQuiz();
		}
		
		// takes question from quiz and saves into question object
		question = test1.getQuiz().poll();	
		skipSubmitStyleEvents();
		
		// handles the various types of questions/ if no questions left in queue
		try {
		if(question.getQuestionType() == 0) {
			System.out.println(question.getQuestionType());
			makeMultipleChoice();
		}
		else if(question.getQuestionType() == 1) {
			System.out.println(question.getQuestionType());
			makeInputPhotoQuestion();
		}
		else {
			System.out.println(question.getQuestionType());
			makeInputQuestion();
		}
		}
		catch(Exception e) {
			System.out.println("Done!");
		}
		
	}// end line

	/**
	 * ends quiz
	 */
	private void finishQuiz() {
		double totalQ = rightAns + wrongAns;
		totalQ = (rightAns/totalQ)*100;

		// grade formatting
		Text finish = new Text("Done!");
		Text grade = new Text(String.format("%,.2f%%", totalQ));
		
		finish.setFont(Font.font(100));
		grade.setFont(Font.font(100));
		vbox.getChildren().addAll(finish, grade);
	}

	/**
	 * method sets the event handling the skip and submit buttons
	 */
	private void skipSubmitStyleEvents() {
		//events
		skip.setOnMouseEntered(e->{
			skip.setFill(Color.ORANGE);
		});
		skip.setOnMouseExited(e->{
			skip.setFill(Color.BLACK);
		});
		submit.setOnMouseEntered(e->{
			submit.setFill(Color.ORANGE);
		});
		submit.setOnMouseExited(e->{
			submit.setFill(Color.BLACK);
		});
	}
	
	/**
	 * sets scene for multiple choice style question
	 */
	private void makeMultipleChoice() {
		// text settings
		questionText = new Text(question.getQuestion());
		questionText.setFont(Font.font(40));
		questionBox.getChildren().add(questionText);
		
		// add to vbox
		vbox.getChildren().add(questionBox);
		
		// gets answers and sets fonts
		List<String> ans = question.getAnswers(); 
		ans1 = new Text(ans.get(0));
		ans2 = new Text(ans.get(1));
		ans3 = new Text(ans.get(2));
		ans4 = new Text(ans.get(3));
		ansGrid.add(ans1, 0, 0);
		ansGrid.add(ans2, 0, 1);
		ansGrid.add(ans3, 1, 0);
		ansGrid.add(ans4, 1, 1);
		setAnsMouseEvents();
		setAnsClickEvents();
		ansGrid.setAlignment(Pos.BASELINE_CENTER);
		styleAns();
		vbox.getChildren().addAll(ansGrid, skipSubStack);
		
		// skip events
		skip.setOnMouseClicked(e->{
			wrongAns ++;
			System.out.println("wrong ans: " + wrongAns);
			clearQuestion();
			nextQuestion();
		});
	}
	
	
	/**
	 * sets the MC answer events
	 */
	private void setAnsClickEvents() {
		// events
		ans1.setOnMouseClicked(e->{
			checker = question.checkAnswer(ans1.getText());
			submitQuestion();
		});
		ans2.setOnMouseClicked(e->{
			checker = question.checkAnswer(ans2.getText());
			submitQuestion();
		});
		ans3.setOnMouseClicked(e->{
			checker = question.checkAnswer(ans3.getText());
			submitQuestion();
		});
		ans4.setOnMouseClicked(e->{
			checker = question.checkAnswer(ans4.getText());
			submitQuestion();
		});
		
		
	}// end line

	/**
	 * styles the answer texts and grid for multiple choice questions
	 */
	private void styleAns() {
	ans1.setFont(Font.font(18));
	ans2.setFont(Font.font(18));
	ans3.setFont(Font.font(18));
	ans4.setFont(Font.font(18));
	ansGrid.setPadding(new Insets(10,10,10,10));
	ansGrid.setVgap(10);
	ansGrid.setHgap(100);
	
	}

	/**
	 * sets the answer hover mouse events
	 * 
	 * ** opted for different method to avoid clutter
	 */
	private void setAnsMouseEvents() {
		ans1.setOnMouseEntered(e->{
			ans1.setFill(Color.ORANGE);
		});
		ans2.setOnMouseEntered(e->{
			ans2.setFill(Color.ORANGE);
		});
		ans3.setOnMouseEntered(e->{
			ans3.setFill(Color.ORANGE);
		});
		ans4.setOnMouseEntered(e->{
			ans4.setFill(Color.ORANGE);
		});
		
		ans1.setOnMouseExited(e->{
			ans1.setFill(Color.BLACK);
		});
		ans2.setOnMouseExited(e->{
			ans2.setFill(Color.BLACK);
		});
		ans3.setOnMouseExited(e->{
			ans3.setFill(Color.BLACK);
		});
		ans4.setOnMouseExited(e->{
			ans4.setFill(Color.BLACK);
		});
		
		
	}

	/**
	 * sets scene for image input style question
	 */
	private void makeInputPhotoQuestion() {
		// reads the image
		readImage();
		
		//text settings
		questionText = new Text(question.getQuestion());
		questionText.setFont(Font.font(40));
		questionBox.getChildren().add(questionText);
		vbox.getChildren().addAll(questionBox, imageStack);
		vbox.getChildren().addAll(input, skipSubStack);
		questionBox.setPadding(new Insets(10,10,10,10));		
		input.setPadding(new Insets(10,10,10,10));	
		skipSubmit.setPadding(new Insets(10,10,10,10));
		
		//events
		skip.setOnMouseClicked(e->{
			wrongAns ++;
			clearQuestion();
			nextQuestion();
		});
		submit.setOnMouseClicked(e->{
			checker = question.checkAnswer(input.getText());
			submitQuestion();
		});
	}
	
	
	/**
	 * sets scene for input style question
	 */
	private void makeInputQuestion() {
		// text settings
		questionText = new Text(question.getQuestion());
		questionText.setFont(Font.font(40));
		questionBox.getChildren().add(questionText);
		vbox.getChildren().addAll(questionBox);
		vbox.getChildren().addAll(input, skipSubStack);
		questionBox.setPadding(new Insets(10,10,10,10));		
		input.setPadding(new Insets(10,10,10,10));	
		skipSubmit.setPadding(new Insets(10,10,10,10));
		
		//events
		skip.setOnMouseClicked(e->{
			wrongAns ++;
			clearQuestion();
			nextQuestion();
		});
		submit.setOnMouseClicked(e->{
			checker = question.checkAnswer(input.getText());
			submitQuestion();
		});
	}
	

	/**
	 * reads image into scene
	 */
	private void readImage() {
		//Passing FileInputStream object as a parameter 
		FileInputStream inputstream = null;
		try {
			inputstream = new FileInputStream(question.getImgPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		questionImg = new Image(inputstream);
		imgView = new ImageView(questionImg);
		imageStack = new StackPane(imgView);
		imageStack.setPadding(new Insets(10,10,10,10));
		
	}

	
	/**
	 * submit question method. verifies whether or not the answer is correct
	 * the same for all types of questions. Question objects have the same method to verify
	 */
	private void submitQuestion() {	
		System.out.println(checker);
		if(checker == true) {
			rightAns ++;
			vbox.getChildren().add(correct);
			vbox.getChildren().add(next);
			clearQuestion();
			next.setOnMouseClicked(e->{
				vbox.getChildren().removeAll(correct, next);	
				nextQuestion();
			});
		}
		else {
			wrongAns ++;
			vbox.getChildren().add(incorrect);
			vbox.getChildren().add(next);
			clearQuestion();
			next.setOnMouseClicked(e->{
				vbox.getChildren().removeAll(incorrect, next);	
				nextQuestion();
			});
			}
		
			
		
	}// end line

	/**
	 * clears scene of question
	 */
	private void clearQuestion() {
		if(question.getQuestionType() == 0) {
			vbox.getChildren().removeAll(questionBox, skipSubStack, ansGrid);
			questionBox.getChildren().removeAll(questionText);
			ansGrid.getChildren().removeAll(ans1, ans2, ans3, ans4);
		}
		else if(question.getQuestionType() == 1) {
			vbox.getChildren().removeAll(questionBox, input, skipSubStack, imageStack);
			questionBox.getChildren().removeAll(questionText);
			imageStack.getChildren().removeAll(imgView);
		}
		else {
			vbox.getChildren().removeAll(questionBox, input, skipSubStack);
			questionBox.getChildren().removeAll(questionText);
		}
		
	}

	/**
	 * 
	 * @return the main scene to client
	 */
	public Scene getScene() {
		return scene;
	}

}
