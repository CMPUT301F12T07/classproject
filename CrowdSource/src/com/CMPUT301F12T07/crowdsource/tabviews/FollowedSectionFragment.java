package com.CMPUT301F12T07.crowdsource.tabviews;

import java.util.List;

import com.CMPUT301F12T07.crowdsource.R;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.DBHandler;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.TaskLoadHandler;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FollowedSectionFragment extends Fragment {
	
	private ListView myList;
	private List<Task> tasks;
	private DBHandler db;
	
    public FollowedSectionFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View myFeed = inflater.inflate(R.layout.activity_followed_tasks, container, false);
    	
    	db = new DBHandler(inflater.getContext());
        
        this.tasks = db.getFollowedTasks(Secure.getString(getActivity().getContentResolver(), Secure.ANDROID_ID));
        
        myList = (ListView) myFeed.findViewById(R.id.tasklist);
        myList.setAdapter(new TaskListAdapter(inflater.getContext(), tasks));
        
        // Adds listener for when a Task is clicked in the ListView
        myList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		Intent intent = new Intent(view.getContext(), TaskLoadHandler.class);
        		intent.putExtra("taskWebObject", tasks.get(position).get_wid());
        		startActivity(intent);
        	}
        });
        
        return myFeed;
    }
    
    @Override
	public void onResume() {
		super.onResume();
		this.tasks = db.getFollowedTasks(Secure.getString(getActivity().getContentResolver(), Secure.ANDROID_ID));
		myList.setAdapter(new TaskListAdapter(getActivity(), tasks));
	}
    
}