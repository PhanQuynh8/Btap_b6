package com.example.btap_b6;
import java.util.ArrayList;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class PhotoData {
//    public static ArrayList<Photo> generatePhotoData(){
//        ArrayList<Photo> photos = new ArrayList<>();
//        photos.add(new Photo(0,"https://upload.wikimedia.org/wikipedia/commons/7/74/%E0%B4%95%E0%B5%88%E0%B4%A4%E0%B4%9A%E0%B5%8D%E0%B4%9A%E0%B4%95%E0%B5%8D%E0%B4%95.jpg","Pineapple","The pineapple (Ananas coumosus) is" +
//                "a tropical plant with an edible fruit and the most economically significant plant in the family Bromeliaceae"));
//        photos.add(new Photo(1,"https://hoaquafuji.com/storage/app/media/gia-cherry-tren-thi-truong-00.png","Cherry","A cherry is the fruit of many plants" +
//                "of the Prunus, and is a fleshy drupe (stone fruit)"));
//        photos.add(new Photo(2,"https://upload.wikimedia.org/wikipedia/commons/4/43/Ambersweet_oranges.jpg","Orange",
//                "An orange is a fruit of various citrus species in the family Rutaceae (see list of plants known as orange); it primarily refers to Citrus × sinensis,[1] which is also called sweet orange, to distinguish it from the related Citrus × aurantium, referred to as bitter orange. The sweet orange reproduces asexually (apomixis through nucellar embryony); varieties of sweet orange arise through mutations"));
//        photos.add(new Photo(3,"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Avocado_Hass_-_single_and_halved.jpg/640px-Avocado_Hass_-_single_and_halved.jpg","Avocado",
//                "The avocado (Persea americana) is a medium-sized, evergreen tree in the laurel family (Lauraceae). It is native to the Americas and was first domesticated by Mesoamerican tribes more than 5,000 years ago. Then as now it was prized for its large and unusually oily fruit"));
//        photos.add(new Photo(4,"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Garden_strawberry_%28Fragaria_%C3%97_ananassa%29_single2.jpg/800px-Garden_strawberry_%28Fragaria_%C3%97_ananassa%29_single2.jpg","Strawberry",
//                "The garden strawberry (or simply strawberry; Fragaria × ananassa)[1] is a widely grown hybrid species of the genus Fragaria, collectively known as the strawberries, which are cultivated worldwide for their fruit. The fruit is widely appreciated for its characteristic aroma, bright red color, juicy texture, and sweetness. " +
//                        "It is consumed in large quantities, either fresh or in such prepared foods as jam, juice, pies, ice cream, milkshakes, and chocolates. Artificial strawberry flavorings and aromas are also widely used in products such as candy, soap, lip gloss, perfume, and many others."));
//        return photos;
//    }
//    public static Photo getPhotoFromId(int id){
//        ArrayList<Photo> phs = generatePhotoData();
//        for(int i = 0; i< phs.size(); i++){
//            if(phs.get(i).getId() == id){
//                return phs.get(i);
//            }
//        }
//        return null;
//    }
    private static Context context;

    public static void init(Context context) {
        PhotoData.context = context;
    }

    public static ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("photo.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title_photo");
                String description = jsonObject.getString("description_photo");
                photos.add(new Photo(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photos;
    }

    private static String loadJSONFromAsset(String fileName) throws IOException {
        String jsonString;
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonString;
    }
    public static Photo getPhotoFromId(int id) {
        Photo photo = null;

        try {
            String jsonString = loadJSONFromAsset("photo.json"); // Assuming the JSON file is named photos.json and is located in the assets folder
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int photoId = jsonObject.getInt("id");

                if (photoId == id) {
                    String source = jsonObject.getString("source_photo");
                    String title = jsonObject.getString("title_photo");
                    String description = jsonObject.getString("description_photo");
                    photo = new Photo(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photo;
    }
}
