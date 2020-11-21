package com.example.smarttutor;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Storage {
    private final FirebaseStorage storage = FirebaseStorage.getInstance("gs://smarttutor-b0801.appspot.com");
    private final StorageReference storageRef;
    public Storage(String subject){
        storageRef = storage.getReference().child(subject);
    }

    public ArrayList<Question> getQuestions(String subject) {
        ArrayList<Question> questions = new ArrayList<>();
        final long MEGABYTE = 1024 * 1024;

        storageRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item : listResult.getItems()) {
                            storageRef.child(String.valueOf(item)).getBytes(MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                public void onSuccess(byte[] bytes) {
                                    String questionUnparsed = new String(bytes, StandardCharsets.UTF_8);
                                    StringBuilder questionTitle = new StringBuilder();
                                    StringBuilder questionDescription = new StringBuilder();
                                    StringBuilder indexString = new StringBuilder();
                                    int indexID = questionUnparsed.indexOf("#")+1;
                                    while(questionUnparsed.charAt(indexID) >= '0' && questionUnparsed.charAt(indexID) <= '9'){
                                        indexString.append(questionUnparsed.charAt(indexID));
                                        ++indexID;
                                    }
                                    int id = Integer.parseInt(String.valueOf(indexString));

                                    for(int i = questionUnparsed.indexOf("Title:"); i < questionUnparsed.indexOf("Description:"); ++i){
                                        questionTitle.append(questionUnparsed.charAt(i));
                                    }

                                    for(int i = questionUnparsed.indexOf("Description:"); i < questionUnparsed.length(); ++i){
                                        questionDescription.append(questionUnparsed.charAt(i));
                                    }

                                    questions.add(new Question(id, String.valueOf(questionTitle), String.valueOf(questionDescription)));
                                }
                            });
                        }
                    }
                });
        return questions;
    }

    public ArrayList<Article> getArticles(){
        ArrayList<Article> articles = new ArrayList<>();
        final long MEGABYTE = 1024 * 1024;

        storageRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    public void onSuccess(ListResult listResult) {
                        for (StorageReference item : listResult.getItems()) {
                            storageRef.child(String.valueOf(item)).getBytes(MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                public void onSuccess(byte[] bytes) {
                                    String questionUnparsed = new String(bytes, StandardCharsets.UTF_8);
                                    StringBuilder questionTitle = new StringBuilder();
                                    StringBuilder questionDescription = new StringBuilder();
                                    StringBuilder questionShortDescription = new StringBuilder();
                                    StringBuilder indexString = new StringBuilder();
                                    int indexID = questionUnparsed.indexOf("#")+1;
                                    while(questionUnparsed.charAt(indexID) >= '0' && questionUnparsed.charAt(indexID) <= '9'){
                                        indexString.append(questionUnparsed.charAt(indexID));
                                        ++indexID;
                                    }
                                    int id = Integer.parseInt(String.valueOf(indexString));

                                    for(int i = questionUnparsed.indexOf("Title:")+6; i < questionUnparsed.indexOf("Description:"); ++i){
                                        questionTitle.append(questionUnparsed.charAt(i));
                                    }

                                    for(int i = questionUnparsed.indexOf("Description:")+12; i < questionUnparsed.indexOf("SDescription:"); ++i){
                                        questionDescription.append(questionUnparsed.charAt(i));
                                    }
                                    for(int i = questionUnparsed.indexOf("SDescription:")+13; i < questionUnparsed.length(); ++i){
                                        questionShortDescription.append(questionUnparsed.charAt(i));
                                    }

                                    articles.add(new Article(id, String.valueOf(questionTitle), String.valueOf(questionShortDescription), String.valueOf(questionDescription)));
                                }
                            });
                        }
                    }
                });
        return articles;
    }

}
