package txiner.top.dailytask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import txiner.top.dailytask.util.Task;
import txiner.top.dailytask.util.TaskAdapter;

public class MainActivity extends AppCompatActivity {


    private ListView listView = null;
    ArrayList<Map<String,Object>> tasks=null;
    TaskAdapter taskAdapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialog();
                }
        });

        tasks=getTasks();


        listView = (ListView) findViewById(R.id.task_list);
        taskAdapter=new TaskAdapter(this,tasks);
        listView.setAdapter(taskAdapter);


    }

    private ArrayList<Map<String, Object>> getTasks() {
        ArrayList<Map<String, Object>> tasks=new ArrayList<>();
        for (int i=0;i<3;i++){
            Map<String, Object> task=new HashMap<>();
            task.put("name","name "+i);
            task.put("content","content "+i);
            task.put("over",false);
            tasks.add(task);
        }
        return tasks;
    }






    public void setDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("添加计划");
        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.task_composent,null);
        builder.setView(view);
        final EditText addTaskName= (EditText) view.findViewById(R.id.add_task_name);
        final EditText addTaskContent = (EditText) view.findViewById(R.id.add_task_content);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map<String, Object> task=new HashMap<>();
                String taskName=addTaskName.getText().toString();
                String taskContent=addTaskContent.getText().toString();
                if (taskName!=null&&taskName.trim()!=""){
                    task.put("name",taskName);
                    task.put("content",taskContent);
                    task.put("over",false);
                    taskAdapter.add(task);
                    taskAdapter.notifyDataSetChanged();
                }

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }













}
