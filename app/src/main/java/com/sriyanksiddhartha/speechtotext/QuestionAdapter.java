package com.sriyanksiddhartha.speechtotext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by onlyo on 3/20/2018.
 */

public class QuestionAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<Question> arrayQuestion;

    public QuestionAdapter(Context content, int layout, List<Question> arrayquestion ){
        myContext=content;
        myLayout=layout;
        arrayQuestion=arrayquestion;
    }

    @Override
    public int getCount() {
        return arrayQuestion.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //LayoutInflater dung no de hien thi tren tung item, thay vi listview co 1 dong thi khi 2 dong
        //No se dua giao dien khac day vao
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Co giao dien r, ta lay view con (dong fullName la 1 view,....) in  myLayout (row_student.xml)
        view = inflater.inflate(myLayout,null);

        //Anh xa va gan gia tri vao (de lat sau cho giao dien cua tung dong vao activity_main.xml)
        ImageView img_foodImage =(ImageView) view.findViewById(R.id.img_who);
        img_foodImage.setImageResource(arrayQuestion.get(i).quesImage);



        TextView txt_foodPrice =(TextView) view.findViewById(R.id.txt_question);
        //Int + String = String, day la cach lam ngan hon String.valueOf()
        txt_foodPrice.setText(arrayQuestion.get(i).quesContent);

        return view;
    }
}
