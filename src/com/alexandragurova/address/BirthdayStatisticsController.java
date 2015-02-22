package com.alexandragurova.address;

import com.alexandragurova.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Gurova on 18.02.2015.
 */
public class BirthdayStatisticsController {
    @FXML
    private BarChart barChart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * This method automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize(){
        //Get an array with the English month names.
        String[] months =
                DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        //Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));

        //Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    public void setPersonData(ObservableList<Person> persons){
        int[] monthCounter = new int[12];

        for(Person p : persons){
            int month =  p.getBirthday().get(Calendar.MONTH);
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series =
                new XYChart.Series<String, Integer>();

        for(int i = 0; i < monthCounter.length; i++){
            series.getData()
                    .add(new XYChart.Data<String, Integer>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }
}
