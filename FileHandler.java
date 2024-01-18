import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Nicholas Lombardo
 *  @description unpacks question objects and reads/writes into main question file
 *
 */
public class FileHandler {
	
	private static List<Question> questions;
	private String path;
	/**
	 * no arg constructor
	 */
	public FileHandler(String pathIn){
		path = pathIn;
		questions = new LinkedList<Question>();
	}
	
	
	/**
	 * reads the CSV file w questions
	 */
	private void readCSV() {

		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null) {
				
				String[] lineArray = line.split("	");
				Question quest;
				if (lineArray.length == 5) {
					quest = new Question(lineArray[0], lineArray[1], lineArray[2], 
							lineArray[3], lineArray[4]);
				} else if (lineArray.length == 3) {
					quest = new Question(lineArray[0], lineArray[1], lineArray[2]);
				} else {
					quest = new Question(lineArray[0], lineArray[1]);
				}
				
				
				questions.add(quest);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end line
	
	/**
	 * write to CSV
	 */
	private void writeCSV(Question q) {
		
		try {
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			if(q.getQuestionType() == 0) {
					
			pw.println(q.getQuestion() + "	" + q.getAnswers().get(0) 
					+ "	" + q.getAnswers().get(1) + "	" 
					+ q.getAnswers().get(2) + "	" + q.getAnswers().get(3));
					
			System.out.println("printed Question");
			} else if (q.getQuestionType() == 1) {
				pw.println(q.getQuestion() + "	" + q.getAnswers().get(0) + "	" + q.getImgPath());		
				System.out.println("printed Question");
				
			} else {
				
				pw.println(q.getQuestion() + "	" + q.getAnswers().get(0));
				System.out.println("printed Question");
				
			}
			
			pw.flush();
			pw.close();
		}
		catch (Exception e){
			System.out.println("did not print Question");
		}
	}

	/**\
	 * adds question to file
	 * @param question
	 */
	public void addQuestion(Question q) {
		
		writeCSV(q);
	}
	
	/**
	 * 
	 * @returns the read list 
	 */
	public List<Question> getList(){
		System.out.println(questions);
		readCSV();
		return questions;
	}
}
