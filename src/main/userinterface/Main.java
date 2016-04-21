package main.userinterface;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.model.Cart;
import main.model.HotelWrapper;
import main.model.TripCombo;
import main.controller.SearchController;
import main.flightsearch.models.Flight;
import main.toursearch.model.Tour;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class Main extends Application {

    SearchController searchController;

    // Table views
    TableView<TripCombo> tableViewCombo;
    TableView<Flight> tableViewFlights;
    TableView<HotelWrapper> tableViewHotels;
    TableView<Tour> tableViewTours;
    TableView<Cart> tableViewCart;

    ObservableList<Cart> cartList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        searchController = new SearchController(df.parse("2016-04-20"), df.parse("2016-04-22"));

        // Top menu
        HBox topMenu = new HBox();

        Image imageCombo = new Image(getClass().getResourceAsStream("img/icombo.png"));
        Image imageFlights = new Image(getClass().getResourceAsStream("img/iflights.png"));
        Image imageHotels = new Image(getClass().getResourceAsStream("img/ihotels.png"));
        Image imageTours = new Image(getClass().getResourceAsStream("img/itours.png"));
        Image imageCart = new Image(getClass().getResourceAsStream("img/icart.png"));
        Image imageSearch = new Image(getClass().getResourceAsStream("img/isearch.png"));

        Button buttonCombo = new Button("Combo", new ImageView(imageCombo));
        Button buttonFlights = new Button("Flight",new ImageView(imageFlights));
        Button buttonHotels = new Button("Hotel",new ImageView(imageHotels));
        Button buttonTours = new Button("Tour", new ImageView(imageTours));
        Button buttonCart = new Button("View cart", new ImageView(imageCart));
        topMenu.getChildren().addAll(buttonCombo, buttonFlights,buttonHotels,buttonTours,buttonCart);


        /*
                                                #
                                                #
                         Combos                 #
                                                #
                                                #
        */

        // Combo search menu
        VBox menuCombo = new VBox();
        Label comboLocationFromLabel = new Label("Depart from");
        Label comboLocationToLabel = new Label("Destination");
        Label comboDepartDateLabel = new Label("Depart date");
        Label comboReturnDateLabel = new Label("Return date");
        Label comboPassengerNumberLabel = new Label("Passengers");

        // Combo boxes
        ComboBox<String> comboDepartLocationBox = new ComboBox<>();
        comboDepartLocationBox.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur", "Iceland", "Casablanca");
        comboDepartLocationBox.setValue("Reykjavik");
        comboDepartLocationBox.setMaxWidth(Double.MAX_VALUE);
        comboDepartLocationBox.setOnAction(e -> searchController.setDepartLocation(comboDepartLocationBox.getValue()));
        searchController.setDepartLocation("Reykjavik");

        ComboBox<String> comboDestLocationBox = new ComboBox<>();
        comboDestLocationBox.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur", "Iceland", "Casablanca");
        comboDestLocationBox.setValue("Akureyri");
        comboDestLocationBox.setMaxWidth(Double.MAX_VALUE);
        comboDestLocationBox.setOnAction(e -> searchController.setDestLocation(comboDestLocationBox.getValue()));
        searchController.setDestLocation("Akureyri ");

        // # date pickers
        DatePicker comboDepartDatePicker = new DatePicker();
        comboDepartDatePicker.setOnAction(e -> {
            Date date = Date.from(comboDepartDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            searchController.setDepartureDate(date);
        });

        DatePicker comboReturnDatePicker = new DatePicker();
        comboReturnDatePicker.setOnAction(e -> {
            Date date = Date.from(comboReturnDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            searchController.setDepartureDate(date);
        });

        // # combo passengers
        ComboBox<String> comboPassengersNumber = new ComboBox<String>();
        comboPassengersNumber.getItems().addAll("1","2","3","4","5");
        comboPassengersNumber.setValue("1");
        comboPassengersNumber.setMaxWidth(Double.MAX_VALUE);
        comboPassengersNumber.setOnAction(event -> {
            String passengers = comboPassengersNumber.getValue();
            searchController.setNumPeople(Integer.parseInt(passengers));
        });
        searchController.setNumPeople(1);

        Label comboPriceLowerLabel = new Label("Price");

        TextField comboPriceLower = new TextField();
        comboPriceLower.setPromptText("Minimum price");

        TextField comboPriceHigher = new TextField();
        comboPriceHigher.setPromptText("Maximum price");

        Button comboSearchButton = new Button("Search", new ImageView(imageSearch));
        comboSearchButton.setMaxWidth(Double.MAX_VALUE);
        Button comboToCart = new Button("Add combo to cart", new ImageView(imageCart));
        comboToCart.setMaxWidth(Double.MAX_VALUE);

        menuCombo.getChildren().addAll(
                comboLocationFromLabel,
                comboDepartLocationBox,
                comboLocationToLabel,
                comboDestLocationBox,
                comboDepartDateLabel,
                comboDepartDatePicker,
                comboReturnDateLabel,
                comboReturnDatePicker,
                comboPassengerNumberLabel,
                comboPassengersNumber,
                comboPriceLowerLabel,
                comboPriceLower,
                comboPriceHigher,
                comboSearchButton,
                comboToCart
        );

        // COMBO COLUMN SETUP
        TableColumn<TripCombo, String> columnComboFlightDepartureTime = new TableColumn<>("Departure");
        columnComboFlightDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));

        TableColumn<TripCombo, String> columnComboFlightReturnTime = new TableColumn<>("Return");
        columnComboFlightReturnTime.setCellValueFactory(new PropertyValueFactory<>("returnTime"));

        TableColumn<TripCombo, String> columnComboHotelName = new TableColumn<>("Hotel name");
        columnComboHotelName.setCellValueFactory(new PropertyValueFactory<>("hotelName"));

        TableColumn<TripCombo, String> columnComboTourName = new TableColumn<>("Tourname");
        columnComboTourName.setCellValueFactory(new PropertyValueFactory<>("tourName"));

        TableColumn<TripCombo, String> columnComboTourType = new TableColumn<>("Tour type");
        columnComboTourType.setCellValueFactory(new PropertyValueFactory<>("tourType"));

        TableColumn<TripCombo, String> columnComboTotalPrice = new TableColumn<>("Total price");
        columnComboTotalPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewCombo = new TableView<>();
        tableViewCombo.getColumns().addAll(
                columnComboFlightDepartureTime,
                columnComboFlightReturnTime,
                columnComboHotelName,
                columnComboTourName,
                columnComboTourType,
                columnComboTotalPrice
        );

        HBox mainCombo = new HBox();
        mainCombo.getChildren().addAll(tableViewCombo);


        /*
                                                #
                                                #
                         Flights                #
                                                #
                                                #
        */

        //HBox hb = new HBox();
        //hb.getChildren().addAll(label1, textField);
        //hb.setSpacing(10);

        // flights left search menu
        VBox menuFlights = new VBox();

        Label flightsLocationFromLabel = new Label("Flying from:");
        Label flightsLocationToLabel = new Label("Flying to:");
        Label flightsDateFromLabel = new Label("From:");
        Label flightsDateToLabel = new Label("To:");
        Label flightsPassengersLabel = new Label("Passengers:");

        // # date pickers
        DatePicker flightsDateFrom = new DatePicker();
        flightsDateFrom.setOnAction(e -> {
            Date date = Date.from(flightsDateFrom.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            searchController.setDepartureDate(date);
        });
        DatePicker flightsDateTo = new DatePicker();
        flightsDateTo.setOnAction(e -> {
            Date date = Date.from(flightsDateTo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            searchController.setReturnDate(date);
        });

        // # combo boxes

        // # combo locations from
        ComboBox<String> flightsLocationFrom = new ComboBox<String>();
        flightsLocationFrom.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur", "Iceland", "Casablanca");
        flightsLocationFrom.setValue("Reykjavik");
        searchController.setDepartLocation("Reykjavik");
        flightsLocationFrom.setOnAction(e -> searchController.setDepartLocation(flightsLocationFrom.getValue()));
        // # combo locations to
        ComboBox<String> flightsLocationTo = new ComboBox<String>();
        flightsLocationTo.getItems().addAll("Reykjavik","Akureyri","Egilsstadir","Isafjordur", "Iceland", "Casablanca");
        flightsLocationTo.setValue("Akureyri");
        searchController.setDestLocation("Akureyri");
        flightsLocationTo.setOnAction(e -> searchController.setDestLocation(flightsLocationTo.getValue()));
        // # combo passengers
        ComboBox<String> flightsPassengersNumber = new ComboBox<String>();
        flightsPassengersNumber.getItems().addAll("1","2","3","4","5");
        flightsPassengersNumber.setValue("1");
        searchController.setNumPeople(1);
        flightsPassengersNumber.setOnAction(e -> searchController.setNumPeople(Integer.parseInt(flightsPassengersNumber.getValue())));

        // Flight luxuries
        CheckBox flightWifiCheck = new CheckBox("WiFi");
        flightWifiCheck.setOnAction(e -> searchController.setFlightWifi(flightWifiCheck.isSelected()));

        Button searchButtonFlight = new Button("Search", new ImageView(imageSearch));
        Button buttonFlightToCart = new Button("Add flight to cart", new ImageView(imageCart));
        buttonFlightToCart.setMaxWidth(Double.MAX_VALUE);

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
                flightsPassengersNumber,
                flightWifiCheck,
                searchButtonFlight,
                buttonFlightToCart);

        // FLIGHTS COLUMN SETUP

        // flights from column setup
        TableColumn<Flight, String> columnFlightDepartureTime = new TableColumn<>("Departure");
        columnFlightDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        // flights from column setup
        TableColumn<Flight, String> columnFlightArrivalTime = new TableColumn<>("Arrival");
        columnFlightArrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        // flights from column setup
        TableColumn<Flight, String> columnFlightAirportFrom = new TableColumn<>("Airport dep.");
        columnFlightAirportFrom.setCellValueFactory(new PropertyValueFactory<>("departureLoc"));
        // flights from column setup
        TableColumn<Flight, String> columnFlightAirportTo = new TableColumn<>("Airport arr.");
        columnFlightAirportTo.setCellValueFactory(new PropertyValueFactory<>("arrivalLoc"));
        // flights from column setup
        TableColumn<Flight, Integer> columnFlightPrice = new TableColumn<>("Price");
        columnFlightPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewFlights = new TableView<>();
        tableViewFlights.getColumns().addAll(
                columnFlightDepartureTime,
                columnFlightArrivalTime,
                columnFlightAirportFrom,
                columnFlightAirportTo,
                columnFlightPrice);


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

        ComboBox<String> hotelLocation = new ComboBox<>();
        hotelLocation.getItems().addAll("Reykjavík", "Egilsstaðir", "Akureyri", "Ísafjörður");
        hotelLocation.setValue("Reykjavík");
        hotelLocation.setOnAction(e -> searchController.setDestLocation(hotelLocation.getValue()));
        hotelLocation.setMaxWidth(Double.MAX_VALUE);
        searchController.setDestLocation("Akureyri");

        CheckBox cb1 = new CheckBox();
        CheckBox cb2 = new CheckBox();
        CheckBox cb3 = new CheckBox();
        CheckBox cb4 = new CheckBox();
        CheckBox cb5 = new CheckBox();
        CheckBox cb6 = new CheckBox();
        CheckBox cb7 = new CheckBox();
        CheckBox cb8 = new CheckBox();
        CheckBox cb9 = new CheckBox();
        CheckBox cb10 = new CheckBox();
        CheckBox cb11 = new CheckBox();
        CheckBox cb12 = new CheckBox();
        CheckBox cb13 = new CheckBox();

        cb1.setText("Room Service");
        cb1.setSelected(false);
        cb1.setOnAction(e -> searchController.chooseFacility(1));

        cb2.setText("Bar");
        cb2.setSelected(false);
        cb2.setOnAction(e -> searchController.chooseFacility(7));

        cb3.setText("Breakfast");
        cb3.setSelected(false);
        cb3.setOnAction(e -> searchController.chooseFacility(2));

        cb4.setText("Gym");
        cb4.setSelected(false);
        cb4.setOnAction(e -> searchController.chooseFacility(8));

        cb5.setText("Restaurant");
        cb5.setSelected(false);
        cb5.setOnAction(e -> searchController.chooseFacility(3));

        cb6.setText("Special events");
        cb6.setSelected(false);
        cb6.setOnAction(e -> searchController.chooseFacility(9));

        cb7.setText("Pool");
        cb7.setSelected(false);
        cb7.setOnAction(e -> searchController.chooseFacility(4));

        cb8.setText("Wi-Fi");
        cb8.setSelected(false);
        cb8.setOnAction(e -> searchController.chooseFacility(10));

        cb9.setText("Luggage Storage");
        cb9.setSelected(false);
        cb9.setOnAction(e -> searchController.chooseFacility(5));

        cb10.setText("Handicap access");
        cb10.setSelected(false);
        cb10.setOnAction(e -> searchController.chooseFacility(11));

        cb11.setText("Garage");
        cb11.setSelected(false);
        cb11.setOnAction(e -> searchController.chooseFacility(0));

        cb12.setText("Computers");
        cb12.setSelected(false);
        cb12.setOnAction(e -> searchController.chooseFacility(12));

        cb13.setText("Spa");
        cb13.setSelected(false);
        cb13.setOnAction(e -> searchController.chooseFacility(13));

        Button searchHButton = new Button("Search", new ImageView(imageSearch));
        searchHButton.setMaxWidth(Double.MAX_VALUE);
        Button bookHButton = new Button("Add room to cart", new ImageView(imageCart));
        bookHButton.setMaxWidth(Double.MAX_VALUE);

        final ToggleGroup groupH = new ToggleGroup();

        RadioButton rb1 = new RadioButton("All hotels");
        rb1.setToggleGroup(groupH);
        rb1.setSelected(true);
        rb1.setUserData("0");

        RadioButton rb2 = new RadioButton("Name");
        rb2.setToggleGroup(groupH);
        rb2.setUserData("1");

        RadioButton rb3 = new RadioButton("Location");
        rb3.setToggleGroup(groupH);
        rb3.setUserData("2");

        RadioButton rb4 = new RadioButton("Hotel chain");
        rb4.setToggleGroup(groupH);
        rb4.setUserData("3");

        RadioButton rb5 = new RadioButton("Substring");
        rb5.setToggleGroup(groupH);
        rb5.setUserData("4");

        groupH.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (groupH.getSelectedToggle() != null) {
                    String act = (String) groupH.getSelectedToggle().getUserData();
                    searchController.setSelectedHotelSearchMethod(Integer.parseInt(act));
                }
            }
        });

        VBox menuHotels = new VBox();

        DatePicker checkInDate = new DatePicker();
        DatePicker checkOutDate = new DatePicker();

        Label labelSH = new Label("Search string:");
        TextField tFSH = new TextField ();

        menuHotels.getChildren().addAll(
                checkInDate,
                checkOutDate,
                labelSH,
                tFSH,
                rb1,
                rb2,
                rb3,
                rb4,
                rb5,
                cb1,
                cb2,
                cb3,
                cb4,
                cb5,
                cb6,
                cb7,
                cb8,
                cb9,
                cb10,
                cb11,
                cb12,
                cb13,
                searchHButton,
                bookHButton
        );

        // hotels from column setup
        TableColumn<HotelWrapper, String> columnHotelName = new TableColumn<>("Hotel Name");
        columnHotelName.setCellValueFactory(new PropertyValueFactory<>("name"));
        // hotels from column setup
        TableColumn<HotelWrapper, String> columnHotelChain = new TableColumn<>("Hotel Chain");
        columnHotelChain.setCellValueFactory(new PropertyValueFactory<>("chain"));
        // hotels from column setup
        TableColumn<HotelWrapper, String> columnHotelLocation = new TableColumn<>("Hotel Location");
        columnHotelLocation.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<HotelWrapper, String> columnHotelCapacity = new TableColumn<>("Capacity");
        columnHotelCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        // flights from column setup
        TableColumn<HotelWrapper, Integer> columnHotelPrice = new TableColumn<>("Price");
        columnHotelPrice.setCellValueFactory(new PropertyValueFactory<>("rate"));


        tableViewHotels = new TableView<>();
        tableViewHotels.getColumns().addAll(
                columnHotelName,
                columnHotelChain,
                columnHotelLocation,
                columnHotelCapacity,
                columnHotelPrice
        );

        HBox mainHotels = new HBox();
        mainHotels.getChildren().addAll(tableViewHotels);

        /*


                         Tours


        */
        VBox menuTours = new VBox();
        Label label3 = new Label("TOURS");

        DatePicker tourDepartDate = new DatePicker();

        Label tourDepartureLabel = new Label("Departure");
        ComboBox<String> tourDeparture = new ComboBox<String>();
        tourDeparture.getItems().addAll("Reykjavik","Husavik","Vik","Downtown Reykjavik");
        tourDeparture.setValue("Reykjavik");
        tourDeparture.setMaxWidth(Double.MAX_VALUE);
        tourDeparture.setOnAction(e -> searchController.setDepartLocation(tourDeparture.getValue()));

        Label tourDestinationLabel = new Label("Destination");
        ComboBox<String> tourDestination = new ComboBox<String>();
        tourDestination.getItems().addAll("The Ocean","Thingvellir","Vatnajokull","Depends on weather","Silfra", "Reykjavik sights", "Geysir", "Blue lagoon");
        tourDestination.setValue("Reykjavik");
        tourDestination.setMaxWidth(Double.MAX_VALUE);
        tourDestination.setOnAction(e -> searchController.setDestLocation(tourDestination.getValue()));

        Label tourDurationLabel = new Label("Duration");
        TextField tourDurationLower = new TextField();
        tourDurationLower.setPromptText("Minimum duration");
        TextField tourDurationHigher = new TextField();
        tourDurationHigher.setPromptText("Maximum duration");

        Label tourTypeLabel = new Label("Type");
        ComboBox<String> tourType = new ComboBox<>();
        tourType.getItems().addAll("Adventure", "Northern Lights", "Reykjavik", "Golden Circle", "Nature");
        tourType.setValue("Adventure");
        tourType.setMaxWidth(Double.MAX_VALUE);
        tourType.setOnAction(e -> searchController.setTourType(tourType.getValue()));

        Label tourRatingLabel = new Label("Rating");

        ComboBox<Integer> tourRating = new ComboBox<>();
        tourRating.getItems().addAll(0, 1, 2, 3, 4, 5);
        tourRating.setMaxWidth(Double.MAX_VALUE);
        tourRating.setValue(0);
        tourRating.setOnAction(e -> searchController.setTourRating(tourRating.getValue()));

        Label tourPriceLabel = new Label("Price");
        TextField tourPriceLower = new TextField();
        tourPriceLower.setPromptText("Minimum price");

        TextField tourPriceHigher = new TextField();
        tourPriceHigher.setPromptText("Maximum price");

        CheckBox tourHotelPickup = new CheckBox("Hotel pickup");
        tourHotelPickup.setOnAction(e -> searchController.setTourHotelPickup(tourHotelPickup.isSelected()));

        TextField tourName = new TextField();
        tourName.setPromptText("Tour name");
        tourName.setMaxWidth(Double.MAX_VALUE);

        Button tourSearchButton = new Button("Search", new ImageView(imageSearch));
        tourSearchButton.setMaxWidth(Double.MAX_VALUE);
        Button tourToCart = new Button("Add tour to cart", new ImageView(imageCart));
        tourToCart.setMaxWidth(Double.MAX_VALUE);

        menuTours.getChildren().addAll(
                label3,
                tourDepartDate,
                tourDepartureLabel,
                tourDeparture,
                tourDestinationLabel,
                tourDestination,
                tourPriceLabel,
                tourPriceLower,
                tourPriceHigher,
                tourDurationLabel,
                tourDurationLower,
                tourDurationHigher,
                tourTypeLabel,
                tourType,
                tourRatingLabel,
                tourRating,
                tourHotelPickup,
                tourName,
                tourSearchButton,
                tourToCart
        );

        // TOURS TABLES

        // Tours from column setup
        TableColumn<Tour, String> columnTourName = new TableColumn<>("Tour Name");
        columnTourName.setCellValueFactory(new PropertyValueFactory<>("name"));
        // Tours from column setup
        TableColumn<Tour, String> columnTourType = new TableColumn<>("Tour Type");
        columnTourType.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Tours from column setup
        TableColumn<Tour, String> columnTourDeparture = new TableColumn<>("Tour Departure");
        columnTourDeparture.setCellValueFactory(new PropertyValueFactory<>("departureLocation"));
        // Tours from column setup
        TableColumn<Tour, Integer> columnTourDuration = new TableColumn<>("Tour Duration");
        columnTourDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        // Tours from column setup
        TableColumn<Tour, String> columnTourPrice = new TableColumn<>("Tour Price");
        columnTourPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewTours = new TableView<>();
        tableViewTours.getColumns().addAll(
                columnTourName,
                columnTourType,
                columnTourDeparture,
                columnTourDuration,
                columnTourPrice);

        HBox mainTours = new HBox();
        mainTours.getChildren().addAll(tableViewTours);

        /*


                         Cart


        */
        VBox menuCart = new VBox();
        Label label4 = new Label("CART");
        Slider slider = new Slider(0,1,0.5);
        menuCart.getChildren().addAll(new ImageView(imageCart));



        // CART TABLES

        // cart from column setup
        TableColumn<Cart, String> columnCartType = new TableColumn<>("Type");
        columnCartType.setCellValueFactory(new PropertyValueFactory<>("cartType"));
        // cart from column setup
        TableColumn<Cart, String> columnCartFrom = new TableColumn<>("Date from");
        columnCartFrom.setCellValueFactory(new PropertyValueFactory<>("cartFrom"));
        // cart from column setup
        TableColumn<Cart, String> columnCartTo = new TableColumn<>("Date to");
        columnCartTo.setCellValueFactory(new PropertyValueFactory<>("cartTo"));
        // cart from column setup
        TableColumn<Cart, Integer> columnCartPrice = new TableColumn<>("Price");
        columnCartPrice.setCellValueFactory(new PropertyValueFactory<>("cartPrice"));

        tableViewCart = new TableView<>();
        tableViewCart.getColumns().addAll(
                columnCartType,
                columnCartFrom,
                columnCartTo,
                columnCartPrice);





        HBox mainCart = new HBox();
        mainCart.getChildren().addAll(tableViewCart);

        /*


                         Border pane


        */

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(menuCombo);
        borderPane.setCenter(mainCombo);


        // menu button actions
        buttonCombo.setOnAction(e -> {
            borderPane.setCenter(mainCombo);
            borderPane.setLeft(menuCombo);
        });

        buttonFlights.setOnAction(e -> {
            // borderPane.setLeft(menuFlights);
            borderPane.setCenter(mainFlights);
            borderPane.setLeft(menuFlights);
        });
        buttonHotels.setOnAction(e -> {
            borderPane.setLeft(menuHotels);
            borderPane.setCenter(mainHotels);
        });
        buttonTours.setOnAction(e -> {
            borderPane.setLeft(menuTours);
            borderPane.setCenter(mainTours);
        });
        buttonCart.setOnAction(e -> {
            System.out.println(tableViewCart.getItems().size());
            tableViewCart.setItems(cartList);
            borderPane.setLeft(menuCart);
            borderPane.setCenter(mainCart);
        });


        // Search buttons

        comboSearchButton.setOnAction(e -> {
            int priceLower = 0;
            int priceHigher = Integer.MAX_VALUE;

            try { priceLower = Integer.parseInt(comboPriceLower.getText()); }
            catch (NumberFormatException err) { System.out.println("Error: " + err.getMessage()); }

            try { priceHigher = Integer.parseInt(comboPriceHigher.getText()); }
            catch (NumberFormatException err) { System.out.println("Error: " + err.getMessage()); }

            searchController.setMinPrice(priceLower);
            searchController.setMaxPrice(priceHigher);

            searchAll();
        });

        searchButtonFlight.setOnAction(e -> tableViewFlights.setItems(getFlights()));

        searchHButton.setOnAction(e -> {
            searchController.setHotelSearchString(tFSH.getText());
            tableViewHotels.setItems(getHotels());
        });

        comboToCart.setOnAction(e -> {
            System.out.println("Selected combo: " + tableViewCombo.getSelectionModel().getSelectedItem());
        });

        buttonFlightToCart.setOnAction(e -> {
            System.out.println("Selected flight: " + tableViewFlights.getSelectionModel().getSelectedItem().getFlightNumber());
            Flight selectedFlight = tableViewFlights.getSelectionModel().getSelectedItem();
            Cart cart = new Cart("Flight", selectedFlight.getDepartureDate(), selectedFlight.getArrivalDate(), (int) selectedFlight.getPrice());

            cartList.add(cart);
            tableViewCart.setItems(cartList);
        });

        bookHButton.setOnAction(e -> {
            System.out.println("Selected hotel room id: " + tableViewHotels.getSelectionModel().getSelectedItem().getHotelRoom().getId());
        });

        tourToCart.setOnAction(e -> {
            System.out.println("Selected tour: " + tableViewTours.getSelectionModel().getSelectedItem().getName());
        });

        tourSearchButton.setOnAction(e -> {
            int priceLower = 0;
            int priceHigher = Integer.MAX_VALUE;
            int durationLower = 0;
            int durationHigher = Integer.MAX_VALUE;

            try { priceLower = Integer.parseInt(tourPriceLower.getText()); }
            catch (NumberFormatException err) { System.out.println("Error: " + err.getMessage()); }

            try { priceHigher = Integer.parseInt(tourPriceHigher.getText()); }
            catch (NumberFormatException err) { System.out.println("Error: " + err.getMessage()); }

            try { durationLower = Integer.parseInt(tourDurationLower.getText()); }
            catch (NumberFormatException err) { System.out.println("Error: " + err.getMessage()); }

            try { durationHigher = Integer.parseInt(tourDurationHigher.getText()); }
            catch (NumberFormatException err) { System.out.println("Error: " + err.getMessage()); }

            searchController.setTourPriceLower(priceLower);
            searchController.setTourPriceHigher(priceHigher);
            searchController.setTourDurationLower(durationLower);
            searchController.setTourDurationHigher(durationHigher);
            searchController.setTourName(tourName.getText());

            tableViewTours.setItems(getTours());
        });

        /*


                         Tooltips


        */

        Tooltip.install(buttonCart, new Tooltip("View a list of items you have added to the cart"));
        Tooltip.install(buttonFlights, new Tooltip("Search for flights matching your criteria"));
        Tooltip.install(buttonHotels, new Tooltip("Search for hotels matching your criteria"));
        Tooltip.install(buttonTours, new Tooltip("Search for tours matching your criteria"));

        /*


                         Style


        */

        mainCombo.setId("main");
        mainFlights.setId("main");
        mainHotels.setId("main");
        mainTours.setId("main");
        mainCart.setId("main");

        topMenu.setId("topMenu");

        buttonCombo.setId("menuButton");
        buttonCart.setId("menuButton");
        buttonFlights.setId("menuButton");
        buttonHotels.setId("menuButton");
        buttonTours.setId("menuButton");

        menuCombo.setId("leftMenu");
        menuFlights.setId("leftMenu");
        menuHotels.setId("leftMenu");
        menuTours.setId("leftMenu");
        menuCart.setId("leftMenu");

        //setup

        flightsLocationFrom.setMaxWidth(Double.MAX_VALUE);
        flightsLocationTo.setMaxWidth(Double.MAX_VALUE);
        flightsPassengersNumber.setMaxWidth(Double.MAX_VALUE);
        searchButtonFlight.setMaxWidth(Double.MAX_VALUE);

        primaryStage.setTitle("Trip Planner 3000");

        Image icon = new Image(getClass().getResourceAsStream("img/icon.png"));
        primaryStage.getIcons().add(icon);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("main/userinterface/style.css");
        primaryStage.show();
    }

    public void searchAll() {
        tableViewCombo.setItems(getCombos());
        tableViewFlights.setItems(getFlights());
        tableViewHotels.setItems(getHotels());
        tableViewTours.setItems(getTours());
    }

    /// Table data getters

    public ObservableList<Flight> getFlights(){
        return FXCollections.observableArrayList(searchController.searchFlights());
    }

    public ObservableList<HotelWrapper> getHotels(){
        return FXCollections.observableArrayList(searchController.searchHotels());
    }

    public ObservableList<Tour> getTours(){
        return FXCollections.observableArrayList(searchController.searchTours());
    }

    public ObservableList<TripCombo> getCombos() {
        return FXCollections.observableArrayList(searchController.searchCombos());
    }
}
