package com.marius.atestat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.marius.atestat.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME="MyTabel.db";
    private static  final int DATABESE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABESE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE="CREATE TABLE " +
                QuestionTable.TABLE_NAME+ " ( "+
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, "+
                QuestionTable.COLUMN_OPTION2 + " TEXT ,"+
                QuestionTable.COLUMN_OPTION3 + " TEXT ,"+
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, "+
                QuestionTable.COLUMN_DIFICULTATE_NR + " TEXT " + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    private  void fillQuestionTable() {

        Question q1=new Question("Modulul numărului complex z=(3-4i)(12+5i) este:","|z| = 45","|z| = 65","|z| = 50",2,Question.DIFICULTATE_USOR);
        addQuestion(q1);

        Question q2=new Question("Distanța de la dreapta d:3x-4y+15=0 la punctul A(1,2) este:","3/4","2/3","2",3,Question.DIFICULTATE_USOR);
        addQuestion(q2);

        Question q3=new Question("Suma primilor 3 termeni ai progresei geometrice cu b1 = 2 si b4 = 54","26","34","28",1,Question.DIFICULTATE_USOR);
        addQuestion(q3);

        Question q4=new Question("Fie f(x)=x^2 - 4x + 1. Atunci f(f(1)) este: ","13","10","15",1,Question.DIFICULTATE_USOR);
        addQuestion(q4);

        Question q5=new Question("Stiid ca radacinile complexe ale ecuatiei x^2 + 6x + m = 0 sunt egale atunci m este:","m = 10","m = 9","m = 7",2,Question.DIFICULTATE_USOR);
        addQuestion(q5);

        Question q6=new Question("Lungimea razei  cercului circumscris triunghiului ABC, in care A = 30° si BC = 6 este:"," R = 6 "," R = 4 ","R = sqrt(6)",1,Question.DIFICULTATE_MEDIU);
        addQuestion(q6);

        Question q7=new Question("Solutile ecuatie sqrt(x-2) = 2x - 5 este:","x = 9/4 ","x1 = 9/4, x2 = 3","x = 3",3,Question.DIFICULTATE_MEDIU);
        addQuestion(q7);

        Question q8=new Question("Numarul functilor f:{0,1,2}->{0,1,2} cu proprietatea f(0)*f(1)!=0 este:"," 27 "," 14 "," 12 ",3,Question.DIFICULTATE_MEDIU);
        addQuestion(q8);

        Question q9=new Question("Funcția f(x) = x^4 + x^3 + 2x^2 - x + 3 se divide cu: ","3x^2 - 2x + 1","x^2 - x + 1","x^2 + x - 3",2,Question.DIFICULTATE_MEDIU);
        addQuestion(q9);

        Question q10=new Question("Rădăcinile reale ale ecuației x^3 - 3x^2 + 6x - 8 = 0 sunt: ","x = 3","x = 2","x = 4",2,Question.DIFICULTATE_MEDIU);
        addQuestion(q10);

        Question q11=new Question("Fie z = (1-i)/(1+i). Atunci z^12 este: "," z^12 = 1"," z^12 = -1"," z^12 = i",1,Question.DIFICULTATE_MEDIU);
        addQuestion(q11);

        Question q12=new Question("sin(π/12)*sin(5π/12) este: "," 1/2 "," 1/3 "," 1/4 ",3,Question.DIFICULTATE_MEDIU);
        addQuestion(q12);

        Question q13=new Question("Solutile ecuatiei |2x - 1| = |x + 7| sunt: ","x1 = 2, x2 = -8","x1 = 2, x2 = 8","x1 = -2, x2 = 8",3,Question.DIFICULTATE_MEDIU);
        addQuestion(q13);

        Question q14=new Question("Funcția f:(-∞,-2] ∪ [0,∞)  f(x) = x + sqrt(x^2 + 2x) este: ","concavă","convexă","concavă și convexă",1,Question.DIFICULTATE_MEDIU);
        addQuestion(q14);

        Question q15=new Question("Să se calculeze P = cos1°∙cos2°∙…∙cos100°","2/3","0","1",2,Question.DIFICULTATE_MEDIU);
        addQuestion(q15);

        Question q16=new Question("Restul împărțirii polinomului X^10 la (X+1)^3 este:","45X^2 + 80X + 36","-9X^2 + 22","40X^2 +80X + 28",1,Question.DIFICULTATE_GREU);
        addQuestion(q16);

        Question q17=new Question("Coeficientul lui X^89 din dezvoltarea: (x-1)(x-2)...(x-89)(x-90) este: ","-4175","-4095","-3855",2,Question.DIFICULTATE_GREU);
        addQuestion(q17);

        Question q18=new Question("Daca ctg(x) = 2 si ctg(y) = 3 si x,y ∈(0,π/2), atunci "," x + y = π/2 "," x + y = π/6"," x + y = π/4",3,Question.DIFICULTATE_GREU);
        addQuestion(q18);

        Question q19=new Question("Triunghiul ABC, AB = AC = 10 si BC = 12. Atunci r, raza cercului inscris este: "," r = 3 "," r = 4"," r =  5/3 ",1,Question.DIFICULTATE_GREU);
        addQuestion(q19);

        Question q20=new Question("Fie z = 1 + i. Atunci 1/z este: ","(1 + i)/2","(1 - i)/2","1 - i",2,Question.DIFICULTATE_GREU);
        addQuestion(q20);

        Question q21=new Question("Mulțimea primitivelor funcției f:R → R, f(x) = 1 /[x (1 + x^3)] este: ","ln x  + arctg x + c","ln x - ln(x^3 + 1) + x","(1/3)*ln[x^3 /(1 + x^3)]",3,Question.DIFICULTATE_GREU);
        addQuestion(q21);

        Question q22=new Question("Valoarea lui arcsin(sin 4) este: ","π + 4","π - 4","-4",1,Question.DIFICULTATE_GREU);
        addQuestion(q22);

        Question q23=new Question("Mulțimea soluțiilor ecuației sin x + sin 2x + sin 3x + ... + sin 100x = 100  este: ","{kπ | k întreg} ","{kπ/2 + 2kπ | k întreg}","∅",2,Question.DIFICULTATE_GREU);
        addQuestion(q23);

        Question q24=new Question("Mulțimea soluțiilor sistemului x^2 + y^2 = 425 , lg x + lg y = 2 este: ","{(15,15)}","{(1,10);(10,1)}","{(20,5);(5,20)}",3,Question.DIFICULTATE_GREU);
        addQuestion(q24);

        Question q25=new Question("Partea intreaga a lui a = 1 + 1/2 + 1/2^2 + ... + 1/2^9 este: ","[a] = 1","[a] = 2","[a] = 3",1,Question.DIFICULTATE_GREU);
        addQuestion(q25);

        Question q26=new Question("Funcția f:R → R, f(x) = sin 4x + cos (x√2) are perioada: ","2π","nu este periodică","π√2",2,Question.DIFICULTATE_GREU);
        addQuestion(q26);

        Question q27=new Question("Numerele naturale x care verifica (2x - 1)/(8-x)>=1 sunt: ","{1,2,3,4}","{2,3,4,7,8}","{3,4,5,6,7}",3,Question.DIFICULTATE_GREU);
        addQuestion(q27);

        Question q28=new Question("Ecuația x^3 - 6x^2 + 11x + m = 0 are rădăcinile în progresie aritmetică, m aparține: ","[-7,-5]","[-4,-2]","[2,4]",1,Question.DIFICULTATE_GREU);
        addQuestion(q28);

        Question q29=new Question("Mulțimea valorilor lui m pentru care ecuația x(x - 1)(x - 2)(x - 3) = m are toate rădăcinile reale este: ","[-1,9/4]","[-1,9/16]","[-1,9/8]",2,Question.DIFICULTATE_GREU);
        addQuestion(q29);

        Question q30=new Question("Cate numere impare de doua cifre se pot forma cu 2,5,6,7,9"," 10 "," 17 "," 15 ",3,Question.DIFICULTATE_GREU);
        addQuestion(q30);
    }

    private  void addQuestion (Question question){
        ContentValues cv=new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_DIFICULTATE_NR,question.getDificultate());
        db.insert(QuestionTable.TABLE_NAME,null,cv);
    }


    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ QuestionTable.TABLE_NAME,null);

        if(c.moveToFirst()){
            do{
                Question question =new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDificultate(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFICULTATE_NR)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }


    public ArrayList<Question> getQuestions(String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME +
                " WHERE " + QuestionTable.COLUMN_DIFICULTATE_NR + " = ?", selectionArgs);

        if(c.moveToFirst()){
            do{
                Question question =new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setDificultate(c.getString(c.getColumnIndex(QuestionTable.COLUMN_DIFICULTATE_NR)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
