package ness.edu.sqlite.dialogs;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import ness.edu.sqlite.R;
import ness.edu.sqlite.models.Todo;
import ness.edu.sqlite.sqlite.DAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTodoFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final int ACTION_UPDATE = 0;
    public static final int ACTION_INSERT = 1;
    public static final String ARG_TODO = "todo";
    public static final String ARG_ACTION = "action";
    private int action;
    private Todo model;

    EditText etMission;
    Spinner spImportance; //entries = @array / importance
    Button btnDone;

    public static AddTodoFragment newInstance(int action, Todo model) {
        Bundle args = new Bundle();
        args.putInt(ARG_ACTION, action);
        args.putParcelable(ARG_TODO, model);

        AddTodoFragment fragment = new AddTodoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_todo, container, false);

        etMission = v.findViewById(R.id.etMission);
        spImportance = v.findViewById(R.id.spImportance);
        btnDone = v.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);

        action = getArguments().getInt(ARG_ACTION);
        model = getArguments().getParcelable(ARG_TODO);
        if (action == ACTION_UPDATE)
            updateUI();
        return v;
    }

    private void updateUI() {
        etMission.setText(model.getMission());
        String[] importance = getResources().getStringArray(R.array.importance);
        //Arrays.binarySearch(importance, model.getImportance());

        //spImportance.setSelection(model.getImportance());
    }

    @Override
    public void onClick(View view) {
        String mission = etMission.getText().toString();
        String importance = spImportance.getSelectedItem().toString();

        // A IS B
        // LSP

        //spinner -> selected
        DAO dao = DAO.getInstance(getContext());

        long lastInsertedid = dao.addTodo(mission, importance);
        long lastID = dao.getLastInsertedID();

        dismiss();

        //prepared statements

        //Notify the listeners:
        Todo todo = new Todo((int) lastID, mission, importance);
        Intent intent = new Intent("addedTodo");
        intent.putExtra("todo", todo);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }
}
