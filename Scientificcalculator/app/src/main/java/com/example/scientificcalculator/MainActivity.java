package com.example.scientificcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
private static  final char Addition ='+';
    private static  final char Substraction ='-';
    private static  final char Multiplication ='*';
    private static  final char Division ='/';
    private static  final char percent ='%';

private char cuurentsymbol;
private double Firstvalue=Double.NaN;
    private double Secondvalue;
    private TextView inputDisplay,outputDisplay;
    private DecimalFormat decimalformat;
    private MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,
    buttondot,buttonadd,buttonsub,buttondivision,buttonmultiply,buttonclear,buttonoff,buttonequal,buttonPercent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
decimalformat=new DecimalFormat("#.##########");
inputDisplay=findViewById(R.id.input);
        outputDisplay=findViewById(R.id.output);
        button0=findViewById(R.id.zero);
        button1=findViewById(R.id.btn1);
        button2=findViewById(R.id.btn2);
        button3=findViewById(R.id.btn3);
        button4=findViewById(R.id.btn4);
        button5=findViewById(R.id.btn5);
        button6=findViewById(R.id.btn6);
        button7=findViewById(R.id.btn7);
        button8=findViewById(R.id.btn8);
        button9=findViewById(R.id.btn9);
        buttonadd=findViewById(R.id.plus);
        buttonsub=findViewById(R.id.minus);
        buttondot=findViewById(R.id.point);
        buttondivision=findViewById(R.id.division);
        buttonmultiply=findViewById(R.id.multiply);
        buttonclear=findViewById(R.id.Clear);
        buttonoff=findViewById(R.id.off);
        buttonequal=findViewById(R.id.equal);
        buttonPercent=findViewById(R.id.percent);


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"0");
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"3");
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"4");
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"5");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"8");
            }
        });


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDisplay.setText(inputDisplay.getText()+"9");
            }
        });

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
allcalculateitem();
cuurentsymbol = Addition;
outputDisplay.setText(decimalformat.format(Firstvalue)+"+");
           inputDisplay.setText(null);
            }
        });


        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allcalculateitem();
                cuurentsymbol = Substraction;
                outputDisplay.setText(decimalformat.format(Firstvalue)+"-");
                inputDisplay.setText(null);
            }
        });

        buttondivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allcalculateitem();
                cuurentsymbol = Division;
                outputDisplay.setText(decimalformat.format(Firstvalue)+"/");
                inputDisplay.setText(null);
            }
        });

        buttonmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allcalculateitem();
                cuurentsymbol = Multiplication;
                outputDisplay.setText(decimalformat.format(Firstvalue)+"*");
                inputDisplay.setText(null);
            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allcalculateitem();
                cuurentsymbol = percent ;
                outputDisplay.setText(decimalformat.format(Firstvalue)+"%");
                inputDisplay.setText(null);
            }
        });


        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               inputDisplay.setText(inputDisplay.getText()+".");

            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputDisplay.getText().length() > 0) {
                   CharSequence currentText=inputDisplay.getText();
                inputDisplay.setText(currentText.subSequence(0,currentText.length()-1));
                }else{
                  Firstvalue=Double.NaN;
                  Secondvalue=Double.NaN;
                  inputDisplay.setText("");
                  outputDisplay.setText("");
                }
            }
        });
buttonoff.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       finish();
    }
});

        buttonequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              allcalculateitem();
              outputDisplay.setText(decimalformat.format(Firstvalue));
              Firstvalue=Double.NaN;
              cuurentsymbol=  '0';
            }
        });


    }
    private void allcalculateitem() {
        if (!Double.isNaN(Firstvalue)) {
            Secondvalue = Double.parseDouble(inputDisplay.getText().toString());
            inputDisplay.setText(null);

            if (cuurentsymbol == Addition)
                Firstvalue = this.Firstvalue + Secondvalue;
            else if (cuurentsymbol == Substraction)
                Firstvalue = this.Firstvalue - Secondvalue;
            else if (cuurentsymbol == Multiplication)
                Firstvalue = this.Firstvalue * Secondvalue;
            else if (cuurentsymbol == Division)
                Firstvalue = this.Firstvalue / Secondvalue;
            else if (cuurentsymbol == percent)
                Firstvalue = this.Firstvalue % Secondvalue;
        }else{
            try {
                Firstvalue =Double.parseDouble(inputDisplay.getText().toString());
            }catch (Exception e){

            }
        }
    }
}