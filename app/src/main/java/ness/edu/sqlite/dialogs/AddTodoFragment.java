package ness.edu.sqlite.dialogs;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import ness.edu.sqlite.R;
import ness.edu.sqlite.TodosDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTodoFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText etMission;
    Spinner spImportance; //entries = @array / importance
    Button btnDone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_todo, container, false);

        etMission = v.findViewById(R.id.etMission);
        spImportance = v.findViewById(R.id.spImportance);
        btnDone = v.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        String mission = etMission.getText().toString();
        String importance = spImportance.getSelectedItem().toString();

        //spinner -> selected

        TodosDBHelper helper = new TodosDBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        //maps key value pairs.
        ContentValues values = new ContentValues();
        values.put(TodosDBHelper.TodosContract.TBL_TODOS_COL_MISSION, mission);
        values.put(TodosDBHelper.TodosContract.TBL_TODOS_COL_IMPORTANCE, importance);

        db.insert(TodosDBHelper.TodosContract.TBL_TODOS, null, values);
        dismiss();

        //prepared statements
    }
}
