package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    // TABLE DATA LIST
    public ObservableList<tableFlights> gettableFlights(){
        ObservableList<tableFlights> flights = FXCollections.observableArrayList();
        flights.add(new tableFlights("Casablanca","Isafjordur",500,0));
        flights.add(new tableFlights("Reykjavik","Isafjordur",800,0));
        return flights;
    }

    // Create tables
    TableView<tableFlights> tableViewFlights;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        // Top menu
        HBox topMenu = new HBox();
        Button buttonFlights = new Button("Flight search");
        Button buttonHotels = new Button("Hotel search");
        Button buttonTours = new Button("Tour search");
        Button buttonCart = new Button("View cart");
        topMenu.getChildren().addAll(buttonFlights,buttonHotels,buttonTours,buttonCart);

        /*
                                                #
                                                #
                         Flights                #
                                                #
                                                #
        */

        // flights left search menu
        VBox menuFlights = new VBox();

        Label flightsLocationFromLabel = new Label("Flying from:");
        Label flightsLocationToLabel = new Label("Flying to:");
        Label flightsDateFromLabel = new Label("From:");
        Label flightsDateToLabel = new Label("To:");
        Label flightsPassengersLabel = new Label("Passengers:");

        // # date pickers
        DatePicker flightsDateFrom = new DatePicker();
        DatePicker flightsDateTo = new DatePicker();

        // # date picker - events
        flightsDateFrom.setOnAction(event -> {
            LocalDate date = flightsDateFrom.getValue();
            System.out.println("Flight departure date: " + date);
        });
        flightsDateTo.setOnAction(event -> {
            LocalDate date = flightsDateTo.getValue();
            System.out.println("Flight arrival date: " + date);
        });

        // # combo boxes

        // # combo locations from
        ComboBox<String> flightsLocationFrom = new ComboBox<String>();
        flightsLocationFrom.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur","Casablanca");
        flightsLocationFrom.setValue("Reykjavik");
        flightsLocationFrom.setOnAction(event -> {
            String locationFrom = flightsLocationFrom.getValue();
            System.out.println("Flying from: " + locationFrom);
        });
        // # combo locations to
        ComboBox<String> flightsLocationTo = new ComboBox<String>();
        flightsLocationTo.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur","Casablanca");
        flightsLocationTo.setValue("Reykjavik");
        flightsLocationTo.setOnAction(event -> {
            String locationTo = flightsLocationFrom.getValue();
            System.out.println("Flying to: " + locationTo);
        });
        // # combo passengers
        ComboBox<String> flightsPassengersNumber = new ComboBox<String>();
        flightsPassengersNumber.getItems().addAll("1","2","3","4","5");
        flightsPassengersNumber.setValue("1");
        flightsPassengersNumber.setOnAction(event -> {
            String passengers = flightsPassengersNumber.getValue();
            System.out.println("Number of passengers: " + passengers);
        });

        // flights menu setup
        menuFlights.getChildren().addAll(
                flightsLocationFromLabel,
                flightsLocationFrom,
                flightsLocationToLabel,
                flightsLocationTo,
                flightsDateFromLabel,
                flightsDateFrom,
                flightsDateToLabel,
                flightsDateTo,
                flightsPassengersLabel,
                flightsPassengersNumber);

        // flights data center

        // flights from column setup
        TableColumn<tableFlights, String> columnFlightFrom = new TableColumn<>("From");
        columnFlightFrom.setCellValueFactory(new PropertyValueFactory<>("locationFromData"));
        // flights to column setup
        TableColumn<tableFlights, String> columnFlightTo = new TableColumn<>("To");
        columnFlightTo.setCellValueFactory(new PropertyValueFactory<>("locationToData"));
        // flights price column setup
        TableColumn<tableFlights, Double> columnFlightPrice = new TableColumn<>("Price");
        columnFlightPrice.setCellValueFactory(new PropertyValueFactory<>("priceData"));
        // flights passengers column setup
        TableColumn<tableFlights, Integer> columnFlightPassengers = new TableColumn<>("Passengers");
        columnFlightPassengers.setCellValueFactory(new PropertyValueFactory<>("passengersData"));

        tableViewFlights = new TableView<>();
        tableViewFlights.setItems(gettableFlights());
        tableViewFlights.getColumns().addAll(columnFlightFrom,columnFlightTo,columnFlightPrice,columnFlightPassengers);

        // flights VIEW

        HBox mainFlights = new HBox();
        mainFlights.getChildren().addAll(tableViewFlights);

        /*
                                                #
                                                #
                         Hotels                 #
                                                #
                                                #
        */
        VBox menuHotels = new VBox();
        Label label2 = new Label("HOTELS");
        menuHotels.getChildren().addAll(label2);

        HBox mainHotels = new HBox();
        Label dataHotels = new Label("HOTEL DATA WILL BE DISPLAYED HERE");
        mainHotels.getChildren().addAll(dataHotels);

        /*


                         Tours


        */
        VBox menuTours = new VBox();
        Label label3 = new Label("TOURS");
        menuTours.getChildren().addAll(label3);

        HBox mainTours = new HBox();
        Label dataTours = new Label("TOUR DATA WILL BE DISPLAYED HERE");
        mainTours.getChildren().addAll(dataTours);

        /*


                         Cart


        */
        VBox menuCart = new VBox();
        Label label4 = new Label("CART");
        Slider slider = new Slider(0,1,0.5);
        menuCart.getChildren().addAll(label4);

        HBox mainCart = new HBox();
        Label dataCart = new Label("CART WILL BE DISPLAYED HERE");
        mainCart.getChildren().addAll(dataCart,slider);

        /*


                         Border pane


        */
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(menuFlights);
        borderPane.setCenter(mainFlights);


        // menu button actions
        buttonFlights.setOnAction(e -> {
            // borderPane.setLeft(menuFlights);
            borderPane.setCenter(mainFlights);
        });
        buttonHotels.setOnAction(e -> {
            // borderPane.setLeft(menuHotels);
            borderPane.setCenter(mainHotels);
        });
        buttonTours.setOnAction(e -> {
            // borderPane.setLeft(menuTours);
            borderPane.setCenter(mainTours);
        });
        buttonCart.setOnAction(e -> {
            // borderPane.setLeft(menuCart);
            borderPane.setCenter(mainCart);
        });

        //setup

        primaryStage.setTitle("Trip Planner 3000");
        primaryStage.setScene(new Scene(borderPane, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
