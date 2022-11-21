import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static DataOutputStream dataOutputStream;

    static {
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream("src/query.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(ArrayList arrayList) {
        try{
            for(Object object: arrayList) {
                dataOutputStream.writeBytes(object + "\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] tables = {
            "master","creature","change","part","content_change","order_"
    };

    public static int[] num_columns = {
            2,2,3,2,2,2
    };

    public static Map<Integer, String[]> columns_map = new HashMap<>();

    public static Map<String, Object[]> data_map = new HashMap<>();
    static int limit = 15;

    public static Integer[] generate_random_numbers(int max, int min){
        Integer[] result = new Integer[limit];
        for(int i=0; i<limit;++i) {
            result[i] = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        return result;
    }

    public static String[] generate_content(){
        String[] result = new String[15];
        for(int i=0; i<limit;++i){
            result[i] = data_map.get("name1")[i]+ "change";
        }
        return result;
    }

    public static void set_up(){
        columns_map.put(0, new String[]{"name", "birthday"});
        columns_map.put(1, new String[]{"name", "master_id"});
        columns_map.put(2, new String[]{"type", "time", "place"});
        columns_map.put(3, new String[]{"name", "description"});
        columns_map.put(4, new String[]{"change_id", "part_id"});
        columns_map.put(5, new String[]{"creature_id", "part_id"});
        data_map.put("name0",new String[]{
                "Tommy","Johnny","Polly","Arthur","Ada","Shelby","Dustin","Rose","Grace","Michael",
                "Steve","Frank","Werner","Mark","Justin"
        });
        data_map.put("birthday0",new String[]{
                "2021-10-03","2021-10-02","2021-10-04","2021-10-05","2021-10-06","2021-10-08",
                "2021-10-07","2021-10-01","2001-10-08","2001-10-03","2000-10-08","1999-09-08",
                "1999-09-08","1999-09-09","1999-08-10"
        });
        data_map.put("name1", new String[]{
                "ALPHA-001","ALPHA-002","ALPHA-003","ALPHA-004","ALPHA-005","ALPHA-006","ALPHA-007",
                "ALPHA-008","ALPHA-009","ALPHA-010","ALPHA-011","ALPHA-012","ALPHA-013","ALPHA-014",
                "ALPHA-015"
        });
        data_map.put("master_id1",generate_random_numbers(1,1000));
        data_map.put("type2", generate_random_numbers(1,0));
        data_map.put("time2", new String[]{
                "2021-10-03 10:00:01","2021-10-02 11:01:00","2021-10-04 10:01:08","2021-10-05 11:05:08","2021-10-06 12:01:03","2021-10-08 10:08:00",
                "2021-10-03 18:00:01","2021-10-02 08:01:00","2021-10-04 10:00:07","2021-10-05 15:08:06","2021-10-06 17:06:07","2021-10-08 19:05:00",
                "2021-10-03 14:00:01","2021-10-02 00:01:00","2021-10-04 00:00:08","2021-10-05 16:30:06","2021-10-06 17:30:07","2021-10-08 19:35:00",
        });
        data_map.put("place2",new String[]{
                "Vietnam","Russia","USA","China","Korea","Canada","France","Italy","England","Germany",
                "Brazil","Argentina","Portugal","Denmark","Ukraine"
        });
        data_map.put("name3", new String[]{
                "voice","sigh","smell","hearing","felling","shape","color",
                "weight","height","width","material","thinking","desire","character","intelligence"
        });
        data_map.put("description3", new String[]{
                "better","worse","more beautiful","less","further","taller","shorter",
                "max size","min size","same","hotter","colder","cooler","bigger","smaller"
        });
        data_map.put("change_id4",generate_random_numbers(1,1000));
        data_map.put("part_id4",generate_random_numbers(1,1000));
        data_map.put("creature_id5",generate_random_numbers(1,1000));
        data_map.put("part_id5",generate_random_numbers(1,1000));
    }
    public static void generateQuery(int index){
        ArrayList<String> list = new ArrayList<>();
        String queryBuilder ;
        for(int i=0; i<1000;++i){
            queryBuilder = "INSERT INTO " + tables[index] + " (";
            for(int j=0; j<num_columns[index];++j) {
                queryBuilder += columns_map.get(index)[j];
                if(j != num_columns[index]-1) queryBuilder += ", ";
            }
            queryBuilder += ") VALUES (";
            for(int j=0; j<num_columns[index];++j){
                String name = columns_map.get(index)[j];
                Object data = data_map.get(name+index)[new Random().nextInt(15)];
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
        generateQuery(2);
    }
}