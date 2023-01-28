import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileController {
	private File file;
	private boolean isNew;

	public FileController() {
		this.file = new File("highScore.txt");
		try {
			this.isNew = this.file.createNewFile();
			saveHighScoreToFile(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getHighScoreFromFile() {
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
	
	public void saveHighScoreToFile(int score) throws IOException {
		if (this.isNew) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
			bw.write(Integer.toString(score));
			bw.close();
		}
		else {
			int previousitHighScore = Integer.parseInt(getHighScoreFromFile());
			if (score > previousitHighScore) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
				//must define the bw after we read from the file
				bw.write(Integer.toString(score));
				bw.close();
			}
		}
	}
}
