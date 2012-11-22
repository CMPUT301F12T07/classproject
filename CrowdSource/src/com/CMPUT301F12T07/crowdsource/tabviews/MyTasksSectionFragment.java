package com.CMPUT301F12T07.crowdsource.tabviews;

import java.util.List;

import com.CMPUT301F12T07.crowdsource.R;
import com.CMPUT301F12T07.crowdsource.ViewTaskActivity;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.LocalDB;
import com.CMPUT301F12T07.crowdsource.taskmodeldb.Task;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MyTasksSectionFragment extends Fragment {
	
	private ListView myPrivateList;
	private ListView myPublicList;
	private List<Task> tasks;
	private LocalDB db;
	
    public MyTasksSectionFragment() {
    }

    public static final String ARG_SECTION_NUMBER = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View myFeed = inflater.inflate(R.layout.activity_my_tasks, container, false);
    	
    	db = new LocalDB(inflater.getContext());
        
        this.tasks = db.getAllTasksByUid(Secure.getString(getActivity().getContentResolver(), Secure.ANDROID_ID));
        
        myPrivateList = (ListView) myFeed.findViewById(R.id.privatetasklist);
        myPrivateList.setAdapter(new TaskListAdapter(inflater.getContext(), tasks));
        
        // Max Height implementation so that Private List and Public List can Coincide
        if (myPrivateList.getAdapter().getCount() >= 3){
        	View taskItem = myPrivateList.getAdapter().getView(0, null, myPrivateList);
        	taskItem.measure(0, 0);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) (3.9 * taskItem.getMeasuredHeight()));
        	myPrivateList.setLayoutParams(params);
        }
        
        // TODO: Add Text if you have no Tasks Specified (ie. No Public Tasks, No Private Tasks)
        
        // Adds listener for when a Task is clicked in the ListView
        myPrivateList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		Intent intent = new Intent(view.getContext(), ViewTaskActivity.class);
        		intent.putExtra("taskObject", tasks.get(position).get_tid());
        		startActivity(intent);
        	}
        });
        
        
        
        myPublicList = (ListView) myFeed.findViewById(R.id.publictasklist);
        myPublicList.setAdapter(new TaskListAdapter(inflater.getContext(), tasks));
        
        // Adds listener for when a Task is clicked in the ListView
        myPublicList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        		Intent intent = new Intent(view.getContext(), ViewTaskActivity.class);
        		intent.putExtra("taskObject", tasks.get(position).get_tid());
        		startActivity(intent);
        	}
        });
        
        // TODO: Add Text if you have no Tasks Specified (ie. No Public Tasks, No Private Tasks)
        
        return myFeed;
    }
    
    @Override
	public void onResume() {
		super.onResume();
		this.tasks = db.getAllTasksByUid(Secure.getString(getActivity().getContentResolver(), Secure.ANDROID_ID));
		myPrivateList.setAdapter(new TaskListAdapter(getActivity(), tasks));
		
		// Max Height implementation so that Private List and Public List can Coincide
		if (myPrivateList.getAdapter().getCount() >= 3){
        	View taskItem = myPrivateList.getAdapter().getView(0, null, myPrivateList);
        	taskItem.measure(0, 0);
        	myPrivateList.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) (3.9 * taskItem.getMeasuredHeight())));
        }
		myPublicList.setAdapter(new TaskListAdapter(getActivity(), tasks));
		// TODO: Add Text if you have no Tasks Specified (ie. No Public Tasks, No Private Tasks)
	}
    
}