package com.example.tourmatenewproject.expandable_list_view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();

        List<String> expense = new ArrayList<>();
        expense.add("Add new expense");
        expense.add("View all expenses");
        expense.add("Add more budget");
        expense.add("View more budget list");

        List<String> moments = new ArrayList<>();
        moments.add("Take a photo");
        moments.add("View gallery");
        moments.add("View all moments");

        /*List<String> more_event = new ArrayList<>();
        more_event.add("Edit event");
        more_event.add("Delete event");*/

        expandableDetailList.put("Expense", expense);
        expandableDetailList.put("Moments", moments);
        //expandableDetailList.put("More on event", more_event);
        return expandableDetailList;
    }

}
