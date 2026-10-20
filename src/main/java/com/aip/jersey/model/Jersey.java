package com.aip.jersey.model;

public class Jersey {

    private int id;
    private String teamName;
    private String playerName;
    private int jerseyNumber;
    private String size;
    private double price;
    private int quantity;

    public Jersey() {
    }

    public Jersey(int id, String teamName, String playerName, int jerseyNumber,
                  String size, double price, int quantity) {
        this.id = id;
        this.teamName = teamName;
        this.playerName = playerName;
        this.jerseyNumber = jerseyNumber;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public int getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}