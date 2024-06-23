package edu.birzeit.saeedmosaffer.taskmanager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Room implements Serializable {
    private String title;
    private String details;

    public Room(String taskName) {}

    public Room(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("details", details);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static Room fromJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String title = jsonObject.getString("title");
            String details = jsonObject.getString("details");
            return new Room(title, details);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
