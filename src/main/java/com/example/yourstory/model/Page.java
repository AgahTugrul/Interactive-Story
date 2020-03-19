package com.example.yourstory.model;

public class Page {
    private int imageId;
    private int textId;
    private Choice firstChoice,secondChoice;
    private boolean isFinalPage=false;

    public Page(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
        isFinalPage=true;
    }

    public Page(int imageId, int textId, Choice firstChoice, Choice secondChoice) {
        this.imageId = imageId;
        this.textId = textId;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
    }

    public boolean isFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(boolean finalPage) {
        isFinalPage = finalPage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(Choice firstChoice) {
        this.firstChoice = firstChoice;
    }

    public Choice getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(Choice secondChoice) {
        this.secondChoice = secondChoice;
    }
}
