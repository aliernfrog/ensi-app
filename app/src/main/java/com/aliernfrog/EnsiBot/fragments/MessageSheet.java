package com.aliernfrog.EnsiBot.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aliernfrog.EnsiBot.ChatActivity;
import com.aliernfrog.EnsiBot.R;
import com.aliernfrog.EnsiBot.utils.AppUtil;
import com.aliernfrog.EnsiBot.utils.FileUtil;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;

import java.io.File;

public class MessageSheet extends BottomSheetDialogFragment {
    TextView message_author;
    TextView message_content;
    Button addToStarboard;
    Button deleteMessage;

    ChatActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_message, container, false);

        message_author = view.findViewById(R.id.messageSheet_message_author);
        message_content = view.findViewById(R.id.messageSheet_message_content);
        addToStarboard = view.findViewById(R.id.messageSheet_starboard);
        deleteMessage = view.findViewById(R.id.messageSheet_delete);

        context = (ChatActivity) getActivity();

        loadMessage();
        setListeners();

        return view;
    }

    void addToStarboard() {
        try {
            String starboardPath = context.getExternalFilesDir(".saved").getPath()+"/starboard.json";
            File starboardFile = new File(starboardPath);
            String content = FileUtil.readFile(starboardFile.getPath());
            JSONArray starboardArray = new JSONArray(content);
            starboardArray.put(context.getChosenMessageData());
            String parentPath = starboardFile.getParent();
            String fileName = starboardFile.getName();
            FileUtil.saveFile(parentPath, fileName, starboardArray.toString());
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleteMessage() {
        context.deleteChosenMessage();
        dismiss();
    }

    void loadMessage() {
        View view = context.getChosenMessage();
        TextView authorView = view.findViewById(R.id.message_author_username);
        TextView contentView = view.findViewById(R.id.message_content);
        String author = authorView.getText().toString();
        String content = contentView.getText().toString();
        message_author.setText(author);
        message_content.setText(content);
        if (context.getIsStarboard()) addToStarboard.setVisibility(View.GONE);
    }

    void setListeners() {
        AppUtil.handleOnPressEvent(addToStarboard, this::addToStarboard);
        AppUtil.handleOnPressEvent(deleteMessage, this::deleteMessage);
    }
}
