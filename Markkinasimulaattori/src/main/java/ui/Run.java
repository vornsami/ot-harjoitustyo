/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import actors.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import objects.Item;
import objects.ItemType;
import systems.Trader;
import static objects.ItemType.*;
import systems.Simulation;

/**
 *
 * @author Sami
 */
public class Run extends Application {

    public static void main(String[] args) {
        
        Application.launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        AnchorPane pane = new AnchorPane();
        this.createStart(pane, stage);
        
        Scene scene = new Scene(pane);
        
        stage.setTitle("Markkinasimulaattori");
        stage.setScene(scene);
        stage.show();
    }
    
    // Before simulation starts
    
    private void createStart(AnchorPane pane, Stage stage) {
        VBox optionArea = new VBox();
        optionArea.setSpacing(15);
        optionArea.setMaxWidth(170);
        
        AnchorPane.setTopAnchor(optionArea, 50.0);
        AnchorPane.setBottomAnchor(optionArea, 10.0);
        AnchorPane.setLeftAnchor(optionArea, 15.0);
        
        VBox marketActors = new VBox();
        marketActors.setSpacing(10);
        
        AnchorPane.setTopAnchor(marketActors, 50.0);
        AnchorPane.setBottomAnchor(marketActors, 10.0);
        AnchorPane.setRightAnchor(marketActors, 90.0);
        AnchorPane.setLeftAnchor(marketActors, 200.0);
        
        VBox startBox = new VBox();
        
        Button startButton = new Button("START");
        
        Label roundLabel = new Label("Days:");
        
        TextField rounds = new TextField("20");
        rounds.setTextFormatter(new TextFormatter<String>(t -> {
            if (t.getText().isEmpty() || !t.getText().matches("\\d+")) {
                t.setText("");
            }
            return t;
        }));
        rounds.setMaxWidth(80);
        startBox.getChildren().addAll(roundLabel, rounds, startButton);
        
        AnchorPane.setRightAnchor(startBox, 10.0);
        AnchorPane.setBottomAnchor(startBox, 10.0);
        
        pane.getChildren().addAll(optionArea, marketActors, startBox);
        
        VBox productBox = new VBox();
        
        Button addCompany = new Button("Add Company");
        addCompany.setOnAction(e -> {
            this.createMarketElement(marketActors, productBox);
        });
        
        Button addProduct = new Button("Add Product");
        productBox.setSpacing(5);
        addProduct.setOnAction(e -> {
            this.addProduct(productBox, marketActors);
        });
        
        this.createMarketElement(marketActors, productBox);
        this.addProduct(productBox, marketActors);
        
        optionArea.getChildren().addAll(addCompany, addProduct, productBox);
        
        startButton.setOnAction(e -> this.startSimulation(marketActors, productBox, startBox, stage));
        
        stage.setHeight(500);
        stage.setWidth(1200);
        
    }
    
    private void createMarketElement(VBox marketActors, VBox productBox) {
        
        HBox actorBox = new HBox();
        actorBox.setSpacing(5);
        actorBox.setStyle(" -fx-background-color: white;");
        
        // Company name
        
        TextField nameField = new TextField("Company " + (marketActors.getChildren().size() + 1));
        
        // Employee button
        
        Button addEmployee = new Button("Add Employee");
        
        // Number of employees
        Text employeeCount = new Text("1");
        employeeCount.setFont(new Font(22));
        
        addEmployee.setOnAction(e -> {
            this.addEmployeeCount(employeeCount);
        });
        
        // Wage slider
        VBox sliderHolder = new VBox();
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(1000);
        slider.setValue(100);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMinorTickCount(1);
        slider.setMajorTickUnit(100);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(200);
        
        Label wageTitle = new Label("Employee wages: ");
        Label wage = new Label();
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(wageTitle, wage);
        
        wage.textProperty().bind((ObservableValue) slider.valueProperty().asString());
        slider.setOnMousePressed(e -> wage.textProperty().unbind());
        slider.setOnMouseReleased(e -> wage.textProperty().bind((ObservableValue) slider.valueProperty().asString()));
        
        sliderHolder.getChildren().addAll(titleBox, slider);
        
        // Product selection
        ChoiceBox<Integer> productSelect = new ChoiceBox<>();
        for (int i = 1; i <= productBox.getChildren().size(); i++) {
            productSelect.getItems().add(i);
        }
        productSelect.setValue(1);
        
        // Efficiency slider
        
        VBox effSliderHolder = new VBox();
        Slider effSlider = new Slider();
        effSlider.setMin(0);
        effSlider.setMax(100);
        effSlider.setValue(10);
        effSlider.setShowTickMarks(true);
        effSlider.setShowTickLabels(true);
        effSlider.setMinorTickCount(9);
        effSlider.setMajorTickUnit(10);
        effSlider.setSnapToTicks(true);
        
        Label effTitle = new Label("Efficiency: ");
        Label effLabel = new Label();
        HBox effLabelBox = new HBox();
        effLabelBox.getChildren().addAll(effTitle, effLabel);
        
        effLabel.textProperty().bind((ObservableValue) effSlider.valueProperty().asString());
        effSlider.setOnMousePressed(e -> effLabel.textProperty().unbind());
        effSlider.setOnMouseReleased(e -> effLabel.textProperty().bind((ObservableValue) effSlider.valueProperty().asString()));
        
        effSliderHolder.getChildren().addAll(effLabelBox, effSlider);
        
        // Starting Price
        VBox priceBox = new VBox();
        
        Label priceLabel = new Label("Starting price:");
        
        TextField price = new TextField("20");
        price.setTextFormatter(new TextFormatter<String>(t -> {
            if (t.getText().isEmpty() || !t.getText().matches("\\d+")) {
                t.setText("");
            }
            return t;
        }));
        price.setMaxWidth(100);
        priceBox.getChildren().addAll(priceLabel, price);
        
        // Add all to base
        actorBox.getChildren().addAll(nameField, addEmployee, employeeCount, sliderHolder, productSelect, effSliderHolder, priceBox);
        marketActors.getChildren().add(actorBox);
    }
    
    private void addEmployeeCount(Text employeeCount) {
        int num = Integer.parseInt(employeeCount.getText());
        num++;
        employeeCount.setText(num + "");
    }
    
    private void addProduct(VBox productBox, VBox marketActors) {
        
        VBox base = new VBox();
        
        TextField nameField = new TextField("Product " + (productBox.getChildren().size() + 1));
        ChoiceBox typeSelect = new ChoiceBox();
        
        typeSelect.getItems().addAll(NECESSITY, GENERAL, LUXURY);
        typeSelect.valueProperty().set(GENERAL);
        
        base.getChildren().addAll(typeSelect, nameField);
        
        productBox.getChildren().add(base);
        
        marketActors.getChildren().forEach(a -> 
                ((ChoiceBox) ((HBox) a).getChildren().get(4))
                        .getItems().add(productBox.getChildren().size())
        );
    }
    
    // Starting the simulation
    
    private void startSimulation(VBox marketActors, VBox productBox, VBox startBox, Stage stage) {
        
        List<Person> people = new ArrayList<>();
        List<Company> companies = this.simulationConverter(marketActors, productBox, stage);
        companies.forEach(a -> {
            people.addAll(a.getEmployees());
        });
        Item[] itemList = this.itemListCreator(productBox);
        
        int rounds = this.getRounds(startBox);
        
        people.forEach(a -> a.setAllBuyLimits(new double[itemList.length]));
        
        for (int i = 0; i < itemList.length; i++) {
            int p = i;
            double average = companies.stream()
                    .filter(a -> a.getProd() == p)
                    .mapToDouble(a -> a.getSellLimit(p))
                    .average()
                    .getAsDouble();
            
            people.forEach(a -> a.setBuyLimit(p, average));
            
        }
        stage.hide();
        Simulation simulation = new Simulation(people, companies, itemList);
        simulation.run(rounds);
        
    }
    
    private List<Company> simulationConverter(VBox marketActors, VBox productBox, Stage stage) {
        
        try {
            List<Company> companies = new ArrayList<>();

            int compCount = marketActors.getChildren().size();

            for (int i = 0; i < compCount; i++) {
                
                HBox marketElement = (HBox) marketActors.getChildren().get(i);
                String name = ((TextField) marketElement.getChildren().get(0)).getText();
                int employeeCount = Integer.parseInt(((Text) marketElement.getChildren().get(2)).getText());
                double wages = ((Slider) ((VBox) marketElement.getChildren().get(3)).getChildren().get(1)).getValue();
                int prod = (int) ((ChoiceBox) marketElement.getChildren().get(4)).getValue();
                double eff  = ((Slider) ((VBox) marketElement.getChildren().get(5)).getChildren().get(1)).getValue();
                double price = Double.parseDouble(((TextField) ((VBox) marketElement.getChildren().get(6)).getChildren().get(1)).getText());
                
                List<Person> employees = new ArrayList<>();
                for (int e = 0; e < employeeCount; e++) {
                    employees.add(new Person(wages));
                }
                
                double[] sellLimits = new double[productBox.getChildren().size()];
                for (int p = 0; p < sellLimits.length; p++) {
                    sellLimits[p] = -1;
                }
                sellLimits[prod - 1] = price;
                
                int[] prodCounts = new int[productBox.getChildren().size()];
                prodCounts[prod - 1] = 2 * employeeCount;
                
                companies.add(new Company(name, prod - 1, eff, employeeCount * wages * 2, null, sellLimits, prodCounts, employees, wages));
            }
            
            return companies;
            
        } catch (Exception e) {
            System.out.println(e);
            
            Popup popup = this.createError();
            popup.show(stage);
        }
        return null;
    }
    
    private Item[] itemListCreator(VBox productBox) {
        int prodCount = productBox.getChildren().size();
        Item[] itemList = new Item[prodCount];
        
        for (int i = 0; i < prodCount; i++) {
            VBox productElement = (VBox) productBox.getChildren().get(i);
            
            itemList[i] = new Item(
                    ((TextField) productElement.getChildren().get(1)).getText(), 
                    (ItemType) ((ChoiceBox) productElement.getChildren().get(0)).getValue()
            );
        }
        return itemList;
    }
    
    private int getRounds(VBox startBox) {
        return Integer.parseInt(((TextField) startBox.getChildren().get(1)).getText());
    }
    
    private Popup createError() {
        Popup popup = new Popup();
        
        VBox content = new VBox();
        content.setStyle(" -fx-background-color: white;");
        
        Button button = new Button("Close");
        button.setOnAction(e -> popup.hide());
        
        content.getChildren().addAll(new Text("There was an error"), button);
        
        popup.getContent().add(content);
        
        return popup;
    }
}
