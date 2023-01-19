import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileController {
	
	private File file;
	private boolean isNew;

	public FileController() {
		this.file = new File("highScore.txt");
		try {
			this.isNew = this.file.createNewFile();
			//System.out.println("In con " + this.isNew);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//isNew = this.file.createNewFile())
		//GetHighScoreInFile();
		
	}
	
	
	public String GetHighScoreInFile() {
		
		StringBuffer sb = new StringBuffer();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String sCurrentLine;
		    while ((sCurrentLine = br.readLine()) != null) 
		    {
		        sb.append(sCurrentLine);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public void SaveHighScoreToFile(int score) throws IOException {
		//System.out.println(this.file.createNewFile());
		if (this.isNew) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
			bw.write(Integer.toString(score));
			bw.close();
		}
		else {
			int previousitHighScore = Integer.parseInt(GetHighScoreInFile());
			if (score > previousitHighScore) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
				//must define the bw after we read from the file
				bw.write(Integer.toString(score));
				bw.close();
			}
		}
		
	}
}
