import java.io.*;

/**
 * Class responsable for Writing and Reading files
 */
public class FileManager {

    /**
     * Creates a new file that saves player status and progress
     * @param player name of the file
     * @param story content to be saved
     */
    public static void save(String player, String story) {

        BufferedWriter outputBufferedWriter = null;


        try {
            outputBufferedWriter = new BufferedWriter(new FileWriter(player));
            outputBufferedWriter.write(story, 0, story.length());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (outputBufferedWriter != null) {
                    outputBufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads a specific file
     * @param fileName name of the file
     * @return the content of the file
     */
    public static String load(String fileName) {

        String line = "";
        String fileContent = "";
        BufferedReader inputBufferedReader;

        try {
            inputBufferedReader = new BufferedReader(new FileReader(fileName + ".txt"));

            while((line = inputBufferedReader.readLine()) != null) {
                fileContent += line + "\n";
            }



        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return fileContent;
    }
}
