package bot;

import bot.entities.Exercise;
import bot.entities.ExerciseType;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExercisesDB
{
    private static final String EXERCISES_FILE_NAME = "exercises.json";

    public static List<Exercise> get() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStreamReader inputStream = new InputStreamReader(classLoader.getResourceAsStream(EXERCISES_FILE_NAME));

        List<Exercise> exercises = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {

            File file = new File("temp");
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            outputStream.close();

            Object obj = parser.parse(new FileReader(file));

            JSONArray exercisesJson =  (JSONArray) obj;

            for (JSONObject currentExercise : (Iterable<JSONObject>) exercisesJson)
            {
                exercises.add(new Exercise(currentExercise.get("name").toString(),
                        ExerciseType.valueOf(currentExercise.get("type").toString())));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();// todo: throws and handle it, also handles null up there
        }

        return exercises;
    }
}
