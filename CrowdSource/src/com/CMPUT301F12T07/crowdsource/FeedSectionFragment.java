package com.CMPUT301F12T07.crowdsource;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class FeedSectionFragment extends Fragment {
	
	private ListView myList;
	private List<Task> tasks;
	private LocalDB db;
	
    public FeedSectionFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View myFeed = inflater.inflate(R.layout.activity_feed, container, false);
    	
    	db = new LocalDB(inflater.getContext());
        
        this.tasks = db.getAllTasks();
        
        myList = (ListView) myFeed.findViewById(R.id.tasklist);
        myList.setAdapter(new TaskListAdapter(inflater.getContext(), tasks));
        
        // Adds listener for when a Task is clicked in the ListView
        myList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		Intent intent = new Intent(view.getContext(), ViewTaskActivity.class);
        		intent.putExtra("taskObject", tasks.get(position).get_tid());
        		startActivity(intent);
        	}
        });
        
        final Button EmptyDB = (Button) myFeed.findViewById(R.id.DBDEBUG);
        EmptyDB.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		db.emptyDatabase();
        		getActivity().finish();
        		getActivity().startActivity(getActivity().getIntent());
        	}
        });
        
        final Button RandomTaskGen = (Button) myFeed.findViewById(R.id.RandTask);
        RandomTaskGen.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		db.createRandomTask();
        		getActivity().finish();
        		getActivity().startActivity(getActivity().getIntent());
        	}
        });
    	
    	
    	
        
        return myFeed;
    }
    
    @Override
	public void onResume() {
		super.onResume();
		this.tasks = db.getAllTasks();
		myList.setAdapter(new TaskListAdapter(getActivity(), tasks));
	}
}