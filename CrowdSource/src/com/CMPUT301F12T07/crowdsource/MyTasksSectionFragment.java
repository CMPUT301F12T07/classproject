package com.CMPUT301F12T07.crowdsource;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MyTasksSectionFragment extends Fragment {
	
	private ListView myList;
	private List<Task> tasks;
	private LocalDB db;
	
    public MyTasksSectionFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View myFeed = inflater.inflate(R.layout.activity_feed, container, false);
    	
    	db = new LocalDB(inflater.getContext());
        
        this.tasks = db.getAllTasksByUid(Secure.getString(getActivity().getContentResolver(), Secure.ANDROID_ID));
        
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
        
        return myFeed;
    }
}