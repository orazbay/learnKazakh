package kz.learn.orazbay.main.settings;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kz.learn.orazbay.MyAbstractFragment;
import kz.learn.orazbay.R;
import kz.learn.orazbay.main.AnimationInitter;
import kz.learn.orazbay.utils.Functions;

/**
 * Created by orazbay on 11/12/17.
 */

public class SettingsFragment extends MyAbstractFragment implements AnimationInitter{
    private TextView nameTv;
    private RelativeLayout nameEditCont;

    public SettingsFragment(){
        super(SettingsFragment.class.getName(),R.layout.fragment_settings);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        findViews();
        nameTv.setText(Functions.getStringFromSP(context,"name"));
        nameEditCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogName(context, true, new NameDilaogInterface() {
                    @Override
                    public void onNameEntered(String name) {
                        Functions.saveStringToSP(context,"name",name);
                        nameTv.setText(name);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void show() {
    }

    @Override
    public void findViews() {
        nameTv=view.findViewById(R.id.nameTV);
        nameEditCont=view.findViewById(R.id.nameEditCont);
    }
    public static void showDialogName(Context context, boolean cancelable, final NameDilaogInterface nameDilaogInterface){
        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view=((Activity)context).getLayoutInflater().inflate(R.layout.dialog_name_content_view,null);

        final TextInputLayout textInputLayout=view.findViewById(R.id.textInputLayout);
        final EditText editText=view.findViewById(R.id.editText);
        builder.setView(view)
                .setTitle(R.string.enter_your_name)
                .setCancelable(cancelable)
                .setPositiveButton(android.R.string.ok,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nameDilaogInterface.onNameEntered(editText.getText().toString());
                    }
                });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name=String.valueOf(charSequence).trim();
                if (name.length()>0){
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
                    textInputLayout.setError("");
                }else {
                    textInputLayout.setError("Имя не может быть пустым");
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
