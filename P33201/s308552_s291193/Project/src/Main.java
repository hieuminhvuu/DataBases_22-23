package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static DataOutputStream dataOutputStream;

    static {
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream("src/insert.sql"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(ArrayList arrayList){
        try{
            for(Object object: arrayList){
                dataOutputStream.writeBytes(object + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String[] tables = {
            "attribute","player","sponsor","goal","league","stadium","coach","referee","club","match",
            "player_club", "club_league", "club_match", "match_league", "match_goal","match_referee",
            "match_stadium", "player_goal", "play_MOTM", "sponsor_club"
    };

    public static int[] num_columns = {
            7,9,4,3,3,4,3,4,6,4,2,2,2,2,2,2,2,2,2,2
    };

    public static Map<Integer,String[]> columns_map = new HashMap<>();

    public static Map<String, Object[]> data_map = new HashMap<>();
    static int limit = 10;

    public static Integer[] generate_random_numbers(int max, int min){
        Integer[] result = new Integer[limit];
        for(int i=0; i<limit;++i) {
            result[i] = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        return result;
    }

    public static String[] generate_content(){
        String[] result = new String[10];
        for(int i=0; i<limit;++i){
            result[i] = data_map.get("name1")[i]+ "_SALVAGE";
        }
        return result;
    }

    public static void set_up(){
        columns_map.put(0, new String[]{"attack", "defense","dribble", "pass","speed","power","stamina"});
        columns_map.put(1, new String[]{"full_name", "height","weight","position_","nationality","shirt_number","birthday","image","id_attribute"});
        columns_map.put(2, new String[]{"name", "headquarter","management","assets"});
        columns_map.put(3, new String[]{"time", "video","rating"});
        columns_map.put(4, new String[]{"name", "region", "level"});
        columns_map.put(5, new String[]{"name", "coordinate","capacity","owner"});
        columns_map.put(6, new String[]{"full_name","birthday","nationality"});
        columns_map.put(7, new String[]{"full_name","birthday","nationality","rating"});
        columns_map.put(8, new String[]{"full_name","nationality","owner","logo","id_coach","id_stadium"});
        columns_map.put(9, new String[]{"special_name","time","weather","audience"});
        columns_map.put(10, new String[]{"id_player","id_team"});
        columns_map.put(11, new String[]{"club_id","league_id"});
        columns_map.put(12, new String[]{"club_id","match_id"});
        columns_map.put(13, new String[]{"id_player","id_team"});
        columns_map.put(14, new String[]{"id_player","id_team"});
        columns_map.put(15, new String[]{"id_player","id_team"});
        columns_map.put(16, new String[]{"id_player","id_team"});
        columns_map.put(17, new String[]{"id_player","id_team"});
        columns_map.put(18, new String[]{"id_player","id_team"});

        data_map.put("attack0",generate_random_numbers(50,100));
        data_map.put("defense0",generate_random_numbers(50,100));
        data_map.put("dribble0",generate_random_numbers(50,100));
        data_map.put("pass0",generate_random_numbers(50,100));
        data_map.put("speed0",generate_random_numbers(50,100));
        data_map.put("power0",generate_random_numbers(50,100));
        data_map.put("stamina0",generate_random_numbers(50,100));

//        "full_name", "height","weight","position","nationality",
//                "shirt_number","birthday","image","id_attribute"
        data_map.put("full_name1", new String[]{
                "Ronaldo","Messi","Benzema","Neymar","Beackam","Shelby","Dustin","Rose","Grace","Michael",
                "Raphina","Frank","Werner","Mark","Halland"
        });
        data_map.put("height1",generate_random_numbers(170,200));
        data_map.put("weight1",generate_random_numbers(170,200));
        data_map.put("position_1", new String[]{"GK", "CB", "LB", "RB", "CDM", "CAM", "CM", "CF", "RF", "LF", "LM", "RM"});
        data_map.put("nationality1", new String[]{"English", "Russian", "Japanese", "Chinese", "USA", "French", "German", "Brazilian", "Argentinian", "Vietnamese"});
        data_map.put("shirt_number1", generate_random_numbers(1, 30));
        data_map.put("birthday1", new String[]{
                "1999-10-03","1987-10-02","1989-10-04","1987-10-05","1986-10-06","1986-10-08",
                "1989-10-07","1999-10-01","1989-10-08","1976-10-03","1985-10-08","1994-09-08",
                "1999-09-08","1999-09-09","1999-08-10"
        });
        data_map.put("image1", new String[]{"https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png",
                "https://cdn1.iconfinder.com/data/icons/user-pictures/100/unknown-512.png"});
        data_map.put("id_attribute1", generate_random_numbers(1, 1000));


    }
    public static void generateQuery(int index){
        ArrayList<String> list = new ArrayList<>();
        String queryBuilder ;
        for(int i=0; i<100;++i){
            queryBuilder = "INSERT INTO " + tables[index] + " (";
            for(int j=0; j<num_columns[index];++j) {
                queryBuilder += columns_map.get(index)[j];
                if(j != num_columns[index]-1) queryBuilder += ", ";
            }
            queryBuilder += ") VALUES (";
            for(int j=0; j<num_columns[index];++j){
                String name = columns_map.get(index)[j];
                Object data = data_map.get(name+index)[new Random().nextInt(10)];
                if(data.getClass() == String.class) queryBuilder += "'" + data + "'";
                else queryBuilder += data;
                if(j != num_columns[index]-1) queryBuilder += ",";
            }
            queryBuilder += ");";
            list.add(queryBuilder);
        }
        saveToFile(list);
    }

    public static void main(String[] args) throws Exception {
        set_up();
        generateQuery(0);
    }
}